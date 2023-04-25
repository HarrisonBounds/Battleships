package game;

import java.io.Serializable;
import java.util.ArrayList;

public class GameData implements Serializable{
	private ArrayList<String[]> waterCoordinates;
	
	public GameData(ArrayList<String[]> waterCoordinates) {
		this.waterCoordinates = waterCoordinates;
	}
	
	public ArrayList<String[]> getWaterCoordinates() {
		return this.waterCoordinates;
	}
	
}
