package clases;

import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import utils.UtilsDB;

public class CartaTerreno {

	private int id;
	private String rutaCarta;
	private short numeroCarta;
	private byte posicionX;
	private byte posicionY;
	
	public CartaTerreno(int id, String rutaCarta, short numeroCarta, byte posicionX, byte posicionY) {
		this.id = id;
		this.rutaCarta = rutaCarta;
		this.numeroCarta = numeroCarta;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getRutaCarta() {
		return rutaCarta;
	}



	public void setRutaCarta(String rutaCarta) {
		this.rutaCarta = rutaCarta;
	}



	public short getNumeroCarta() {
		return numeroCarta;
	}



	public void setNumeroCarta(short numeroCarta) {
		this.numeroCarta = numeroCarta;
	}



	public byte getPosicionX() {
		return posicionX;
	}



	public void setPosicionX(byte posicionX) {
		this.posicionX = posicionX;
	}



	public byte getPosicionY() {
		return posicionY;
	}



	public void setPosicionY(byte posicionY) {
		this.posicionY = posicionY;
	}
	
	public String toString() {
		return "id: " + this.id + " ruta: " + this.rutaCarta;
	}


}
