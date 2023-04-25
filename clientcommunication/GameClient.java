package clientcommunication;

import javax.swing.JTextArea;

import game.GameController;
import ocsf.client.AbstractClient;
import userinterface.*;
import userinterface.Error;

public class GameClient extends AbstractClient
{
  // Private data fields for storing the GUI controllers.
  private LoginControl loginControl;
  private CreateAccountControl createAccountControl;
  private GameController gameControl;

  // Setters for the GUI controllers.
  public void setLoginControl(LoginControl loginControl)
  {
    this.loginControl = loginControl;
  }
  public void setCreateAccountControl(CreateAccountControl createAccountControl)
  {
    this.createAccountControl = createAccountControl;
  }
  
  public void setGameController(GameController gc) {
	  this.gameControl = gc;
  }

  // Constructor for initializing the client with default settings.
  public GameClient()
  {
    super("localhost", 8300);
  }
  
  // Method that handles messages from the server.
  public void handleMessageFromServer(Object arg0)
  {
    // If we received a String, figure out what this event is.
    if (arg0 instanceof String)
    {
      // Get the text of the message.
      String message = (String)arg0;
      
      // If we successfully logged in, tell the login controller.
      if (message.equals("LoginSuccessful"))
      {
        loginControl.loginSuccess();
      }
      
      // If we successfully created an account, tell the create account controller.
      else if (message.equals("CreateAccountSuccessful"))
      {
        createAccountControl.createAccountSuccess();
      }
      else if (message.equals("StartGame"))
      {
    	  gameControl.startGame();
      }
    }
    
    // If we received an Error, figure out where to display it.
    else if (arg0 instanceof Error)
    {
      // Get the Error object.
      Error error = (Error)arg0;
      
      // Display login errors using the login controller.
      if (error.getType().equals("Login"))
      {
        loginControl.displayError(error.getMessage());
      }
      
      // Display account creation errors using the create account controller.
      else if (error.getType().equals("CreateAccount"))
      {
        createAccountControl.displayError(error.getMessage());
      }
    }
    else if (arg0 instanceof String[])
    {
    	String[] data = (String[])arg0;
    	String fireLocation = (String)data[0];
    	String hitCheck = (String)data[1];
    	String username = (String)data[2];
    	System.out.println("chatClient fireloc: " + fireLocation + " hitCheck: " + hitCheck + "\n");
    	
    	if (hitCheck.equals("Hit") || hitCheck.equals("Sink"))
    	{
    		gameControl.markHit(fireLocation);
    	}
    	else if (hitCheck.equals("Miss"))
    	{
    		gameControl.markMiss(fireLocation);
    	}
    	else if (hitCheck.equals("Winner"))
    	{
    		gameControl.markHit(fireLocation);
    		gameControl.announceWinner(username);
    	}
    }
  }  
}
