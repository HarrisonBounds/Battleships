package game;

import java.awt.*;
import javax.swing.*;

import clientcommunication.ChatClient;

public class GamePanel extends JPanel{
	private ImageIcon icon;
	private JPanel container;
	private ChatClient client;
	private GameController gameControl;
	private GameData gameData;
	private GameLog log;
	private PlayerWaterPanel playerWater;
	private PlayerWaterPanel opponentWater;
	private JButton battleshipsImg;
	/*
	 * the main layout of this panel is the GridLayout.
	 * The pageStart panel will contain a simple graphic for the game.
	 * The lineStart panel will contain the GameLog.
	 * The center panel will contain the centerGrid which will house both WaterPanels.
	 * The lineEnd panel will contain lineEndGrid and the radioBtns panel which will house a visual of the 5 ships, and respected buttons.
	 * https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
	 */
	private JPanel pageStart;
	private JPanel lineStart;
	private JPanel center;
	private JPanel centerGrid; //2 rows, 1 colummn
	private JPanel lineEnd;
	private JPanel lineEndGrid; //5 rows for ships, 1 row for cardLayout
	private JPanel radioBtns;
	/*
	 * cardLayout will be contained within lineEndGrid.
	 * If the user is still placing ships (preShipsPlaced), it will display the rotateShip and placeShip buttons.
	 * Once the user has placed all the ships (postShipsPlaced), it will show only the fire button
	 */
	private CardLayout cardLayout;
	private JPanel cards;
	private JPanel preShipsPlaced; 
	private JPanel postShipsPlaced;
	private JButton fireBtn;
	private final ButtonGroup buttonGrp;
	private JRadioButton alignHorizontal;
	private JRadioButton alignVertical;
	private JButton placeShip;
	
	/*
	 * The following buttons are simply to hold images of battleships for an aesthetic.
	 */
	private JButton ship1;
	private JButton ship2;
	private JButton ship3;
	private JButton ship4;
	private JButton ship5;
	

	public GamePanel(GameController gc, JPanel container) {
		gameData = new GameData();
		//System.out.println(playerWater.getShips());
		// BATTLESHIPS img at the top of the GUI
		battleshipsImg = new JButton(new ImageIcon(this.getClass().getResource("/Battleships.PNG")));
		battleshipsImg.setBackground(Color.BLACK);
		battleshipsImg.setRolloverEnabled(false);
		pageStart = new JPanel(new GridLayout(1,1));
		pageStart.add(battleshipsImg);
		
		// Game Log to the left of the GUI
		log = new GameLog();
		JScrollPane scroll = new JScrollPane(log);
		lineStart = new JPanel(new GridLayout(1,1));
		lineStart.add(log);

		// Game boards in the center of the GUI
		playerWater = new PlayerWaterPanel();
		opponentWater = new PlayerWaterPanel();
		centerGrid = new JPanel(new GridLayout(2,1));
		centerGrid.add(opponentWater);
		centerGrid.add(playerWater);
		center = new JPanel();
		center.add(centerGrid);
		
		// Right hand side of the GUI
		lineEndGrid = new JPanel(new GridLayout(6,1));
		//lineEndGrid = new JPanel(new GridBagLayout());
		lineEnd = new JPanel();
		
		ship1 = new JButton(new ImageIcon(this.getClass().getResource("/Ship2.PNG")));
		ship1.setPreferredSize(new Dimension(5,10));
		ship2 = new JButton(new ImageIcon(this.getClass().getResource("/Ship2.PNG")));
		ship2.setPreferredSize(new Dimension(5,10));
		ship3 = new JButton(new ImageIcon(this.getClass().getResource("/Ship3.PNG")));
		ship3.setPreferredSize(new Dimension(5,10));
		ship4 = new JButton(new ImageIcon(this.getClass().getResource("/Ship4.PNG")));
		ship4.setPreferredSize(new Dimension(5,10));
		ship5 = new JButton(new ImageIcon(this.getClass().getResource("/Ship5.PNG")));
		ship5.setPreferredSize(new Dimension(5,10));
		lineEndGrid.add(ship1);
		lineEndGrid.add(ship2);
		lineEndGrid.add(ship3);
		lineEndGrid.add(ship4);
		lineEndGrid.add(ship5);
		
		// Bottom right hand side of the GUI
		preShipsPlaced = new JPanel();
		radioBtns = new JPanel(new GridLayout(2,1));
		alignHorizontal = new JRadioButton("Horizontal");
		alignHorizontal.setSelected(true);
		alignVertical = new JRadioButton("Vertical");
		placeShip = new JButton("Place Ship");
		placeShip.addActionListener(gc);
		
		buttonGrp = new ButtonGroup();
		buttonGrp.add(alignHorizontal);
		buttonGrp.add(alignVertical);
		
		radioBtns.add(alignHorizontal);
		radioBtns.add(alignVertical);
		preShipsPlaced.add(radioBtns);
		preShipsPlaced.add(placeShip);
		
		postShipsPlaced = new JPanel();
		fireBtn = new JButton("FIRE");
		fireBtn.addActionListener(gc);
		postShipsPlaced.add(fireBtn);
		
		cardLayout = new CardLayout();
		cards = new JPanel(cardLayout);
		cards.add(preShipsPlaced, "preShipsPlaced");
		cards.add(postShipsPlaced, "postShipsPlaced");
		//cardLayout.add("preShipsPlaced", preShipsPlaced);
		//cardLayout.add("postShipsPlaced", postShipsPlaced);
		
		cardLayout.show(cards, "preShipsPlaced");
		
		lineEndGrid.add(cards);
		lineEnd.add(lineEndGrid);
		
		//adding each individual panel to the main JPanel
		this.setLayout(new BorderLayout());
		this.add(pageStart, BorderLayout.PAGE_START);
		this.add(lineStart, BorderLayout.LINE_START);
		this.add(center, BorderLayout.CENTER);
		this.add(lineEnd, BorderLayout.LINE_END);
	}
	/*
	 * The purpose of this function is to get the user's preferred alignment for their ships.
	 * The value is retrieved from the pair of radio buttons (alignHorizontal/alignVertical)
	 */
	public String getAlignment() {
		String alignment = "";
		
		if (alignHorizontal.isSelected()) {
			return alignment = "Horizontal";
		}
		else {
			return alignment = "Vertical";
		}
	}
	
	public PlayerWaterPanel getPlayerWater() {
		return this.playerWater;
	}
	
	public JPanel getCards() {
		return this.cards;
	}
}
