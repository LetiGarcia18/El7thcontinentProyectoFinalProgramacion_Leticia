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
 * Clase PantallaGameOver que hereda de la clase JPanel, que va a representar la pantalla que aparecerá en el juego si nuestro 
 * personaje se queda sin energía, por lo tanto, se terminará el juego.
 * @author Leticia
 *
 */
public class PantallaGameOver extends JPanel{
	/** La ventana que contiene el JPanel del la pantalla de Game Over **/
	private Ventana ventana;
	/** La imagen que va a tener de fondo la pantalla de Game Over**/
	private Image imagenFondo;
	
	/**
	 * Constructor de la clase PantallaGameOver, a la cual se le va a pasar por parámetros el objeto ventana. En este constructor
	 * se le pone una imagen de fondo a la pantalla, y un botón que te llevará de regreso a la pantalla de Menú principal. 
	 * @param v a ventanan que va a contener el JPanel.
	 */
	public PantallaGameOver(Ventana v) {
		this.ventana = v;
		
		imagenFondo = new ImageIcon("./imagenesFondo/gameOver.png").getImage();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, -46, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton botonSalir = new BotonComun("Ok...");
		botonSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla("menuInicio"); 
			}
		});
		botonSalir.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		GridBagConstraints gbc_botonStart = new GridBagConstraints();
		gbc_botonStart.fill = GridBagConstraints.BOTH;
		gbc_botonStart.insets = new Insets(550, 300, 100, 300);
		gbc_botonStart.gridx = 9;
		gbc_botonStart.gridy = 15;
		add(botonSalir, gbc_botonStart);
	}
	
	/**
	 * Función pública que nos permite dibujar y pintar los componentes de esta pantalla con Swing. 
	*/
	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null); 
	}

}
