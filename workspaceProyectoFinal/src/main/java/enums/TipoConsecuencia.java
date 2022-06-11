package enums;

/**
 * Enum TipoConsecuencia que va a representar las consecuencias de las acciones
 * que se van a ir realizando durante el juego.
 * 
 * @author Leticia
 *
 */
public enum TipoConsecuencia {
	/** Consecuencia que te desplaza **/
	DESPLAZARSE,
	/** Consecuencia que te trae una carta al tablero **/
	TRAER_CARTA,
	/** Consecuencia que te quita una carta del tablero **/
	QUITAR_CARTA,
	/** Consecuencia que restaura energía al personaje **/
	RESTAURAR,
	/** Consecuencia que hace ganar la partida **/
	GANAR
}
