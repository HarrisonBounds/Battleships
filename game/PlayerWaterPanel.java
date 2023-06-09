package game;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class PlayerWaterPanel  extends JPanel
{
	//variables for the buttons
	private JButton[] btns = new JButton[100];
	private Boolean addShipFlag = false;
	private Boolean fireFlag = false;
	private String alignment;
	private Ship[] ships = new Ship[5];
	private int shipCounter = 0;
	private ArrayList<String[]> waterCoordinates;
	private String fireLocation;

	public PlayerWaterPanel()
	{
		/*
		 * creating each ship with their respective size
		 * the first two ships will have a size of 2, the rest will have size 3,4,5
		 */
		for (int i = 0; i < ships.length ; i++) {
			if (i <= 1) {
				ships[i] = new Ship(2);
			}
			else {
				ships[i] = new Ship(i+1);
			}
		}

		waterCoordinates = new ArrayList<String[]>();
		//Create the even handler
		EventHandler eh = new EventHandler();

		setLayout(new BorderLayout(0, 0));

		JPanel numAxis = new JPanel();
		add(numAxis, BorderLayout.NORTH);
		numAxis.setLayout(new GridLayout(0, 11, 0, 0));

		JLabel lbl0 = new JLabel("X");
		numAxis.add(lbl0);

		JLabel lblNum1 = new JLabel("1");
		numAxis.add(lblNum1);

		JLabel lblNum2 = new JLabel("2");
		numAxis.add(lblNum2);

		JLabel lblNum3 = new JLabel("3");
		numAxis.add(lblNum3);

		JLabel lblNum4 = new JLabel("4");
		numAxis.add(lblNum4);

		JLabel lblNum5 = new JLabel("5");
		numAxis.add(lblNum5);

		JLabel lblNum6 = new JLabel("6");
		numAxis.add(lblNum6);

		JLabel lblNum7 = new JLabel("7");
		numAxis.add(lblNum7);

		JLabel lblNum8 = new JLabel("8");
		numAxis.add(lblNum8);

		JLabel lblNum9 = new JLabel("9");
		numAxis.add(lblNum9);

		JLabel lblNum10 = new JLabel("10");
		numAxis.add(lblNum10);

		JPanel letAxis = new JPanel();
		add(letAxis, BorderLayout.WEST);
		letAxis.setLayout(new GridLayout(10, 0, 0, 0));

		JLabel lblA = new JLabel("  A   ");
		letAxis.add(lblA);

		JLabel lblB = new JLabel("  B");
		letAxis.add(lblB);

		JLabel lblC = new JLabel("  C");
		letAxis.add(lblC);

		JLabel lblD = new JLabel("  D");
		letAxis.add(lblD);

		JLabel lblE = new JLabel("  E");
		letAxis.add(lblE);

		JLabel lblF = new JLabel("  F");
		letAxis.add(lblF);

		JLabel lblG = new JLabel("  G");
		letAxis.add(lblG);

		JLabel lblH = new JLabel("  H");
		letAxis.add(lblH);

		JLabel lblI = new JLabel("  I");
		letAxis.add(lblI);

		JLabel lblJ = new JLabel("  J");
		letAxis.add(lblJ);

		JPanel board = new JPanel();
		add(board, BorderLayout.CENTER);
		board.setLayout(new GridLayout(0, 10, 0, 0));

		//add the btns to the board panel
		int counter = 0;
		for(char alpha = 'A'; alpha < 'K'; alpha++)
		{
			for(int i = 1; i < 11; i++)
			{
				btns[counter] = new JButton("");
				btns[counter].setToolTipText(alpha + String.valueOf(i));
				btns[counter].setBackground(new Color(0, 0, 160));
				btns[counter].addActionListener(eh);
				board.add(btns[counter]);
				counter++;
			}
		}

	}

//	public Boolean checkHit(JButton butt)
//	{
//		Boolean hit = false;
//		for(int i = 0; i < 5; i++)
//		{
//			for(String s : ships[i].getCoords())
//			{
//				if(s.contains(butt.getToolTipText()))
//				{
//					ships[i].markHit();
//					if(ships[i].isSunk())
//					{
//						JOptionPane.showMessageDialog(null, "A BATTLESHIP WAS SUNK!", "SINK", JOptionPane.INFORMATION_MESSAGE);
//					}
//					hit = true;
//					return hit;
//				}
//			}
//		}
//		return hit;	
//	}

	public void isHit(String fireLocation)
	{
		for (JButton button : btns) 
		{
			if (button.getToolTipText().equals(fireLocation))
			{
				button.setBackground(new Color(255, 0, 0));
			}
		}
		
		//butt.setBackground(new Color(255, 0, 0));
	}

	public void isMiss(String fireLocation)
	{
		for (JButton button : btns) 
		{
			if (button.getToolTipText().equals(fireLocation))
			{
				button.setBackground(new Color(255, 255, 255));
			}
		}
		
		//butt.setBackground(new Color(255, 255, 255));
	}

	public Boolean addShip(JButton source, Ship ship, String alignment)
	{
		int size = ship.getSize();
		int numLimit = Integer.parseInt(source.getToolTipText().replaceAll("\\D+", ""));
		String[] coords = new String[size];
		String[] checkCoords = new String[size];
		char letLimit = source.getToolTipText().charAt(0);
		Boolean bigEnough = true;

		if (alignment.equals("Horizontal"))
		{
			//check to see if the ship fits horizontally
			if((numLimit + size) > 11)
			{
				bigEnough = false;
				return bigEnough;
			}

			for(int i = 0; i < btns.length; i++)
			{
				if (btns[i].getToolTipText() == source.getToolTipText())
				{
					for(int j = 0; j < size; j++)
					{
						coords[j] = btns[i+j].getToolTipText();
					}
				}
			}
		}
		if (alignment.equals("Vertical"))
		{
			//check to see if the ship fits vertically
			if((letLimit + size) > 'K')
			{
				bigEnough = false;
				return bigEnough;
			}

			for(int i = 0; i < btns.length; i++)
			{
				if (btns[i].getToolTipText() == source.getToolTipText())
				{
					for(int j = 0; j < size * 10; j += 10)
					{
						coords[j/10] = btns[i+j].getToolTipText();
					}
				}
			}
		}

		//check to see if ship is on other ship
		for(Ship s : ships)
		{
			checkCoords = s.getCoords();

			for(int i = 0; i < coords.length; i ++)
			{
				if(checkCoords != null)
				{
					for(int j = 0; j < checkCoords.length; j ++)
					{
						if(coords[i] == checkCoords[j])
						{
							JOptionPane.showMessageDialog(null, "Ships cannot be placed on top of each other!", "OOPS", JOptionPane.INFORMATION_MESSAGE);
							bigEnough = false;
							return bigEnough;
						}
					}
				}
			}
		}

		//set the ship to the appropriate color
		for(String s : coords)
		{
			for(int i = 0; i < btns.length; i ++)
			{
				if(btns[i].getToolTipText() == s)
				{
					btns[i].setBackground(new Color(128, 128, 128));
				}
			}
		}

		ship.setCoords(coords);
		/*
		 * adding the coordinates to be able to pass them to the server via a GameData object
		 */
		//String coord = Arrays.toString(ship.getCoords());
		waterCoordinates.add(coords);

		return bigEnough;
	}

	public Boolean setShipFlagTrue(String align)
	{
		Boolean doneAdding = false;
		addShipFlag = true;
		alignment = align;

		if (shipCounter == 6)
		{
			doneAdding = true;
			return doneAdding;
		}
		else
		{
			return doneAdding;
		}
	}

	public void setFireFlagTrue()
	{
		fireFlag = true;
	}

	public void setShips(Ship[] fleet)
	{
		ships = fleet;
	}

	public ArrayList<String[]> getShipCoordinates() {
		return this.waterCoordinates;
	}

	public int getShipCounter() {
		return shipCounter;
	}

	public void setShipCounter(int sc) {
		shipCounter = sc;
	}

	public String getFireLocation() {
		return this.fireLocation;
	}

	public void setFireLocation(String fireLocation) {
		this.fireLocation = fireLocation;
	}

	// Implement this in the GameController
	class EventHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			String command = ae.getActionCommand();
			JButton source = (JButton) ae.getSource();

			
			setFireLocation(source.getToolTipText());
			//fire
//			if (fireFlag)
//			{
//				setFireLocation(source.getToolTipText());
////				if(checkHit(source))
////				{
////					isHit(source);
////					
////				}
////				else
////				{
////					isMiss(source);
////				}
//
//				fireFlag = false;
//			}

			//add the ships
			if (addShipFlag)
			{
				if(addShip(source, ships[shipCounter], alignment))
				{
					shipCounter++;
				}
				addShipFlag = false;
			}
		}
	}
}