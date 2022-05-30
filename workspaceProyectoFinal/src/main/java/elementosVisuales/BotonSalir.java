package elementosVisuales;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class BotonSalir extends JButton{
	
	public BotonSalir(String m) {
		super(m);
		
		this.setBounds(20, 10, 140, 19);
		this.setFont(new Font("Rockwell", Font.PLAIN, 15));
		setBackground(new Color(255, 96, 65));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}
		

}
