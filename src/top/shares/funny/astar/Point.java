package top.shares.funny.astar;

/**
 * Point entry
 * 
 * @author dongyado<dongyado@gmail.com>
 * 
 * */

public class Point {
	
	public Point parent = null;
	
	public int h;
	public int g;
	
	// position
	public int x;
	public int y;
	
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
	
	public String getPosition(){
		return "["+ this.x+ ":" + this.y + "]";
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