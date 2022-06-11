package pantallas;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import elementosVisuales.BotonComun;

public class PantallaHistoriaInicial extends Pantalla {
	/**Archivo de texto donde se van a ir escribiendo las consecuencias de las
	 * acciones que ocurran a lo largo de la partida **/
	private File file;

	/**
	 * Constructor de la clase PantallaHistoriaInicial donde se le pasa por
	 * parámetros el objeto ventana y la ruta de la imagen de fondo de la pantalla.
	 * En este constructor se va a mostrar la historia principal del juego, con una
	 * imagen que se pondrá de fondo de la pantalla. También aparecerá un botón
	 * "Continue" que nos llevará a la pantalla del tablero de juego, donde
	 * comenzaremos la partida.
	 * 
	 * @param v               La ventanan que va a contener el JPanel.
	 * @param rutaImagenFondo La ruta de la imagen del fondo de la pantalla
	 * @param file            Archivo de texto donde se va a ir registrando las
	 *                        consecuencias de las acciones de la partida.
	 */
	public PantallaHistoriaInicial(Ventana v, String rutaImagenFondo, final File file) {
		super(v, rutaImagenFondo);
		this.file = file;
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
				getVentana().dibujaTablero(file);
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

}
