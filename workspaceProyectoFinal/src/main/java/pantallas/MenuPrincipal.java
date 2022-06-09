package pantallas;

import javax.swing.JPanel;
import javax.swing.WindowConstants;
import elementosVisuales.BotonComun;
import java.awt.GridBagLayout;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DebugGraphics;
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
 * Clase MenuPrincipal la cual hereda de JPanel, y va a representar la pantalla del menú de inicio del juego.
 * @author Leticia
 *
 */
public class MenuPrincipal extends JPanel{
	/** La ventana que contiene el JPanel del menú principal **/
	private Ventana ventana;
	/** La imagen que va a tener de fondo la pantalla de menú principal**/
	private Image imagenFondo;
	

	/**
	 * Constructor de la clase MenuPrincipal, donde se le pasa por parámetros el objeto ventana. En este constructor se va a poner una imagen de fondo, 
	 * y tres botones. Un boton de "Start", para empezar el juego, que nos va a llevar a otra pantalla donde nos saldrá la historia del
	 * personaje. Al pulsar dicho botón, también va a comenzar a reproducirse una canción de fondo, que se estará reproduciendo
	 * todo el rato, a lo largo del juego, hasta qe cerremos el programa. Un botón de "Exit", para salir del programa. 
	 * Y un botón de "Games rules", que nos va a llevar a una pantalla donde se nos explican las reglas del juego.
	 * @param v La ventanan que va a contener el JPanel.
	 */
	public MenuPrincipal(Ventana v) {
		
		this.ventana = v;
		
		imagenFondo = new ImageIcon("./imagenesFondo/mapa.jpg").getImage();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, -46, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton botonStart = new BotonComun("Start");
		botonStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla("historiaPersonaje"); 
				ReproducirSonido("./musica/music.wav");
			}
		});
		botonStart.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		GridBagConstraints gbc_botonStart = new GridBagConstraints();
		gbc_botonStart.fill = GridBagConstraints.BOTH;
		gbc_botonStart.insets = new Insets(90, 100, -9, 100);
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
				System.exit (0);
			}
		});
		
		JButton botonReglas = new BotonComun("Game Rules");
		botonReglas.setFont(new Font("Vladimir Script", Font.BOLD, 30));
		GridBagConstraints gbc_botonReglas = new GridBagConstraints();
		gbc_botonReglas.fill = GridBagConstraints.BOTH;
		gbc_botonReglas.insets = new Insets(0, 0, 0, 0);
		gbc_botonReglas.gridx = 9;
		gbc_botonReglas.gridy = 0;
		add(botonReglas, gbc_botonReglas);
	 
		botonReglas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla("reglas");
			}
		});
		
	}
	
	/**
	 * Función que nos permite dibujar y pintar los componentes de esta pantalla con Swing. 
	*/
	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null); 
	}
	
	/**
	 * Función que va a permitir reproducir sonido pasándole por parámetros la ruta del audio.
	 * @param nombreSonido La ruta del audio.
	 */
	public void ReproducirSonido(String nombreSonido) {
        try {
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(new File(nombreSonido));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

}
