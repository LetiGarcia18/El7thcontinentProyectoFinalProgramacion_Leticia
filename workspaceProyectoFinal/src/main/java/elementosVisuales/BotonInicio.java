package elementosVisuales;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class BotonInicio extends JButton{
	
	public BotonInicio(String m) {
		super(m);
		estiloPorDefecto();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(new Color(255,195,0));
				setFont(new Font("Vladimir Script", Font.PLAIN, 40));
				setSize(50,50);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				estiloPorDefecto();
			}
		});
	}
	
	private void estiloPorDefecto() {
		this.setForeground(new Color(211, 47, 12));
		this.setFont(new Font("Vladimir Script", Font.PLAIN, 40));
		this.setBackground(new Color(255, 160, 122));
		this.setSize(50,37);
		
	}
	

}
