package clases;
/**
 * Clase CartaEvento que hereda de la super clase CartaEnMapa y va a representar las cartas de tipo evento que van a ir 
 * surgiendo como consecuencia de la resolución de las acciones
 * @author Leticia
 *
 */
public class CartaEvento extends CartaEnMapa{
	/** id de la carta asociada a la carta evento**/	
	private int idCartaAsociada;
	/** id de la acción que desactiva la carta evento **/
	private int idAccionDesactivada;

	/**
	 * Constructor de la clase CartaEvento, en el cual se crea el objeto, que contendrá un id, un número de carta, la ruta
	 * de la imagen de la carta, el texto que contiene lo que está ocurriendo en la carta, la posición X de la carta, la
	 * posición Y, el id de la carta asociada a la carta de evento, y el id de la acción que desactiva la carta evento.
	 * @param id El id de la carta evento
	 * @param numeroCarta El número de carta de la carta Evento
	 * @param rutaImagen La ruta de la imagen de la carta evento
	 * @param textoCarta El texto que describe lo que está ocurriendo en la carta
	 * @param posicionX La posición X de la carta
	 * @param posicionY La posición Y de la carta
	 * @param idCartaAsociada El id de la carta asociada a la carta de evento
	 * @param idAccionDesactivada El id de la acción que desactiva la carta evento
	 */
	public CartaEvento(int id, String numeroCarta, String rutaImagen, String textoCarta, byte posicionX, byte posicionY, int idCartaAsociada, int idAccionDesactivada) {
		super(id, numeroCarta, rutaImagen, textoCarta, posicionX, posicionY, false);
		this.idCartaAsociada = idCartaAsociada;
		this.idAccionDesactivada = idAccionDesactivada;
	}

	/**
	 * Getter del id de la carta asociada a la carta de evento
	 * @return Nos devuelve la carta asociada a la carta de evento
	 */
	public int getIdCartaAsociada() {
		return idCartaAsociada;
	}

	/**
	 * Getter de la carta asociada a la carta de evento
	 * @param idCartaAsociada id de la carta asociada a la carta de evento
	 */
	public void setIdCartaAsociada(int idCartaAsociada) {
		this.idCartaAsociada = idCartaAsociada;
	}

	/**
	 * Getter del id de la acción que desactiva la carta evento
	 * @return Nos devuelve el id de la acción que desactiva la carta evento
	 */
	public int getIdAccionDesactivada() {
		return idAccionDesactivada;
	}

	/**
	 * Setter del id de la acción que desactiva la carta evento
	 * @param idAccionDesactivada El id de la acción que desactiva la carta evento
	 */
	public void setIdAccionDesactivada(int idAccionDesactivada) {
		this.idAccionDesactivada = idAccionDesactivada;
	}
	
}
