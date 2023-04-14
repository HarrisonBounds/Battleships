package game;

public class GameData {
	private Ship[] ships;
	private PlayerWaterPanel water;

	public GameData() {
		// Creating the 5 ships
		ships = new Ship[5];

		for(int i = 0; i < ships.length; i ++) {
			if(i == 0) {
				ships[i] = new Ship(2);
			}
			else {
				ships[i] = new Ship(i + 1);
			}
		}
		water.setShips(ships);
	}
}
