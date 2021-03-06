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
 * Clase PantallaGameOver que hereda de la super clase Pantalla, que va a
 * representar la pantalla que aparecer? en el juego si nuestro personaje se
 * queda sin energ?a, por lo tanto, se terminar? el juego.
 * 
 * @author Leticia
 *
 */
public class PantallaGameOver extends Pantalla {

	/**
	 * Constructor de la clase PantallaGameOver, a la cual se le va a pasar por
	 * par?metros el objeto ventana y la ruta de la imagen de fondo de la pantalla.
	 * En este constructor se le pone una imagen de fondo a la pantalla, y un bot?n
	 * que te llevar? de regreso a la pantalla de Men? principal.
	 * 
	 * @param v a ventanan que va a contener el JPanel.
	 * @param rutaImagenFondo La ruta de la imagen del fondo de la pantalla
	 */
	public PantallaGameOver(Ventana v, String rutaImagenFondo) {
		super(v, rutaImagenFondo);

		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, -46, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JButton botonSalir = new BotonComun("Ok...");
		botonSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
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

}
