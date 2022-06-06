package clases;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import enums.TipoAccion;
import utils.UtilsDB;

public class Personaje {
	
	private int id;
	private String nombre;
	private String numeroCartaPosicionado = "010";
	private ArrayList<Carta> inventario;
	private ArrayList<CartaEstado> estadosPersonaje;
	private short contadorEnergia;
	private String rutaIconoPersonaje;
	private String rutaImagenHistoria;
	private short energiaInicial = 100;
	private TipoAccion habilidad; //Crear getter (si resuelve la accion de la que nuestro personaje es mejor, sumarle +1 cuando ese personaje resuelva ese tipo de acción


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
	
	

	public int getId() {
		return id;
	}

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
