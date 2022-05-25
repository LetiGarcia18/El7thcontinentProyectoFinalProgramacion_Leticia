package clases;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Personaje {
	
	private String nombre;
	private int numeroCartaPosicionado;
	private ArrayList<CartaObjeto> inventario;
	private ArrayList<CartaEstado> estadosPersonaje;
	private short contadorEnergia;
	private String rutaCarta;


	public Personaje(String nombre, int numeroCartaPosicionado, short contadorEnergia, String rutaCarta) {
		this.nombre = nombre;
		this.numeroCartaPosicionado = numeroCartaPosicionado;
		this.contadorEnergia = contadorEnergia;
		this.rutaCarta = rutaCarta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroCartaPosicionado() {
		return numeroCartaPosicionado;
	}

	public void setNumeroCartaPosicionado(int numeroCartaPosicionado) {
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

	public String getRutaCarta() {
		return rutaCarta;
	}

	public void setRutaCarta(String rutaCarta) {
		this.rutaCarta = rutaCarta;
	}

		
	
	
	


}
