package game;

import javax.swing.JFrame;

public class TestDriver extends JFrame {

	public TestDriver() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel gp = new GamePanel();
		//GameController gc = new GameController();
		//gp.setController(gc);

		this.add(gp);
		this.setSize(950, 500);
		setVisible(true);
	}




	public static void main(String[] args) {
		TestDriver td = new TestDriver();
	}
}