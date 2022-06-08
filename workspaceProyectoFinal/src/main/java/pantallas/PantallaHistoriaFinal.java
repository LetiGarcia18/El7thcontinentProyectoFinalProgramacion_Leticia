package pantallas;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import elementosVisuales.BotonComun;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Clase PantallaHistoriaFinal que va a heredar de JPanel. Va a representar la pantalla que aparecerá cuando el personaje gane la partida.
 * @author Leticia
 *
 */
public class PantallaHistoriaFinal extends JPanel {
	/** La ventana que contiene el JPanel del la pantalla con la historia final **/
	private Ventana ventana;
	/** La imagen que va a tener de fondo la pantalla de la historia final**/
	private Image imagenFondo;

	/**
	 * Constructor de la clase PantallaHistoriaFinal, donde se le pasa por parámetros el objeto ventana. En este constructor se va a 
	 * poner una imagen de fondo, y un botón. El botón será un botón que diga "Continue" que nos llevará a la pantalla de victoria del juego.
	 * @param v La ventanan que va a contener el JPanel.
	 */
	public PantallaHistoriaFinal(Ventana v) {

		this.ventana = v;

		imagenFondo = new ImageIcon("./imagenesFondo/fondoNegro.jpg").getImage();

		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, -46, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JButton botonEscape = new BotonComun("Escape from the island...");
		botonEscape.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla("pantallaVictoria"); 
			}
		});
		botonEscape.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		GridBagConstraints gbc_botonEscape = new GridBagConstraints();
		gbc_botonEscape.fill = GridBagConstraints.BOTH;
		gbc_botonEscape.insets = new Insets(90, 100, -9, 100);
		gbc_botonEscape.gridx = 9;
		gbc_botonEscape.gridy = 15;
		add(botonEscape, gbc_botonEscape);

	}

	/**
	 * Función que nos permite dibujar y pintar los componentes de esta pantalla con Swing. 
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);
	}

}
