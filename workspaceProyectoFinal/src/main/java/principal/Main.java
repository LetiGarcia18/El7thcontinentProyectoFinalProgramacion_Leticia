package principal;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;


import excepciones.CharacterDoesNotExistException;
import pantallas.Ventana;

/**
 * Clase Principal donde se encuentra el Main del programa the7thContinent.
 * 
 * @author Leticia
 *
 */
public class Main {
	/**
	 * Main del programa donde se le introduce como argumentos de programa al
	 * String[] args, el nombre del personaje. El nombre se comprobará si está en
	 * BBDD, y si no lo está (o no se le dan argumentos de programa) cogerá por
	 * defecto a uno de los personajes para que el programa se pueda seguir
	 * ejecutando. Los nombres de los personaje son Ferdinand y Mary Kingsley
	 * 
	 * En el Main también se crea el objeto Ventana (este objeto es el que hará 
	 * que nuestro programa se vea visualmente), pasándole por parámetros el
	 * nombre del personaje y un archivo de texto (File).
	 * 
	 * @param args Array de String donde se le introduce como argumentos de programa
	 *             el nombre del personaje del juego. Ej: -nombre Ferdinand
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String nombrePersonaje = "cancamuso";

		for (byte i = 0; i < args.length; i++) {
			if (args[i].equals("-nombre")) {
				nombrePersonaje = args[i + 1];
			}

			if (args[i].equals("-h") || args[i].equals("-help")) {
				System.out.println("Argumentos disponibles:\n"
						+ "\t-nombre NOMBRE DEL PERSONAJE : Indica el nombre del personaje del juego");
				System.exit(0);
			}
		}
		
		String ruta = "./resumenPartida.txt";
		File file = new File(ruta);
		try {
			
			if (!file.exists()) {
				file.createNewFile();
			}else {
				PrintWriter writer = new PrintWriter(file); 
				writer.print(""); 
				writer.close();
			}
			Ventana ventana = new Ventana(nombrePersonaje, file);
		} catch (CharacterDoesNotExistException | IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Character doesn't exist", JOptionPane.ERROR_MESSAGE);
			try {
				Ventana ventana = new Ventana("Ferdinand", file);
			} catch (CharacterDoesNotExistException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
