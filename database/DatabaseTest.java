package database;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.*;

public class DatabaseTest {
	private String[] users = {"jsmith@uca.edu","msmith@uca.edu","tjones@yahoo.com","jjones@yahoo.com"};
	private String[] passwords = {"hello123","pass123","123456","hello1234"};
	private String[] wins = {"1","5","3","2"};
	private String[] losses = {"2","0","0","1"};
	private Database db;
	private int rando;

	@Test
	public void testSetConnection() 
	{
		db = new Database();
		db.setConnection();

		assertNotNull("Check testSetConnection", db.getConnection()); 

	}

	@Test
	public void testQuery() {
		rando = ((int)Math.random()*users.length); 
		db = new Database();
		db.setConnection();

		assertNotNull("Check testQuery", db.getConnection()); 

		//Use Random # to extract username and expected password

		String username = users[rando]; 
		String expected = passwords[rando];

		//get actual result (invoke query with username)
		ArrayList<String> res = db.query("SELECT aes_decrypt(password,'key') FROM users WHERE username='" + username +"'");
		String actual = res.get(0);
		//compare expected with actual using assertEquals
		assertEquals("testQuery: input " + username , expected, actual);

	}

	@Test
	public void testExecuteDML() {
		db = new Database();
		db.setConnection();
		String username = "slopezmontano@cub.uca.edu";
		String password = "123hello";

		assertNotNull("Check testExecuteDML", db.getConnection()); 

		// execute the dml statement
		try {
			db.executeDML("INSERT INTO users VALUES('"+username+"',aes_encrypt('"+password+"','key'),0,0)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testCreateNewAccount() {
		db = new Database();
		db.setConnection();
		String username = "samantha@gmail.com";
		String password = "1234pass";

		assertNotNull("Check testCreateNewAccount", db.getConnection()); 

		// execute the dml statement
		try {
			db.executeDML("INSERT INTO users VALUES('"+username+"',aes_encrypt('"+password+"','key'),0,0)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testVerifyAccount() {
		rando = ((int)Math.random()*users.length); 
		db = new Database();
		db.setConnection();

		assertNotNull("Check testVerifyAccount", db.getConnection()); 

		//Use Random # to extract username and expected password

		String username = users[rando]; 
		String expected = passwords[rando];

		//get actual result (invoke query with username)
		ArrayList<String> res = db.query("SELECT aes_decrypt(password,'key') FROM users WHERE username='" + username +"'");
		String actual = res.get(0);
		//compare expected with actual using assertEquals
		assertEquals("testVerifyAccount: input " + username , expected, actual);

	}

	@Test
	public void testGetLeaderboard() {
		rando = ((int)Math.random()*users.length); 
		db = new Database();
		db.setConnection();

		assertNotNull("Check testGetLeaderboard", db.getConnection()); 

		//Use Random # to extract username and expected password

		String username = users[rando]; 
		String expected = wins[rando];

		//get actual result (invoke query with username)
		ArrayList<String> res = db.query("SELECT wins FROM users WHERE username='" + username +"'");
		String actual = res.get(0);
		//compare expected with actual using assertEquals
		assertEquals("testGetLeaderboard: input " + username , expected, actual);

	}

	@Test
	public void testUpdateLeaderboard() {
		fail("Not yet implemented");
	}

}
