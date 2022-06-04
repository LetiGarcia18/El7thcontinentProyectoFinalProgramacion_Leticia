package clases;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import utils.UtilsDB;

public class Personaje {
	
	private String nombre;
	private String numeroCartaPosicionado;
	private ArrayList<Carta> inventario;
	private ArrayList<CartaEstado> estadosPersonaje;
	private short contadorEnergia;
	private String rutaImagen;
	private short energiaInicial = 10;


	public Personaje(String nombre, String numeroCartaPosicionado, String rutaImagen) {
		this.nombre = nombre;
		this.numeroCartaPosicionado = numeroCartaPosicionado;
		this.contadorEnergia = energiaInicial;
		this.rutaImagen = rutaImagen;
		this.estadosPersonaje = new ArrayList<CartaEstado>();
	}
	
	public void cargaCartasEstado() {
		Statement smt = UtilsDB.conectarBD();

		try {
			ResultSet cursorCartaEstado = smt
					.executeQuery("select id, numeroCarta, rutaImagen from cartasEstado");

			while (cursorCartaEstado.next()) {
				int id = cursorCartaEstado.getInt("id");
				String numeroCarta = cursorCartaEstado.getString("numeroCarta");
				String rutaImagen = cursorCartaEstado.getString("rutaImagen");

				CartaEstado cartaEstado = new CartaEstado(id, numeroCarta, rutaImagen);
				this.estadosPersonaje.add(cartaEstado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UtilsDB.desconectarBD();
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	
	public ArrayList<CartaEstado> getEstadosPersonaje() {
		return estadosPersonaje;
	}

	public void setEstadosPersonaje(ArrayList<CartaEstado> estadosPersonaje) {
		this.estadosPersonaje = estadosPersonaje;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	
	public void reduceEnergia(Accion accion) {
		short costeEnergia = accion.getCosteAccion();
		this.contadorEnergia -= costeEnergia;
		
	}
	
	public void aumentaEnergia(short energia) {
		this.contadorEnergia += energia;
	}

	public String toString() {
		return "Nuestro personaje " + this.getNombre() + " está en la carta número " + this.getNumeroCartaPosicionado();
	}
	
}
