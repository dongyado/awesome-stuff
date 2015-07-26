package top.shares.funny.astar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/*
 *  A Star search algorithm
 * 
 * given a two-dimensional matix, find the shortest way to the destination
 * 
 * how to find a shortest way from A to D, and 1 stands cannot pass
 * 
 * [ 
 * 0 0 0 0 0 0 0 0 0 0 0 0 
 * 0 0 0 0 0 0 0 0 0 0 0 0 
 * 0 0 0 0 1 0 0 0 0 0 0 0 
 * 0 0 A 0 1 0 0 D 0 0 0 0 
 * 0 0 0 0 1 0 0 0 0 0 0 0 
 * 0 0 0 0 0 0 0 0 0 0 0 0 
 * 0 0 0 0 0 0 0 0 0 0 0 0 
 * ]
 * 
 * 
 * @author dongyado<dongaydo@gmail.com>
 * 
 * */
public class AStar {
	
	private int normalStepCost = 10;
	private int diagonallyStepCost = 14;
	private HashMap<String, Point> openList = new HashMap<String, Point>();
	private HashMap<String, Point> closeList = new HashMap<String, Point>();

	private Point startPoint = new Point(2, 3, 0);
	private Point endPoint = new Point(6, 3, 0);
	
	private ArrayList<ArrayList<Point>> matrix = new ArrayList<ArrayList<Point>>();
	private int rows = 7;
	private int columns = 10;
	
	// 1 stands not walkable
	private int[][] map  = new int[][]{
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,1,0,0,0,0,0},
		{0,0,0,0,1,0,0,0,0,0},
		{0,0,0,0,1,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0}
	};

	
	public AStar() {
		
		// create matrix and calculate h of each point 
		// 	according to start point and end point
		for( int i = 0; i < rows; i++)
		{
			ArrayList<Point> list = new ArrayList<Point>();
			for(int j = 0; j < columns; j++)
			{
				Point point = new Point(j, i, map[i][j]);
				point.h = Math.abs(endPoint.x - j ) * this.normalStepCost + Math.abs(endPoint.y - i) * this.normalStepCost;
				
				// add unwalkable point to closed list
				if (map[i][j] == 1) this.closeList.put(point.getKey(), point);
				list.add(point);
			}
			this.matrix.add(list);
		}
		

				
		this.openList.put(this.startPoint.getKey(), this.startPoint);
		
		//this.traversalAroundPoints(this.startPoint);
		
		
		this.traversalAroundPoints(new Point(3, 3, 0));
		
		p("OpenedList:");
		printHashMapList(this.openList);
		
		p("ClosedList:");
		printHashMapList(this.closeList);
		
		
		
		// printMatrix
		p("Matrix:");
		printMatrix(this.matrix);
		
	}
	
	
	
	/**
	 * find 
	 * 
	 * */
	
	
	public static void p(Object o)
	{
		System.out.println(o.toString());
	}
	
	
	
	public void traversalAroundPoints( Point point )
	{
		
		this.closeList.put(point.getKey(), point);
		
		
		// Find out point around specified point and push it to openList
		Point p = null;
		
		// top
		if (point.y > 0 )
		{
			// top point 
			p = this.matrix.get(point.y -1).get(point.x);
			if (this.openList.get(p.getKey()) == null && p.walkable == 0) this.openList.put(p.getKey(), p);
			
			
			// left top point
			if (point.x > 0)
			{
				p = this.matrix.get(point.y - 1).get(point.x - 1);
				if (this.openList.get(p.getKey()) == null && p.walkable == 0)
					 this.openList.put(p.getKey(), p);
			}
			
			// right top point
			if(point.x < this.columns - 1){
				p = this.matrix.get(point.y - 1).get(point.x + 1);
				if (this.openList.get(p.getKey()) == null && p.walkable == 0)
				{
					this.openList.put(p.getKey(), p);	
				}
			} 
		} 
		
		if (point.y < this.rows - 1)
		{
			// bottom
			p = this.matrix.get(point.y + 1).get(point.x);
			if (this.openList.get(p.getKey()) == null && p.walkable == 0) this.openList.put(p.getKey(), p);
			
			
			if (point.x > 0)
			{
				p = this.matrix.get(point.y + 1).get(point.x - 1);
				if (this.openList.get(p.getKey()) == null && p.walkable == 0)
					 this.openList.put(p.getKey(), p);
			}
			
			if(point.x < this.columns - 1){
				p = this.matrix.get(point.y + 1).get(point.x + 1);
				if (this.openList.get(p.getKey()) == null && p.walkable == 0)
				{
					this.openList.put(p.getKey(), p);	
				}
			} 
		}
		
		if (point.x > 0) {
			// left point
			p = this.matrix.get(point.y).get(point.x - 1);
			if (this.openList.get(p.getKey()) == null && p.walkable == 0) this.openList.put(point.getKey(), p);
		}
		
		// right top point
		if(point.x < this.columns - 1){
			p = this.matrix.get(point.y).get(point.x + 1);
			if (this.openList.get(p.getKey()) == null && p.walkable == 0)
			{
				this.openList.put(p.getKey(), p);	
			}
		} 
		
	}
	
	
	/**
	 * print hashmap list
	 * 
	 * */
	
	public static void printHashMapList(HashMap<String, Point> list)
	{
		Set<Entry<String, Point>> set = list.entrySet();
		
		Iterator<Entry<String, Point>> it = set.iterator();
		
		Entry<String, Point> e = null;
		Point p = null;
		while(it.hasNext())
		{
			e = it.next();
			
			p = e.getValue();
			System.out.println(p);
		}
	}
	
	
	
	
	/***
	 * print verbose information of spechifid matrix
	 * 
	 * 
	 * */
	public static void printMatrix(ArrayList<ArrayList<Point>> matrix)
	{
		int rows = matrix.size();
		int colums = matrix.get(0).size();
		
		Iterator<ArrayList<Point>> rowIt = matrix.iterator();
		ArrayList<Point> row = null;
		
		Iterator<Point> it = null;
		Point p = null;
		while(rowIt.hasNext())
		{
			row  = rowIt.next();
			it = row.iterator();
			System.out.print("{");
			while(it.hasNext())
			{
				p = it.next();
				System.out.print(p);
			}
			System.out.println("}");
		}
		
	}


	public static void main(String[] args){
		
		AStar star = new AStar();

	}

}


































