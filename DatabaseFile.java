package lab5out;

import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;

public class DatabaseFile
{
  // Private data fields for storing the file streams.
  FileInputStream fis;
  FileOutputStream fos;

  // Private data field for storing a HashMap of usernames and passwords.
  HashMap<String, String> database;
  
  // Method for verifying a username and password.
  public boolean verifyAccount(String username, String password)
  {
    // Read the database file.
    readFile();
    
    // Stop if this account doesn't exist.
    if (database.get(username) == null)
      return false;
    
    // Check the username and password.
    if (database.get(username).equals(password))
      return true;
    else
      return false;
  }
  
  // Method for creating a new account.
  public boolean createNewAccount(String username, String password)
  {
    // Read the database file.
    readFile();
    
    // Stop if this account already exists.
    if (database.get(username) != null)
      return false;
    
    // Add the new account.
    database.put(username, password);
    
    // Write the database to the file.
    writeFile();
    return true;
  }
  
  // Method for reading the database from a file.
  public synchronized void readFile()
  {
    // Create a new HashMap for the database.
    database = new HashMap<String, String>();
    
    // Catch any exceptions that occur when reading the file.
    try
    {
      // Create a FileInputStream and a BufferedReader.
      fis = new FileInputStream("database.txt");
      BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
      
      // Read every line from the file one at a time.
      String line = reader.readLine();
      while(line != null)
      {
        // Split this line into the username and password components.
        String[] data = line.split("\\|");
        
        // Save the username and password in the HashMap.
        if (data.length == 2)
          database.put(data[0], data[1]);
        
        // Read the next line from the file.
        line = reader.readLine();
      }
      
      // Make sure the file is closed.
      fis.close();
    }
    
    // If an exception occurs, we can't do anything else.
    catch (Exception exception)
    {
      return;
    }
  }
  
  // Method for writing to the current database to a file.
  public synchronized void writeFile()
  {
    // Loop through every account in the database.
    String output = "";
    for (Entry<String, String> entry : database.entrySet())
    {
      // Append the username and password for this account to our output.
      output += entry.getKey() + "|" + entry.getValue() + "\n";
    }
    
    // Catch any exceptions that occur when writing the file.
    try
    {
      // Create a FileOutputStream and BufferedWriter.
      fos = new FileOutputStream("database.txt");
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
      
      // Write the file contents.
      writer.write(output);
      writer.flush();
      
      // Make sure the file is closed.
      fos.close();
    }
    
    // If an exception occurs, we can't do anything else.
    catch (Exception exception)
    {
      return;
    }
  }
}
