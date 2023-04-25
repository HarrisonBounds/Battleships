package game;

import javax.swing.*;

public class GameLog extends JTextArea{
	JTextArea log;
	JScrollPane scroll;
	
	GameLog() {
		this.setText("[server]: Welcome to Battleships... May your ships live long");
		this.setEditable(false);
		//log = new GameLog();
		
		//scroll = new JScrollPane(this);
	}
	
	public void writeToLog(String message) {
		this.append("[server]:" + message);
		//log.append("[server]:" + message);
	}
	
	public void setLog(JTextArea log)
	{
		this.log = log;
	}
}
