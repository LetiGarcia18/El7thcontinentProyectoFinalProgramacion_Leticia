package pantallas;

import javax.swing.JPanel;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JComboBox;

import clases.Accion;
import clases.Carta;
import clases.CartaEnMapa;
import clases.CartaEstado;
import clases.CartaEvento;
import clases.CartaTerreno;
import clases.Consecuencia;
import clases.Personaje;
import enums.TipoAccion;
import enums.TipoConsecuencia;

import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import java.awt.Image;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Clase Tablero, que hereda de JPanel y va a representar el tablero de juego
 * del programa.
 * 
 * @author Leticia
 *
 */
public class Tablero extends JPanel {
	/** La ventana que contiene el JPanel del tablero **/
	private Ventana ventana;
	/**
	 * Representa el margen derecho que van a tener algunos elementos de la pantalla
	 **/
	private int margenDerecho;
	/** ArrayList de las cartas que hay en el mapa **/
	private ArrayList<CartaEnMapa> cartasEnMapa;
	/** El personaje del juego **/
	private Personaje personaje;
	/** Un botón desplegable donde van a aparecer los números de las cartas **/
	private JComboBox comboBoxNumeroCarta;
	/**
	 * random que se utilizará a la hora de sacar un número aleatorio entre 0 y 6
	 * para comprobar si se ha superado la dificultad de una acción
	 **/
	private Random random;
	/** La imagen que va a tener de fondo la pantalla del tablero **/
	private Image imagenFondo;
	/**
	 * Booleano que nos indicará con true si hemos ganado la partida. Se inicializa
	 * a false inicialmente
	 **/
	private boolean hasGanado;
	/** Representa el alto de los botones de esta pantalla **/
	private int altoBoton;
	/** Representa el ancho de los botones de esta pantalla **/
	private int anchoBoton;
	/** Representa el margen que hay entre los botones de esta pantalla **/
	private int margenEntreBotones;
	/** Representa el valor de la posición Y de los botones **/
	private int posicionYBotones;

