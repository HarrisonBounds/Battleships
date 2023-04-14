package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GameController implements ActionListener {
	
	private JPanel gp;
	
	public GameController(JPanel container) {
		gp = container;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equalsIgnoreCase("Place Ship")) {
			//GamePanel gp = 
		}
		
		
		
		
	}

}
