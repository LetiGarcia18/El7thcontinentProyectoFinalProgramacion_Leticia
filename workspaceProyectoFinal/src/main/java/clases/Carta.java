package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;

import enums.TipoAccion;
import enums.TipoConsecuencia;
import utils.UtilsDB;

/**
 * Super clase Carta, en la cual se van a crear objetos que van a contener un
 * id, un n�mero de carta, unas acciones asociadas a esa carta, la ruta de la
 * imagen de la carta, un peque�o texto que describe lo que ocurre en esa carta,
 * y si se encuentra en la mesa o no. Esta clase representa las cartas del juego.
 * 
 * @author Leticia
 *
 */
public class Carta {
	/** El id de la carta **/
	private int id;
	/** El n�mero de la carta **/
	private String numeroCarta;
	/** Las acciones que va a tener una carta **/
	private HashMap<Integer, Accion> acciones;
	/** La ruta de la imagen de esa carta **/
	private String rutaImagen;
	/** El peque�o texto que va a tener cada carta. Este texto va a aparecer como una
	 * ayuda a la hora de pasar el cursor por encima de la carta **/
	private String textoCarta;
	/** Variable booleana que nos dice si est� o no en la mesa **/
	private boolean estaEnMesa;

	/**
	 * Constructor de la clase Carta, en la cual se va a crear un objeto Carta que
	 * va a contener un id, un n�mero de carta, la ruta de la imagen de la carta, el
	 * texto que contiene lo que est� ocurriendo en la carta, y un boolean que dice
	 * si se encuentra o no en la mesa
	 * 
	 * @param id                     El id de la carta
	 * @param numeroCarta            El n�mero de la carta
	 * @param rutaImagen             La ruta de la imagen de la carta
	 * @param textoCarta             El texto que describe lo que est� ocurriendo en la carta
	 * @param estaInicialmenteEnMesa Nos dice si se encuentra o no en la mesa
	 */
	public Carta(int id, String numeroCarta, String rutaImagen, String textoCarta, boolean estaInicialmenteEnMesa) {
		this.id = id;
		this.numeroCarta = numeroCarta;
		this.acciones = new HashMap<Integer, Accion>();
		this.rutaImagen = rutaImagen;
		this.textoCarta = textoCarta;
		this.estaEnMesa = estaInicialmenteEnMesa;
	}

	/**
	 * Getter del id de la carta
	 * 
	 * @return Nos devuelve el id de la carta
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter del id de la carta
	 * 
	 * @param id El id de la carta
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Setter del n�mero de la carta
	 * 
	 * @return Nos devuelve el n�mero de la carta
	 */
	public String getNumeroCarta() {
		return numeroCarta;
	}

	/**
	 * Setter del n�mero de la carta
	 * 
	 * @param numeroCarta Nos devuelve el n�mero de la carta
	 */
	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	/**
	 * Getter de las acciones de la carta
	 * 
	 * @return Nos devuelve las acciones que puede tener la carta
	 */
	public HashMap<Integer, Accion> getAcciones() {
		return acciones;
	}

	/**
	 * Setter de las acciones de la carta
	 * 
	 * @param acciones Las acciones de la carta
	 */
	public void setAcciones(HashMap<Integer, Accion> acciones) {
		this.acciones = acciones;
	}

	/**
	 * Getter de la ruta de la imagen
	 * 
	 * @return Nos devuelve la ruta de la imagen de la carta
	 */
	public String getRutaImagen() {
		return rutaImagen;
	}

	/**
	 * Setter de la ruta de la imagen
	 * 
	 * @param rutaImagen La ruta de la imagen de la carta
	 */
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	/**
	 * Getter del texto que tiene la carta
	 * 
	 * @return Nos devuelve el texto de la carta
	 */
	public String getTextoCarta() {
		return textoCarta;
	}

	/**
	 * Setter del texto de la carta
	 * 
	 * @param textoCarta El texto de la carta
	 */
	public void setTextoCarta(String textoCarta) {
		this.textoCarta = textoCarta;
	}

