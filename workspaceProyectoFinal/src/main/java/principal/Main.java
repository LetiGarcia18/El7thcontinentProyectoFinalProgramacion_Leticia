package principal;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import clases.Accion;
import clases.CartaTerreno;
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
		
		if(nombrePersonaje!= null) {
			System.out.println("El nombre del personaje recibido por argumentos es: " + nombrePersonaje);				
		}else {
			System.out.println("Dime un nombre para el personaje: ");
			nombrePersonaje = sc.nextLine();
		}
		
		
		
		/*Splash miSplash = new Splash();
		miSplash.setVisible(true);*/
	
		
		Ventana ventana = new Ventana(nombrePersonaje);
		

	}

}
