package clases;

import enums.TipoConsecuencia;

public class Consecuencia {

	private int id;
	private TipoConsecuencia tipoConsecuencia;
	private byte esPositiva;
	private int accion_id;
	private String cartaObjetivo;
	
	public Consecuencia(int id, TipoConsecuencia tipoConsecuencia, int accion_id, byte esPositiva, String cartaObjetivo) {
		this.id = id;
		this.tipoConsecuencia = tipoConsecuencia;
		this.esPositiva = esPositiva;
		this.accion_id = accion_id;
		this.cartaObjetivo = cartaObjetivo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoConsecuencia getTipoConsecuencia() {
		return tipoConsecuencia;
	}

	public void setTipoConsecuencia(TipoConsecuencia tipoConsecuencia) {
		this.tipoConsecuencia = tipoConsecuencia;
	}

	public byte getEsPositiva() {
		return esPositiva;
	}

	public void setEsPositiva(byte esPositiva) {
		this.esPositiva = esPositiva;
	}

	public int getAccion_id() {
		return accion_id;
	}

	public void setAccion_id(int accion_id) {
		this.accion_id = accion_id;
	}

	public String getCartaObjetivo() {
		return cartaObjetivo;
	}

	public void setCartaObjetivo(String cartaObjetivo) {
		this.cartaObjetivo = cartaObjetivo;
	}

	@Override
	public String toString() {
		return "Consecuencia: id: " + id + ", tipoConsecuencia: " + tipoConsecuencia + ", esPositiva: " + esPositiva
				+ ", accion_id: " + accion_id + ", cartaObjetivo: " + cartaObjetivo;
	}
	
	
	
	
}
