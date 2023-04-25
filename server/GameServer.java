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
	private ConnectionToClient player;
	private ConnectionToClient opp;
	private ArrayList<String[]> p1Coords;
	private ArrayList<String[]> p2Coords;
	private ArrayList<ConnectionToClient> clientList;
	private int p1Ships;
	private int p2Ships;
	private int playerNum;

	private HashMap<String, ConnectionToClient> playerList;


	// Constructor for initializing the server with default settings.
	public GameServer() 
	{
		super(12345);
		this.setTimeout(500);

		p1Ships = 5;
		p2Ships = 5;
		playerNum = 1;

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


		log.append("Client " + client.getId() + " connected\n");

		//		try {
		//			client.sendToClient(log);
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
	}

	public void clientDisconnected(ConnectionToClient client)
	{


		log.append("Client " + client.getId() + " disconnected\n");

		//		try {
		//			client.sendToClient(log);
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
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
			Long test;
			if (db.verifyAccount(data.getUsername(), data.getPassword()))
			{
				result = "LoginSuccessful";
				log.append("Client " + arg1.getId() + " successfully logged in as " + data.getUsername() + "\n");
				//clientList.put(arg1, data.getUsername());
				arg1.setInfo("username", data.getUsername());
				arg1.setInfo("player", playerNum);
				playerNum++;
				//clientList.add(arg1);

//				if (clientList.size() == 2)
//				{
//					setupGame(clientList);
//				}

				//				test = player1.getId();
				//				
				//				if (test.equals(null))
				//				{
				//					player1 = arg1;
				//					log.append("Player 1: " + data.getUsername());
				//				}
				//				else
				//				{
				//					player2 = arg1;
				//					log.append("Player 2: " + data.getUsername());
				//				}

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
			int pNum = (int)arg1.getInfo("player");
			String result;

			if (pNum == 1)
			{
				p1Coords = data.getWaterCoordinates();

				result = "StartGame";
				log.append("player: " + arg1.getInfo("username") + " is ready\n");

				try
				{
					arg1.sendToClient(result);
				}
				catch (IOException e)
				{
					return;
				}
			}
			else
			{
				p2Coords = data.getWaterCoordinates();

				result = "StartGame";
				log.append("player: " + arg1.getInfo("username") + " is ready\n");

				try
				{
					arg1.sendToClient(result);
				}
				catch (IOException e)
				{
					return;
				}
			}
		}

		else if (arg0 instanceof PlayerWaterPanelData)
		{
			PlayerWaterPanelData data = (PlayerWaterPanelData)arg0;
			String fireLocation = data.getFireLocation();
			String hitCheck = "";
			String username = (String)arg1.getInfo("username");
			String win;
			int pNum = (int)arg1.getInfo("player");

			if (pNum == 1)
			{
				hitCheck = checkForHit(fireLocation, p2Coords);

				if (hitCheck.equals("Sink"))
				{
					p2Ships--;

					if (p2Ships == 0)
					{
						hitCheck = "Winner";
					}
				}
				
				String[] result = new String[] {fireLocation, hitCheck, username};
				
				log.append(arg1.getInfo("username") + " fire at " + fireLocation + " is a " + hitCheck + "!\n");

				try
				{
					arg1.sendToClient(result);
					
					if (hitCheck.equals("Winner"))
					{
						this.sendToAllClients(result);
					}
				}
				catch (IOException e)
				{
					return;
				}
				
				
				
				
			}
			else
			{
				hitCheck = checkForHit(fireLocation, p1Coords);

				if (hitCheck.equals("Sink"))
				{
					p1Ships--;

					if (p1Ships == 0)
					{
						hitCheck = "Winner";
					}
				}
				
				String[] result = new String[] {fireLocation, hitCheck, username};
				
				log.append(arg1.getInfo("username") + "fire at " + fireLocation + " is a " + hitCheck + "!\n");

				try
				{
					arg1.sendToClient(result);
					
					if (hitCheck.equals("Winner"))
					{
						this.sendToAllClients(result);
					}
				}
				catch (IOException e)
				{
					return;
				}
			}

		}
	}


	public void setupGame(ArrayList<ConnectionToClient> clientList) 
	{
		playerList = new HashMap<String, ConnectionToClient>();

		Thread[] list = this.getClientConnections();

		//playerList.put("Player 1", list[0]);
		//playerList.put("Player 2", list[1]);
	}


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

					if (isSink(coords))
					{
						result = "Sink";
					}
				}
			}


		}

		return result;
	}

}
