package userinterface;

import java.awt.*;
import javax.swing.*;

public class MainMenuPanel extends JPanel
{
  // Constructor for the contacts panel.
  public MainMenuPanel(MainMenuControl mc)
  {
	    
	    // Create the join game button.
	    JButton joinButton = new JButton("Join Game");
	    joinButton.addActionListener(mc);
	    JPanel joinButtonBuffer = new JPanel();
	    joinButtonBuffer.add(joinButton);
	    
	    // Create the logout button.
	    JButton logoutButton = new JButton("Logout");
	    logoutButton.addActionListener(mc);
	    JPanel logoutButtonBuffer = new JPanel();
	    logoutButtonBuffer.add(logoutButton);
	    
	    // Create the logout button.
	    JButton viewLeaderboardButton = new JButton("View Leaderboard");
	    logoutButton.addActionListener(mc);
	    JPanel viewLeaderboardButtonBuffer = new JPanel();
	    viewLeaderboardButtonBuffer.add(viewLeaderboardButton);

	    // Arrange the components in a grid.
	    JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5));
	    grid.add(joinButton);
	    grid.add(logoutButton);
	    grid.add(viewLeaderboardButton);
	    this.add(grid);
  }
}
