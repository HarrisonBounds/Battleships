package game;

import java.io.Serializable;

public class PlayerWaterPanelData implements Serializable{
	private String fireLocation;
	
	public PlayerWaterPanelData(String fireLocation) {
		this.fireLocation = fireLocation;
	}
	
	public String getFireLocation() {
		return this.fireLocation;
	}
	
}
