package clases;
/**
 * Super clase que a su vez hereda de la super Clase Carta. Esta clase va a representar las cartas que est�n en el tablero.
 * @author Leticia
 *
 */
public class CartaEnMapa extends Carta{
	/** La posici�n X en la que se encuentra la carta en el tablero de juego **/
	private byte posicionX;
	/** La posici�n Y en la que se encuentra la carta en el tablero de juego **/
	private byte posicionY;

	/**
	 * Constructor de la clase CartaEnMapa, donde se va a crear un objeto que se va a componer de un id, un n�mero de carta, 
	 * una ruta de la imagen donde se encuentra la carta, un texto descriptivo de lo que est� ocurriendo en esa carta,
	 * la posici�n X y la posici�n Y de la carta.
	 * @param id Id de la carta
	 * @param numeroCarta El n�mero de la carta
	 * @param rutaImagen La ruta de la imagen de la carta
	 * @param textoCarta El texto que muestra lo que ocurre en esa carta (aparece cuando se pasa el cursor por encima de la carta)
	 * @param posicionX La posici�n X de la carta
	 * @param posicionY La posici�n Y de la carta
	 * @param estaInicialmenteEnMesa booleano que nos dice si est� inicialmente en el tablero o no.
	 */
	public CartaEnMapa(int id, String numeroCarta, String rutaImagen, String textoCarta, byte posicionX, byte posicionY, boolean estaInicialmenteEnMesa) {
		super(id, numeroCarta, rutaImagen, textoCarta, estaInicialmenteEnMesa);
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}

	/**
	 * Getter de la posici�n X de la carta
	 * @return Nos devuelve la posici�n X de la carta
	 */
	public byte getPosicionX() {
		return posicionX;
	}

	/**
	 * Setter de la posici�n X de la carta
	 * @param posicionX La posici�n X de la carta
	 */
	public void setPosicionX(byte posicionX) {
		this.posicionX = posicionX;
	}

	/**
	 * Getter de la posici�n Y de la carta
	 * @return Nos devuelve la posici�n Y de la carta
	 */
	public byte getPosicionY() {
		return posicionY;
	}

	/**
	 * Setter de la posici�n Y de la carta
	 * @param posicionY La posici�n Y de la carta
	 */
	public void setPosicionY(byte posicionY) {
		this.posicionY = posicionY;
	}	

}
