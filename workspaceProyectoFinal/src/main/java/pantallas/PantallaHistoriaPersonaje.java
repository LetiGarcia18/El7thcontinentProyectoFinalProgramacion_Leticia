package pantallas;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import clases.Personaje;
import elementosVisuales.BotonComun;
/**
 * Clase PantallaHistoriaPersonaje que hereda de JPanel, la cual va a representar la pantalla donde se contar� la historia del 
 * personaje.
 * @author Leticia
 *
 */
public class PantallaHistoriaPersonaje extends JPanel {
	/** La ventana que contiene el JPanel del men� principal **/
	private Ventana ventana;
	/** La imagen que va a tener de fondo la pantalla de men� principal**/
	private Image imagenFondo;
	/** EL personaje del juego **/
	private Personaje personaje;

	/**
	 * Constructor de PantallaHistoriaPersonaje en el cual se le pasa por par�metros la ventana que va a contener esta pantalla JPanel
	 * y el personaje del juego. Esta pantalla va a contener la historia del personaje, y un bot�n de continuar el cual nos va a 
	 * llevar a otra pantalla, en concreto a la pantalla de la historia principal del juego.
	 * @param v La ventanan que va a contener el JPanel.
	 * @param personaje El personaje del juego
	 */
	public PantallaHistoriaPersonaje(Ventana v, Personaje personaje) {
		this.ventana=v;
		this.personaje = personaje;
		imagenFondo= new ImageIcon(personaje.getRutaImagenHistoria()).getImage();

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
		
		
	}
	
	/**
	 * Funci�n p�blica que nos permite dibujar y pintar los componentes de esta pantalla con Swing. 
	*/
	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);
	}

}
