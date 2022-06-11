package clases;

import enums.TipoConsecuencia;

/**
 * Clase Consecuencia la cual va a representar las consecuencias que tienen las
 * acciones de las cartas.
 * 
 * @author Leticia
 *
 */
public class Consecuencia {
	/** El id de la consecuencia **/
	private int id;
	/** El tipo de consecuencia que se puede producir al resolver una acción **/
	private TipoConsecuencia tipoConsecuencia;
	/** Nos dice si la conseciencia es positiva o no (1 positiva, 0 negativa) **/
	private byte esPositiva;
	/** id de la acción que desencadena la consecuencia **/
	private int accionId;
	/** El número de la carta que tiene la accionId **/
	private String cartaObjetivo;

	/**
	 * Constructor de la clase Consecuencia, donde se va a crear un objeto, que va a
	 * contener un id, un tipo de consecuencia, el id de la acción que desencadena
	 * la consecuencia y el número de la carta que tiene la accionId
	 * 
	 * @param id               El id de la consecuencia
	 * @param tipoConsecuencia El tipo de consecuencia que se puede producir
	 * @param accionId         id de la acción que desencadena la consecuencia
	 * @param esPositivaNos    dice si la conseciencia es positiva o no (1 positiva,
	 *                         0 negativa)
	 * @param cartaObjetivo    El número de la carta que tiene la accionId
	 */
	public Consecuencia(int id, TipoConsecuencia tipoConsecuencia, int accionId, byte esPositiva,
			String cartaObjetivo) {
		this.id = id;
		this.tipoConsecuencia = tipoConsecuencia;
		this.esPositiva = esPositiva;
		this.accionId = accionId;
		this.cartaObjetivo = cartaObjetivo;
	}

	/**
	 * Getter del id de la consecuencia
	 * 
	 * @return Nos decuelve el id de la consecuencia
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter del id de la consecuencia
	 * 
	 * @param id El id de la consecuencia
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter del tipo de consecuencia
	 * 
	 * @return Nos devuelve el tipo de consecuencia
	 */
	public TipoConsecuencia getTipoConsecuencia() {
		return tipoConsecuencia;
	}

	/**
	 * Setter del tipo de consecuencia
	 * 
	 * @param tipoConsecuencia El tipo de consecuencia
	 */
	public void setTipoConsecuencia(TipoConsecuencia tipoConsecuencia) {
		this.tipoConsecuencia = tipoConsecuencia;
	}

	/**
	 * Getter de si la consecuencia es positiva o no
	 * 
	 * @return Nos devuelve un 1 si es positiva o un 0 si es negativa
	 */
	public byte getEsPositiva() {
		return esPositiva;
	}

	/**
	 * Setter de si la consecuencia es positiva o no
	 * 
	 * @param esPositiva byte que puede ser 1, si es positiva, o 0 si es negativa
	 */
	public void setEsPositiva(byte esPositiva) {
		this.esPositiva = esPositiva;
	}

	/**
	 * Getter del id de la acción que desencadena la consecuencia
	 * 
	 * @return Nos devuelve el id de la acción que desencadena la consecuencia
	 */
	public int getAccionId() {
		return accionId;
	}

	/**
	 * Setter del id de la acción que desencadena la consecuencia
	 * 
	 * @param accionId El id de la acción que desencadena la consecuencia
	 */
	public void setAccionId(int accionId) {
		this.accionId = accionId;
	}

	/**
	 * Getter de la carta que tiene la accionId
	 * 
	 * @return Nos devuelve la carta que tiene la accionId
	 */
	public String getCartaObjetivo() {
		return cartaObjetivo;
	}

	/**
	 * Setter de la carta que tiene la accionId
	 * 
	 * @param cartaObjetivo la carta que tiene la accionId
	 */
	public void setCartaObjetivo(String cartaObjetivo) {
		this.cartaObjetivo = cartaObjetivo;
	}

	/**
	 * Función toString que nos devuelve una String con los datos del id de la
	 * consecuencia, el tipo de consecuencia, si es positiva o no, el id de la
	 * acción que desencade la consecuencia, y la carta que contiene esa acción.
	 */
	@Override
	public String toString() {
		return "Consecuencia: id: " + id + ", tipoConsecuencia: " + tipoConsecuencia + ", esPositiva: " + esPositiva
				+ ", accion_id: " + accionId + ", cartaObjetivo: " + cartaObjetivo;
	}

}
