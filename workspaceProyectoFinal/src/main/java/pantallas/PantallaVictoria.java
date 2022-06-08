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

import elementosVisuales.BotonComun;
/**
 * Clase PantallaVictoria que hereda de JPanel. Va a representar la pantalla final del juego, cuando se gana la partida.
 * @author Leticia
 *
 */
public class PantallaVictoria extends JPanel{
	/** La ventana que contiene el JPanel del men� principal **/
	private Ventana ventana;
	/** La imagen que va a tener de fondo la pantalla de men� principal**/
	private Image imagenFondo;
	/**
	 * Constructor de la clase PantallaVictoria, que se le va a pasar por par�metros el objeto ventana. En este constructor se va a poner una imagen de fondo, 
	 * y un boton de "Exit", para salir del programa. 
	 * @param v La ventanan que va a contener el JPanel.
	 */
	public PantallaVictoria(Ventana v) {

		this.ventana = v;

		imagenFondo = new ImageIcon("./imagenesFondo/victory.png").getImage();

		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, -46, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JButton botonBye = new BotonComun("Exit");
		botonBye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit (0); 
			}
		});
		botonBye.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		GridBagConstraints gbc_botonBye = new GridBagConstraints();
		gbc_botonBye.fill = GridBagConstraints.BOTH;
		gbc_botonBye.insets = new Insets(90, 100, -9, 100);
		gbc_botonBye.gridx = 9;
		gbc_botonBye.gridy = 15;
		add(botonBye, gbc_botonBye);

	}
	
	/**
	 * Funci�n que nos permite dibujar y pintar los componentes de esta pantalla con Swing. 
	*/
	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);
	}
}
