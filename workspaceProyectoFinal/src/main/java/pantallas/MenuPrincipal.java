package pantallas;

import javax.swing.JPanel;
import elementosVisuales.BotonComun;
import java.awt.GridBagLayout;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;

/**
 * Clase MenuPrincipal la cual hereda de Pantalla, y va a representar la
 * pantalla del men? de inicio del juego.
 * 
 * @author Leticia
 *
 */
public class MenuPrincipal extends Pantalla {

	/**
	 * Constructor de la clase MenuPrincipal, donde se le pasa por par?metros el
	 * objeto ventana y la ruta de la imagen de fondo de la pantalla. En este
	 * constructor se va a poner una imagen de fondo, y tres botones. Un boton de
	 * "Start", para empezar el juego, que nos va a llevar a otra pantalla donde nos
	 * saldr? una imagen gif recomend?ndonos el uso de auriculares durante el juego.
	 * Al pulsar dicho bot?n, tambi?n va a comenzar a reproducirse una canci?n de
	 * fondo, que se estar? reproduciendo todo el rato, a lo largo del juego, hasta
	 * qe cerremos el programa. Un bot?n de "Exit", para salir del programa. Y un
	 * bot?n de "Games rules", que nos va a llevar a una pantalla donde se nos
	 * explican las reglas del juego.
	 * 
	 * @param v La ventanan que va a contener el JPanel.
	 * @param rutaImagenFondo La ruta de la imagen del fondo de la pantalla
	 */
	public MenuPrincipal(Ventana v, String rutaImagenFondo) {
		super(v, rutaImagenFondo);

		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, -46, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JButton botonStart = new BotonComun("Start");
		botonStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getVentana().cambiarAPantalla("usaAuriculares");
				ReproducirSonido("./musica/music.wav");
			}
		});
		botonStart.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		GridBagConstraints gbc_botonStart = new GridBagConstraints();
		gbc_botonStart.fill = GridBagConstraints.BOTH;
		gbc_botonStart.insets = new Insets(90, 100, 5, 100);
		gbc_botonStart.gridx = 9;
		gbc_botonStart.gridy = 15;
		add(botonStart, gbc_botonStart);

		JButton botonExit = new BotonComun("Exit");
		botonExit.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		GridBagConstraints gbc_botonExit = new GridBagConstraints();
		gbc_botonExit.fill = GridBagConstraints.BOTH;
		gbc_botonExit.insets = new Insets(90, 100, 40, 100);
		gbc_botonExit.gridx = 9;
		gbc_botonExit.gridy = 20;
		add(botonExit, gbc_botonExit);

		botonExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		JButton botonReglas = new BotonComun("Game Rules");
		botonReglas.setFont(new Font("Vladimir Script", Font.BOLD, 30));
		GridBagConstraints gbc_botonReglas = new GridBagConstraints();
		gbc_botonReglas.fill = GridBagConstraints.BOTH;
		gbc_botonReglas.insets = new Insets(0, 0, 0, 0);
		gbc_botonReglas.gridx = 9;
		gbc_botonReglas.gridy = 4;
		add(botonReglas, gbc_botonReglas);

		botonReglas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getVentana().cambiarAPantalla("reglas");
			}
		});

	}

	/**
	 * Funci?n privada que va a permitir reproducir sonido pas?ndole por par?metros
	 * la ruta del audio.
	 * 
	 * @param nombreSonido La ruta del audio.
	 */
	private void ReproducirSonido(String nombreSonido) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20.0f);
			clip.start();
			clip.loop(UNDEFINED_CONDITION);

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			ex.printStackTrace();
		}
	}

}
