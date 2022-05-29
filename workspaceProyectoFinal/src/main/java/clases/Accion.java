package clases;

import java.sql.SQLException;
import java.sql.Statement;

import enums.TipoAccion;
import utils.UtilsDB;

public class Accion {
	private int id;
	private TipoAccion tipoAccion;
	private String descripcion;
	private short costeAccion;
	private short dificultadAccion;
	private int carta_id;
	
	
	public Accion(int id, TipoAccion tipoAccion, String descripcion, short costeAccion, short dificultadAccion, int carta_id) {
		this.id = id;
		this.tipoAccion = tipoAccion;
		this.descripcion = descripcion;
		this.costeAccion = costeAccion;
		this.dificultadAccion = dificultadAccion;
		this.carta_id = carta_id;
		
	}
	
	public int getId() {
		return this.id;
	}
	
	public TipoAccion getTipoAccion() {
		return this.tipoAccion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	
	public short getCosteAccion() {
		return costeAccion;
	}

	public void setCosteAccion(short costeAccion) {
		this.costeAccion = costeAccion;
	}

	public short getDificultadAccion() {
		return dificultadAccion;
	}

	public void setDificultadAccion(short dificultadAccion) {
		this.dificultadAccion = dificultadAccion;
	}

	@Override
	public String toString() {
		return "[" + id + "][" + tipoAccion + "] '" + descripcion + "'";
	}


	
	

}
