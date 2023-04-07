package game;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Leaderboard{
	private String[] columns;
	private Object [][] records;
	private JTable log;
	private JScrollPane scrollPane;

	public Leaderboard(records, columns) {
		this.records = records;
		this.columns = columns;

		log = new JTable(records, columns)
		scrollPane = new ScrollPane(log);
	}
}
