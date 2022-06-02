package clases;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import enums.TipoAccion;
import enums.TipoConsecuencia;
import utils.UtilsDB;

public class CartaTerreno {

	private int id;
	private String rutaCarta;
	private short numeroCarta;
	private byte posicionX;
	private byte posicionY;
	private HashMap<Integer, Accion> accionesTerreno;
	//private String rutaCartaQueTeTraeLaAccion;


	
	
	public CartaTerreno(int id, String rutaCarta, short numeroCarta, byte posicionX, byte posicionY) {
		this.id = id;
		this.rutaCarta = rutaCarta;
		this.numeroCarta = numeroCarta;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.accionesTerreno = new HashMap<Integer, Accion>();
		
		
	}
	
	public CartaTerreno(int id) throws SQLException {
		Statement query = UtilsDB.conectarBD();
		ResultSet cursor = query.executeQuery("select * from cartaTerreno where id = '" + id + "'");
		if(cursor.next()) {
			this.id = cursor.getInt("id");
			this.rutaCarta = cursor.getString("ruta");
			this.numeroCarta = cursor.getShort("numeroCarta");
			this.posicionX = cursor.getByte("posicionX");
			this.posicionY = cursor.getByte("posicionY");
		}else {
			throw new SQLException("No se ha podido consultar");
		}
		UtilsDB.desconectarBD();

		
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
	
	
	public HashMap<Integer, Accion> getAccionesTerreno() {
		return accionesTerreno;
	}

	public void cargarAccionesTerreno() {
		Statement smt = UtilsDB.conectarBD();
		HashMap<Integer, Accion> ret = new HashMap<Integer, Accion>();
		
		try {
			ResultSet cursor = smt.executeQuery("select * from accion where carta_id = '" + this.id + "'");
			while(cursor.next()) {
				
				int id = cursor.getInt("id");
				String tipoAccion = cursor.getString("tipo");
				String descripcion = cursor.getString("descripcion");
				short costeAccion = cursor.getShort("costeAccion");
				short dificultadAccion = cursor.getShort("dificultadAccion");
				int carta_id = cursor.getInt("carta_id");
				
				Accion actual = new Accion(id, TipoAccion.valueOf(tipoAccion), descripcion, costeAccion, dificultadAccion, carta_id);
				ret.put(actual.getId(), actual);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Iterator iterador = ret.keySet().iterator(); 
		while(iterador.hasNext()) {
			int key = (Integer)iterador.next();
			Accion accion = ret.get(key);
			
			// CARGAR CONSECUENCIAS PARA LA ACCION (buenas y/o malas)
			
			try {
				ResultSet cursorConsecuencias;
				cursorConsecuencias = smt.executeQuery("select * from consecuencia where accion_id = '" + accion.getId() + "'");
				while(cursorConsecuencias.next()) {
					int idConsecuencia = cursorConsecuencias.getInt("id");
					String tipoConsecuencia = cursorConsecuencias.getString("tipo");
					int accion_id = cursorConsecuencias.getInt("accion_id");
					byte esPositiva = cursorConsecuencias.getByte("esPositiva");
					int cartaObjetivo = cursorConsecuencias.getInt("cartaObjetivo");
					
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
		
		this.accionesTerreno = ret;
		
		
		
	}
	
	public String toString() {
		String accionesTexto = "";
		Iterator<Integer> iterador = this.accionesTerreno.keySet().iterator();
		while(iterador.hasNext()) {
			Integer key = (Integer)iterador.next();
			Accion accion = this.accionesTerreno.get(key);
			accionesTexto += accion.toString() + "\t";
		}
		return "[" + this.numeroCarta + "] ruta: " + this.rutaCarta + " Acciones: " + accionesTexto;
	}


}
