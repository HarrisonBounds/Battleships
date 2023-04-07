package game;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel{
	private GameController control;
	private GameLog log;
	//private WaterPanel playerWater;
	//private WaterPanel opponentWater;
	private JButton battleshipsImg;
	/*
	 * the main layout of this panel is the GridLayout.
	 * The pageStart panel will contain a simple graphic for the game.
	 * The lineStart panel will contain the GameLog.
	 * The center panel will contain the centerGrid which will house both WaterPanels.
	 * The lineEnd panel will contain lineEndGrid which will house a visual of the 5 ships, and respected buttons.
	 * https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
	 */
	private JPanel pageStart;
	private JPanel lineStart;
	private JPanel center;
	private JPanel centerGrid; //2 rows, 1 colummn
	private JPanel lineEnd;
	private JPanel lineEndGrid; //5 rows for ships, 1 row for cardLayout
	/*
	 * cardLayout will be contained within lineEndGrid.
	 * If the user is still placing ships (preShipsPlaced), it will display the rotateShip and placeShip buttons.
	 * Once the user has placed all the ships (postShipsPlaced), it will show only the fire button
	 */
	//private CardLayout cardLayout;
	private JPanel preShipsPlaced; 
	private JPanel postShipsPlaced;
	private JButton fireBtn;
	private JButton rotateShip;
	private JButton placeShip;

	public GamePanel() {
		control = new GameController();
		this.setController(control);

		battleshipsImg = new JButton(new ImageIcon(this.getClass().getResource("C:\\Users\\adney\\git\\Battleships\\game\\Battleships.PNG")));
		pageStart = new JPanel();
		pageStart.add(battleshipsImg);

		log = new GameLog();
		lineStart = new JPanel();
		lineStart.add(log);

		//adding each individual panel to the main JPanel
		this.setLayout(new BorderLayout());
		this.add(pageStart, BorderLayout.PAGE_START);
		this.add(lineStart, BorderLayout.LINE_START);
	}

	public void setController(GameController control) {
		this.control = control;
	}
}
