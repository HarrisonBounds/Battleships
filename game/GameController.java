package game;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import clientcommunication.GameClient;

public class GameController implements ActionListener {

	private JPanel container;
	private GameClient client;
	private GameData gameData;
	private PlayerWaterPanelData pwPanelData;

	public GameController(JPanel container, GameClient client) {
		this.container = container;
		this.client = client;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		GamePanel gp = (GamePanel)container.getComponent(3);
		PlayerWaterPanel opponent = gp.getOpponentWater();
		PlayerWaterPanel pwp = gp.getPlayerWater();

		if (command.equalsIgnoreCase("Place Ship")) {
			/*
			 * setShipFlagTrue triggers the placement of a ship
			 * it takes in the argument of the current alignment ("horizontal"/"vertical")
			 */
			if (pwp.setShipFlagTrue(gp.getAlignment())) {
				//after placing a ship, the shipCounter gets automatically incremented
				//				if (pwp.getShipCounter() == 5) {
				//					try {
				//						data = new GameData(pwp.getShipCoordinates());
				//						client.sendToServer(data);
				//						//then we change the button to FIRE
				//						//CardLayout cardLayout = (CardLayout)gp.getCards().getLayout();
				//						//cardLayout.show(gp.getCards(), "postShipsPlaced");
				//					} catch (IOException e1) {
				//						// TODO Auto-generated catch block
				//						e1.printStackTrace();
				//					}
				//				}
				/*
				 * when we shipCounter is equal to 4, we have 5 ships on the board
				 * we can let the server know we're ready
				 */

			}
		}

		if (command.equalsIgnoreCase("Ready"))
		{
			if (pwp.getShipCounter() == 5) {
				try {
					gameData = new GameData(pwp.getShipCoordinates());
					client.sendToServer(gameData);
					//then we change the button to FIRE
					//CardLayout cardLayout = (CardLayout)gp.getCards().getLayout();
					//cardLayout.show(gp.getCards(), "postShipsPlaced");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (command.equalsIgnoreCase("FIRE")) {
			/*
			 * setFireFlag triggers the firing of a ship
			 * It assigns the location of the fire so we can then send that location to the server
			 */
			//opponent.setFireFlagTrue();
			String fireLocation = opponent.getFireLocation();
			System.out.println(fireLocation);
			pwPanelData = new PlayerWaterPanelData(fireLocation);

			try {
				client.sendToServer(pwPanelData);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void startGame()
	{
		GamePanel gp = (GamePanel)container.getComponent(3);
		CardLayout cardLayout = (CardLayout)gp.getCards().getLayout();
		cardLayout.show(gp.getCards(), "postShipsPlaced");
	}
	public void setLog(JTextArea log)
	{
		GamePanel gp = (GamePanel)container.getComponent(3);
		gp.setLog(log);
	}
	public void markHit(String fireLocation)
	{
		GamePanel gp = (GamePanel)container.getComponent(3);
		PlayerWaterPanel opponent = gp.getOpponentWater();

		opponent.isHit(fireLocation);
	}
	public void markMiss(String fireLocation)
	{
		GamePanel gp = (GamePanel)container.getComponent(3);
		PlayerWaterPanel opponent = gp.getOpponentWater();

		opponent.isMiss(fireLocation);
	}
	public void announceWinner(String username)
	{
		GamePanel gp = (GamePanel)container.getComponent(3);
		gp.announceWinner(username);
	}
}