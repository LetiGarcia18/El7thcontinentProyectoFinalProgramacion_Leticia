package pantallas;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Pantalla extends JPanel {
	/** La ventana que contiene el JPanel del menú principal **/
	private Ventana ventana;
	/** La imagen que va a tener de fondo la pantalla de menú principal **/
	private Image imagenFondo;

	public Pantalla(Ventana v, String rutaImagenFondo) {
		this.ventana = v;
		this.imagenFondo = new ImageIcon(rutaImagenFondo).getImage();

	}
	
	/**
	 * Función pública que nos permite dibujar y pintar los componentes de esta
	 * pantalla con Swing.
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);
	}
}
