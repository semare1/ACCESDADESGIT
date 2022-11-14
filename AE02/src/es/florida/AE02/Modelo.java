package es.florida.AE02;

import java.io.File;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

/**
 * Classe destinada a contindre els mètodes de l'aplicació.
 *
 */
public class Modelo {

	private static String ficheroXML = "conexio.xml";
	static Connection con;

	/**
	 * Mètode encarregat d'executar un buider per a llegir el document ficheroXML.
	 * Torna un array amb la lectura del XML on està la url, l'usuari i la
	 * contrasenya per a poder accedir a la base de dades.
	 * 
	 * @return Array amb els nodes del ficheroXML.
	 */
	public String[] leeXML() {
		String[] conexion = new String[3];
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = (Document) dBuilder.parse(new File(ficheroXML));
			Element raiz = document.getDocumentElement();
			NodeList nodeList = document.getElementsByTagName("conexion");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					String url = eElement.getElementsByTagName("url").item(0).getTextContent();
					String usuario = eElement.getElementsByTagName("usuario").item(0).getTextContent();
					String contrasenya = eElement.getElementsByTagName("contrasenya").item(0).getTextContent();
					conexion[0] = url;
					conexion[1] = usuario;
					conexion[2] = contrasenya;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conexion;
	}

	/**
	 * Mètode encarregat d'obrir la connexió de la base de dades cridant al mètode
	 * leeXML.
	 * 
	 */
	public void abreConexionBD() {
		String[] conexion = leeXML();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(conexion[0], conexion[1], conexion[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mètode que tanca la connexió amb la base de dades.
	 * 
	 * @throws SQLException
	 */
	public void cierraConexionBD() throws SQLException {
		if (!con.isClosed()) {
			con.close();
		}
	}

	/**
	 * Mètode encarregat d'encriptar la contrasenya proporcionada per l'usuari.
	 * 
	 * @param contra String amb la contrasenya de l'usuari.
	 * @return Torna un String amb la contrasenya convertida en format MD5.
	 * @throws Exception tira una excepció en lloc del try/catch.
	 */
	private String convertirContrasenya(String contra) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(contra.getBytes());
		byte[] digest = md.digest();
		StringBuilder sb = new StringBuilder(32);
		for (byte b : digest) {
			sb.append(String.format("%2x", b & 0xff));
		}
		return sb.toString();
	}

	/**
	 * Mètode booleà que valida si l'usuari està registrat en la base de dades.
	 * Cridarà al mètode convertirContrassenya per a convertir la contrasenya
	 * proporcionada per paràmetre.
	 * 
	 * @param user   String proporcionat en interfície.
	 * @param contra String proporcionat en interfície.
	 * @return booleano en funció de si l'usuari està avalidado o no.
	 */
	public boolean usuarioAutorizado(String user, String contra) {
		boolean usuarioValidado = false;
		abreConexionBD();
		String password = "", login = "";
		try {
			login = user;
			password = convertirContrasenya(contra);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM users WHERE USER = '" + login + "' AND pass = '" + password + "'");
			if (rs.next()) {
				usuarioValidado = true;
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioValidado;
	}

	/**
	 * Mètode que mostra l'estructura de la BD. Torna un String amb el resultat de
	 * la consulta.
	 * 
	 * @return String amb l'estructura de la BD.
	 */
	public static String estructuraBD() {
		String resultado = "";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SHOW TABLES FROM books");
			int numCampos = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= numCampos; i++) {
					resultado += rs.getString(i) + " - ";
				}
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	/**
	 * Mètode que mostra l'estructura d'una taula seleccionada per l'usuari.
	 * 
	 * @param tablaElegida String amb la taula triada per l'usuari.
	 * @return String amb el resultat de la consulta.
	 */
	public static String estructuraTablaBD(String tablaElegida) {
		String resultado = "";
		String seleccion = tablaElegida;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("DESCRIBE " + seleccion);
			int numCampos = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= numCampos; i++) {
					resultado += rs.getString(i) + " - ";
				}
				resultado += "\n";
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	/**
	 * Mètode que mostra tot el contingut de la taula seleccionada i parametritzada.
	 * 
	 * @param tablaElegida String amb la selecció de l'usuari.
	 * @return String amb el resultat de la consulta mostrant el contingut de la
	 *         taula.
	 */
	public static String muestraContenidoTabla(String tablaElegida) {
		String resultado = "";
		String seleccion = tablaElegida;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + seleccion);
			int numCampos = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= numCampos; i++) {
					resultado += rs.getString(i) + " - ";
				}
				resultado += "\n";
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	/**
	 * Mètode que proporciona a l'usuari la possibilitat d'introduir una sentència
	 * SQL de diferents tipus.
	 * 
	 * @param sentence String proporcionada per l'usuari.
	 * @return String amb el resutlado de la sentència.
	 */
	public static String sentenciaSQL(String sentence) {
		String resultado = "";
		String[] sentencia = sentence.split(" ");
		try {
			Statement stmt = con.createStatement();
			if (sentencia[0].toUpperCase().equals("SELECT")) {
				ResultSet rs = stmt.executeQuery(sentence);
				ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
				int numCampos = rsmd.getColumnCount();
				while (rs.next()) {
					for (int i = 1; i <= numCampos; i++) {
						resultado += rs.getString(i) + " - ";
					}
					resultado += "\n";
				}
				rs.close();
			} else if (sentencia[0].toUpperCase().equals("INSERT") || sentencia[0].toUpperCase().equals("UPDATE")
					|| sentencia[0].toUpperCase().equals("DELETE")) {

				int respuesta = JOptionPane.showConfirmDialog(null, "¿Desitja realitzar esta consulta?", "Alerta!",
						JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
					PreparedStatement preparedStmt = con.prepareStatement(sentence.toUpperCase());
					int resultadoConsulta = preparedStmt.executeUpdate();
					if (resultadoConsulta == 1) {
						JOptionPane.showMessageDialog(null, "Consulta realitzada correctament");
					} else {
						JOptionPane.showMessageDialog(null, "No s'ha pogut realitzar la consulta");
					}
					preparedStmt.close();
				} else {
					JOptionPane.showMessageDialog(null, "Consulta cancel.lada");
				}
				stmt.close();
			} else {
				JOptionPane.showMessageDialog(null, "No es possible realitzar este tipus de consulta");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}
}
