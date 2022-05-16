package pantallas;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.Color;
import java.awt.Canvas;
import javax.swing.JDesktopPane;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import clases.CartaTerreno;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.GridBagLayout;
import net.miginfocom.swing.MigLayout;
import utils.UtilsDB;

import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import java.awt.Button;
import javax.swing.JLabel;
import java.awt.Font;

public class Tablero extends JPanel {

	// private CartaTerreno[][] terrenos;

	private Ventana ventana;
	private int margenDerecho = 20;

	public Tablero(Ventana v) {

		setLayout(null);

		JLabel lblTitulo = new JLabel("Mi mapa beta");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitulo.setBounds(margenDerecho, 20, 150, 13);
		add(lblTitulo);
		
		
		ArrayList<CartaTerreno> cartasTerreno = dameCartasTerreno();
		
		int anchoCasilla = 100;
		int margenIzquierdo = 20;
		int margenSuperior = 40;
		dibujaTerrenos(cartasTerreno, anchoCasilla, margenIzquierdo, margenSuperior);
		
		// Test dibujar fondo
		/*
		JLabel imagen = new JLabel(new ImageIcon("cuadrados/grey.jpg"));
		int posicionX = margenIzquierdo;
		int posicionY = margenSuperior;
		imagen.setBounds(posicionX, posicionY, anchoCasilla * 3, anchoCasilla * 3); 
		add(imagen);
		*/
		
		
	}
	
	private ArrayList<CartaTerreno> dameCartasTerreno() {
		String rutaImagen = "";
		int id = 0;
		short numeroCarta = 0;
		byte posicionX = 0;
		byte posicionY = 0;
		CartaTerreno cartaTerreno;
		ArrayList<CartaTerreno> cartasTerreno = new ArrayList<CartaTerreno>();
		Statement smt = UtilsDB.conectarBD();

		try {
			ResultSet cursor = smt.executeQuery("select id, ruta, numeroCarta, posicionX, posicionY from cartaTerreno");

			while (cursor.next()) {
				id = cursor.getInt("id");
				rutaImagen = cursor.getString("ruta");
				numeroCarta = cursor.getShort("numeroCarta");
				posicionX = cursor.getByte("posicionX");
				posicionY = cursor.getByte("posicionY");
				
				cartaTerreno = new CartaTerreno(id, rutaImagen, numeroCarta, posicionX, posicionY);
				cartasTerreno.add(cartaTerreno);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UtilsDB.desconectarBD();
		
		return cartasTerreno;
	}
	
	public void dibujaTerrenos(ArrayList<CartaTerreno> cartasTerreno, int anchoCasilla, int margenIzquierdo, int margenSuperior) {
		for(int i = 0; i < cartasTerreno.size(); i ++ ) {
			CartaTerreno carta = cartasTerreno.get(i);
			JLabel imagen = new JLabel(new ImageIcon(carta.getRutaCarta()));
			int posicionX = (carta.getPosicionX() * anchoCasilla) + margenIzquierdo;
			int posicionY = (carta.getPosicionY() * anchoCasilla) + margenSuperior;
			imagen.setBounds(posicionX, posicionY, anchoCasilla, anchoCasilla); 
			add(imagen);
		}
	}
}