	/**
	 * Constructor de Tablero, al cual se le pasa por parámetros la ventana que va a
	 * contener el JPanel de Tablero, un ArrayList con las cartas que va a haber en
	 * el mapa, y el personaje. En este constructor se va a dibujar el tablero. Van
	 * a aparecer en la parte central/izquierda las cartas de terreno iniciales, el
	 * token del personaje, en la parte lateral derecha van a aparecer distintos
	 * JLabel actuando de separadores entre las cartas de estado del personaje, el
	 * inventario del personaje y las acciones que va a poner ir realizando el
	 * personaje
	 * 
	 * @param v            La ventanan que va a contener el JPanel.
	 * @param cartasEnMapa Las cartas que van a aparecer en el tablero.
	 * @param personaje    El personaje del juego
	 */
	public Tablero(Ventana v, ArrayList<CartaEnMapa> cartasEnMapa, Personaje personaje) {

		this.ventana = v;
		this.cartasEnMapa = cartasEnMapa;
		this.personaje = personaje;
		random = new Random();
		this.margenDerecho = 20;
		this.hasGanado = false;
		this.altoBoton = 37;
		this.anchoBoton = 150;
		this.margenEntreBotones = 40;
		this.posicionYBotones = 470;
		imagenFondo = new ImageIcon("./imagenesFondo/isla.jpg").getImage();

		setLayout(null);

		JLabel labelPersonaje = new JLabel(personaje.getNombre() + ".  Good at: " + personaje.getHabilidad());
		labelPersonaje.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPersonaje.setIcon(new ImageIcon(personaje.getRutaIconoPersonaje()));
		labelPersonaje.setBounds(720, 0, 800, 65);
		labelPersonaje.setForeground(getBackground());
		labelPersonaje.setFont(new Font("Rockwell", Font.BOLD, 30));
		add(labelPersonaje);

		JLabel labelInventario = new JLabel(" _______________________ Inventory ____________________________");
		labelInventario.setHorizontalAlignment(SwingConstants.RIGHT);
		labelInventario.setIcon(new ImageIcon("./iconos/maleta.png"));
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

	/**
	 * Función pública que va a dibujar las cartas en el tablero de juego, donde se
	 * le van a pasar por parámetros en ancho de la casilla (que representa el ancho
	 * de la carta), el margen izquierdo, y el margen superior. Va a recorrer el
	 * ArrayList de las cartasEnMapa y va a ir almacenando en la variable X la
	 * posición exacta en la que se debe colocar en el tablero, al igual que irá
	 * haciendo con la posición Y, de modo que al dibujarse, no se van a solapar las
	 * cartas. Va a realizar lo mismo con las cartas de estado, con la peculiaridad
	 * de que va a comprobar si la carta de estado esta ya dibujada en el tablero, y
	 * si es así, calcula la posición de la siguiente carta de estado, para que
	 * cuando se dibuje más de una, no se solapen tampoco. Y por último, realizará
	 * lo mismo con las cartas de inventario. Realizará el mismo procedimiento que
	 * se realiza con las cartas de estado, antes de dibujar la carta inventario,
	 * comprobará si está en ya dibujada en el tablero, y si es así calculará la
	 * posición de la siguiente para que nuevamente, no se solapen.
	 * 
	 * @param anchoCasilla    El ancho de las cartas que se van a dibujar
	 * @param margenIzquierdo El margen izquierdo que tendrán las cartas
	 * @param margenSuperior  El margen superior que tendrán las cartas
	 */
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

	/**
	 * Función pública que va a dibujar individualmente cada carta. Para ello, va a
	 * coger la ruta de la imagen de cada carta, la escalará al tamaño adecuado, y
	 * si se trata de cartas de estado, aparecerá un JLabel encima de cada carta de
	 * estado con el título de los estados. Se le va a pasar por parámetros la carta
	 * a dibujar, la posición X e Y de la carta y el ancho de la misma.
	 * 
	 * @param carta        La carta que va a ser dibujada.
	 * @param posicionX    La posición X donde se dibujará la carta
	 * @param posicionY    La posición Y donde se dibujará la carta.
	 * @param anchoCasilla El ancho que tendrá la carta.
	 */
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

	/**
	 * Función pública que va a dibujar al persona en el tablero de juego, donde se
	 * le van a pasar por parámetros la carta de terreno donde se va a posicionar el
	 * personaje, el ancho de la casilla de la carta, el tamaño que tendrá el token
	 * del personaje, el margen izquierdo que va a el token del personaje y el
	 * margen superior que va a tener el token del personaje. Esta función va a
	 * calcular la posición X e Y de la carta donde el perosnaje se va a situar, y
	 * va a llamar a otra función que dibujará al persona encima de esa carta.
	 * 
	 * @param cartaTerreno     La carta donde va a estar posicionado el personaje
	 * @param anchoCasilla     El ancho de casilla de la carta
	 * @param tamanioPersonaje El tamaño del token del personaje
	 * @param margenIzquierdo  El margen izquierdo que va a el token del personaje
	 * @param margenSuperior   El margen superior que va a tener el token del
	 *                         personaje
	 */
	public void dibujaEnMapaPersonaje(CartaEnMapa cartaTerreno, int anchoCasilla, int tamanioPersonaje,
			int margenIzquierdo, int margenSuperior) {
		int posicionX = (cartaTerreno.getPosicionX() * anchoCasilla) + margenIzquierdo;
		int posicionY = (cartaTerreno.getPosicionY() * anchoCasilla) + margenSuperior;
		dibujaPersonaje(posicionX, posicionY, anchoCasilla, tamanioPersonaje);
	}

	/**
	 * Función pública que va a dubujar al personaje, donde se le va a pasar por
	 * parámetros la posición X e Y de la carta, el ancho de la carta, y el tamaño
	 * del personaje. Se va a coger la ruta de la imagen del personaje, y se va a
	 * escalar al tamaño adecuado que se quiere que tenga el token. Se dibujará
	 * también un JLabel en la parte superior izquierda de la pantalla de Tablero,
	 * en el que va a mostrar la energía que tiene el personaje durante el juego.
	 * 
	 * @param posicionXCarta   La posición X de la carta donde se va a encontrar el
	 *                         personaje posicionado
	 * @param posicionYCarta   La posición Y de la carta donde se va a encontrar el
	 *                         personaje posicionado
	 * @param anchoCasilla     El ancho de la carta
	 * @param tamanioPersonaje El tamaño que tendrá el token del personaje
	 */
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
		energiaPersonaje.setForeground(getBackground());
		add(energiaPersonaje);

	}

	/**
	 * Función privada que nos va a dar una carta que se encuentre en el mapa, junto
	 * a su número de carta. Se le van a pasar por parámetros el número de la carta.
	 * Esta función va a recorrer el ArrayList de las cartasEnMapa, y nos va a
	 * devolver la carta que tenga como número de carta, el número de carta que se
	 * le pasa por parámetros, y si no la encuentra, nos devuelve null.
	 * 
	 * @param numeroCarta El número de carta que queremos que nos devuelva
	 * @return La carta que coincida con el número de carta que se le pasan por
	 *         parámetros, o null si no encuentra la carta.
	 */
	private CartaEnMapa dameCartaEnMapaConNumero(String numeroCarta) {
		for (int i = 0; i < cartasEnMapa.size(); i++) {
			CartaEnMapa cartaEnMapa = cartasEnMapa.get(i);
			if (cartaEnMapa.getNumeroCarta().equals(numeroCarta)) {
				return cartaEnMapa;
			}
		}
		return null;
	}

	/**
	 * Función privada que nos devuelve la carta donde se encuentra el personaje. Se
	 * le pasa por parámetros el número de carta. Esta función, dependiendo del tipo
	 * de carta que queramos, va a recorrer un ArrayList u otro. Si el número de
	 * carta que le pasamos por parámetros coincide con alguna carta de estado que
	 * tenga ese número, nos devolverá esa carta de estado. Si el número de carta
	 * que le pasamos por parámetros no coincide con ninguna de estado, se metererá
	 * a buscar en el ArrayList de las cartas de inventario, y si la encuentra, nos
	 * la devolverá. Y si no encuentra ni un tipo de carta u otra, nos devolverá
	 * null.
	 * 
	 * @param numeroCarta El número de carta que queremos que nos devuelva
	 * @return Nos devolverá o una carta de estado, o una carta de inventario o null
	 *         si no la encuentra en ninguno de los dos tipos.
	 */
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

