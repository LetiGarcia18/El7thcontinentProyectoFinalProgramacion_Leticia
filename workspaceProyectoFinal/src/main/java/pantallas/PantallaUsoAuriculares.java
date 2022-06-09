package pantallas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import elementosVisuales.BotonComun;

public class PantallaUsoAuriculares extends JPanel{
	/** La ventana que contiene el JPanel del menú principal **/
	private Ventana ventana;
	/** La imagen que va a tener de fondo la pantalla de menú principal**/
	private Image imagenFondo;
	
	public PantallaUsoAuriculares(Ventana v) {
		
		this.ventana = v;
		
		//imagenFondo = new ImageIcon("./imagenesFondo/headphones.gif").getImage();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, -46, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton botonOk = new JButton("Okey");
		botonOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla("historiaPersonaje"); 
			}
		});
		botonOk.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		GridBagConstraints gbc_botonOk = new GridBagConstraints();
		gbc_botonOk.fill = GridBagConstraints.BOTH;
		gbc_botonOk.insets = new Insets(90, 100, 60, 100);
		gbc_botonOk.gridx = 11;
		gbc_botonOk.gridy = 15;
		add(botonOk, gbc_botonOk);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Image img = new ImageIcon("./imagenesFondo/headphones.gif").getImage();
	    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	

}
