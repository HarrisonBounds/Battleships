package game;

import javax.swing.JTextArea;

public class GameLog {
	JTextArea log;
	JScrollPane scroll;
	
	GameLog() {
		log = new JTextArea();
		scroll = new JScrollPane(log);
		log.setText("Welcome to Battleships... May your ships live long");
	}
	
	public void writeToLog(String message) {
		log.append(message);
	}
}
