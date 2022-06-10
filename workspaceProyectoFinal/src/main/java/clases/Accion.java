package clases;


import java.util.ArrayList;
import enums.TipoAccion;

/**
 * Clase que representa las acciones que puede realizar el personaje. Las acciones se encuentra en los distintos tipos de carta.
 * @author Leticia
 *
 */
public class Accion {
	/** id de la acci�n **/
	private int id;
	/** El tipo de acci�n a resolver **/
	private TipoAccion tipoAccion;
	/** La descripci�n de la acci�n **/
	private String descripcion;
	/** El coste de energ�a de la acci�n **/
	private short costeAccion;
	/** La dificultad de la acci�n. Va a ir de 0 a 6, y se resolver� con una tirada ficticia de un dado de 6 **/
	private short dificultadAccion;
	/** El n�mero de la carta donde se encuentra la acci�n **/
	private String numeroCarta;
	/** Las consecuencias positivas de la acci�n **/
	private ArrayList<Consecuencia> consecuenciasPositivas;
	/** Las consecuencias negativas de la acci�n **/
	private ArrayList<Consecuencia> consecuenciasNegativas;
	
	/**
	 * Constructor de la clase Accion. En �l se va a crear el objeto acci�n, que va a estar compuesto por el id, el tipo de acci�n,
	 * la descripci�n de la misma, el coste de energ�a que va a tener la acci�n, la dificultad y el n�mero de carta donde se encuentra
	 * la acci�n.
	 * @param id El id de la acci�n
	 * @param tipoAccion El tipo de acci�n
	 * @param descripcion Peque�a descripci�n de la acci�n.
	 * @param costeAccion El coste de energ�a de la acci�n
	 * @param dificultadAccion La dificultad que va a tener la acci�n
	 * @param numeroCarta El n�mero de carta donde se encuentra dicha acci�n
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
	 * Getter del id de acci�n
	 * @return Nos devuelve el id de acci�n
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Getter del tipo de acci�n
	 * @return Nos devuelve el tipo de acci�n 
	 */
	public TipoAccion getTipoAccion() {
		return this.tipoAccion;
	}
	
	/**
	 * Getter de la descripci�n de la acci�n
	 * @return Nos devuelve la descripci�n de la acci�n 
	 */
	public String getDescripcion() {
		return this.descripcion;
	}
	
	/**
	 * Getter del coste de la acci�n
	 * @return Nos devuelve el coste de la acci�n
	 */
	public short getCosteAccion() {
		return costeAccion;
	}

	/**
	 * Setter del coste de la acci�n
	 * @param costeAccion El coste de la acci�n
	 */
	public void setCosteAccion(short costeAccion) {
		this.costeAccion = costeAccion;
	}

	/**
	 * Getter de la dificultad de la acci�n
	 * @return Nos devuelve la dificultad de la acci�n
	 */
	public short getDificultadAccion() {
		return dificultadAccion;
	}

	/**
	 * Setter de la dificultad de la acci�n
	 * @param dificultadAccion La dificultad de la acci�n
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
	 * Funci�n que a�ade una consecuencia positiva a la hora de resolver una acci�n
	 * @param consecuencia La consecuencia positiva que va a tener una acci�n
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
	 * Funci�n p�blica que a�ade una consecuencia negativa a la hora de resolver una acci�n
	 * @param consecuencia La consecuencia negativa que va a tener una acci�n
	 */
	public void agregaConsecuenciaNegativa(Consecuencia consecuencia) {
		this.consecuenciasNegativas.add(consecuencia);
	}

	/*
	 * Funci�n toString de la clase Acci�n. Nos devuelve una String con el id de la acci�n, el tipo, la descripci�n de la misma, 
	 * y las consecuencias positivas y negativas que pudiera tener esa acci�n
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