	/**
	 * Función pública que nos va a dibujar las acciones en el tablero. Primero, va
	 * a utilizar la funcion dameCartaEnMapaConNumero para ver en qué carta se
	 * encuentra el personaje, y también va a utilizar la función
	 * dameCartasAdyacentes , que va a devolver las cartas adyacentes a la carta
	 * donde está posicionado el personaje. Una vez realizado esto, llamando a sus
	 * correspondientes funciones, se van a dibujar las acciones de la carta en la
	 * que está posicionado el personaje, y de las cartas adyacentes, también, si
	 * existen cartas de evento, se dibujarán las acciones de dichas cartas, si el
	 * personaje tiene estados, también se dibujarán las acciones de las cartas de
	 * esos estados y si el personaje tiene inventario, se dibujarán las acciones de
	 * las cartas de inventario.
	 */
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

	/**
	 * Función pública que va a dibujar las acciones de cada carta. Se le va a pasar
	 * por parámetros la carta de la que se van a dibujar las acciones y las cartas
	 * adyacentes que puedan tener acciones a dibujar. En esta función se iterará
	 * por las acciones de la carta y se llamará a la función dibujarAccion para que
	 * se vayan dibujando dichas acciones en el Tablero.
	 * 
	 * @param carta            La carta de la que se van a dibujar las acciones
	 * @param cartasAdyacentes Las cartas adyacentes a la carta de la que se van a
	 *                         dibujar las acciones.
	 */
	public void dibujarAccionesDeCarta(Carta carta, ArrayList<CartaEnMapa> cartasAdyacentes) {
		HashMap<Integer, Accion> acciones = carta.getAcciones();
		Iterator iterador = acciones.keySet().iterator();
		while (iterador.hasNext()) {
			int key = (Integer) iterador.next();
			Accion accion = acciones.get(key);
			dibujarAccion(accion, cartasAdyacentes);
		}
	}

	/**
	 * Función pública que va a dubujar la acción en el Tablero de juego. Se le va a
	 * pasar por parámetros la acción a dibujar, y las cartas adyacentes de la carta
	 * donde se encuentra la acción. En esta función va a comprobar si el ArrayList
	 * de cartas adyacentes no está vacío, si no lo está va a ir dibujando las
	 * acciones de las cartas adyacentes. Si esas acciones (las de las cartas
	 * adyacentes) están dibujadas, se desactivará la acción de la carta donde se
	 * encuentra el personaje, siendo imposible realizarla de nuevo. También se
	 * dibujarán las acciones de la carta donde está situado el personaje. Se
	 * representarán con un botón donde pondrá la acción de la que se trata, y al
	 * lado de cada botón, aparecerá el coste y la dificultad que va a tener
	 * resolver dicha acción. Para el caso de la acción MOVE, va a tener al lado del
	 * botón, un botón desplegable donde aparecerán el número de todas las cartas de
	 * terreno donde va a ser posible desplazarse.
	 * 
	 * @param accion           La acción que se va a dibujar
	 * @param cartasAdyacentes Las cartas adyacentes de la carta donde se encuentra
	 *                         la acción.
	 */
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

			ImageIcon iconobtn = new ImageIcon(accion.getRutaIconoAccion());
			botonAccion.setText(tipoAccion.toString());
			botonAccion.setFont(new Font("Rockwell", Font.PLAIN, 11));
			botonAccion.setBounds(posicionX, this.posicionYBotones, this.anchoBoton, this.altoBoton);
			botonAccion.setToolTipText(accion.getDescripcion());
			botonAccion.setIcon(iconobtn);
			botonAccion.setIconTextGap(2);
			botonAccion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
			botonAccion.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
			botonAccion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
			botonAccion.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
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
			JOptionPane.showMessageDialog(ventana, "Difficulty not overcome with: " + tirada, "DIFFICULTY ROLL",
					JOptionPane.INFORMATION_MESSAGE);
			resolverConsecuencias(accion.getConsecuenciasNegativas());
		}

		if (!hasGanado) {
			ventana.dibujaTablero();

			if (personaje.getContadorEnergia() <= 0) {
				JOptionPane.showMessageDialog(ventana, "You have fainted. You've run out of energy...",
						"Fin de la partida", JOptionPane.CLOSED_OPTION);
				ventana.cambiarAPantalla("game over");
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
					hasGanado = true;
					ventana.cambiarAPantalla("pantallaHistoriaFinal");
				} else {
					JOptionPane.showMessageDialog(ventana,
							"You still can't run away from the island. You have pieces left to find. Keep looking!",
							"DO NOT ESCAPE YET", JOptionPane.INFORMATION_MESSAGE);

				}

				break;

			}

		}

	}
}
