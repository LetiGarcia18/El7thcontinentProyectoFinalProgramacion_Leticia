package clases;
/**
 * Super clase que a su vez hereda de la super Clase Carta. Esta clase va a representar las cartas que están en el tablero.
 * @author Leticia
 *
 */
public class CartaEnMapa extends Carta{
	/** La posición X en la que se encuentra la carta en el tablero de juego **/
	private byte posicionX;
	/** La posición Y en la que se encuentra la carta en el tablero de juego **/
	private byte posicionY;

	/**
	 * Constructor de la clase CartaEnMapa, donde se va a crear un objeto que se va a componer de un id, un número de carta, 
	 * una ruta de la imagen donde se encuentra la carta, un texto descriptivo de lo que está ocurriendo en esa carta,
	 * la posición X y la posición Y de la carta.
	 * @param id Id de la carta
	 * @param numeroCarta El número de la carta
	 * @param rutaImagen La ruta de la imagen de la carta
	 * @param textoCarta El texto que muestra lo que ocurre en esa carta (aparece cuando se pasa el cursor por encima de la carta)
	 * @param posicionX La posición X de la carta
	 * @param posicionY La posición Y de la carta
	 * @param estaInicialmenteEnMesa booleano que nos dice si está inicialmente en el tablero o no.
	 */
	public CartaEnMapa(int id, String numeroCarta, String rutaImagen, String textoCarta, byte posicionX, byte posicionY, boolean estaInicialmenteEnMesa) {
		super(id, numeroCarta, rutaImagen, textoCarta, estaInicialmenteEnMesa);
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}

	/**
	 * Getter de la posición X de la carta
	 * @return Nos devuelve la posición X de la carta
	 */
	public byte getPosicionX() {
		return posicionX;
	}

	/**
	 * Setter de la posición X de la carta
	 * @param posicionX La posición X de la carta
	 */
	public void setPosicionX(byte posicionX) {
		this.posicionX = posicionX;
	}

	/**
	 * Getter de la posición Y de la carta
	 * @return Nos devuelve la posición Y de la carta
	 */
	public byte getPosicionY() {
		return posicionY;
	}

	/**
	 * Setter de la posición Y de la carta
	 * @param posicionY La posición Y de la carta
	 */
	public void setPosicionY(byte posicionY) {
		this.posicionY = posicionY;
	}	

}
