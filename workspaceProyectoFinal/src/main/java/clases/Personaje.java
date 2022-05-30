package clases;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Personaje {
	
	private String nombre;
	private short numeroCartaPosicionado;
	private ArrayList<CartaObjeto> inventario;
	private ArrayList<CartaEstado> estadosPersonaje;
	private short contadorEnergia;
	private String rutaImagen;


	public Personaje(String nombre, short numeroCartaPosicionado, short contadorEnergia, String rutaImagen) {
		this.nombre = nombre;
		this.numeroCartaPosicionado = numeroCartaPosicionado;
		this.contadorEnergia = contadorEnergia;
		this.rutaImagen = rutaImagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public short getNumeroCartaPosicionado() {
		return numeroCartaPosicionado;
	}

	public void setNumeroCartaPosicionado(short numeroCartaPosicionado) {
		this.numeroCartaPosicionado = numeroCartaPosicionado;
	}

	public ArrayList<CartaObjeto> getInventario() {
		return inventario;
	}

	public void setInventario(ArrayList<CartaObjeto> inventario) {
		this.inventario = inventario;
	}

	public short getContadorEnergia() {
		return contadorEnergia;
	}

	public void setContadorEnergia(short contadorEnergia) {
		this.contadorEnergia = contadorEnergia;
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

	public String toString() {
		return "Nuestro personaje " + this.getNombre() + " está en la carta número " + this.getNumeroCartaPosicionado();
	}
	
	
	


}
