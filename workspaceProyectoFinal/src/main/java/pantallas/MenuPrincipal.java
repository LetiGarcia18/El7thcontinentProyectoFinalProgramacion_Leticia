package pantallas;

import javax.swing.JPanel;
import javax.swing.WindowConstants;
import elementosVisuales.BotonInicio;
import java.awt.GridBagLayout;

import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;

public class MenuPrincipal extends JPanel{
	
	private Ventana ventana;
	private Image imagenFondo;
	

	
	public MenuPrincipal(Ventana v) {
		
		this.ventana = v;
		
		imagenFondo = new ImageIcon("./imagenesFondo/mapa.jpg").getImage();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, -46, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton botonStart = new BotonInicio("Start");
		botonStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla("historiaPersonaje"); //Con esto ya podemos cambiar a otra pantalla dándole al botón de registro
			}
		});
		botonStart.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		GridBagConstraints gbc_botonStart = new GridBagConstraints();
		gbc_botonStart.fill = GridBagConstraints.BOTH;
		gbc_botonStart.insets = new Insets(90, 100, -9, 100);
		gbc_botonStart.gridx = 9;
		gbc_botonStart.gridy = 15;
		add(botonStart, gbc_botonStart);
		
		JButton botonExit = new BotonInicio("Exit");
		botonExit.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		GridBagConstraints gbc_botonExit = new GridBagConstraints();
		gbc_botonExit.fill = GridBagConstraints.BOTH;
		gbc_botonExit.insets = new Insets(90, 100, 40, 100);
		gbc_botonExit.gridx = 9;
		gbc_botonExit.gridy = 20;
		add(botonExit, gbc_botonExit);
		
		botonExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit (0);
			}
		});
		
		JButton botonReglas = new BotonInicio("Game Rules");
		botonReglas.setFont(new Font("Vladimir Script", Font.BOLD, 30));
		GridBagConstraints gbc_botonReglas = new GridBagConstraints();
		gbc_botonReglas.fill = GridBagConstraints.BOTH;
		gbc_botonReglas.insets = new Insets(0, 0, 0, 0);
		gbc_botonReglas.gridx = 9;
		gbc_botonReglas.gridy = 0;
		add(botonReglas, gbc_botonReglas);
	 
		botonReglas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla("reglas");
			}
		});
		
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null); 
	}


}
