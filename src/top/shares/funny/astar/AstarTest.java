package top.shares.funny.astar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class AstarTest {
	
	public class Position{
		public int x;
		public int y;
		
		public Position(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {

		// 1 stands not walkable
		int[][] map  = new int[][]{
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,1,1,1,0,0,0},
			{0,0,0,0,0,0,1,0,0,0},
			{0,0,0,1,1,1,1,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0}
		};
		
		Point startPoint = new Point(1, 3, 0);
		Point endPoint = new Point(8, 3, 0);

		AStarSearch search = new AStarSearch(startPoint, endPoint, map);
		
		LinkedList<Point> path = search.search();
		
		printPath(path);
		
		search.printMatrixWithPath();

	}
	
	public int[][] createMap(int rows, int columns, HashMap<String, Position> notWalkable )
	{
		int[][] map = new int[10][10];
		
		return map;
	}
	
	public static void printMap(int[][] map)
	{
		
	}
	
	/**
	 * print the point of path
	 * 
	 * */
	
	public static void printPath(LinkedList<Point> list){
		
		Point p = null;
		p("--------path---------");
		Iterator<Point> it = list.iterator();
		while(it.hasNext())
		{
			p = it.next();
			p(p.getPosition());
		}
		p("---------------------");
	}
	
	
	/**
	 * print 
	 * 
	 * */
	public static void p(Object o)
	{
		System.out.println(o.toString());
	}

}
