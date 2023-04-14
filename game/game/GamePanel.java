package game;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel{
	private GameController control;
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
	private JPanel cardLayout;
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
	

	public GamePanel() {
		control = new GameController();
		this.setController(control);

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
		centerGrid.add(playerWater);
		centerGrid.add(opponentWater);
		center = new JPanel();
		center.add(centerGrid);
		
		// Right hand side of the GUI
		lineEndGrid = new JPanel(new GridLayout(6,1));
		lineEnd = new JPanel();
		
		ship1 = new JButton();
		ship2 = new JButton();
		ship3 = new JButton();
		ship4 = new JButton();
		ship5 = new JButton();
		lineEndGrid.add(ship1);
		lineEndGrid.add(ship2);
		lineEndGrid.add(ship3);
		lineEndGrid.add(ship4);
		lineEndGrid.add(ship5);
		
		preShipsPlaced = new JPanel();
		radioBtns = new JPanel(new GridLayout(2,1));
		alignHorizontal = new JRadioButton("Horizontal");
		alignHorizontal.setSelected(true);
		alignVertical = new JRadioButton("Vertical");
		placeShip = new JButton("Place Ship");
		
		buttonGrp = new ButtonGroup();
		buttonGrp.add(alignHorizontal);
		buttonGrp.add(alignVertical);
		
		radioBtns.add(alignHorizontal);
		radioBtns.add(alignVertical);
		preShipsPlaced.add(radioBtns);
		preShipsPlaced.add(placeShip);
		
		postShipsPlaced = new JPanel();
		fireBtn = new JButton("FIRE");
		postShipsPlaced.add(fireBtn);
		
		cardLayout = new JPanel(new CardLayout());
		cardLayout.add("preShipsPlaced", preShipsPlaced);
		cardLayout.add("postShipsPlaced", postShipsPlaced);
		
		lineEndGrid.add(cardLayout);
		lineEnd.add(lineEndGrid);
		
		
		//adding each individual panel to the main JPanel
		this.setLayout(new BorderLayout());
		this.add(pageStart, BorderLayout.PAGE_START);
		this.add(lineStart, BorderLayout.LINE_START);
		this.add(center, BorderLayout.CENTER);
		this.add(lineEnd, BorderLayout.LINE_END);
	}

	public void setController(GameController control) {
		this.control = control;
	}
}
