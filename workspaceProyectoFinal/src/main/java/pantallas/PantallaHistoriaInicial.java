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

public class PantallaHistoriaInicial extends JPanel {
	/** La ventana que contiene el JPanel del menú principal **/
	private Ventana ventana;
	/** La imagen que va a tener de fondo la pantalla de menú principal **/
	private Image imagenFondo;

	/**
	 * Constructor de la clase PantallaHistoriaInicial donde se le pasa por
	 * parámetros el objeto ventana. En este constructor se va a mostrar la historia
	 * principal del juego, con una imagen que se pondrá de fondo de la pantalla.
	 * También aparecerá un botón "Continue" que nos llevará a la pantalla del
	 * tablero de juego, donde comenzaremos la partida.
	 * 
	 * @param v La ventanan que va a contener el JPanel.
	 */
	public PantallaHistoriaInicial(Ventana v) {

		this.ventana = v;

		imagenFondo = new ImageIcon("./imagenesFondo/historiaPrincipal.png").getImage();

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
				ventana.dibujaTablero();
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
	 * Función pública que nos permite dibujar y pintar los componentes de esta
	 * pantalla con Swing.
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);
	}

}
