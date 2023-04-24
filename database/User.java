package database;

import java.io.Serializable;

public class User implements Serializable 
{
  // Private data fields for the username and password.
  private String username;
  private String password;
  private String wins;
  private String losses;
  
  // Getters for the username and password.
  public String getUsername()
  {
    return username;
  }
  public String getPassword()
  {
    return password;
  }
  
  // Setters for the username and password.
  public void setUsername(String username)
  {
    this.username = username;
  }
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  //getters for wins and losses
  public String getWins()
  {
    return wins;
  }
  public String getLosses()
  {
    return losses;
  }
    
  //setters for wins and losses
  public String setWins(String wins)
  {
    return wins;
  }
  public String setLosses(String losses)
  {
    return losses;
  }
  
    // Constructor that initializes the username and password.
  public User(String username, String password, String wins, String losses)
  {
    setUsername(username);
    setPassword(password);
    setWins(wins);
    setLosses(losses);
  }
}