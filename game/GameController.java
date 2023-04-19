package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import clientcommunication.ChatClient;

public class GameController implements ActionListener {

	private JPanel container;
	private ChatClient client;
	private GameData data;

	public GameController(JPanel container, ChatClient client) {
		this.container = container;
		this.client = client;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equalsIgnoreCase("Place Ship")) {
			GamePanel gp = (GamePanel)container.getComponent(3);
			PlayerWaterPanel pwp = gp.getPlayerWater();
			JButton source = (JButton) e.getSource();
			System.out.println("pressed");

			if (pwp.getShipFlag()) {
				System.out.println(pwp.getShipCounter());
				if (pwp.addShip(source, pwp.getShipAtIndex(pwp.getShipCounter()), gp.getAlignment())) {
					pwp.setShipCounter(pwp.getShipCounter()+1);
				}
				else {
				}
			}

		}		
	}

}