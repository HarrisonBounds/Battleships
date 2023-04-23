package game;

import java.util.ArrayList;

public class GameData {
	private ArrayList<String[]> waterCoordinates;
	
	public GameData(ArrayList<String[]> waterCoordinates) {
		this.waterCoordinates = waterCoordinates;
	}
	
	public ArrayList<String[]> getWaterCoordinates() {
		return this.waterCoordinates;
	}
	
}
