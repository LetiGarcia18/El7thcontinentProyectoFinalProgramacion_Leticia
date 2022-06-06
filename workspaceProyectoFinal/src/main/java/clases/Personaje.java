package clases;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import enums.TipoAccion;
import utils.UtilsDB;

/**
 * Clase Personaje, en la cual va a ser posible crear al personaje, cargar sus estados y su inventario de la BBDD
 * @author Leticia
 *
 */
public class Personaje {
	/** Variable donde se almacena el id del personaje **/
	private int id;
	/** Variable que va a contener el nombre del personaje **/
	private String nombre;
	/** Variable que va a almacenar la carta donde va a comenzar posicionado el personaje. Por defecto va a empezar 
	 * siempre en la carta número 010 **/
	private String numeroCartaPosicionado = "010";
	/** ArrayList que va a almacenar todas las cartas de inventario que el personaje vaya encontrando a lo largo del juego**/
	private ArrayList<Carta> inventario;
	/** ArrayList que va a almacenar los estados de salud que el personaje vaya teniendo durante el juego **/
	private ArrayList<CartaEstado> estadosPersonaje;
	/** Variable que va a almacenar la energía del personaje **/
	private short contadorEnergia;
	/** Variable que almacena la ruta del icono del personaje **/
	private String rutaIconoPersonaje;
	/** Variable que almacena la imagen de la historia del personaje **/
	private String rutaImagenHistoria;
	/** Variable que indica la energía que va a tener inicialmente el personaje siempre que se inicie el juego **/
	private short energiaInicial = 100;
	/** Variable que va a almacenar la habilidad en la que es bueno nuestro personaje **/
	private TipoAccion habilidad; 

	/**
	 * Constructor de la clase personaje. Se le pasan por parámetros el id, el nombre, la habilidad, la ruta donde se encuentra
	 * la imagen de la historia del personaje, y la ruta del icono del mismo. En este contructor también se le asigna al contador
	 * de energía del personaje la energía que va a tener siempre inicialmente.
	 * También se inicializan los ArrayList de los estados del personaje y de su inventario.
	 * @param id Variable de tipo int que almacena el id del personaje en BBDD
	 * @param nombre Variable de tipo String que almacena el nombre del personaje.
	 * @param habilidad Variable de tipo TipoAccion que almacena la habilidad del personaje
	 * @param rutaImagenHistoria Variable que almacena la ruta de la imagen con la historia introductoria del personaje
	 * @param rutaIconoPersonaje Variable que almacena el icono del personaje
	 */
	public Personaje(int id, String nombre, TipoAccion habilidad, String rutaImagenHistoria, String rutaIconoPersonaje) {
		this.id = id;
		this.nombre = nombre;
		this.habilidad = habilidad;
		this.rutaImagenHistoria = rutaImagenHistoria;
		this.rutaIconoPersonaje = rutaIconoPersonaje;
		this.contadorEnergia = energiaInicial;
		this.estadosPersonaje = new ArrayList<CartaEstado>();
		this.inventario = new ArrayList<Carta>();
	}
	
	
	/**
	 * Método público en el cual se cargan de BBDD las cartas de estado que puede tener el personaje.
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
	 * Método público en el cual se cargan de BBDD las cartas de inventario que puede tener el personaje.
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
				
				Carta cartaInventario = new Carta(id, numeroCarta, rutaImagen, textoCarta, false);
				this.inventario.add(cartaInventario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UtilsDB.desconectarBD();
	}
	
	/**
	 * Getter del id del personaje
	 * @return Nos devuelve el id del personaje
	 */
	public int getId() {
		return id;
	}
	/**
	 * Setter del id del personaje
	 * @param id El id del personaje
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRutaIconoPersonaje() {
		return rutaIconoPersonaje;
	}

	public void setRutaIconoPersonaje(String rutaIconoPersonaje) {
		this.rutaIconoPersonaje = rutaIconoPersonaje;
	}

	public String getRutaImagenHistoria() {
		return rutaImagenHistoria;
	}

	public void setRutaImagenHistoria(String rutaImagenHistoria) {
		this.rutaImagenHistoria = rutaImagenHistoria;
	}

	public TipoAccion getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(TipoAccion habilidad) {
		this.habilidad = habilidad;
	}

	public String getNumeroCartaPosicionado() {
		return numeroCartaPosicionado;
	}

	public void setNumeroCartaPosicionado(String numeroCartaPosicionado) {
		this.numeroCartaPosicionado = numeroCartaPosicionado;
	}

	public ArrayList<Carta> getInventario() {
		return inventario;
	}

	public void setInventario(ArrayList<Carta> inventario) {
		this.inventario = inventario;
	}

	public short getContadorEnergia() {
		return contadorEnergia;
	}

	public void setContadorEnergia(short contadorEnergia) {
		this.contadorEnergia = contadorEnergia;
	}
	
	public void restablecerEnergia() {
		this.contadorEnergia = this.energiaInicial;
	}
	
	
	
	public short dameCosteModificado(Accion accion) {
		short costeModificado = 0;
		for (CartaEstado cartaEstado : estadosPersonaje) {
			if(cartaEstado.estaEnMesa()) {
				costeModificado += 1;
			}
		}
		if(this.habilidad == accion.getTipoAccion()) {
			costeModificado -= 1;
		}
	
		return costeModificado;
	}
	
	public int dameNumeroDeEngranajes() {
		ArrayList<Carta> cartasInventario = this.getInventario();
		int contadorCartasEngranaje = 0;
		for (Carta cartaInventario : cartasInventario) {
			if(cartaInventario.estaEnMesa() && cartaInventario.getNumeroCarta().equals("016")) {
				contadorCartasEngranaje += 1;
			}
			if(cartaInventario.estaEnMesa() && cartaInventario.getNumeroCarta().equals("032")) {
				contadorCartasEngranaje += 1;
			}
		}
		
		return contadorCartasEngranaje;
	}
	
	
	public ArrayList<CartaEstado> getEstadosPersonaje() {
		return estadosPersonaje;
	}

	public void setEstadosPersonaje(ArrayList<CartaEstado> estadosPersonaje) {
		this.estadosPersonaje = estadosPersonaje;
	}

	
	
	public void reduceEnergia(Accion accion) {
		short costeModificado = this.dameCosteModificado(accion);
		short costeEnergia = accion.getCosteAccion();
		short costeTotal = (short) (costeModificado + costeEnergia);
		if(costeTotal < 0) {
			costeTotal = 0;
		}
		this.contadorEnergia -= (costeTotal);
		
	}
	
	public void aumentaEnergia(short energia) {
		this.contadorEnergia += energia;
	}

	public String toString() {
		return "Nuestro personaje " + this.getNombre() + " está en la carta número " + this.getNumeroCartaPosicionado();
	}
	
}
