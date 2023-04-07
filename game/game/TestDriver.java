package game;

import javax.swing.JFrame;

public class TestDriver extends JFrame {

	public TestDriver() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel gp = new GamePanel();
		
		this.add(gp);
		setSize(400, 450);
		setVisible(true);
	}
}
