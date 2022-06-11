package clases;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import enums.TipoAccion;
import utils.UtilsDB;

/**
 * Clase Personaje, que va a representar al personaje del juego, en la cual va a
 * ser posible crear al personaje, cargar sus estados y su inventario de la BBDD
 * 
 * @author Leticia
 *
 */
public class Personaje {
	/** Variable donde se almacena el id del personaje **/
	private int id;
	/** Variable que va a contener el nombre del personaje **/
	private String nombre;
	/**
	 * Variable que va a almacenar la carta donde va a comenzar situado el
	 * personaje. Por defecto va a empezar siempre en la carta n�mero 010
	 **/
	private String numeroCartaPosicionado;
	/**
	 * ArrayList que va a almacenar todas las cartas de inventario que el personaje
	 * vaya encontrando a lo largo del juego
	 **/
	private ArrayList<Carta> inventario;
	/**
	 * ArrayList que va a almacenar los estados de salud que el personaje vaya
	 * teniendo durante el juego
	 **/
	private ArrayList<CartaEstado> estadosPersonaje;
	/** Variable que va a almacenar la energ�a del personaje **/
	private short contadorEnergia;
	/** Variable que almacena la ruta del icono del personaje **/
	private String rutaIconoPersonaje;
	/** Variable que almacena la imagen de la historia del personaje **/
	private String rutaImagenHistoria;
	/**
	 * Variable que indica la energ�a que va a tener inicialmente el personaje
	 * siempre que se inicie el juego. Va a comenzar siempre con 100 de energ�a
	 **/
	private short energiaInicial = 80;
	/**
	 * Variable que va a almacenar la habilidad en la que es bueno nuestro
	 * personaje. Seg�n el tipo de habilidad en la que sea bueno, le costar� -1 de
	 * energ�a resolver acciones de ese tipo. Ej: si nuestro personaje es bueno
	 * investigando, las acciones de tipo INVESTIGATE, le costar�n -1 de energ�a
	 * realizarlas
	 **/
	private TipoAccion habilidad;
	/**
	 * Ruta del archivo .txt donde va a estar escrito un resumen en espa�ol de la
	 * historia del personaje
	 **/
	private String rutaHistoriaPersonaje;

	/**
	 * Constructor de la clase personaje. Se le pasan por par�metros el id, el
	 * nombre, la habilidad, la ruta donde se encuentra la imagen de la historia del
	 * personaje, la ruta del icono del mismo, y la ruta de archivo .txt donde est�
	 * en espa�ol el resumen de la historia del personaje. En este contructor
	 * tambi�n se le asigna al contador de energ�a del personaje la energ�a que va a
	 * tener siempre inicialmente, el n�mero de carta en el que va a estar
	 * posicionado en un inicio y tambi�n se inicializan los ArrayList de los
	 * estados del personaje y de su inventario.
	 * 
	 * @param id                    Variable de tipo int que almacena el id del
	 *                              personaje en BBDD
	 * @param nombre                Variable de tipo String que almacena el nombre
	 *                              del personaje.
	 * @param habilidad             Variable de tipo TipoAccion que almacena la
	 *                              habilidad del personaje
	 * @param rutaImagenHistoria    Variable que almacena la ruta de la imagen con
	 *                              la historia introductoria del personaje
	 * @param rutaIconoPersonaje    Variable que almacena el icono del personaje
	 * 
	 * @param rutaHistoriaPersonaje Variable que almacena la ruta del archivo .txt
	 *                              donde va a estar almacenado un resumen en
	 *                              espa�ol de la historia del personaje
	 */
	public Personaje(int id, String nombre, TipoAccion habilidad, String rutaImagenHistoria, String rutaIconoPersonaje,
			String rutaHistoriaPersonaje) {
		this.id = id;
		this.nombre = nombre;
		this.habilidad = habilidad;
		this.rutaImagenHistoria = rutaImagenHistoria;
		this.rutaIconoPersonaje = rutaIconoPersonaje;
		this.rutaHistoriaPersonaje = rutaHistoriaPersonaje;
		this.contadorEnergia = energiaInicial;
		this.estadosPersonaje = new ArrayList<CartaEstado>();
		this.inventario = new ArrayList<Carta>();
		this.numeroCartaPosicionado = "010";
	}

