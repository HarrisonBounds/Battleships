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
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

import database.*;
 
public class Leaderboard extends JPanel{
    JFrame f;
    JTable j;
    Database db;
 
    // Constructor
    public Leaderboard()
    {
    	  f = new JFrame();
    	  f.setTitle("Leaderboard");
    	  
    	  DefaultTableModel model = new DefaultTableModel(new String[]{"User", "Wins"}, 0);
    	  ArrayList<String> lb = db.getLeaderboard();
    	  //lb[0] = user & lb[1] = wins??
    	  model.addRow(new Object[]{lb.get(0), lb.get(1)});
    	  
    	  j = new JTable();
    	  j.setBounds(30, 40, 200, 300);
    	  
    	  j.setModel(model);
    	  
    	  JScrollPane sp = new JScrollPane(j);
    	  f.add(sp);
    	  f.setSize(500, 200);
    	  f.setVisible(true);
    	  
    	  JPanel leaderboard = new JPanel(new BorderLayout());
    	  leaderboard.add(f);
    	  
    	  
//        // Frame initialization
//        f = new JFrame();
// 
//        // Frame Title
//        f.setTitle("Leaderboard");
// 
//        //data?
//        
//        //How do we update this with the database method?
//        
//        // Column Names
//        String[] columnNames = { "Username", "# of Wins" };
// 
//        // Initializing the JTable
//        //j = new JTable(data, columnNames);
//        j.setBounds(30, 40, 200, 300);
// 
//        // adding it to JScrollPane
//        JScrollPane sp = new JScrollPane(j);
//        f.add(sp);
//        // Frame Size
//        f.setSize(500, 200);
//        // Frame Visible = true
//        f.setVisible(true);
//        
//        JPanel lb = new JPanel(new BorderLayout());
//        lb.add(f);
    }
}
