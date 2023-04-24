package database;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Database
{
	private Connection conn;

	public void setConnection()
	{
		//Read the connection properties as Strings
		Properties prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream("database/db.properties");
			prop.load(fis);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password"); 

		try
		{
			//Perform the Connection
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("database connected");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection()
	{
		return conn;
	}

	public ArrayList<String> query(String query)
	{
		int i = 0;
		int rowCount = 0; //Detect empty result set
		ArrayList<String> array = new ArrayList<String>();


		try 
		{
			//Create a statement from the Connection
			Statement stmt = conn.createStatement();

			//Run the query
			ResultSet rs = stmt.executeQuery(query);

			//Get the metadata
			ResultSetMetaData rmd = rs.getMetaData();

			int noColumns = rmd.getColumnCount();

			while(rs.next())
			{
				rowCount++;
				String record = "";
				for(i = 0; i < noColumns; i++)
				{
					String value = rs.getString(i+1);
					record += value + "";
					array.add(record);
				}
			}

			if(rowCount > 0)
			{
				return array;
			}
			else
			{
				return null;
			}

		}
		catch(SQLException e)
		{
			e.printStackTrace();

			return null;
		}

	}

	public void executeDML(String dml) throws SQLException
	{
		//Add your code here
		Statement stmt = conn.createStatement();
		stmt.execute(dml);
	}

	public boolean createNewAccount(String username, String password) 
	{
		// execute the query.
		ArrayList<String> validUsers = query("SELECT username FROM user");

		// Stop if this account doesn't exist.
		if (validUsers.contains(username))
			return false;

		// execute the dml statement
		try {
			executeDML("INSERT INTO user VALUES('"+username+"',aes_encrypt('"+password+"','key'))");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean verifyAccount(String username, String password) 
	{
		// execute the query.
		ArrayList<String> validUsers = query("SELECT username FROM user");
		ArrayList<String> validPasswords = query("SELECT aes_decrypt(password,'key') FROM user");

		// Stop if this account doesn't exist .
		if (!validUsers.contains(username))
			return false;

		int index = validUsers.indexOf(username);
		// Check the username and password.
		if (validPasswords.get(index).equals(password))
			return true;
		else
			return false;
	}

	public ArrayList<User> getLeaderboard()
	{
		int i = 0;
		int rowCount = 0; //Detect empty result set
		ArrayList<String> array = new ArrayList<String>();
		ArrayList<User> leaderboard = new ArrayList<User>();


		try 
		{
			//Create a statement from the Connection
			Statement stmt = conn.createStatement();

			//Run the query
			ResultSet rs = stmt.executeQuery("SELECT username, aes_decrypt(password,'key'), wins, losses FROM user ORDER BY wins DESC");

			//Get the metadata
			ResultSetMetaData rmd = rs.getMetaData();

			int noColumns = rmd.getColumnCount();

			while(rs.next())
			{
				rowCount++;
				String record = "";
				for(i = 0; i < noColumns; i++)
				{
					String value = rs.getString(i+1);
					record += value + "";
					array.add(record);

				}
				User u = new User(rs.getString(0),rs.getString(1),rs.getString(2),rs.getString(3));
				leaderboard.add(u);
			}

			if(rowCount > 0)
			{
				return leaderboard;
			}
			else
			{
				return null;
			}

		}
		catch(SQLException e)
		{
			e.printStackTrace();

			return null;
		}

	}

	public boolean updateLeaderboard(String username, String wins, String losses)
	{
		// execute the query.
		ArrayList<String> validUsers = query("SELECT username FROM user");

		// Stop if this account doesn't exist.
		if (validUsers.contains(username))
			return false;

		// execute the dml statement
		try {
			executeDML("UPDATE user SET wins=wins+"+wins+",losses=losses+"+losses+" WHERE username='"+username+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
