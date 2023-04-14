package game;

public class Ship 
{
	//private data fields
	private int size;
	private Boolean sunk = false;
	private int timesHit = 0;
	private String[] Coords;

	//constructor
	public Ship(int size)
	{
		this.size = size;
	}

	public int getSize()
	{
		return size;
	}

	public void setCoords(String[] Coords)
	{
		this.Coords = Coords;
	}


	public String[] getCoords()
	{
		return Coords;
	}


	public void markSink()
	{
		sunk = true;
	}

	public Boolean isSunk()
	{
		return sunk;
	}

	public void markHit()
	{
		timesHit++;

		if(timesHit == size)
		{
			markSink();
		}
	}
}