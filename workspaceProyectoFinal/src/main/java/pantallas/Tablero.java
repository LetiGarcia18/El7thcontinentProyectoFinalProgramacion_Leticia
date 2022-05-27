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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import clases.Accion;
import clases.CartaTerreno;
import clases.Personaje;
import enums.TipoAccion;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

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
import java.awt.GridBagConstraints;

public class Tablero extends JPanel {

	// private CartaTerreno[][] terrenos;

	private Ventana ventana;
	private int margenDerecho = 20;
	ArrayList<CartaTerreno> cartasTerreno;
	Personaje personaje;

	public Tablero(Ventana v) {

		this.ventana = v;
		setLayout(null);

		JLabel labelTitulo = new JLabel("Mi mapa beta");
		labelTitulo.setBounds(0, 0, 140, 19);
		labelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(labelTitulo);

		JLabel labelPersonaje = new JLabel("Character");
		labelPersonaje.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPersonaje.setIcon(new ImageIcon("iconos\\duck.png"));
		labelPersonaje.setBounds(1050, 20, 200, 50);
		labelPersonaje.setFont(new Font("Sylfaen", Font.PLAIN, 27));
		add(labelPersonaje);

		JButton botonAccion = new JButton("Ok");
		/*botonAccion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla("registro"); //Con esto ya podemos cambiar a otra pantalla dándole al botón de registro
			}
		});*/
		
		
		cartasTerreno = dameCartasTerreno();
		for (CartaTerreno cartaTerreno : cartasTerreno) {
			System.out.println(cartaTerreno);
		}

		int anchoCasilla = 200;
		int margenIzquierdo = 20;
		int margenSuperior = 40;
		personaje = new Personaje("Peter", 2, (short) 100, "cuadrados/personaje.png");
		dibujaEnMapaPersonaje(personaje, cartasTerreno.get(0), anchoCasilla, margenIzquierdo, margenSuperior);
		dibujaTerrenos(cartasTerreno, anchoCasilla, margenIzquierdo, margenSuperior);
		dibujarAcciones(cartasTerreno.get(0));
		

		// Test dibujar fondo
		/*
		 * JLabel imagen = new JLabel(new ImageIcon("cuadrados/grey.jpg")); int
		 * posicionX = margenIzquierdo; int posicionY = margenSuperior;
		 * imagen.setBounds(posicionX, posicionY, anchoCasilla * 3, anchoCasilla * 3);
		 * add(imagen);
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
		
		for (CartaTerreno carta : cartasTerreno) {
			carta.cargarAccionesTerreno();
		}

		return cartasTerreno;
	}

	public void dibujaTerrenos(ArrayList<CartaTerreno> cartasTerreno, int anchoCasilla, int margenIzquierdo,
			int margenSuperior) {
		for (int i = 0; i < cartasTerreno.size(); i++) {
			CartaTerreno carta = cartasTerreno.get(i);
			int posicionX = (carta.getPosicionX() * anchoCasilla) + margenIzquierdo;
			int posicionY = (carta.getPosicionY() * anchoCasilla) + margenSuperior;
			dibujaTerreno(carta, posicionX, posicionY, anchoCasilla);
		}
	}
	
	public void dibujaTerreno(CartaTerreno carta, int posicionX, int posicionY, int anchoCasilla) {
		JLabel imagen = new JLabel(new ImageIcon(carta.getRutaCarta()));
		imagen.setBounds(posicionX, posicionY, anchoCasilla, anchoCasilla);
		add(imagen);
	}
	
	public void dibujaEnMapaPersonaje(Personaje personaje, CartaTerreno carta, int anchoCasilla, int margenIzquierdo, int margenSuperior) {
		int posicionX = (carta.getPosicionX() * anchoCasilla) + margenIzquierdo;
		int posicionY = (carta.getPosicionY()* anchoCasilla) + margenSuperior;
		dibujaPersonaje(personaje, posicionX, posicionY, anchoCasilla);
	}
	
	
	public void dibujaPersonaje(Personaje personaje, int posicionX, int posicionY, int anchoCasilla) {
		JLabel imagen = new JLabel(new ImageIcon(personaje.getRutaCarta()));
		imagen.setBounds(posicionX, posicionY, anchoCasilla, anchoCasilla);
		add(imagen);
		
		
	}
	
	
	public void dibujarAcciones(CartaTerreno carta) {
		HashMap<Integer, Accion> acciones = carta.getAccionesTerreno();
		int posicionY = 600;
		int altoBoton = 35;
		int anchoBoton = 130;
		int margenEntreBotones = 40;
		Iterator iterador = acciones.keySet().iterator(); 
		while(iterador.hasNext()) {
			int key = (Integer)iterador.next();
			Accion accion = acciones.get(key);
			posicionY = posicionY + margenEntreBotones;
			dibujarAccion(accion, posicionY, anchoBoton, altoBoton) ;
		}
	}
	
	public void dibujarAccion(Accion accion, int posicionY, int anchoBoton, int altoBoton) {
		JButton botonAccion = new JButton();
		int posicionX = 1010;
		int anchoJComboBox = 150;
		int altoJComboBox = 25;
		final TipoAccion tipoAccion = accion.getTipoAccion();
		botonAccion.setText(tipoAccion.toString());
		botonAccion.setBounds(posicionX, posicionY, anchoBoton, altoBoton);
		botonAccion.setToolTipText(accion.getDescripcion());
		botonAccion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showConfirmDialog(ventana, "You clicked the action button " + tipoAccion.toString() + ". Are you sure about this decision?", "TESTING", JOptionPane.YES_NO_CANCEL_OPTION);
			}
		});
		if (tipoAccion == TipoAccion.MOVE) {
			JComboBox numeroCartaMoverse = new JComboBox();
			for (int i = 0; i < cartasTerreno.size(); i++) {
				short numeroCarta = cartasTerreno.get(i).getNumeroCarta();
				numeroCartaMoverse.addItem(numeroCarta);
			}
			numeroCartaMoverse.setBounds(posicionX + 170, posicionY, anchoJComboBox, altoJComboBox);
			
			add(numeroCartaMoverse);
		}
		
			
			

		add(botonAccion);
			
	}
		
	
}
