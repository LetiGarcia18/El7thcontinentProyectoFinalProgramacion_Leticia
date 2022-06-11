package clases;

/**
 * Clase CartaTerreno que hereda de la super clase CartaEnMapa, la cual va a
 * representar las cartas de terreno en el tablero.
 * 
 * @author Leticia
 *
 */
public class CartaTerreno extends CartaEnMapa {

	/**
	 * Constructor de la clase CartaTerreno, que va a crear objetos los cuales van a
	 * contener un id, la ruta de la imagen de la carta terreno, el número de carta,
	 * el texto descriptivo de la carta, la posición X de la carta, la posición Y de
	 * la carta, y un booleano que nos va a decir si se encuentra la carta
	 * inicialmente en la mesa.
	 * 
	 * @param id                     El id de la carta de terreno
	 * @param rutaImagen             La ruta de la imagen de la carta Terreno
	 * @param numeroCarta            El número de la carta de terreno
	 * @param textoCarta             El texto descriptivo de la carta terreno
	 * @param posicionX              La posición X de la carta
	 * @param posicionY              La posición Y de la carta
	 * @param estaInicialmenteEnMesa Booleano que nos dice si la carta va a aparecer
	 *                               inicialmente o no, en el tablero
	 */
	public CartaTerreno(int id, String rutaImagen, String numeroCarta, String textoCarta, byte posicionX,
			byte posicionY, boolean estaInicialmenteEnMesa) {
		super(id, numeroCarta, rutaImagen, textoCarta, posicionX, posicionY, estaInicialmenteEnMesa);

	}

}
