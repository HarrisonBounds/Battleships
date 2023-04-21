package game;

//import javax.swing.*;
//import java.awt.*;
//
//public class Leaderboard extends JTable{
//	private String[] columns;
//	private Object [][] records;
//	private JTable log;
//	private JScrollPane scrollPane;
//
//	public Leaderboard(Object[][] records, String[] columns) {
//		this.records = records;
//		this.columns = columns;
//
//		log = new JTable(records, columns);
//		scrollPane = new JScrollPane(log);
//		
//		JPanel board = new JPanel(new BorderLayout());
//		board.add(scrollPane);
//	}
//}

import javax.swing.*;
import java.awt.*;
import database.*;
 
public class Leaderboard extends JPanel{
    JFrame f;
    JTable j;
    Database db;
 
    // Constructor
    public Leaderboard()
    {
        // Frame initialization
        f = new JFrame();
 
        // Frame Title
        f.setTitle("Leaderboard");
 
        //data?
        //data = database method?
        //How do we update this with the database method?
 
        // Column Names
        String[] columnNames = { "Username", "# of Wins" };
 
        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);
 
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);
        
        JPanel lb = new JPanel(new BorderLayout());
        lb.add(f);
    }
}
