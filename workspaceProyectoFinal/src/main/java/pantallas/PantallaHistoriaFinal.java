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
 * Clase PantallaHistoriaFinal que va a heredar de la super clase Pantalla. Va a
 * representar la pantalla que aparecerá cuando el personaje gane la partida.
 * 
 * @author Leticia
 *
 */
public class PantallaHistoriaFinal extends Pantalla {
	/**
	 * Constructor de la clase PantallaHistoriaFinal, donde se le pasa por
	 * parámetros el objeto ventana y la ruta de la imagen de fondo de la pantalla.
	 * En este constructor se va a poner una imagen de fondo, y un botón. El botón
	 * será un botón que diga "Continue" que nos llevará a la pantalla de victoria
	 * del juego.
	 * 
	 * @param v La ventanan que va a contener el JPanel.
	 * @param rutaImagenFondo La ruta de la imagen del fondo de la pantalla
	 */
	public PantallaHistoriaFinal(Ventana v, String rutaImagenFondo) {
		super(v, rutaImagenFondo);

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
				getVentana().cambiarAPantalla("pantallaVictoria");
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

}
