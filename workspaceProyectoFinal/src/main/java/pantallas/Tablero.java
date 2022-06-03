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
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import clases.Accion;
import clases.CartaTerreno;
import clases.Consecuencia;
import clases.Personaje;
import elementosVisuales.BotonInicio;
import elementosVisuales.BotonSalir;
import enums.TipoAccion;
import enums.TipoConsecuencia;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.Image;

import net.miginfocom.swing.MigLayout;
import utils.UtilsDB;

import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import java.awt.Button;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;

public class Tablero extends JPanel {

	private Ventana ventana;
	private int margenDerecho = 20;
	ArrayList<CartaTerreno> cartasTerreno;
	Personaje personaje;
	JComboBox comboBoxNumeroCarta;
	Random random;
	private Image imagenFondo;

	public Tablero(Ventana v, ArrayList<CartaTerreno> cartasTerreno, Personaje personaje) {

		this.ventana = v;
		this.cartasTerreno = cartasTerreno;
		this.personaje = personaje;
		random = new Random();
		imagenFondo = new ImageIcon("./imagenesFondo/mar.png").getImage();

		setLayout(null);

		JButton botonSalir = new BotonSalir("Exit");
		add(botonSalir);

		JLabel labelPersonaje = new JLabel("Character");
		labelPersonaje.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPersonaje.setIcon(new ImageIcon("cartasPersonaje\\characterToken2.png"));
		labelPersonaje.setBounds(700, 20, 400, 80);
		labelPersonaje.setFont(new Font("Rockwell", Font.PLAIN, 27));
		add(labelPersonaje);

		comboBoxNumeroCarta = new JComboBox();
		add(comboBoxNumeroCarta);

		CartaTerreno cartaActual = dameCartaTerrenoConNumero(this.personaje.getNumeroCartaPosicionado());
		int anchoCasilla = 170;
		int tamanioPersonaje = anchoCasilla / 3;
		int margenIzquierdo = 0;
		int margenSuperior = 0;
		dibujaEnMapaPersonaje(cartaActual, anchoCasilla, tamanioPersonaje, margenIzquierdo, margenSuperior);
		dibujaCartasTerrenoIniciales(anchoCasilla, margenIzquierdo, margenSuperior);
		dibujarAcciones(cartaActual);

		// Test dibujar fondo
		/*
		 * JLabel imagen = new JLabel(new ImageIcon("cuadrados/grey.jpg")); int
		 * posicionX = margenIzquierdo; int posicionY = margenSuperior;
		 * imagen.setBounds(posicionX, posicionY, anchoCasilla * 3, anchoCasilla * 3);
		 * add(imagen);
		 */
		// System.out.println(cartasTerreno);
	}

	/*
	 * public void dibujaTerrenos(ArrayList<CartaTerreno> cartasTerreno, int
	 * anchoCasilla, int margenIzquierdo, int margenSuperior) { for (int i = 0; i <
	 * cartasTerreno.size(); i++) { CartaTerreno carta = cartasTerreno.get(i); int
	 * posicionX = (carta.getPosicionX() * anchoCasilla) + margenIzquierdo; int
	 * posicionY = (carta.getPosicionY() * anchoCasilla) + margenSuperior;
	 * dibujaTerreno(carta, posicionX, posicionY, anchoCasilla); } }
	 */
	
	public void dibujaCartasTerrenoIniciales(int anchoCasilla, int margenIzquierdo, int margenSuperior) {
		for (int i = 0; i < this.cartasTerreno.size(); i++) {
			CartaTerreno carta = this.cartasTerreno.get(i);
			int posicionX = (carta.getPosicionX() * anchoCasilla) + margenIzquierdo;
			int posicionY = (carta.getPosicionY() * anchoCasilla) + margenSuperior;
			dibujaTerreno(carta, posicionX, posicionY, anchoCasilla);
		}
	}

