package pantallas;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Super clase Pantalla, que hereda de JPanel, de la que van a heredar el resto
 * de pantallas.
 * 
 * @author Leticia
 *
 */
public class Pantalla extends JPanel {
	/** La ventana que contiene el JPanel del menú principal **/
	private Ventana ventana;
	/** La imagen que va a tener de fondo la pantalla de menú principal **/
	private Image imagenFondo;

	/**
	 * Constructor de la super clase Pantalla, que se le va a pasar por parámetros
	 * la ventana, y la ruta de la imagen de fondo.
	 * 
	 * @param v La ventanan que va a contener el JPanel.
	 * @param rutaImagenFondo La ruta de la imagen del fondo de la pantalla
	 */
	public Pantalla(Ventana v, String rutaImagenFondo) {
		this.ventana = v;
		this.imagenFondo = new ImageIcon(rutaImagenFondo).getImage();

	}

	/**
	 * Getter de Ventana 
	 * 
	 * @return Nos devuelve la ventana
	 */
	public Ventana getVentana() {
		return ventana;
	}

	/**
	 * Setter de Ventana
	 * 
	 * @param ventana El objeto ventana
	 */
	public void setVentana(Ventana ventana) {
		this.ventana = ventana;
	}

	/**
	 * Getter de la imagen de fondo
	 * 
	 * @return Nos devuelve la imagen de fondo
	 */
	public Image getImagenFondo() {
		return imagenFondo;
	}

	/**
	 * Setter de la imagen de fondo
	 * 
	 * @param imagenFondo La imagen de fondo
	 */
	public void setImagenFondo(Image imagenFondo) {
		this.imagenFondo = imagenFondo;
	}

	/**
	 * Función pública que nos permite dibujar y pintar los componentes de esta
	 * pantalla con Swing.
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);
	}
}
