package pantallas;

import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Canvas;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.JProgressBar;

public class Ventana extends JFrame{

		
		public Ventana() {
			this.setSize(1000,700); 
			this.setLocationRelativeTo(null);
			this.setTitle("The 7th continent");
			this.setIconImage(new ImageIcon("./iconos/iconoIsla.png").getImage());
			this.setAlwaysOnTop(true);
			//this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("./iconos/cursor.png").getImage(),new Point(0,0),"custom cursor"));
			this.setResizable(false); //No deja cambiar el tamaño de la ventana
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
			this.setContentPane(new Tablero(this));
			this.setVisible(true);
		}


}
