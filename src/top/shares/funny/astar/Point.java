package top.shares.funny.astar;

/**
 * Point entry
 * 
 * @author dongyado<dongyado@gmail.com>
 * 
 * */

public class Point {
	
	public Point parent;
	
	public int h;
	public int g;
	
	// f = g + h;
	private int f;
	
	// position
	public int x;
	public int y;
	
	private String key = null;
	
	public int getF(){
		return this.g + this.h;
	}
	
	public String getKey(){
		return this.x + "_" + this.y;
	}
	
	@Override
	public String toString(){	
		return "[" + this.x + ":" + this.y + ":" + this.walkable + ":" + this.g + ":" + this.h + ":"+ this.getF() +"]";
	}
	
	// walkable;
	public int walkable = 0;
	
	public Point(int x, int y, int walkable)
	{
		this.x = x;
		this.y = y;
		this.walkable = walkable;
	}
	

	
}