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

public class CartaTerreno extends CartaEnMapa {
	
	
	public CartaTerreno(int id, String rutaImagen, String numeroCarta, int posicionX, int posicionY) {
		super(id, numeroCarta, rutaImagen, posicionX, posicionY);		
		
	}
		
	
}
