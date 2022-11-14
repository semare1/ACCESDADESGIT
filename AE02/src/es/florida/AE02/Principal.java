package es.florida.AE02;

public class Principal {
	
	public static void main(String[] args) {
		
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(vista,modelo);
	}

}
