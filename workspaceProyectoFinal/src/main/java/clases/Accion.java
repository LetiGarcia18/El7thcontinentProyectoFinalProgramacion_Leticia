package clases;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import enums.TipoAccion;
import utils.UtilsDB;

public class Accion {
	private int id;
	private TipoAccion tipoAccion;
	private String descripcion;
	private short costeAccion;
	private short dificultadAccion;
	private String numeroCarta;
	private ArrayList<Consecuencia> consecuenciasPositivas;
	private ArrayList<Consecuencia> consecuenciasNegativas;
	
	
	public Accion(int id, TipoAccion tipoAccion, String descripcion, short costeAccion, short dificultadAccion, String numeroCarta) {
		this.id = id;
		this.tipoAccion = tipoAccion;
		this.descripcion = descripcion;
		this.costeAccion = costeAccion;
		this.dificultadAccion = dificultadAccion;
		this.numeroCarta = numeroCarta;
		this.consecuenciasPositivas = new ArrayList<Consecuencia>();
		this.consecuenciasNegativas = new ArrayList<Consecuencia>();
		
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
	
	
	public ArrayList<Consecuencia> getConsecuenciasPositivas() {
		return consecuenciasPositivas;
	}

	public void agregaConsecuenciaPositiva(Consecuencia consecuencia) {
		this.consecuenciasPositivas.add(consecuencia);
	}

	public ArrayList<Consecuencia> getConsecuenciasNegativas() {
		return consecuenciasNegativas;
	}

	public void agregaConsecuenciaNegativa(Consecuencia consecuencia) {
		this.consecuenciasNegativas.add(consecuencia);
	}

	@Override
	public String toString() {
		String consecuenciasPositivasString = "";
		for (int i = 0; i < consecuenciasPositivas.size(); i++){
			Consecuencia consecuencia = consecuenciasPositivas.get(i);
			consecuenciasPositivasString += consecuencia.toString() + "\t";
		}
		String consecuenciasNegativasString = "";
		for (int i = 0; i < consecuenciasNegativas.size(); i++){
			Consecuencia consecuencia = consecuenciasNegativas.get(i);
			consecuenciasNegativasString += consecuencia.toString() + "\t";
		}
		return "[" + id + "][" + tipoAccion + "] '" + descripcion + "'" + "\n" + "Consecuencias positivas: " 
		+ consecuenciasPositivasString+ "\n" + "Consecuencias negativas: " 
		+ consecuenciasNegativasString;
	}


	
	

}
