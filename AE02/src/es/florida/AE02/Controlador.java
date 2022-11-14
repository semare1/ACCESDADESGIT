package es.florida.AE02;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 *Esta classe, principalment es va a encarregar de generar els actionListener necessaris a casa botó ,
 *i en ells cridarà a cada mètode consecutiu de la classe model
 */
public class Controlador {

	protected static final Component JFrame = null;
	
	private Vista vista;
	private Modelo modelo;
	private ActionListener actionListener_acces, actionListener_consulta, actionListener_tacarConexio, actionListener_btnEstructuraTabla, 
							actionListener_MuestraEstructuraBD, actionListener_MuestraContenidoTabla;

	Controlador(Vista vista, Modelo modelo) {
		this.vista = vista;
		this.modelo = modelo;
		control();
	}

	/**
	 * En este mètode control cridarem a tots
	 * els actionListener convenients 
	 */
	
	public void control() {
		vista.getPanel_funciones().setVisible(false);

		actionListener_acces = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String usr = vista.getTxtLogin().getText();
				String passwd = vista.getPasswordFieldContra().getText();
				if (modelo.usuarioAutorizado(usr, passwd)) {
					JOptionPane.showMessageDialog(JFrame, "L'usuari " + usr + " a iniciat sessió");
					vista.getPanel_funciones().setVisible(true);
					vista.getPanel_login().setVisible(false);
					vista.getTxtLogin().setText("");
					vista.getPasswordFieldContra().setText("");
					modelo.abreConexionBD();
				} else  {
					JOptionPane.showMessageDialog(JFrame, "Usuari no autoritzat");
					vista.getTxtLogin().setText("");
					vista.getPasswordFieldContra().setText("");
				}

			}

		};
		vista.getBtnAcces().addActionListener(actionListener_acces);

		actionListener_consulta = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String resultadoSentencia = Modelo.sentenciaSQL(vista.getTxtConsulta().getText());
				vista.getTextAreaContingut().setText(resultadoSentencia);
				vista.getTxtConsulta().setText("");
				
			}
			
		};
		vista.getBtnConsulta().addActionListener(actionListener_consulta);

		actionListener_btnEstructuraTabla =  new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String resultadoSentencia = Modelo.estructuraTablaBD(vista.getTextArea().getText());
				vista.getTextAreaContingut().setText(resultadoSentencia);
				vista.getTextArea().setText("");
				vista.getTxtConsulta().setText("");
				
			}
			
		};
		vista.getBtnEstructuraTabla().addActionListener(actionListener_btnEstructuraTabla);
		
	
	
		actionListener_MuestraEstructuraBD =new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String resultadoSentencia = Modelo.estructuraBD();
				vista.getTextAreaContingut().setText(resultadoSentencia);
				vista.getTextArea().setText("");
				vista.getTxtConsulta().setText("");
				
			}
			
		};
		
		vista.getBtnMuestraBD().addActionListener(actionListener_MuestraEstructuraBD);

		actionListener_MuestraContenidoTabla = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String resultadoSentencia = Modelo.muestraContenidoTabla(vista.getTextArea().getText());
				vista.getTextAreaContingut().setText(resultadoSentencia);
				vista.getTxtConsulta().setText("");
				
			}
			
		};
		vista.getBtnContenidoTabla().addActionListener(actionListener_MuestraContenidoTabla);

		actionListener_tacarConexio =new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(JFrame, "¿Estes segur de que vols tancar sessió?");
				if (respuesta == JOptionPane.YES_OPTION) {
					try {
						modelo.cierraConexionBD();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					vista.getPanel_funciones().setVisible(false);
					vista.getPanel_login().setVisible(true);
					
				}
			}
			
		};
		
		vista.getBtnTancarConexio().addActionListener(actionListener_tacarConexio);
		
	}
}
