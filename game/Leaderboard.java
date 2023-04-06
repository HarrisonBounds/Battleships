package game;

public class Leaderboard extends JTable{
	private String[] columns;
	private Object [][] records;
	private JScrollPane scrollPane;
	
	public Leaderboard(records, columns) {
		this.records = records;
		this.columns = columns;
		
		scrollPane = new ScrollPane(this);
	}
	
}
