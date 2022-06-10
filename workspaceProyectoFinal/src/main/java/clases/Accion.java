package clases;


import java.util.ArrayList;
import enums.TipoAccion;

/**
 * Clase que representa las acciones que puede realizar el personaje. Las acciones se encuentra en los distintos tipos de carta.
 * @author Leticia
 *
 */
public class Accion {
	/** id de la acción **/
	private int id;
	/** El tipo de acción a resolver **/
	private TipoAccion tipoAccion;
	/** La descripción de la acción **/
	private String descripcion;
	/** El coste de energía de la acción **/
	private short costeAccion;
	/** La dificultad de la acción. Va a ir de 0 a 6, y se resolverá con una tirada ficticia de un dado de 6 **/
	private short dificultadAccion;
	/** El número de la carta donde se encuentra la acción **/
	private String numeroCarta;
	/** Las consecuencias positivas de la acción **/
	private ArrayList<Consecuencia> consecuenciasPositivas;
	/** Las consecuencias negativas de la acción **/
	private ArrayList<Consecuencia> consecuenciasNegativas;
	
	/**
	 * Constructor de la clase Accion. En él se va a crear el objeto acción, que va a estar compuesto por el id, el tipo de acción,
	 * la descripción de la misma, el coste de energía que va a tener la acción, la dificultad y el número de carta donde se encuentra
	 * la acción.
	 * @param id El id de la acción
	 * @param tipoAccion El tipo de acción
	 * @param descripcion Pequeña descripción de la acción.
	 * @param costeAccion El coste de energía de la acción
	 * @param dificultadAccion La dificultad que va a tener la acción
	 * @param numeroCarta El número de carta donde se encuentra dicha acción
	 */
	public Accion(int id, TipoAccion tipoAccion, String descripcion, short costeAccion, short dificultadAccion, String numeroCarta) {
		this.id = id;
		this.tipoAccion = tipoAccion;
		this.descripcion = descripcion;
		this.costeAccion = costeAccion;
		this.dificultadAccion = dificultadAccion;
		this.numeroCarta = numeroCarta;
		this.consecuenciasPositivas = new ArrayList<Consecuencia>();
		this.consecuenciasNegativas = new ArrayList<Consecuencia>();
		
	}
	
	/**
	 * Getter del id de acción
	 * @return Nos devuelve el id de acción
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Getter del tipo de acción
	 * @return Nos devuelve el tipo de acción 
	 */
	public TipoAccion getTipoAccion() {
		return this.tipoAccion;
	}
	
	/**
	 * Getter de la descripción de la acción
	 * @return Nos devuelve la descripción de la acción 
	 */
	public String getDescripcion() {
		return this.descripcion;
	}
	
	/**
	 * Getter del coste de la acción
	 * @return Nos devuelve el coste de la acción
	 */
	public short getCosteAccion() {
		return costeAccion;
	}

	/**
	 * Setter del coste de la acción
	 * @param costeAccion El coste de la acción
	 */
	public void setCosteAccion(short costeAccion) {
		this.costeAccion = costeAccion;
	}

	/**
	 * Getter de la dificultad de la acción
	 * @return Nos devuelve la dificultad de la acción
	 */
	public short getDificultadAccion() {
		return dificultadAccion;
	}

	/**
	 * Setter de la dificultad de la acción
	 * @param dificultadAccion La dificultad de la acción
	 */
	public void setDificultadAccion(short dificultadAccion) {
		this.dificultadAccion = dificultadAccion;
	}
	
	/**
	 * Getter de las consecuencias positivas
	 * @return Nos devuelve las consecuencias positivas
	 */
	public ArrayList<Consecuencia> getConsecuenciasPositivas() {
		return consecuenciasPositivas;
	}

	/**
	 * Función que añade una consecuencia positiva a la hora de resolver una acción
	 * @param consecuencia La consecuencia positiva que va a tener una acción
	 */
	public void agregaConsecuenciaPositiva(Consecuencia consecuencia) {
		this.consecuenciasPositivas.add(consecuencia);
	}

	/**
	 * Getter de las consecuencias negativas
	 * @return Nos devuelve las consecuencias negativas
	 */
	public ArrayList<Consecuencia> getConsecuenciasNegativas() {
		return consecuenciasNegativas;
	}

	/**
	 * Función pública que añade una consecuencia negativa a la hora de resolver una acción
	 * @param consecuencia La consecuencia negativa que va a tener una acción
	 */
	public void agregaConsecuenciaNegativa(Consecuencia consecuencia) {
		this.consecuenciasNegativas.add(consecuencia);
	}

	/*
	 * Función toString de la clase Acción. Nos devuelve una String con el id de la acción, el tipo, la descripción de la misma, 
	 * y las consecuencias positivas y negativas que pudiera tener esa acción
	 */
	@Override
	public String toString() {
		String consecuenciasPositivasString = "";
		for (int i = 0; i < consecuenciasPositivas.size(); i++){
			Consecuencia consecuencia = consecuenciasPositivas.get(i);
			consecuenciasPositivasString += consecuencia.toString() + "\t";
		}
		String consecuenciasNegativasString = "";
		for (int i = 0; i < consecuenciasNegativas.size(); i++){
			Consecuencia consecuencia = consecuenciasNegativas.get(i);
			consecuenciasNegativasString += consecuencia.toString() + "\t";
		}
		return "[" + id + "][" + tipoAccion + "] '" + descripcion + "'" + "\n" + "Consecuencias positivas: " 
		+ consecuenciasPositivasString+ "\n" + "Consecuencias negativas: " 
		+ consecuenciasNegativasString;
	}


}
