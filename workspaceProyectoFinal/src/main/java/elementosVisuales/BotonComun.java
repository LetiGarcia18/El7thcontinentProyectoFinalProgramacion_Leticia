package elementosVisuales;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

/**
 * Clase BotonComun que va a heredar de JButton, y va a representar a la mayoría
 * de los botones que tengan interactividad en el juego, sobre todo los de las
 * pantallas de menú principal, la pantalla de reglas, la del final del juego,
 * etc.
 * 
 * @author Leticia
 *
 */
public class BotonComun extends JButton {
	/**
	 * Constructor de la clase BotonComun, donde se le pasa por parámetros una
	 * String, que representará el nombre del botón. Dicho botón, tendrá un estilo
	 * por defecto, que al pasar el mouse por encima, se cambiarán, y al quitar el
	 * mouse, volverá el botón a ese estilo por defecto.
	 * 
	 * @param m String que representa el nombre que tendrá el botón visualmente.
	 */
	public BotonComun(String m) {
		super(m);
		estiloPorDefecto();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(new Color(131, 94, 0));
				setBackground(new Color(255, 195, 0));
				setFont(new Font("Vladimir Script", Font.PLAIN, 40));
				setSize(50, 50);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				estiloPorDefecto();
			}
		});
	}

	/**
	 * Función privada que devuelve el estilo del botón a su estilo original.
	 */
	private void estiloPorDefecto() {
		this.setForeground(new Color(247, 224, 138));
		this.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		this.setBackground(new Color(131, 94, 0));
		this.setSize(50, 37);
	}
}
