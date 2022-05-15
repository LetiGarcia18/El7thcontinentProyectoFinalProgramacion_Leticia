package pantallas;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.Color;
import java.awt.Canvas;
import javax.swing.JDesktopPane;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.GridBagLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import java.awt.Button;
import javax.swing.JLabel;
import java.awt.Font;

public class Tablero extends JPanel{

	//private CartaTerreno[][] terrenos;

	private Ventana ventana;
	private int margenDerecho = 20;

	public Tablero(Ventana v) {
		
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Mi pedazo de mapa");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitulo.setBounds(margenDerecho, 20, 150, 13);
		add(lblTitulo);
		
		String[][] colores = {{"blue","brown","green","grey","orange"},
				{"grey","orange","pink","purple","red"},{"purple","red","yellow","blue","brown"},
				{"blue","brown","green","grey","orange"},{"grey","orange","pink","purple","red"}};
		/*ArrayList<String> colores = new ArrayList<String>(
				Arrays.asList("blue", "brown", "green", "grey", "orange", "pink", "purple", "red", "yellow")
				);*/
		
		dibujarCuadradosPrueba(colores, margenDerecho, 100, 100);
	}
	
	public void dibujarCuadradosPrueba(String[][] colores, int x, int y, int ladoCuadrado) {
		for (int i = 0; i < colores.length; i++) {
			for(int j = 0; j < colores[i].length; j++) {
				String color = colores[i][j];
				JLabel image = new JLabel(new ImageIcon("cuadrados/" + color + ".jpg"));
				image.setBounds((ladoCuadrado * i) + x , ladoCuadrado + (y * j), ladoCuadrado, ladoCuadrado);
				add(image);
			}
			/*String color = colores.get(i);
			JLabel image = new JLabel(new ImageIcon("cuadrados/" + color + ".jpg"));
			image.setBounds((ladoCuadrado * i) + x, y, ladoCuadrado, ladoCuadrado);
			add(image);*/
		}
		
		
	}
}
