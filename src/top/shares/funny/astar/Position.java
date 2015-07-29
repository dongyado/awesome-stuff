package top.shares.funny.astar;

public class Position{
	public int x;
	public int y;
	public String key;
	
	public Position(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.key = x + "_" + y;
	}
}