	public void dibujaTerreno(CartaTerreno carta, int posicionX, int posicionY, int anchoCasilla) {
		ImageIcon icon = new ImageIcon(carta.getRutaImagen());
		Image imagenIcon = icon.getImage();
		Image imagenIconConTamanio = imagenIcon.getScaledInstance(anchoCasilla, anchoCasilla,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(imagenIconConTamanio);
		JLabel imagen = new JLabel(icon);
		imagen.setBounds(posicionX, posicionY, anchoCasilla, anchoCasilla);
		add(imagen);

	}

	public void dibujaEnMapaPersonaje(CartaTerreno carta, int anchoCasilla, int tamanioPersonaje, int margenIzquierdo,
			int margenSuperior) {
		int posicionX = (carta.getPosicionX() * anchoCasilla) + margenIzquierdo;
		int posicionY = (carta.getPosicionY() * anchoCasilla) + margenSuperior;
		dibujaPersonaje(posicionX, posicionY, anchoCasilla, tamanioPersonaje);
	}

	public void dibujaPersonaje(int posicionXCarta, int posicionYCarta, int anchoCasilla, int tamanioPersonaje) {
		ImageIcon icon = new ImageIcon(this.personaje.getRutaImagen());
		Image imagenIcon = icon.getImage();
		Image imagenIconConTamanio = imagenIcon.getScaledInstance(tamanioPersonaje, tamanioPersonaje,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(imagenIconConTamanio);
		JLabel imagen = new JLabel(icon);
		int posicionX = posicionXCarta + (anchoCasilla / 2) - (tamanioPersonaje / 2);
		int posicionY = posicionYCarta + (anchoCasilla / 2) - (tamanioPersonaje / 2);
		imagen.setBounds(posicionX, posicionY, tamanioPersonaje, tamanioPersonaje);
		add(imagen);

		JLabel energiaPersonaje = new JLabel("Energía: " + personaje.getContadorEnergia());
		energiaPersonaje.setFont(new Font("Rockwell", Font.BOLD, 18));
		energiaPersonaje.setBounds(600, 5, 183, 29);
		add(energiaPersonaje);

	}

	private CartaTerreno dameCartaTerrenoConNumero(short numero) {
		for (int i = 0; i < cartasTerreno.size(); i++) {
			if (cartasTerreno.get(i).getNumeroCarta() == numero) {
				return cartasTerreno.get(i);
			}
		}
		return null;
	}

	public void dibujarAcciones(CartaTerreno carta) {
		HashMap<Integer, Accion> acciones = carta.getAcciones();
		int posicionY = 600;
		int altoBoton = 35;
		int anchoBoton = 130;
		int margenEntreBotones = 40;
		Iterator iterador = acciones.keySet().iterator();
		while (iterador.hasNext()) {
			int key = (Integer) iterador.next();
			Accion accion = acciones.get(key);
			posicionY = posicionY + margenEntreBotones;
			dibujarAccion(accion, posicionY, anchoBoton, altoBoton);
		}
	}

	public void dibujarAccion(final Accion accion, int posicionY, int anchoBoton, int altoBoton) {
		final JButton botonAccion = new JButton();
		JLabel labelDificultadAccion = new JLabel("Dificultad: " + accion.getDificultadAccion());
		JLabel labelCosteAccion = new JLabel("Coste: " + accion.getCosteAccion());
		int posicionX = 1200;
		int anchoJComboBox = 150;
		int altoJComboBox = 25;
		final TipoAccion tipoAccion = accion.getTipoAccion();
		labelDificultadAccion.setFont(new Font("Rockwell", Font.PLAIN, 13));
		labelDificultadAccion.setBounds(1120, posicionY, 183, 29);
		add(labelDificultadAccion);

		labelCosteAccion.setFont(new Font("Rockwell", Font.PLAIN, 13));
		labelCosteAccion.setBounds(1060, posicionY, 183, 29);
		add(labelCosteAccion);

		botonAccion.setText(tipoAccion.toString());
		botonAccion.setFont(new Font("Rockwell", Font.PLAIN, 13));
		botonAccion.setBounds(posicionX, posicionY, anchoBoton, altoBoton);
		botonAccion.setToolTipText(accion.getDescripcion());
		botonAccion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resolverAccion(accion);
			}
		});

		if (tipoAccion == TipoAccion.MOVE) {

			short cartaPosicionPersonaje = (short) personaje.getNumeroCartaPosicionado();
			for (int i = 0; i < cartasTerreno.size(); i++) {
				short numeroCarta = cartasTerreno.get(i).getNumeroCarta();
				if (numeroCarta != cartaPosicionPersonaje) {
					comboBoxNumeroCarta.addItem(numeroCarta);
				}
			}

			comboBoxNumeroCarta.setBounds(posicionX + 170, posicionY, anchoJComboBox, altoJComboBox);
		}

		add(botonAccion);
	}

	public void resolverAccion(Accion accion) {
		personaje.reduceEnergia(accion);

		short dificultadAccion = accion.getDificultadAccion();
		short tirada = obtenerTiradaDificultad();
		TipoAccion tipoAccion = accion.getTipoAccion();

		if (tirada >= dificultadAccion) {
			// EXITO
			resolverConsecuencias(accion.getConsecuenciasPositivas());

		} else {
			// FRACASO
			JOptionPane.showMessageDialog(ventana, "Dificultad no superada con: " + tirada, "TIRADA DIFICULTAD",
					JOptionPane.INFORMATION_MESSAGE);
			resolverConsecuencias(accion.getConsecuenciasNegativas());
		}

		ventana.dibujaTablero();

		if (personaje.getContadorEnergia() <= 0) {
			JOptionPane.showMessageDialog(ventana, "Has desfallecido. Te has quedado sin energía...",
					"Fin de la partida", JOptionPane.CLOSED_OPTION);
			ventana.cambiarAPantalla("game over");
		}
	}

	public short obtenerTiradaDificultad() {
		return (short) (random.nextInt(6) + 1);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);
	}

	public void resolverConsecuencias(ArrayList<Consecuencia> consecuencias) {

		for (Consecuencia consecuencia : consecuencias) {
			TipoConsecuencia tipoConsecuencia = consecuencia.getTipoConsecuencia();

			switch (tipoConsecuencia) {
			case DESPLAZARSE:
				short numeroCartaSeleccionado = (Short) comboBoxNumeroCarta.getSelectedItem();
				personaje.setNumeroCartaPosicionado(numeroCartaSeleccionado);
			break;
			case RESTAURAR:
				personaje.aumentaEnergia((short)12);
			break;	
			case TRAER_CARTA:
				
			break;
			case QUITAR_CARTA:
			
			break;
				

			}
		}
	}
}
