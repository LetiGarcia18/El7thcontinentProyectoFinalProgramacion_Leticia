package pantallas;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clases.Personaje;
import elementosVisuales.BotonComun;

/**
 * Clase PantallaHistoriaPersonaje que hereda de JPanel, la cual va a
 * representar la pantalla donde se contará la historia del personaje.
 * 
 * @author Leticia
 *
 */
public class PantallaHistoriaPersonaje extends JPanel {
	/** La ventana que contiene el JPanel del menú principal **/
	private Ventana ventana;
	/** La imagen que va a tener de fondo la pantalla de menú principal **/
	private Image imagenFondo;
	/** EL personaje del juego **/
	private Personaje personaje;

	/**
	 * Constructor de PantallaHistoriaPersonaje en el cual se le pasa por parámetros
	 * la ventana que va a contener esta pantalla JPanel y el personaje del juego.
	 * Esta pantalla va a contener la historia del personaje, y un botón de
	 * continuar el cual nos va a llevar a otra pantalla, en concreto a la pantalla
	 * de la historia principal del juego.
	 * 
	 * @param v         La ventanan que va a contener el JPanel.
	 * @param personaje El personaje del juego
	 * @throws IOException Excepción que se lanzará si no encuentra el archivo .txt
	 */
	public PantallaHistoriaPersonaje(Ventana v, Personaje personaje) throws IOException {
		this.ventana = v;
		this.personaje = personaje;
		imagenFondo = new ImageIcon(personaje.getRutaImagenHistoria()).getImage();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, -46, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JButton botonContinuar = new BotonComun("Continue");
		botonContinuar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla("historiaPrincipal");
			}
		});
		botonContinuar.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		GridBagConstraints gbc_botonContinuar = new GridBagConstraints();
		gbc_botonContinuar.fill = GridBagConstraints.BOTH;
		gbc_botonContinuar.insets = new Insets(90, 100, -9, 100);
		gbc_botonContinuar.gridx = 9;
		gbc_botonContinuar.gridy = 15;
		add(botonContinuar, gbc_botonContinuar);

		JButton botonVerFile = new BotonComun("Read abstract in spanish");
		final String historia = recorrer(new File(personaje.getRutaHistoriaPersonaje()));
		botonVerFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(ventana, historia, "Story", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		botonVerFile.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		GridBagConstraints gbc_botonVerFile = new GridBagConstraints();
		gbc_botonVerFile.fill = GridBagConstraints.BOTH;
		gbc_botonVerFile.insets = new Insets(90, 500, -9, 100);
		gbc_botonVerFile.gridx = 10;
		gbc_botonVerFile.gridy = 15;
		add(botonVerFile, gbc_botonVerFile);

	}

	public static String recorrer(File rutaArchivo) throws IOException {
		String ret;
		BufferedReader obj = new BufferedReader(new FileReader(rutaArchivo));

		while ((ret = obj.readLine()) != null) {
			return ret;
		}

		return null;
	}

	/**
	 * Función pública que nos permite dibujar y pintar los componentes de esta
	 * pantalla con Swing.
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);
	}

}
