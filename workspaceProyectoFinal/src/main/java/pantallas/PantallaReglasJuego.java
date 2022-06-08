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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import elementosVisuales.BotonComun;
/**
 * Clase PantallaReglasJuego que hereda de JPanel y va a representar a la pantalla que contiene las reglas del juego.
 * @author Leticia
 *
 */
public class PantallaReglasJuego extends JPanel{
	/** La ventana que contiene el JPanel del menú principal **/
	private Ventana ventana;
	/** La imagen que va a tener de fondo la pantalla de menú principal**/
	private Image imagenFondo;
	
	/**
	 * Constructor de la clase PantallaReglasJuego, que se le va  a pasar por parámetros el objeto ventana.En este constructor se va a poner una imagen de fondo, 
	 * y un botón, en el que pondrá "Return", y nos llevará de regreso a la pantalla del Menú principal.
	 * @param v La ventanan que va a contener el JPanel.
	 */
	public PantallaReglasJuego(Ventana v) {
		
		this.ventana = v;
		imagenFondo = new ImageIcon("./imagenesFondo/bookRules.png").getImage();
		
		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, -46, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		
		JButton botonVolver = new BotonComun("Return");
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla("menuInicio");
			}
		});
		botonVolver.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		GridBagConstraints gbc_botonVolver = new GridBagConstraints();
		gbc_botonVolver.fill = GridBagConstraints.BOTH;
		gbc_botonVolver.insets = new Insets(90, 100, -9, 100);
		gbc_botonVolver.gridx = 9;
		gbc_botonVolver.gridy = 15;
		add(botonVolver, gbc_botonVolver);
		
		
	}
	
	/**
	 * Función que nos permite dibujar y pintar los componentes de esta pantalla con Swing. 
	*/
	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);
	}

}
