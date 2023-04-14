package game;

import javax.swing.*;

public class Leaderboard{
	private String[] columns;
	private Object [][] records;
	private JTable log;
	private JScrollPane scrollPane;

	public Leaderboard(Object[][] records, String[] columns) {
		this.records = records;
		this.columns = columns;

		log = new JTable(records, columns);
		scrollPane = new JScrollPane(log);
	}
}
