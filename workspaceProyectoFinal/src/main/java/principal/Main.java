package principal;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

import clases.Accion;
import clases.CartaTerreno;
import excepciones.CharacterDoesNotExistException;
import pantallas.Splash;
import pantallas.Ventana;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String nombrePersonaje = "";
		
		for (byte i = 0; i < args.length; i++) {
			if (args[i].equals("-nombre")) {
				nombrePersonaje = args[i+1];
			}
			
			if(args[i].equals("-h") || args[i].equals("-help")) {
				System.out.println("Argumentos disponibles:\n"
    					+ "\t-nombre NOMBRE DEL PERSONAJE : Indica el nombre del personaje del juego");
    			System.exit(0);
			}
		}
				
		
		
		/*Splash miSplash = new Splash();
		miSplash.setVisible(true);*/
		
		
		//No me funciona si no le meto argumentos de programa. Me debería coger un personaje por defecto y ejecutarse el programa
		if(nombrePersonaje == null && nombrePersonaje != "Ferdinand" && nombrePersonaje != "duck") {
			nombrePersonaje = "Ferdinand";	
		}
		
		try {
			Ventana ventana = new Ventana(nombrePersonaje);
		} catch (CharacterDoesNotExistException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Character doesn't exist", JOptionPane.ERROR_MESSAGE);
		}
		
		
		

	}

}
