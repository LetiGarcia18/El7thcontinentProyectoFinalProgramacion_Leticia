package clases;

import java.util.ArrayList;

public class Personaje {
	
	private String nombre;
	private int numeroCartaPosicionado;
	private ArrayList<CartaObjeto> inventario;
	private short contadorEnergia;

	public Personaje(String nombre, int numeroCartaPosicionado, short contadorEnergia) {
		this.nombre = nombre;
		this.numeroCartaPosicionado = numeroCartaPosicionado;
		this.contadorEnergia = contadorEnergia;
	}
	


}