	/**
	 * Funci�n p�blica en el cual se cargan de BBDD las cartas de estado que puede
	 * tener el personaje.
	 */
	public void cargaCartasEstado() {
		Statement smt = UtilsDB.conectarBD();

		try {
			ResultSet cursorCartaEstado = smt
					.executeQuery("select id, numeroCarta, rutaImagen, textoCarta, textoEstado from cartasEstado");

			while (cursorCartaEstado.next()) {
				int id = cursorCartaEstado.getInt("id");
				String numeroCarta = cursorCartaEstado.getString("numeroCarta");
				String rutaImagen = cursorCartaEstado.getString("rutaImagen");
				String textoCarta = cursorCartaEstado.getString("textoCarta");
				String textoEstado = cursorCartaEstado.getString("textoEstado");

				CartaEstado cartaEstado = new CartaEstado(id, numeroCarta, textoCarta, rutaImagen, textoEstado);
				this.estadosPersonaje.add(cartaEstado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UtilsDB.desconectarBD();
	}

	/**
	 * Funci�n p�blica en el cual se cargan de BBDD las cartas de inventario que
	 * puede tener el personaje.
	 */
	public void cargaCartasInventario() {
		Statement smt = UtilsDB.conectarBD();

		try {
			ResultSet cursorCartaInventario = smt
					.executeQuery("select id, numeroCarta, rutaImagen, textoCarta from cartasInventario");
			while (cursorCartaInventario.next()) {
				int id = cursorCartaInventario.getInt("id");
				String numeroCarta = cursorCartaInventario.getString("numeroCarta");
				String rutaImagen = cursorCartaInventario.getString("rutaImagen");
				String textoCarta = cursorCartaInventario.getString("textoCarta");

				Carta cartaInventario = new Carta(id, numeroCarta, rutaImagen, textoCarta, true);
				this.inventario.add(cartaInventario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UtilsDB.desconectarBD();
	}

	/**
	 * Getter del id del personaje
	 * 
	 * @return Nos devuelve el id del personaje
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter del id del personaje
	 * 
	 * @param id El id del personaje
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter del nombre del personaje
	 * 
	 * @return Nos devuelve el nombre del personaje
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter del nombre del personaje
	 * 
	 * @param nombre El nombre del personaje
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter de la ruta del icono del personaje
	 * 
	 * @return Nos devuelve la ruta del icono del personaje
	 */
	public String getRutaIconoPersonaje() {
		return rutaIconoPersonaje;
	}

	/**
	 * Setter de la ruta del icono del Personaje
	 * 
	 * @param rutaIconoPersonaje La ruta del icono del personaje
	 */
	public void setRutaIconoPersonaje(String rutaIconoPersonaje) {
		this.rutaIconoPersonaje = rutaIconoPersonaje;
	}

	/**
	 * Getter de la ruta de la imagen de la historia del personaje
	 * 
	 * @return Nos devuelde la ruta de la imagen de la historia del personaje
	 */
	public String getRutaImagenHistoria() {
		return rutaImagenHistoria;
	}

	/**
	 * Setter de la ruta de la imagen de la historia del personaje
	 * 
	 * @param rutaImagenHistoria La ruta de la imagen de la historia del personaje
	 */
	public void setRutaImagenHistoria(String rutaImagenHistoria) {
		this.rutaImagenHistoria = rutaImagenHistoria;
	}

	public String getRutaHistoriaPersonaje() {
		return rutaHistoriaPersonaje;
	}

	public void setRutaHistoriaPersonaje(String rutaHistoriaPersonaje) {
		this.rutaHistoriaPersonaje = rutaHistoriaPersonaje;
	}

	/**
	 * Getter de la habilidad que tiene el personaje
	 * 
	 * @return Nos devuelve la habilidad del personaje
	 */
	public TipoAccion getHabilidad() {
		return habilidad;
	}

	/**
	 * Setter de la habilidad que tiene el personaje
	 * 
	 * @param habilidad La habilidad del personaje
	 */
	public void setHabilidad(TipoAccion habilidad) {
		this.habilidad = habilidad;
	}

	/**
	 * Getter del n�mero de la carta donde se va a situar el personaje
	 * 
	 * @return Nos devuelve el n�mero de la carta donde se va a situar el personaje
	 */
	public String getNumeroCartaPosicionado() {
		return numeroCartaPosicionado;
	}

	/**
	 * Setter del n�mero de carta donde se va a situar el personaje
	 * 
	 * @param numeroCartaPosicionado La carta donde se va a situar el personaje
	 */
	public void setNumeroCartaPosicionado(String numeroCartaPosicionado) {
		this.numeroCartaPosicionado = numeroCartaPosicionado;
	}

	/**
	 * Getter del inventario del personaje
	 * 
	 * @return Nos devuelve el inventario que tiene el personaje
	 */
	public ArrayList<Carta> getInventario() {
		return inventario;
	}

	/**
	 * Setter del inventario del personaje
	 * 
	 * @param inventario El inventario del personaje
	 */
	public void setInventario(ArrayList<Carta> inventario) {
		this.inventario = inventario;
	}

	/**
	 * Getter del contador de la energ�a que tiene el personaje
	 * 
	 * @return Nos devuelve la energ�a que tiene el personaje
	 */
	public short getContadorEnergia() {
		return contadorEnergia;
	}

	/**
	 * Setter del contador de la energ�a que tiene el personaje
	 * 
	 * @param contadorEnergia El contador de energ�a del personaje
	 */
	public void setContadorEnergia(short contadorEnergia) {
		this.contadorEnergia = contadorEnergia;
	}

	/**
	 * Funci�n p�blica que reestablece el contador de energ�a del personaje a la
	 * energ�a que tiene inicialmente al iniciar el juego
	 */
	public void restablecerEnergia() {
		this.contadorEnergia = this.energiaInicial;
	}

	/**
	 * Funci�n p�blica que devuelve el coste de energ�a extra (o de menos)
	 * dependiendo de las cartas de estado que tenga el personaje (esto har�a que le
	 * costase m�s energ�a realizar las acciones, porque tener estados es una
	 * consecuencia negativa para el personaje) y dependiendo de la habilidad que
	 * tenga el personaje (por ejemplo, si el personaje tiene la habilidad de
	 * 'escalar', las acciones de ese tipo le va a costar 1 menos de enetg�a).
	 * 
	 * @param accion La acci�n de la que se quiere saber el coste
	 * @return Nos devuelve el coste de energ�a que le costar�a al personaje
	 *         realizar la acci�n.
	 */
	public short dameCosteModificado(Accion accion) {
		short costeModificado = 0;
		for (CartaEstado cartaEstado : estadosPersonaje) {
			if (cartaEstado.estaEnMesa()) {
				costeModificado += 1;
			}
		}
		if (this.habilidad == accion.getTipoAccion()) {
			costeModificado -= 1;
		}

		return costeModificado;
	}

	/**
	 * Funci�n p�blica que nos devuelve el n�mero de cartas de engranaje que posee
	 * el personaje en su inventario.
	 * 
	 * @return Nos devuelve el n�mero de engranajes que tiene el personaje.
	 */
	public int dameNumeroDeEngranajes() {
		ArrayList<Carta> cartasInventario = this.getInventario();
		int contadorCartasEngranaje = 0;
		for (Carta cartaInventario : cartasInventario) {
			if (cartaInventario.estaEnMesa() && cartaInventario.getNumeroCarta().equals("016")) {
				contadorCartasEngranaje += 1;
			}
			if (cartaInventario.estaEnMesa() && cartaInventario.getNumeroCarta().equals("032")) {
				contadorCartasEngranaje += 1;
			}
		}

		return contadorCartasEngranaje;
	}

	/**
	 * Getter de los estados del personaje
	 * 
	 * @return Nos devuelve los estados del personaje
	 */
	public ArrayList<CartaEstado> getEstadosPersonaje() {
		return estadosPersonaje;
	}

	/**
	 * Setter de los estados del personaje
	 * 
	 * @param estadosPersonaje Los estados del personaje
	 */
	public void setEstadosPersonaje(ArrayList<CartaEstado> estadosPersonaje) {
		this.estadosPersonaje = estadosPersonaje;
	}

	/**
	 * Funci�n p�blica que dependiendo del coste de acci�n de cada carta, reduce esa
	 * cantidad de energ�a al personaje
	 * 
	 * @param accion La acci�n que va a realizar el personaje
	 */
	public void reduceEnergia(Accion accion) {
		short costeModificado = this.dameCosteModificado(accion);
		short costeEnergia = accion.getCosteAccion();
		short costeTotal = (short) (costeModificado + costeEnergia);
		if (costeTotal < 0) {
			costeTotal = 0;
		}
		this.contadorEnergia -= (costeTotal);

	}

	/**
	 * Funci�n p�blica que aumenta X cantidad de energ�a al personaje
	 * 
	 * @param energia La cantidad de energ�a que va a aumentar el personaje
	 */
	public void aumentaEnergia(short energia) {
		this.contadorEnergia += energia;
	}

	/**
	 * Funci�n toString de la clase personaje, que nos va a devolver una String con
	 * el nombre y la carta donde est� situado el personaje
	 */
	public String toString() {
		return "Nuestro personaje " + this.getNombre() + " est� en la carta n�mero " + this.getNumeroCartaPosicionado();
	}

}
