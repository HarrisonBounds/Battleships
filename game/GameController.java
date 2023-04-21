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
				//after placing a ship, the shipCounter gets automatically incremented
			}
			/*
			 * when we shipCounter is equal to 4, we have 5 ships on the board
			 * we can let the server know we're ready
			 */
			else if (pwp.getShipCounter() == 5) {

//				try {
//					client.sendToServer("Ready");
					//data = new GameData(pwp.getShipCoordinates());
					System.out.println(pwp.getShipCoordinates());
					//then we change the button to FIRE
					CardLayout cardLayout = (CardLayout)gp.getCards().getLayout();
					cardLayout.show(gp.getCards(), "postShipsPlaced");
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
			}
		}
		if (command.equalsIgnoreCase("FIRE")) {
			//send the server the location of the fire
		}
	}
}