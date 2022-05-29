package pantallas;

import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaMenus extends JFrame{
	private HashMap<String, JPanel> pantallas;
	
	
	public VentanaMenus() {
		pantallas = new HashMap<String, JPanel>();
		pantallas.put("menuInicio", new MenuPrincipal(this));
		
		this.setSize(1400,850); 
		this.setLocationRelativeTo(null);
		this.setTitle("The 7th continent");
		this.setIconImage(new ImageIcon("./iconos/iconoIsla.png").getImage());
		this.setAlwaysOnTop(true);
		//this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("./iconos/cursor.png").getImage(),new Point(0,0),"custom cursor"));
		this.setResizable(false); //No deja cambiar el tamaño de la ventana
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setContentPane(new MenuPrincipal(this));
		this.setContentPane(this.pantallas.get("menuInicio")); 
		
		this.setVisible(true);
	}
	
	public void cambiarAPantalla(String nombrePantalla) {
		Iterator it = this.pantallas.values().iterator();
		while(it.hasNext()) { //Con esto recorremos todas las pantallas y ponemos su visible a falso
			JPanel actual = (JPanel)it.next();
			actual.setVisible(false);
		}
		this.pantallas.get(nombrePantalla).setVisible(true); //Esta nos muestra la pantalla que queremos
		this.setContentPane(this.pantallas.get(nombrePantalla));
	}
}
