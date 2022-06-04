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

import clases.Carta;
import clases.CartaEnMapa;
import clases.CartaEstado;
import clases.CartaEvento;
import clases.CartaTerreno;
import clases.Personaje;
import utils.UtilsDB;

import javax.swing.JProgressBar;

public class Ventana extends JFrame {
	private HashMap<String, JPanel> pantallas;
	private ArrayList<CartaEnMapa> cartasEnMapa;
	private Personaje personaje;

	public Ventana() {
		cartasEnMapa = new ArrayList<CartaEnMapa>();
		cargaCartasTerreno();
		cargaCartasEvento();
		cargarAcciones();

		personaje = new Personaje("Peter", "010", (short) 10, "cartasPersonaje/characterToken.png");

		pantallas = new HashMap<String, JPanel>();
		pantallas.put("menuInicio", new MenuPrincipal(this));
		pantallas.put("game over", new PantallaGameOver(this));
		pantallas.put("reglas", new PantallaReglasJuego(this));

		//this.setUndecorated(true);
		this.setSize(1500, 800);
		// this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Para que me salga en pantalla
		// completa.
		this.setLocationRelativeTo(null);
		this.setTitle("The 7th continent");
		this.setIconImage(new ImageIcon("./iconos/iconoIsla.png").getImage());
		this.setAlwaysOnTop(true);
		// this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new
		// ImageIcon("./iconos/cursor.png").getImage(),new Point(0,0),"custom cursor"));
		this.setResizable(false); // No deja cambiar el tamaño de la ventana
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setContentPane(new MenuPrincipal(this));
		this.setContentPane(this.pantallas.get("menuInicio"));

		this.setVisible(true);
	}

	public void cambiarAPantalla(String nombrePantalla) {
		Iterator it = this.pantallas.values().iterator();
		while (it.hasNext()) { // Con esto recorremos todas las pantallas y ponemos su visible a falso
			JPanel actual = (JPanel) it.next();
			actual.setVisible(false);
		}
		this.pantallas.get(nombrePantalla).setVisible(true); // Esta nos muestra la pantalla que queremos
		this.setContentPane(this.pantallas.get(nombrePantalla));
	}

	public void dibujaTablero() {

		Iterator it = this.pantallas.values().iterator();
		while (it.hasNext()) {
			JPanel actual = (JPanel) it.next();
			actual.setVisible(false);
		}

		pantallas.put("tablero", new Tablero(this, cartasEnMapa, personaje));
		this.pantallas.get("tablero").setVisible(true);
		this.setContentPane(this.pantallas.get("tablero"));
	}

	private void cargaCartasTerreno() {

		Statement smt = UtilsDB.conectarBD();

		try {
			ResultSet cursor = smt.executeQuery(
					"select id, rutaImagen, numeroCarta, posicionX, posicionY, incialmenteVisible from cartasTerreno");

			while (cursor.next()) {
				int id = cursor.getInt("id");
				String rutaImagen = cursor.getString("rutaimagen");
				String numeroCarta = cursor.getString("numeroCarta");
				byte posicionX = cursor.getByte("posicionX");
				byte posicionY = cursor.getByte("posicionY");
				boolean estaIncialmenteVisible = cursor.getBoolean("incialmenteVisible");

				CartaTerreno cartaTerreno = new CartaTerreno(id, rutaImagen, numeroCarta, posicionX, posicionY,
						estaIncialmenteVisible);
				cartasEnMapa.add(cartaTerreno);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UtilsDB.desconectarBD();

	}

	private void cargarAcciones() {
		for (Carta carta : cartasEnMapa) {
			carta.cargarAcciones();
		}
	}

	private void cargaCartasEvento() {

		Statement smt = UtilsDB.conectarBD();

		try {
			ResultSet cursorCartaEvento = smt.executeQuery(
					"select id, numeroCarta, rutaImagen, posicionX, posicionY, id_cartaAsociada, id_accionDesactivada from cartasEvento");

			while (cursorCartaEvento.next()) {
				int id = cursorCartaEvento.getInt("id");
				String numeroCarta = cursorCartaEvento.getString("numeroCarta");
				String rutaImagen = cursorCartaEvento.getString("rutaImagen");
				byte posicionX = cursorCartaEvento.getByte("posicionX");
				byte posicionY = cursorCartaEvento.getByte("posicionY");
				byte id_cartaAsociada = cursorCartaEvento.getByte("id_cartaAsociada");
				byte id_accionDesactivada = cursorCartaEvento.getByte("id_accionDesactivada");

				CartaEnMapa cartaEvento = new CartaEvento(id, numeroCarta, rutaImagen, posicionX, posicionY,
						id_cartaAsociada, id_accionDesactivada);
				cartasEnMapa.add(cartaEvento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UtilsDB.desconectarBD();

	}

	

}
