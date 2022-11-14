package es.florida.AE02;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Vista extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JLabel lblAccsUsuaris;
	private JLabel lblLibreria;
	private JTextField txtConsulta;
	private JButton btnAcces;
	private JButton btnTancarConexio;
	private JButton btnConsulta;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTextArea txtAContingutTaula;
	private JLabel lblContingut;
	private JLabel lblContra;
	private JLabel lblLogin;
	private JLabel lblNewLabel;
	private JLabel lblEscriuLaConsulta;
	private JButton btnMuestraBD;
	private JButton btnEstructuraTabla;
	private JTextArea textArea;
	private JLabel lblNom;
	private JButton btnContenidoTabla;
	private JPanel panel_funciones;
	private JPanel panel_login;
	private JPasswordField passwordFieldContra;
	private JTextArea textAreaContingut;
	

	public Vista() {
		setTitle("Acess a dades AE02");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 696);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLibreria = new JLabel("LLIBRERIA");
		lblLibreria.setFont(new Font("Cascadia Code", Font.PLAIN, 45));
		lblLibreria.setBounds(24, 24, 239, 59);
		contentPane.add(lblLibreria);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\Escritorio\\2DAM\\ACCESO A DATOS\\eclipse-workspace_ACCESDADES\\AE02\\icono_libro.png"));
		lblNewLabel.setBounds(724, 69, 130, 122);
		contentPane.add(lblNewLabel);
		
		panel_funciones = new JPanel();
		panel_funciones.setBackground(new Color(0, 91, 183));
		panel_funciones.setBounds(20, 202, 836, 444);
		contentPane.add(panel_funciones);
		panel_funciones.setLayout(null);
		
		lblEscriuLaConsulta = new JLabel("Escriu la consulta que vuigues fer: ");
		lblEscriuLaConsulta.setBounds(30, 11, 229, 19);
		panel_funciones.add(lblEscriuLaConsulta);
		lblEscriuLaConsulta.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 41, 679, 38);
		panel_funciones.add(scrollPane);
		
		txtConsulta = new JTextField();
		scrollPane.setViewportView(txtConsulta);
		txtConsulta.setColumns(10);
		
		btnConsulta = new JButton("Executar");
		btnConsulta.setBounds(723, 41, 94, 38);
		panel_funciones.add(btnConsulta);
		btnConsulta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblContingut = new JLabel("CONTINGUT:");
		lblContingut.setBounds(30, 82, 146, 26);
		panel_funciones.add(lblContingut);
		lblContingut.setForeground(new Color(255, 255, 255));
		lblContingut.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(30, 106, 787, 204);
		panel_funciones.add(scrollPane_1);
		
		txtAContingutTaula = new JTextArea();
		scrollPane_1.setRowHeaderView(txtAContingutTaula);
		
		textAreaContingut = new JTextArea();
		textAreaContingut.setEditable(false);
		scrollPane_1.setViewportView(textAreaContingut);
		
		btnMuestraBD = new JButton("Mostrar Base de dades");
		btnMuestraBD.setBounds(615, 321, 202, 23);
		panel_funciones.add(btnMuestraBD);
		btnMuestraBD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnEstructuraTabla = new JButton("Veure estructura taula");
		btnEstructuraTabla.setBounds(30, 379, 174, 23);
		panel_funciones.add(btnEstructuraTabla);
		btnEstructuraTabla.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnContenidoTabla = new JButton("Veure contingut taula");
		btnContenidoTabla.setBounds(30, 408, 174, 23);
		panel_funciones.add(btnContenidoTabla);
		btnContenidoTabla.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textArea = new JTextArea();
		textArea.setBounds(30, 346, 174, 26);
		panel_funciones.add(textArea);
		
		lblNom = new JLabel("Escriu el nom de la taula: ");
		lblNom.setBounds(30, 321, 174, 14);
		panel_funciones.add(lblNom);
		lblNom.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		
		btnTancarConexio = new JButton("Tancar connexio");
		btnTancarConexio.setBounds(652, 406, 165, 26);
		panel_funciones.add(btnTancarConexio);
		btnTancarConexio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		panel_login = new JPanel();
		panel_login.setBackground(new Color(113, 184, 255));
		panel_login.setBounds(298, 11, 260, 186);
		contentPane.add(panel_login);
		panel_login.setLayout(null);
		
		lblAccsUsuaris = new JLabel("ACCÉS USUARIS");
		lblAccsUsuaris.setBounds(19, 11, 231, 60);
		panel_login.add(lblAccsUsuaris);
		lblAccsUsuaris.setIcon(new ImageIcon("D:\\Escritorio\\2DAM\\ACCESO A DATOS\\eclipse-workspace_ACCESDADES\\AE02\\user.png"));
		lblAccsUsuaris.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		
		lblLogin = new JLabel("LOGIN:");
		lblLogin.setBounds(56, 76, 49, 18);
		panel_login.add(lblLogin);
		lblLogin.setFont(new Font("Tw Cen MT", Font.PLAIN, 16));
		
		txtLogin = new JTextField();
		txtLogin.setBounds(130, 76, 86, 20);
		panel_login.add(txtLogin);
		txtLogin.setColumns(10);
		
		lblContra = new JLabel("CONTRASENYA:");
		lblContra.setBounds(14, 100, 107, 18);
		panel_login.add(lblContra);
		lblContra.setFont(new Font("Tw Cen MT", Font.PLAIN, 16));
		
		btnAcces = new JButton("Accedir");
		btnAcces.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAcces.setBounds(79, 137, 107, 25);
		panel_login.add(btnAcces);
		btnAcces.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		passwordFieldContra = new JPasswordField();
		passwordFieldContra.setBounds(130, 107, 86, 20);
		panel_login.add(passwordFieldContra);
		
		this.setVisible(true);
	}


	public JPanel getContentPane() {
		return contentPane;
	}


	public JTextField getTxtLogin() {
		return txtLogin;
	}




	public JLabel getLblAccsUsuaris() {
		return lblAccsUsuaris;
	}


	public JLabel getLblLibreria() {
		return lblLibreria;
	}


	public JTextField getTxtConsulta() {
		return txtConsulta;
	}


	public JButton getBtnAcces() {
		return btnAcces;
	}


	public JButton getBtnTancarConexio() {
		return btnTancarConexio;
	}


	public JButton getBtnConsulta() {
		return btnConsulta;
	}


	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public JScrollPane getScrollPane_1() {
		return scrollPane_1;
	}


	public JTextArea getTxtAContingutTaula() {
		return txtAContingutTaula;
	}


	public JLabel getLblContingut() {
		return lblContingut;
	}


	public JTextArea getTextAreaContingut() {
		return textAreaContingut;
	}


	public void setTextAreaContingut(JTextArea textAreaContingut) {
		this.textAreaContingut = textAreaContingut;
	}


	public JLabel getLblContra() {
		return lblContra;
	}


	public JLabel getLblLogin() {
		return lblLogin;
	}


	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}


	public JLabel getLblEscriuLaConsulta() {
		return lblEscriuLaConsulta;
	}


	public JButton getBtnMuestraBD() {
		return btnMuestraBD;
	}


	public JButton getBtnEstructuraTabla() {
		return btnEstructuraTabla;
	}


	public JTextArea getTextArea() {
		return textArea;
	}


	public JLabel getLblNom() {
		return lblNom;
	}


	public JButton getBtnContenidoTabla() {
		return btnContenidoTabla;
	}


	public JPanel getPanel_funciones() {
		return panel_funciones;
	}


	public JPanel getPanel_login() {
		return panel_login;
	}


	public JPasswordField getPasswordFieldContra() {
		return passwordFieldContra;
	}


	
}
