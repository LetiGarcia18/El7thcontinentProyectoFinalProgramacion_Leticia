package pantallas;

import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Ventana extends JFrame{

		
		public Ventana() {
			this.setSize(1000,700); 
			this.setLocationRelativeTo(null);
			this.setTitle("The 7th continent");
			this.setIconImage(new ImageIcon("./iconos/iconoIsla.png").getImage());
			this.setAlwaysOnTop(true);
			this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("./iconos/cursor.png").getImage(),new Point(0,0),"custom cursor"));
			this.setResizable(false); //No deja cambiar el tamaño de la ventana
			this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Esto hace que cuando se cierre la ventana se apague el programa
			this.setContentPane(new Tablero(this));
			this.setVisible(true);
		}


}
