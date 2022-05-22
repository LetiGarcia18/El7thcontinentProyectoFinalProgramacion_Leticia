package clases;

import java.sql.SQLException;
import java.sql.Statement;

import enums.TipoAccion;
import utils.UtilsDB;

public class Accion {
	private int id;
	private TipoAccion tipoAccion;
	private String descripcion;
	private int carta_id;
	
	
	public Accion(int id, TipoAccion tipoAccion, String descripcion, int carta_id) {
		this.id = id;
		this.tipoAccion = tipoAccion;
		this.descripcion = descripcion;
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
	
	
	@Override
	public String toString() {
		return "[" + id + "][" + tipoAccion + "] '" + descripcion + "'";
	}


	
	

}
