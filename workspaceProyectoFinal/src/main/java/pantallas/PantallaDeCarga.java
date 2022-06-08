package pantallas;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
/**
 * Clase Splash que hereda de la clase JDialog, que va a representar una pantalla de carga al inicializar el juego
 * @author letic
 *
 */
public class PantallaDeCarga extends JDialog {
	/** La pantalla de carga **/
	private JLabel SplashScreen;
	/** La barra de progreso de la carga **/
	private JProgressBar barraProgreso;
	/** El porcentaje de la barra de carga **/
	private JLabel porcentaje;

	/**
	 * Constructor de la clase PantallaDeCarga, donde se va a llamar a una función que va a inicializar la pantalla, y a su vez 
	 * se va a llamar a otra función que es la que controle la velocidad de la barra de progreso de la pantalla de carga
	 */
	public PantallaDeCarga() {
		inicializar();
		setSize(300, 150);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setUndecorated(true);

		inicialHilo();
	}

	/**
	 * Función que va a controlar la velocidad de la barra de progreso de la pantalla de carga
	 */
	private void inicialHilo() {
		Thread hilo = new Thread(new Runnable() {
			int x = 0; 

			public void run() {
				
				try {
					while(x <= 100) {
						barraProgreso.setValue(x);
						porcentaje.setText(x + "%");
						x++;
						Thread.sleep(9);
					}
					
					dispose();
					Ventana ventana = new Ventana("");
					ventana.setVisible(true);
				}catch (Exception e) {
					System.out.println("Excepción " + e.getMessage());
				}
				
			}

		});
		hilo.start();
	}

	/**
	 * Función que va a inicializar la pantalla de carga. Se le da un aspecto y un tamaño a la misma. 
	 */
	private void inicializar() {
		SplashScreen = new JLabel("Cargando...");
		SplashScreen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		SplashScreen.setBounds(49, 11, 147, 32);
		getContentPane().add(SplashScreen);

		barraProgreso = new JProgressBar();
		barraProgreso.setBounds(26, 54, 229, 32);
		getContentPane().add(barraProgreso);

		porcentaje = new JLabel("0%");
		porcentaje.setFont(new Font("Tahoma", Font.PLAIN, 15));
		porcentaje.setBounds(26, 97, 264, 14);
		getContentPane().add(porcentaje);
	}

}
