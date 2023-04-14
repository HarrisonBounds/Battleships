package database;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Database
{
	private Connection conn;
	//Add any other data fields you like – at least a Connection object is mandatory
	public Database() throws IOException
	{
		//Add your code here

		//Read the connection properties as Strings
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("database/db.properties");
		prop.load(fis);
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password"); 

		try
		{
			//Perform the Connection
			conn = DriverManager.getConnection(url,user,pass);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
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
					record += value + ",";
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

//	public ArrayList<String> getLeaderboard()
//	{
//
//	}

}
