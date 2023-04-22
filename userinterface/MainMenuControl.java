package userinterface;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import clientcommunication.*;
import game.*;

public class MainMenuControl implements ActionListener {
	// Private data field for storing the container.
	  private JPanel container;
	  private ChatClient client;
	  // Constructor for the main menu controller.
	  public MainMenuControl(JPanel container, ChatClient client)
	  {
	    this.container = container;
	    this.client = client;
	  }
	  
	  // Handle button clicks.
	  public void actionPerformed(ActionEvent ae)
	  {
	    // Get the name of the button clicked.
	    String command = ae.getActionCommand();
	    
	    // The join game button takes the user to the game panel.
	    if (command.equals("Join Game"))
	    {
	      //not sure if I need this below?
	      //MainMenuPanel mainMenuPanel = (MainMenuPanel)container.getComponent(3); //game panel component number
	      //set error?
	      CardLayout cardLayout = (CardLayout)container.getLayout();
	      cardLayout.show(container, "4"); //container number
	     
	    }
	    
	    // The Logout button takes the user back to the initial panel
	    else if (command.equals("Logout"))
	    {
	    	CardLayout cardLayout = (CardLayout)container.getLayout();
	        cardLayout.show(container, "1");
	    }
	    
	    else if (command.equals("View Leaderboard"))
	    {
	    	//Wait for implementation of viewLeaderboardPanel
//	      CreateAccountPanel createAccountPanel = (CreateAccountPanel)container.getComponent(2);
//	      createAccountPanel.setError("");
//	      CardLayout cardLayout = (CardLayout)container.getLayout();
//	      cardLayout.show(container, "3");
	    }
	  }
}
