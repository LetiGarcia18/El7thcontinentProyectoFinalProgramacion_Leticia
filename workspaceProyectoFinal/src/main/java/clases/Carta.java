package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import enums.TipoAccion;
import enums.TipoConsecuencia;
import utils.UtilsDB;

public class Carta {
	
	private int id;
	private String numeroCarta;
	private HashMap<Integer, Accion> acciones;
	private String rutaImagen;
	
	
	public Carta(int id, String numeroCarta, String rutaImagen) {
		super();
		this.id = id;
		this.numeroCarta = numeroCarta;
		this.acciones = new HashMap<Integer, Accion>();
		this.rutaImagen = rutaImagen;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}




	public String getNumeroCarta() {
		return numeroCarta;
	}


	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}


	public HashMap<Integer, Accion> getAcciones() {
		return acciones;
	}


	public void setAcciones(HashMap<Integer, Accion> acciones) {
		this.acciones = acciones;
	}


	public String getRutaImagen() {
		return rutaImagen;
	}


	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	
	public void cargarAcciones() {
		Statement smt = UtilsDB.conectarBD();
		
		try {
			ResultSet cursorAcciones = smt.executeQuery("select * from accion where carta_id = '" + this.id + "'");
			while(cursorAcciones.next()) {
				
				int id = cursorAcciones.getInt("id");
				String tipoAccion = cursorAcciones.getString("tipo");
				String descripcion = cursorAcciones.getString("descripcion");
				short costeAccion = cursorAcciones.getShort("costeAccion");
				short dificultadAccion = cursorAcciones.getShort("dificultadAccion");
				int carta_id = cursorAcciones.getInt("carta_id");
				
				Accion actual = new Accion(id, TipoAccion.valueOf(tipoAccion), descripcion, costeAccion, dificultadAccion, carta_id);
				acciones.put(actual.getId(), actual);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Iterator iterador = acciones.keySet().iterator(); 
		while(iterador.hasNext()) {
			int key = (Integer)iterador.next();
			Accion accion = acciones.get(key);
			
			// CARGAR CONSECUENCIAS PARA LA ACCION (buenas y/o malas)
			
			try {
				ResultSet cursorConsecuencias;
				cursorConsecuencias = smt.executeQuery("select * from consecuencia where accion_id = '" + accion.getId() + "'");
				while(cursorConsecuencias.next()) {
					int idConsecuencia = cursorConsecuencias.getInt("id");
					String tipoConsecuencia = cursorConsecuencias.getString("tipo");
					int accion_id = cursorConsecuencias.getInt("accion_id");
					byte esPositiva = cursorConsecuencias.getByte("esPositiva");
					String cartaObjetivo = cursorConsecuencias.getString("cartaObjetivo");
					
					Consecuencia consecuenciaActual = new Consecuencia(idConsecuencia, TipoConsecuencia.valueOf(tipoConsecuencia), (byte) accion_id, esPositiva, cartaObjetivo);
					
					if(esPositiva == 1) {
						accion.agregaConsecuenciaPositiva(consecuenciaActual);
					}else {
						accion.agregaConsecuenciaNegativa(consecuenciaActual);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		UtilsDB.desconectarBD();
		
		
		
	}
	


	public String toString() {
		String accionesTexto = "";
		Iterator<Integer> iterador = this.acciones.keySet().iterator();
		while(iterador.hasNext()) {
			Integer key = (Integer)iterador.next();
			Accion accion = this.acciones.get(key);
			accionesTexto += accion.toString() + "\t";
		}
		return "[" + this.numeroCarta + "] ruta: " + this.rutaImagen + " Acciones: " + accionesTexto;
	}
	
	
	
	

}