	/**
	 * Funci�n p�blica que nos dice si la carta est� en mesa o no (true: est� en
	 * mesa, false: no est� en la mesa)
	 * 
	 * @return Nos deuelve un booleando, True si est� en la mesa y False si no en la
	 *         mesa
	 */
	public boolean estaEnMesa() {
		return estaEnMesa;
	}

	/**
	 * Setter de la variable booleana que nos dice si est� o no en mesa.
	 * 
	 * @param estaEnMesa Le pasamos true o false, dependiendo si va a estar la carta
	 *                   o no en la mesa
	 */
	public void setEstaEnMesa(boolean estaEnMesa) {
		this.estaEnMesa = estaEnMesa;
	}

	/**
	 * Funci�n p�blica que se conecta a la BBDD y nos carga las acciones que tiene
	 * la carta. Dichas acciones las va almacenando en el HashMap de acciones de la
	 * carta. A su vez, carga las consecuencias que tiene cada acci�n, y seg�n sean
	 * consecuencias positivas o negativas, llama a la funci�n correspondiente de
	 * agregar consecuencias a la acci�n.
	 * 
	 */
	public void cargarAcciones() {
		Statement smt = UtilsDB.conectarBD();

		try {
			ResultSet cursorAcciones = smt
					.executeQuery("select * from accion where carta_num  = '" + this.getNumeroCarta() + "'");
			while (cursorAcciones.next()) {

				int id = cursorAcciones.getInt("id");
				String tipoAccion = cursorAcciones.getString("tipo");
				String descripcion = cursorAcciones.getString("descripcion");
				short costeAccion = cursorAcciones.getShort("costeAccion");
				short dificultadAccion = cursorAcciones.getShort("dificultadAccion");
				String cartaNum = cursorAcciones.getString("carta_num");
				String rutaIconoAccion = cursorAcciones.getString("iconoAccion");

				Accion actual = new Accion(id, TipoAccion.valueOf(tipoAccion), descripcion, costeAccion,
						dificultadAccion, cartaNum, rutaIconoAccion);
				acciones.put(actual.getId(), actual);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Iterator iterador = acciones.keySet().iterator();
		while (iterador.hasNext()) {
			int key = (Integer) iterador.next();
			Accion accion = acciones.get(key);

			// CARGAR CONSECUENCIAS PARA LA ACCION (buenas y/o malas)

			try {
				ResultSet cursorConsecuencias;
				cursorConsecuencias = smt
						.executeQuery("select * from consecuencia where accion_id = '" + accion.getId() + "'");
				while (cursorConsecuencias.next()) {
					int idConsecuencia = cursorConsecuencias.getInt("id");
					String tipoConsecuencia = cursorConsecuencias.getString("tipo");
					int accion_id = cursorConsecuencias.getInt("accion_id");
					byte esPositiva = cursorConsecuencias.getByte("esPositiva");
					String cartaObjetivo = cursorConsecuencias.getString("cartaObjetivo");

					Consecuencia consecuenciaActual = new Consecuencia(idConsecuencia,
							TipoConsecuencia.valueOf(tipoConsecuencia), (byte) accion_id, esPositiva, cartaObjetivo);

					if (esPositiva == 1) {
						accion.agregaConsecuenciaPositiva(consecuenciaActual);
					} else {
						accion.agregaConsecuenciaNegativa(consecuenciaActual);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		UtilsDB.desconectarBD();
	}

	/**
	 * Funci�n toString de la clase Carta, donde nos devuelve una String con el
	 * n�mero de carta, la ruta de la imagen y las acciones que tiene la carta
	 */
	public String toString() {
		String accionesTexto = "";
		Iterator<Integer> iterador = this.acciones.keySet().iterator();
		while (iterador.hasNext()) {
			Integer key = (Integer) iterador.next();
			Accion accion = this.acciones.get(key);
			accionesTexto += accion.toString() + "\t";
		}
		return "[" + this.numeroCarta + "] ruta: " + this.rutaImagen + " Acciones: " + accionesTexto;
	}

}
