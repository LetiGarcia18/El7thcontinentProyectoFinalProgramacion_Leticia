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

public class Ventana extends JFrame {
	private HashMap<String, JPanel> pantallas;
	private ArrayList<CartaEnMapa> cartasEnMapa;
	private ArrayList<Carta> cartas;
	private Personaje personaje;

	public Ventana(String nombre) throws CharacterDoesNotExistException {

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
		this.setResizable(false); // No deja cambiar el tama�o de la ventana
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setContentPane(new MenuPrincipal(this));
		this.setContentPane(this.pantallas.get("menuInicio"));

		this.setVisible(true);
	}

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
	
	public void cargaPersonaje(String nombrePersonaje) throws CharacterDoesNotExistException {

		Statement smt = UtilsDB.conectarBD();

		try {
			ResultSet cursorPersonaje = smt
					.executeQuery("select id, nombre, habilidad, rutaCartaHistoria, rutaIconoPersonaje from personaje where nombre = '"+nombrePersonaje+"'");

			while (cursorPersonaje.next()) {
				int id = cursorPersonaje.getInt("id");
				String nombre = cursorPersonaje.getString("nombre");
				String habilidad = cursorPersonaje.getString("habilidad");
				String rutaCartaHistoria = cursorPersonaje.getString("rutaCartaHistoria");
				String rutaIconoPersonaje = cursorPersonaje.getString("rutaIconoPersonaje");

				this.personaje = new Personaje(id, nombre, TipoAccion.valueOf(habilidad), rutaCartaHistoria,
						rutaIconoPersonaje);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UtilsDB.desconectarBD();

		if (this.personaje == null) {
			throw new CharacterDoesNotExistException("No existe el personaje");
		}
	}

	public HashMap<String, JPanel> getPantallas() {
		return pantallas;
	}

	public void setPantallas(HashMap<String, JPanel> pantallas) {
		this.pantallas = pantallas;
	}
	
	
	

	
	

}
