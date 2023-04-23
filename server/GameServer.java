package server;

import java.awt.*;
import database.*;
import game.*;
import userinterface.*;
import userinterface.Error;
import javax.swing.*;
import java.io.IOException;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import java.util.*;

public class GameServer extends AbstractServer{



	private JTextArea log;
	private JLabel status;
	private boolean running = false;
	private Database db;
	private ConnectionToClient player1;
	private ConnectionToClient player2;
	private ArrayList<String[]> player1Coords;
	private ArrayList<String[]> player2Coords;
	private int p1Ships;
	private int p2Ships;

	private HashMap<ConnectionToClient, String> clientList;


	// Constructor for initializing the server with default settings.
	public GameServer() 
	{
		super(12345);
		this.setTimeout(500);

		clientList = new HashMap<ConnectionToClient, String>();
		
		p1Ships = 5;
		p2Ships = 5;
		
	}

	//Setter for database
	void setDatabase(Database db)
	{
		this.db = db;
	}

	// Getter that returns whether the server is currently running.
	public boolean isRunning()
	{
		return running;
	}

	// Setters for the data fields corresponding to the GUI elements.
	public void setLog(JTextArea log)
	{
		this.log = log;
	}
	
	public void setStatus(JLabel status)
	{
		this.status = status;
	}

	// When the server starts, update the GUI.
	public void serverStarted()
	{
		running = true;
		status.setText("Listening");
		status.setForeground(Color.GREEN);
		log.append("Server started...\n");
	}

	// When the server stops listening, update the GUI.
	public void serverStopped()
	{
		status.setText("Stopped");
		status.setForeground(Color.RED);
		log.append("Server stopped accepting new clients - press Listen to start accepting new clients\n");
	}

	// When the server closes completely, update the GUI.
	public void serverClosed()
	{
		running = false;
		status.setText("Close");
		status.setForeground(Color.RED);
		log.append("Server and all current clients are closed - press Listen to restart\n");
	}

	// When a client connects or disconnects, display a message in the log.
	public void clientConnected(ConnectionToClient client)
	{
		if (player1.equals(null))
		{
			player1 = client;
		}
		else
		{
			player2 = client;
		}
		
		log.append("Client " + client.getId() + " connected\n");
		
		try {
			client.sendToClient(log);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// When a message is received from a client, handle it.
	protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) 
	{
		// If we received LoginData, verify the account information.
		if (arg0 instanceof LoginData)
		{
			// Check the username and password with the database.
			LoginData data = (LoginData)arg0;
			Object result;
			if (db.verifyAccount(data.getUsername(), data.getPassword()))
			{
				result = "LoginSuccessful";
				log.append("Client " + arg1.getId() + " successfully logged in as " + data.getUsername() + "\n");
				clientList.put(arg1, data.getUsername());
			}
			else
			{
				result = new Error("The username and password are incorrect.", "Login");
				log.append("Client " + arg1.getId() + " failed to log in\n");
			}

			// Send the result to the client.
			try
			{
				arg1.sendToClient(result);
			}
			catch (IOException e)
			{
				return;
			}
		}		
		
		// If we received CreateAccountData, create a new account.
		else if (arg0 instanceof CreateAccountData)
		{
			// Try to create the account.
			CreateAccountData data = (CreateAccountData)arg0;
			Object result;
			if (db.createNewAccount(data.getUsername(), data.getPassword()))
			{
				result = "CreateAccountSuccessful";
				log.append("Client " + arg1.getId() + " created a new account called " + data.getUsername() + "\n");
			}
			else
			{
				result = new Error("The username is already in use.", "CreateAccount");
				log.append("Client " + arg1.getId() + " failed to create a new account\n");
			}

			// Send the result to the client.
			try
			{
				arg1.sendToClient(result);
			}
			catch (IOException e)
			{
				return;
			}
		}
		
		else if (arg0 instanceof GameData)
		{
			GameData data = (GameData)arg0;
			
			if (arg1.equals(player1))
			{
				player1Coords = data.getWaterCoordinates();
			}
			else if (arg1.equals(player2))
			{
				player2Coords = data.getWaterCoordinates();
			}
		} 
		
		else if (arg0 instanceof String)
		{
			String fireCoord = (String)arg0;
			String result;
			
			if (arg1.equals(player1))
			{
				result = checkForHit(fireCoord, player2Coords);
				
				if (result.equals("Sink"))
				{
					p2Ships--;
					
					if (p2Ships == 0)
					{
						result = "Winner";
					}
				}
				
				try
				{
					player1.sendToClient(result);
				}
				catch (IOException e)
				{
					return;
				}
			}
			if (arg1.equals(player2))
			{
				result = checkForHit(fireCoord, player1Coords);
				
				if (result.equals("Sink"))
				{
					p1Ships--;
					
					if (p1Ships == 0)
					{
						result = "Winner";
					}
				}
				
				try
				{
					player2.sendToClient(result);
				}
				catch (IOException e)
				{
					return;
				}
			}

		}
	}

	/*
	 * public void setupGame(ConnectionToClient client) { // result to send back to
	 * client
	 * 
	 * Object result;
	 * 
	 * 
	 * // if player 1 has not been initialized, set player1 as the client that just
	 * connected if (player1.equals(null)) { player1 = client;
	 * log.writeToLog("Player 1: " + clientList.get(player1) + "ready!\n");
	 * 
	 * // send a result that would grey out the fire button and write a waiting for
	 * opponent message on log result = "WaitingForOpponent";
	 * 
	 * try { player1.sendToClient(result); } catch (IOException e) { return; }
	 * 
	 * } // if player 1 has been initialized, set player2 as the client that just
	 * connected else { player2 = client; log.writeToLog("Player 2: " +
	 * clientList.get(player2) + "ready!\n");
	 * log.writeToLog("All players ready!\n");
	 * 
	 * // allow the game to start by making the fire button visible and write to log
	 * 
	 * result = "StartGame";
	 * 
	 * try { // send result to both players player1.sendToClient(result);
	 * player2.sendToClient(result); } catch (IOException e) { return; }
	 * 
	 * } }
	 */
	
	// Method that handles listening exceptions by displaying exception information.
	public void listeningException(Throwable exception) 
	{
		running = false;
		status.setText("Exception occurred while listening");
		status.setForeground(Color.RED);
		log.append("Listening exception: " + exception.getMessage() + "\n");
		log.append("Press Listen to restart server\n");
	}
	
	public boolean isSink(String[] arr)
	{
		for (int i = 1; i<arr.length; i++)
		{
			if (arr[i-1] != arr[i])
			{
				return false;
			}
		}
		return true;
	}
	
	public String checkForHit(String fireCoord, ArrayList<String[]> shipCoords) 
	{
		String result = "Miss";
		
		for (String[] coords : shipCoords)
		{
			for (int i = 0; i < coords.length; i++)
			{
				if (fireCoord.equals(coords[i]))
				{
					coords[i] = "HIT";
					result = "Hit";
				}
			}
			
			if (isSink(coords))
			{
				result = "Sink";
			}
		}
		
		return result;
	}

}
