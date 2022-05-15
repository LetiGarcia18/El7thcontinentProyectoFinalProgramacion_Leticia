package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class UtilsDB {
	private static final String cadenaConexion = "jdbc:mysql://127.0.0.1:3306/the7thContinent";
	private static final String usuarioBBDD = "root";
	private static final String contraseniaDDBB = "Midna1993";
	private static Connection conexion;

	public static Statement conectarBD() { // Statement es el objeto que va a permitir hacer consultas. Modificar,
											// borrar, añadir...
		try {
			if (conexion == null) {
				conexion = DriverManager.getConnection(cadenaConexion, usuarioBBDD, contraseniaDDBB);
			}
			return conexion.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("No se ha podido conectar. Intentar más tarde");
			return null;
		}

	}

	public static void desconectarBD() {
		if (conexion != null) {
			try {
				conexion.close();
				conexion = null;
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("No se ha podido desconectar");
			}
		}
	}

}
