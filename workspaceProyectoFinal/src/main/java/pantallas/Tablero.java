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
import clases.Carta;
import clases.CartaEnMapa;
import clases.CartaEstado;
import clases.CartaEvento;
import clases.CartaTerreno;
import clases.Consecuencia;
import clases.Personaje;
import elementosVisuales.BotonComun;
import enums.TipoAccion;
import enums.TipoConsecuencia;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Toolkit;
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
	private ArrayList<CartaEnMapa> cartasEnMapa;
	private Personaje personaje;
	private JComboBox comboBoxNumeroCarta;
	private Random random;
	private Image imagenFondo;
	private boolean hasGanado = false;

	int altoBoton = 35;
	int anchoBoton = 130;
	int margenEntreBotones = 40;
	int posicionYBotones = 470;

	public Tablero(Ventana v, ArrayList<CartaEnMapa> cartasEnMapa, Personaje personaje) {

		this.ventana = v;
		this.cartasEnMapa = cartasEnMapa;
		this.personaje = personaje;
		random = new Random();
		imagenFondo = new ImageIcon("./imagenesFondo/mar.png").getImage();

		setLayout(null);

		JLabel labelPersonaje = new JLabel("Character: " + personaje.getNombre());
		labelPersonaje.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPersonaje.setIcon(new ImageIcon("cartasPersonaje\\characterToken2.png"));
		labelPersonaje.setBounds(950, 0, 400, 65);
		labelPersonaje.setFont(new Font("Rockwell", Font.BOLD, 30));
		add(labelPersonaje);
		
		JLabel labelInventario = new JLabel(" _______________________ Inventory ____________________________");
		labelInventario.setHorizontalAlignment(SwingConstants.RIGHT);
		labelInventario.setIcon(new ImageIcon("./iconos/mochila.png"));
		labelInventario.setBounds(750, 260, 800, 80);
		labelInventario.setFont(new Font("Rockwell", Font.BOLD, 20));
		add(labelInventario);
		
		JLabel labelEstados = new JLabel(" __________________________ Status _____________________________");
		labelEstados.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEstados.setIcon(new ImageIcon("./iconos/estado.png"));
		labelEstados.setBounds(750, 60, 800, 80);
		labelEstados.setFont(new Font("Rockwell", Font.BOLD, 20));
		add(labelEstados);
		
		JLabel labelSeparador = new JLabel("_____________________________________________________________________");
		labelSeparador.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSeparador.setBounds(600, 445, 1000, 80);
		labelSeparador.setFont(new Font("Rockwell", Font.PLAIN, 20));
		add(labelSeparador);
		
		JLabel labelAccionesCarta = new JLabel("Card actions: ");
		labelAccionesCarta.setHorizontalAlignment(SwingConstants.RIGHT);
		labelAccionesCarta.setBounds(200, 500, 800, 80);
		labelAccionesCarta.setFont(new Font("Rockwell", Font.BOLD, 20));
		add(labelAccionesCarta);

		comboBoxNumeroCarta = new JComboBox();
		add(comboBoxNumeroCarta);

		CartaEnMapa cartaActual = dameCartaEnMapaConNumero(this.personaje.getNumeroCartaPosicionado());

		int anchoCasilla = 170;
		int tamanioPersonaje = anchoCasilla / 3;
		int margenIzquierdo = 0;
		int margenSuperior = 0;
		dibujaEnMapaPersonaje(cartaActual, anchoCasilla, tamanioPersonaje, margenIzquierdo, margenSuperior);
		dibujaCartas(anchoCasilla, margenIzquierdo, margenSuperior);
		dibujarAcciones();

	}

	public void dibujaCartas(int anchoCasilla, int margenIzquierdo, int margenSuperior) {
		for (int i = 0; i < this.cartasEnMapa.size(); i++) {
			CartaEnMapa cartaMapa = this.cartasEnMapa.get(i);
			int posicionX = (cartaMapa.getPosicionX() * anchoCasilla) + margenIzquierdo;
			int posicionY = (cartaMapa.getPosicionY() * anchoCasilla) + margenSuperior;
			dibujaCarta(cartaMapa, posicionX, posicionY, anchoCasilla);
		}

		ArrayList<CartaEstado> cartasEstado = personaje.getEstadosPersonaje();
		int posicionXCartaEstado = 875;
		int tamanioCartaEstado = (int) (anchoCasilla / 1.5);
		for (int i = 0; i < cartasEstado.size(); i++) {
			CartaEstado cartaEstado = cartasEstado.get(i);
			if (cartaEstado.estaEnMesa()) {
				dibujaCarta(cartaEstado, posicionXCartaEstado, 150, tamanioCartaEstado);
				posicionXCartaEstado += tamanioCartaEstado + 10;
			}
		}

		ArrayList<Carta> cartasInventario = personaje.getInventario();
		int posicionXCartaInventario = 875;
		int tamanioCartaInventario = (int) (anchoCasilla);
		for (Carta cartaInventario : cartasInventario) {
			if (cartaInventario.estaEnMesa()) {
				dibujaCarta(cartaInventario, posicionXCartaInventario, 325, tamanioCartaInventario);
				posicionXCartaInventario += tamanioCartaInventario + 10;
			}
		}
	}

	public void dibujaCarta(Carta carta, int posicionX, int posicionY, int anchoCasilla) {
		if (carta.estaEnMesa()) {
			ImageIcon icon = new ImageIcon(carta.getRutaImagen());
			Image imagenIcon = icon.getImage();
			Image imagenIconConTamanio = imagenIcon.getScaledInstance(anchoCasilla, anchoCasilla,
					java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(imagenIconConTamanio);
			JLabel imagen = new JLabel(icon);
			imagen.setBounds(posicionX, posicionY, anchoCasilla, anchoCasilla);
			imagen.setToolTipText(carta.getTextoCarta());
			add(imagen);
			if (carta.getClass() == CartaEstado.class) {
				CartaEstado cartaEstado = (CartaEstado) carta;
				JLabel textoEstado = new JLabel(cartaEstado.getTituloEstado());
				textoEstado.setBounds(posicionX + 30, (int) (posicionY - anchoCasilla / 1.5), anchoCasilla,
						anchoCasilla);
				add(textoEstado);
			}
		}
	}

	public void dibujaEnMapaPersonaje(CartaEnMapa cartaTerreno, int anchoCasilla, int tamanioPersonaje,
			int margenIzquierdo, int margenSuperior) {
		int posicionX = (cartaTerreno.getPosicionX() * anchoCasilla) + margenIzquierdo;
		int posicionY = (cartaTerreno.getPosicionY() * anchoCasilla) + margenSuperior;
		dibujaPersonaje(posicionX, posicionY, anchoCasilla, tamanioPersonaje);
	}

	public void dibujaPersonaje(int posicionXCarta, int posicionYCarta, int anchoCasilla, int tamanioPersonaje) {
		ImageIcon icon = new ImageIcon(this.personaje.getRutaIconoPersonaje());
		Image imagenIcon = icon.getImage();
		Image imagenIconConTamanio = imagenIcon.getScaledInstance(tamanioPersonaje, tamanioPersonaje,
				java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(imagenIconConTamanio);
		JLabel imagen = new JLabel(icon);
		int posicionX = posicionXCarta + (anchoCasilla / 2) - (tamanioPersonaje / 2);
		int posicionY = posicionYCarta + (anchoCasilla / 2) - (tamanioPersonaje / 2);
		imagen.setBounds(posicionX, posicionY, tamanioPersonaje, tamanioPersonaje);
		add(imagen);

		JLabel energiaPersonaje = new JLabel("Energy: " + personaje.getContadorEnergia());
		energiaPersonaje.setFont(new Font("Rockwell", Font.BOLD, 30));
		energiaPersonaje.setIcon(new ImageIcon("./iconos/energy.png"));
		energiaPersonaje.setBounds(20, 5, 400, 50);
		add(energiaPersonaje);

	}

	private CartaEnMapa dameCartaEnMapaConNumero(String numeroCarta) {
		for (int i = 0; i < cartasEnMapa.size(); i++) {
			CartaEnMapa cartaEnMapa = cartasEnMapa.get(i);
			if (cartaEnMapa.getNumeroCarta().equals(numeroCarta)) {
				return cartaEnMapa;
			}
		}
		return null;
	}

	private Carta dameCartaEnPersonajeConNumero(String numeroCarta) {
		ArrayList<CartaEstado> estadosPersonaje = personaje.getEstadosPersonaje();
		for (CartaEstado cartaEstado : estadosPersonaje) {
			if (cartaEstado.getNumeroCarta().equals(numeroCarta)) {
				return cartaEstado;
			}
		}

		ArrayList<Carta> inventarioPersonaje = personaje.getInventario();
		for (Carta cartaInventario : inventarioPersonaje) {
			if (cartaInventario.getNumeroCarta().equals(numeroCarta)) {
				return cartaInventario;
			}
		}

		return null;
	}

	public void dibujarAcciones() {
		CartaEnMapa cartaActual = dameCartaEnMapaConNumero(this.personaje.getNumeroCartaPosicionado());
		ArrayList<CartaEnMapa> cartasAdyacentes = dameCartasAdyacentes(cartaActual);

		// Carta de terreno
		dibujarAccionesDeCarta(cartaActual, cartasAdyacentes);

		// Cartas de evento
		for (CartaEnMapa cartaAdyacente : cartasAdyacentes) {
			dibujarAccionesDeCarta(cartaAdyacente, null);
		}

		// Cartas de estado
		ArrayList<CartaEstado> estadosPersonaje = personaje.getEstadosPersonaje();
		for (Carta carta : estadosPersonaje) {
			if (carta.estaEnMesa()) {
				dibujarAccionesDeCarta(carta, null);
			}
		}

		// Cartas de inventario
		ArrayList<Carta> inventario = personaje.getInventario();
		for (Carta cartaInventario : inventario) {
			if (cartaInventario.estaEnMesa()) {
				dibujarAccionesDeCarta(cartaInventario, null);
			}
		}
	}

	public void dibujarAccionesDeCarta(Carta carta, ArrayList<CartaEnMapa> cartasAdyacentes) {
		HashMap<Integer, Accion> acciones = carta.getAcciones();
		Iterator iterador = acciones.keySet().iterator();
		while (iterador.hasNext()) {
			int key = (Integer) iterador.next();
			Accion accion = acciones.get(key);
			dibujarAccion(accion, cartasAdyacentes);
		}
	}

	public void dibujarAccion(final Accion accion, ArrayList<CartaEnMapa> cartasAdyacentes) {
		boolean estaDesactivada = false;

		if (cartasAdyacentes != null) {
			ArrayList<Integer> idAccionesDesactivadas = new ArrayList<Integer>();
			int idAccion = accion.getId();
			for (CartaEnMapa cartasAdyacente : cartasAdyacentes) {
				CartaEvento cartaEvento = (CartaEvento) cartasAdyacente;
				int idDesactivaAccion = cartaEvento.getIdAccionDesactivada();
				idAccionesDesactivadas.add(idDesactivaAccion);
			}

			estaDesactivada = idAccionesDesactivadas.contains(idAccion);
		}

		if (!estaDesactivada) {
			this.posicionYBotones += this.margenEntreBotones;
			final JButton botonAccion = new JButton();
			JLabel labelDificultadAccion = new JLabel("Difficulty: " + accion.getDificultadAccion());
			short costeModificado = personaje.dameCosteModificado(accion);
			String textoLabelCosteAccion = "Cost: " + accion.getCosteAccion();
			if (costeModificado > 0) {
				textoLabelCosteAccion += " + " + costeModificado;
			} else if (costeModificado < 0) {
				textoLabelCosteAccion += " " + costeModificado;
			}
			JLabel labelCosteAccion = new JLabel(textoLabelCosteAccion);
			int posicionX = 1200;
			int anchoJComboBox = 150;
			int altoJComboBox = 25;
			final TipoAccion tipoAccion = accion.getTipoAccion();
			labelDificultadAccion.setFont(new Font("Rockwell", Font.PLAIN, 13));
			labelDificultadAccion.setBounds(1120, this.posicionYBotones, 183, 29);
			add(labelDificultadAccion);

			labelCosteAccion.setFont(new Font("Rockwell", Font.PLAIN, 13));
			labelCosteAccion.setBounds(1040, this.posicionYBotones, 183, 29);
			add(labelCosteAccion);

			botonAccion.setText(tipoAccion.toString());
			botonAccion.setFont(new Font("Rockwell", Font.PLAIN, 13));
			botonAccion.setBounds(posicionX, this.posicionYBotones, this.anchoBoton, this.altoBoton);
			botonAccion.setToolTipText(accion.getDescripcion());
			botonAccion.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					resolverAccion(accion);
				}
			});

			if (tipoAccion == TipoAccion.MOVE) {
				for (int i = 0; i < cartasEnMapa.size(); i++) {
					CartaEnMapa cartaEnMapa = cartasEnMapa.get(i);
					if (cartaEnMapa.getClass() == CartaTerreno.class) {
						if (cartaEnMapa.estaEnMesa()) {
							String numeroCarta = cartaEnMapa.getNumeroCarta();
							String cartaPosicionPersonaje = personaje.getNumeroCartaPosicionado();
							if (!numeroCarta.equals(cartaPosicionPersonaje)) {
								comboBoxNumeroCarta.addItem(numeroCarta);
							}
						}
					}
				}

				comboBoxNumeroCarta.setBounds(posicionX + 170, this.posicionYBotones, anchoJComboBox, altoJComboBox);
			}

			add(botonAccion);
		}
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

		if (!hasGanado) {
			ventana.dibujaTablero();

			if (personaje.getContadorEnergia() <= 0) {
				JOptionPane.showMessageDialog(ventana, "Has desfallecido. Te has quedado sin energía...",
						"Fin de la partida", JOptionPane.CLOSED_OPTION);
				System.exit (0);
			}
		}

	}

	public short obtenerTiradaDificultad() {
		return (short) (random.nextInt(6) + 1);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);
	}

	private ArrayList<CartaEnMapa> dameCartasAdyacentes(CartaEnMapa cartaActual) {
		ArrayList<CartaEnMapa> cartasAdyacentes = new ArrayList<CartaEnMapa>();
		for (CartaEnMapa cartaEnMapa : cartasEnMapa) {
			if (cartaEnMapa.getClass() == CartaEvento.class) {
				CartaEvento cartaEvento = (CartaEvento) cartaEnMapa;
				int idCartaAsociada = cartaEvento.getIdCartaAsociada();
				if (cartaEvento.estaEnMesa()) {
					if (idCartaAsociada == cartaActual.getId()) {
						cartasAdyacentes.add(cartaEvento);
					}
				}
			}
		}
		return cartasAdyacentes;
	}

	public void resolverConsecuencias(ArrayList<Consecuencia> consecuencias) {
		int contadorCartasEngranaje = personaje.dameNumeroDeEngranajes();

		for (Consecuencia consecuencia : consecuencias) {
			String numeroCarta;
			Carta carta;
			TipoConsecuencia tipoConsecuencia = consecuencia.getTipoConsecuencia();

			switch (tipoConsecuencia) {
			case DESPLAZARSE:
				String numeroCartaSeleccionado = (String) comboBoxNumeroCarta.getSelectedItem();
				personaje.setNumeroCartaPosicionado(numeroCartaSeleccionado);
				break;
			case RESTAURAR:
				personaje.aumentaEnergia((short) 12);
				break;
			case TRAER_CARTA:
				numeroCarta = consecuencia.getCartaObjetivo();
				carta = dameCartaEnMapaConNumero(numeroCarta);
				if (carta == null) {
					carta = dameCartaEnPersonajeConNumero(numeroCarta);
				}
				carta.setEstaEnMesa(true);
				if (carta.getClass() == CartaTerreno.class) {
					personaje.setNumeroCartaPosicionado(numeroCarta);
				}
				break;
			case QUITAR_CARTA:
				numeroCarta = consecuencia.getCartaObjetivo();
				carta = dameCartaEnMapaConNumero(numeroCarta);
				if (carta == null) {
					carta = dameCartaEnPersonajeConNumero(numeroCarta);
				}
				carta.setEstaEnMesa(false);

				break;
			case GANAR:
				if (contadorCartasEngranaje == 2) {
					JOptionPane.showMessageDialog(ventana,
							"You have found the two pieces to repair the submarine! Congratulations, you win!",
							"YOU WIN", JOptionPane.INFORMATION_MESSAGE);
					hasGanado = true;
					ventana.cambiarAPantalla("pantallaHistoriaFinal");
				} else {
					JOptionPane.showMessageDialog(ventana, "Oh... Keep looking", "NO WIN",
							JOptionPane.INFORMATION_MESSAGE);

				}

				break;

			}

		}

	}
}
