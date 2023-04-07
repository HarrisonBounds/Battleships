package game;

public class GamePanel {
	private GameLog log;
	private WaterPanel playerWater;
	private WaterPanel opponentWater;
	
	private JButton fireBtn;
	private JButton rotateShip;
	private JButton placeShip;
	/*
	 * the main layout of this GUI is the GridLayout.
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
	private JPanel lineEndGrid; //5 rows for ships, 1 row for fire button
	/*
	 * cardLayout will be contained within lineEndGrid.
	 * If the user is still placing ships, it will display the rotateShip and placeShip buttons.
	 * Once the user has placed all the ships, it will show only the fire button
	 */
	private CardLayout cardLayout;
	
	public GamePanel() {
		
	}
	

}

public static void main(String args[]) {
	
}
