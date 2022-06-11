package pantallas;

import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import clases.Carta;
import clases.CartaEnMapa;
import clases.CartaEvento;
import clases.CartaTerreno;
import clases.Personaje;
import enums.TipoAccion;
import excepciones.CharacterDoesNotExistException;
import utils.UtilsDB;

/**
 * Clase Ventana que hereda de JFrame, que va a contener todas las pantallas del juego.
 * @author Leticia
 *
 */
public class Ventana extends JFrame {
	/** Colección que contiene todas las pantallas del juego **/
	private HashMap<String, JPanel> pantallas;
	/** Colección que contiene todas las cartas que están situadas en el mapa del juego **/
	private ArrayList<CartaEnMapa> cartasEnMapa;
	/** Colección que contiene todas las cartas del juego **/
	private ArrayList<Carta> cartas;
	/** Variable que representa al personaje del juego **/
	private Personaje personaje;

	/**
	 * Consutructor de la clase Ventana, la cual va a lanzar una excepción si no encuentra el nombre del personaje en la BBDD,
	 * de ahí que se le pase el nombre del personaje por parámetros. 
	 * En este contructor se van a cargar desde BBDD las cartas de Terreno, las cartas de evento, al personaje, las cartas de 
	 * estado del personaje, y las cartas de inventario del personaje.
	 * @param nombre El nombre del personaje
	 * @throws CharacterDoesNotExistException Excepción que se lanza cuando no encuentra el nombre del personaje en BBDD
	 * @throws IOException 
	 */
	public Ventana(String nombre) throws CharacterDoesNotExistException, IOException {

		cartasEnMapa = new ArrayList<CartaEnMapa>();
		cartas = new ArrayList<Carta>();
		cargaCartasTerreno();
		cargaCartasEvento();
		cargaPersonaje(nombre);
		
		personaje.cargaCartasEstado();
		personaje.cargaCartasInventario();
		cargarAcciones();

		pantallas = new HashMap<String, JPanel>();
		pantallas.put("menuInicio", new MenuPrincipal(this));
		pantallas.put("game over", new PantallaGameOver(this));
		pantallas.put("reglas", new PantallaReglasJuego(this));
		pantallas.put("historiaPersonaje", new PantallaHistoriaPersonaje(this, this.personaje));
		pantallas.put("pantallaHistoriaFinal", new PantallaHistoriaFinal(this));
		pantallas.put("pantallaVictoria", new PantallaVictoria(this));
		pantallas.put("historiaPrincipal", new PantallaHistoriaInicial(this));
		pantallas.put("usaAuriculares", new PantallaUsoAuriculares(this));

		this.setSize(1500, 800);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setLocationRelativeTo(null);
		this.setTitle("The 7th continent");
		this.setIconImage(new ImageIcon("./iconos/iconoIsla.png").getImage());
		this.setAlwaysOnTop(true);
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("./iconos/mano1.png").getImage(),new Point(0,0),"custom cursor"));
		this.setResizable(false); // No deja cambiar el tamaño de la ventana
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setContentPane(new MenuPrincipal(this));
		this.setContentPane(this.pantallas.get("menuInicio"));

		this.setVisible(true);
	}

	/**
	 * Función pública que permite cambiar de pantalla, pasándole por parámetros el nombre de la pantalla a la que se quiere cambiar. 
	 * Esta función va a recorrer el HashMap de pantallas y va a poner invisible la pantalla que esté en ese momento visible, 
	 * y a continuación va a poner visible la pantalla a la que se quiera cambiar.
	 * @param nombrePantalla El nombre de la pantalla a la que se quiere cambiar. 
	 */
	public void cambiarAPantalla(String nombrePantalla) {
		Iterator it = this.pantallas.values().iterator();
		while (it.hasNext()) { 
			JPanel actual = (JPanel) it.next();
			actual.setVisible(false);
		}
		this.pantallas.get(nombrePantalla).setVisible(true); 
		this.setContentPane(this.pantallas.get(nombrePantalla));
		if (nombrePantalla.equals("game over")) {
			personaje.restablecerEnergia();
			personaje.setNumeroCartaPosicionado("010");
		}
	}

	/**
	 * Función pública que nos va a dibujar el tablero, que representa la pantalla principal del juego, la cual va a tener toda la interactividad.
	 * Esta función va a iterar por el HashMap de pantallas, va a poner invisible la pantalla de tablero, pero a la vez la va a poner
	 * visible esa misma ventana del tablero. 
	 */
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

	/**
	 * Función privada que va a cargar las cartas de terreno de la BBDD. 
	 */
	private void cargaCartasTerreno() {

		Statement smt = UtilsDB.conectarBD();

		try {
			ResultSet cursor = smt.executeQuery(
					"select id, rutaImagen, numeroCarta, textoCarta, posicionX, posicionY, incialmenteVisible from cartasTerreno");

			while (cursor.next()) {
				int id = cursor.getInt("id");
				String rutaImagen = cursor.getString("rutaimagen");
				String numeroCarta = cursor.getString("numeroCarta");
				String textoCarta = cursor.getString("textoCarta");
				byte posicionX = cursor.getByte("posicionX");
				byte posicionY = cursor.getByte("posicionY");
				boolean estaIncialmenteVisible = cursor.getBoolean("incialmenteVisible");

				CartaTerreno cartaTerreno = new CartaTerreno(id, rutaImagen, numeroCarta, textoCarta, posicionX, posicionY, estaIncialmenteVisible);
				cartasEnMapa.add(cartaTerreno);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UtilsDB.desconectarBD();

	}

	/**
	 * Función privada que va a cargar las acciones de BBDD de las cartas que haya en el tablero, de las cartas de los estados del 
	 * personaje, y de las cartas del inventario llamando a otra función que es la que se conecta a la BBDD.
	 */
	private void cargarAcciones() {
		for (Carta carta : cartasEnMapa) {
			carta.cargarAcciones();
		}

		for (Carta carta : personaje.getEstadosPersonaje()) {
			carta.cargarAcciones();
		}

		for (Carta cartaInventario : personaje.getInventario()) {
			cartaInventario.cargarAcciones();
		}

	}

	/**
	 * Función privada que va a cargar de BBDD las cartas de evento del juego. 
	 */
	private void cargaCartasEvento() {

		Statement smt = UtilsDB.conectarBD();

		try {
			ResultSet cursorCartaEvento = smt.executeQuery(
					"select id, numeroCarta, rutaImagen, textoCarta, posicionX, posicionY, id_cartaAsociada, id_accionDesactivada from cartasEvento");

			while (cursorCartaEvento.next()) {
				int id = cursorCartaEvento.getInt("id");
				String numeroCarta = cursorCartaEvento.getString("numeroCarta");
				String rutaImagen = cursorCartaEvento.getString("rutaImagen");
				String textoCarta = cursorCartaEvento.getString("textoCarta");
				byte posicionX = cursorCartaEvento.getByte("posicionX");
				byte posicionY = cursorCartaEvento.getByte("posicionY");
				byte idCartaAsociada = cursorCartaEvento.getByte("id_cartaAsociada");
				byte idAccionDesactivada = cursorCartaEvento.getByte("id_accionDesactivada");

				CartaEnMapa cartaEvento = new CartaEvento(id, numeroCarta, rutaImagen, textoCarta, posicionX, posicionY,
						idCartaAsociada, idAccionDesactivada);
				cartasEnMapa.add(cartaEvento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UtilsDB.desconectarBD();
	}
	
	/**
	 * Función pública que va a cargar el personaje de la BBDD. Se le pasa por parámetros el nombre del personaje, y si lo encuentra 
	 * te carga todos sus datos y se crea un objeto Personaje, y si no encuentra el nombre del personaje en la BBDD, lanza una excepción.
	 * @param nombrePersonaje El nombre del personaje
	 * @throws CharacterDoesNotExistException Excepción que se lanzará si no encuentra el nombre del personaje en la BBDD.
	 */
	public void cargaPersonaje(String nombrePersonaje) throws CharacterDoesNotExistException {

		Statement smt = UtilsDB.conectarBD();

		try {
			ResultSet cursorPersonaje = smt
					.executeQuery("select id, nombre, habilidad, rutaCartaHistoria, rutaIconoPersonaje, rutaHistoriaPersonajeTxt from personaje where nombre = '"+nombrePersonaje+"'");

			while (cursorPersonaje.next()) {
				int id = cursorPersonaje.getInt("id");
				String nombre = cursorPersonaje.getString("nombre");
				String habilidad = cursorPersonaje.getString("habilidad");
				String rutaCartaHistoria = cursorPersonaje.getString("rutaCartaHistoria");
				String rutaIconoPersonaje = cursorPersonaje.getString("rutaIconoPersonaje");
				String rutaHistoriaPersonaje = cursorPersonaje.getString("rutaHistoriaPersonajeTxt");

				this.personaje = new Personaje(id, nombre, TipoAccion.valueOf(habilidad), rutaCartaHistoria, rutaIconoPersonaje, rutaHistoriaPersonaje);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UtilsDB.desconectarBD();

		if (this.personaje == null) {
			throw new CharacterDoesNotExistException("No existe el personaje que se ha introducido por argumentos de programa. Se escogerá a Ferdinand por defecto");
		}
	}

	/**
	 * Getter del HashMap de las pantallas
	 * @return Las pantallas que tenga el HashMap
	 */
	public HashMap<String, JPanel> getPantallas() {
		return pantallas;
	}

	/**
	 * Setter del HashMap de pantallas
	 * @param pantallas Un HashMap con las pantallas
	 */
	public void setPantallas(HashMap<String, JPanel> pantallas) {
		this.pantallas = pantallas;
	}
	
}
