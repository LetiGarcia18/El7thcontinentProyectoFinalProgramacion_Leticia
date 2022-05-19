package pantallas;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Splash extends JDialog {

	private JLabel SplashScreen;
	private JProgressBar barraProgreso;
	private JLabel porcentaje;
	private JLabel porcentaje2;

	public Splash() {
		inicializar();
		setSize(300, 150);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setUndecorated(true);

		inicialHilo();
	}

	private void inicialHilo() {
		Thread hilo = new Thread(new Runnable() {
			int x = 0; 

			public void run() {
				
				try {
					while(x <= 100) {
						barraProgreso.setValue(x);
						porcentaje.setText(x + "%");
						x++;
						Thread.sleep(100);
					}
					
					dispose();
					Ventana ventanaCuadros = new Ventana();
					ventanaCuadros.setVisible(true);
				}catch (Exception e) {
					System.out.println("Excepción " + e.getMessage());
				}
				
			}
				
		
			
		});
		hilo.start();
	}

	private void inicializar() {
		SplashScreen = new JLabel("Pantalla de carga");
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
