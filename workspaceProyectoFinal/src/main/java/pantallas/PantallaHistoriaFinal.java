package pantallas;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import elementosVisuales.BotonInicio;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaHistoriaFinal extends JPanel {

	private Ventana ventana;
	private Image imagenFondo;

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

		JButton botonEscape = new BotonInicio("Escape from the island...");
		botonEscape.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla("pantallaVictoria"); // Con esto ya podemos cambiar a otra pantalla dándole al
																// botón de registro
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

	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);
	}

}
