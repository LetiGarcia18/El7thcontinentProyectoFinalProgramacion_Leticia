package pantallas;

import java.awt.Point;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Canvas;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JTree;

import clases.CartaTerreno;
import clases.Personaje;
import utils.UtilsDB;

import javax.swing.JProgressBar;

public class Ventana extends JFrame{
	private HashMap<String, JPanel> pantallas;
	private ArrayList<CartaTerreno> cartasTerreno;
	private Personaje personaje;
		
	public Ventana() {
		cartasTerreno = dameCartasTerreno();
		for (CartaTerreno cartaTerreno : cartasTerreno) {
			System.out.println(cartaTerreno);
		}
		personaje = new Personaje("Peter", (short)1, (short) 10, "cuadrados/personaje.png");
		System.out.println(personaje);
		
		pantallas = new HashMap<String, JPanel>();
		pantallas.put("menuInicio", new MenuPrincipal(this));
		pantallas.put("game over", new PantallaGameOver(this));
		
		this.setUndecorated(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setTitle("The 7th continent");
		this.setIconImage(new ImageIcon("./iconos/iconoIsla.png").getImage());
		this.setAlwaysOnTop(true);
		//this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("./iconos/cursor.png").getImage(),new Point(0,0),"custom cursor"));
		this.setResizable(false); //No deja cambiar el tamaño de la ventana
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setContentPane(new MenuPrincipal(this));
		this.setContentPane(this.pantallas.get("menuInicio")); 
		
		this.setVisible(true);
	}
	
	public void cambiarAPantalla(String nombrePantalla) {
		Iterator it = this.pantallas.values().iterator();
		while(it.hasNext()) { //Con esto recorremos todas las pantallas y ponemos su visible a falso
			JPanel actual = (JPanel)it.next();
			actual.setVisible(false);
		}
		this.pantallas.get(nombrePantalla).setVisible(true); //Esta nos muestra la pantalla que queremos
		this.setContentPane(this.pantallas.get(nombrePantalla));
	}
	
	public void dibujaTablero() {
		
		Iterator it = this.pantallas.values().iterator();
		while(it.hasNext()) {
			JPanel actual = (JPanel)it.next();
			actual.setVisible(false);
		}
		
		pantallas.put("tablero", new Tablero(this, cartasTerreno, personaje));
		this.pantallas.get("tablero").setVisible(true);
		this.setContentPane(this.pantallas.get("tablero"));
	}
	
	private ArrayList<CartaTerreno> dameCartasTerreno() {
		String rutaImagen = "";
		int id = 0;
		short numeroCarta = 0;
		byte posicionX = 0;
		byte posicionY = 0;
		CartaTerreno cartaTerreno;
		ArrayList<CartaTerreno> cartasTerreno = new ArrayList<CartaTerreno>();
		Statement smt = UtilsDB.conectarBD();

		try {
			ResultSet cursor = smt.executeQuery("select id, ruta, numeroCarta, posicionX, posicionY from cartaTerreno");

			while (cursor.next()) {
				id = cursor.getInt("id");
				rutaImagen = cursor.getString("ruta");
				numeroCarta = cursor.getShort("numeroCarta");
				posicionX = cursor.getByte("posicionX");
				posicionY = cursor.getByte("posicionY");

				cartaTerreno = new CartaTerreno(id, rutaImagen, numeroCarta, posicionX, posicionY);
				cartasTerreno.add(cartaTerreno);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UtilsDB.desconectarBD();
		
		for (CartaTerreno carta : cartasTerreno) {
			carta.cargarAccionesTerreno();
		}

		return cartasTerreno;
	}


}
