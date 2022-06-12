package clases;

/**
 * Clase CartaEstado que hereda de la super clase Carta y va a representar 
 * los estados que puede llegar a tener el personaje durante el juego
 * 
 * @author Leticia
 *
 */
public class CartaEstado extends Carta {
	/** El título del estado que puede tener el personaje. Aparecerá encima de la
	 * carta correspondiente **/
	private String tituloEstado;

	/**
	 * Constructor de la clase CartaEstado, que se va a componer de un id, un número
	 * de carta, un texto descriptivo de la carta, la ruta de la imagen de la carta,
	 * y un título del estado que puede tener el personaje
	 * 
	 * @param id           El id de la carta
	 * @param numeroCarta  El número de la carta
	 * @param textoCarta   El texto que muestra lo que ocurre en esa carta (aparece
	 *                     cuando se pasa el cursor por encima de la carta)
	 * @param rutaImagen   La ruta de la imagen de la carta
	 * @param tituloEstado El título del estado que puede tener el personaje
	 */
	public CartaEstado(int id, String numeroCarta, String textoCarta, String rutaImagen, String tituloEstado) {
		super(id, numeroCarta, rutaImagen, textoCarta, false);
		this.tituloEstado = tituloEstado;

	}

	/**
	 * Getter del titulo del estado de la carta
	 * 
	 * @return El título del estado
	 */
	public String getTituloEstado() {
		return tituloEstado;
	}
}
