package principal;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

import clases.Accion;
import clases.CartaTerreno;
import excepciones.CharacterDoesNotExistException;
import pantallas.PantallaDeCarga;
import pantallas.Ventana;
/**
 * Clase Principal donde se encuentra el Main del programa the7thContinent.
 * @author Leticia
 *
 */
public class Main {
	/**
	 * Main del programa donde se le introduce como argumentos de programa al String[] args, el nombre del personaje.
	 * El nombre se comprobará si está en BBDD, y si no lo está (o no se le dan argumentos de programa) cogerá por defecto
	 * a uno de los personajes para que el programa se pueda seguir ejecutando.
	 * 
	 * En el Main también se crea el objeto Ventana, pasándole por parámetros el nombre del personaje, y este objeto es el que
	 * hará que nuestro programa se vea visualmente.
	 * @param args Array de String donde se le introduce como argumentos de programa el nombre del personaje del juego.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String nombrePersonaje = "cancamuso";
		
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
				
		try {
			Ventana ventana = new Ventana(nombrePersonaje);
		} catch (CharacterDoesNotExistException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Character doesn't exist", JOptionPane.ERROR_MESSAGE);
			try {
				Ventana ventana=new Ventana("Ferdinand");
			} catch (CharacterDoesNotExistException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		

	}

}
