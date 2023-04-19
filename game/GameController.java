package game;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
		GamePanel gp = (GamePanel)container.getComponent(3);
		PlayerWaterPanel pwp = gp.getPlayerWater();

		if (command.equalsIgnoreCase("Place Ship")) {
			/*
			 * setShipFlagTrue triggers the placement of a ship
			 * it takes in the argument of the current alignment ("horizontal"/"vertical")
			 */
			if (pwp.setShipFlagTrue(gp.getAlignment())) {
				//we then must increment the shipCounter
				//pwp.setShipCounter(pwp.getShipCounter()+1);
			}
			//when we have 5 ships on the board, alert the server that this client is ready
			else if (pwp.getShipCounter() == 4) {

//				try {
//					client.sendToServer("Ready");
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				//then we change the button to FIRE
				CardLayout cardLayout = (CardLayout)gp.getCards().getLayout();
				cardLayout.show(gp.getCards(), "postShipsPlaced");
			}
		}		
	}
}