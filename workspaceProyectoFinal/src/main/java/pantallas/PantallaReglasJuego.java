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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import elementosVisuales.BotonComun;

public class PantallaReglasJuego extends JPanel{
	
	private Ventana ventana;
	private Image imagenFondo;
	
	public PantallaReglasJuego(Ventana v) {
		
		this.ventana = v;
		imagenFondo = new ImageIcon("./imagenesFondo/bookRules.png").getImage();
		
		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, -46, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		
		JButton botonVolver = new BotonComun("Return");
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla("menuInicio");
			}
		});
		botonVolver.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		GridBagConstraints gbc_botonVolver = new GridBagConstraints();
		gbc_botonVolver.fill = GridBagConstraints.BOTH;
		gbc_botonVolver.insets = new Insets(90, 100, -9, 100);
		gbc_botonVolver.gridx = 9;
		gbc_botonVolver.gridy = 15;
		add(botonVolver, gbc_botonVolver);
		
		
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);
	}

}
