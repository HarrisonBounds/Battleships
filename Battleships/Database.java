package lab7out;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
  	  FileInputStream fis = new FileInputStream("lab7out/lab7outdb.properties");
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
  
  public boolean createNewAccount(String username, String password) {
      String dml = String.format("insert into user(username, password) values ('%s', AES_ENCRYPT('%s', 'key'))", username, password);
      try {
        this.executeDML(dml);
        return true;
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        return false;
    }
  }
  
  public boolean verifyAccount(String username, String password) {
      ArrayList<String> list = new ArrayList<String>();
      String[] row;
      list = this.query("SELECT * from user where username = " + "'" + username + "'" + " and password = aes_encrypt(" + "'" + password + "'" + ",'key')");
//	for(int i = 0; i < list.size(); i++){
//	    row = list.get(i).split(", ", 2);
//	    if(row[0].equals(username) && row[1].equals(password)) {
//	        return true;
//	    }
      if(list != null) {
    	  return true;
	}
	return false;
      
      
  }
  
}


