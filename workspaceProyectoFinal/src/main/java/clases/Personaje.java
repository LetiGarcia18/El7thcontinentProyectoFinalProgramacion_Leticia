package clases;

import java.util.ArrayList;

public class Personaje {
	
	private String nombre;
	private int numeroCartaPosicionado;
	private ArrayList<CartaObjeto> inventario;
	private ArrayList<CartaEstados> estadosPersonaje;
	private short contadorEnergia;

	public Personaje(String nombre, int numeroCartaPosicionado, short contadorEnergia) {
		this.nombre = nombre;
		this.numeroCartaPosicionado = numeroCartaPosicionado;
		this.contadorEnergia = contadorEnergia;
		
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
	
	
	


}
