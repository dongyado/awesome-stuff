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
		
		// -------------------------------------------------------------------
		// search start
		this.startPoint.g = 0;
		
		Point end = searchPath();
		
		p("end:");
		p(end);
		
		this.printPath(end);
		
		// printMatrix
		p("Matrix:");
		printMatrix(this.matrix);
		
	}
	
	public Point searchPath()
	{
		this.openList.put(this.startPoint.getKey(), this.startPoint);
		while(this.openList.size() != 0)
		{
			Point currentPoint = this.findMinPoint(this.openList);
			this.closeList.put(currentPoint.getKey(), currentPoint);
			this.openList.remove(currentPoint.getKey());
			ArrayList<Point> sPoints = this.traversalSurroundPoints(currentPoint);
			
			p("Min points:");
			p(currentPoint);
			
			Iterator<Point> it = sPoints.iterator();
			Point p = null;
			int type = 0;
			int tempCost = 0;
			
			while(it.hasNext())
			{
				p = it.next();
				
				type = this.findMoveType(currentPoint, p);
				tempCost = type == 1 ? currentPoint.g + this.normalStepCost : currentPoint.g + this.diagonallyStepCost;
				
				if (tempCost < p.g) {
					p.g = tempCost;
					p.parent = currentPoint;
				}
			}
			
//
//			p("OpenedList:");
//			printHashMapList(this.openList);
//		
//			p("ClosedList:");
//			printHashMapList(this.closeList);
//			
			if(this.openList.get(this.endPoint.getKey()) != null)
			{
				return this.openList.get(this.endPoint.getKey());
			}
			
		}
		return null;
	}
	
	public void printPath(Point end){
		Point p = null;
		p("/---------------------");
		p = end;
		while(p != null )
		{
			p(p);
			p = p.parent;
		}
		
		p("/---------------------");
//		p("OpenedList:");
//		printHashMapList(this.openList);
//		
//		
//		p("ClosedList:");
//		printHashMapList(this.closeList);
	}
	
	
	
	public int findMoveType(Point parent, Point next){
		
		if (parent.x == next.x || parent.y == next.y) return 1;
		
		return 2;
	}
	
	public Point findMinPoint(HashMap<String, Point> list)
	{
		Point p = null;
		Set<Entry<String, Point>> set = list.entrySet();
		
		Iterator<Entry<String, Point>> it = set.iterator();
		
		Entry<String, Point> e = null;
		if(it.hasNext())
			p = it.next().getValue();
		
		while(it.hasNext())
		{
			e = it.next();
			
			if(e.getValue().getF() < p.getF())
				p = e.getValue();

		}
		
		return p;
	}
	
	

	public ArrayList<Point> checkPoint(Point p, Point parent, ArrayList<Point> surroundPoints, int type)
	{
		if (this.closeList.get(p.getKey()) != null) return surroundPoints;
		
		if (p.walkable == 0) {
			surroundPoints.add(p);
			
			if(this.openList.get(p.getKey()) == null ){
				p.parent = parent;
				p.g = type == 1 ?  parent.g + this.normalStepCost : parent.g + this.diagonallyStepCost;
				this.openList.put(p.getKey(), p);
			}
				
			
		} else if(this.closeList.get(p.getKey()) == null ){
			this.closeList.put(p.getKey(), p);
		}
		
		return surroundPoints;
	}

	
	
	public ArrayList<Point> traversalSurroundPoints( Point point )
	{
		
		this.closeList.put(point.getKey(), point);
		this.openList.remove(point.getKey());
		
		ArrayList<Point> surroundPoints = new ArrayList<Point>();
		
		// Find out point around specified point and push it to openList
		Point p = null;
		
		// top
		if (point.y > 0 )
		{
			// top point 
			p = this.matrix.get(point.y -1).get(point.x);
			surroundPoints = this.checkPoint(p, point,  surroundPoints, 1);

			
			// left top point
			if (point.x > 0)
			{
				p = this.matrix.get(point.y - 1).get(point.x - 1);
				surroundPoints = this.checkPoint(p, point, surroundPoints,2 );
			}
			
			// right top point
			if(point.x < this.columns - 1){
				p = this.matrix.get(point.y - 1).get(point.x + 1);
				surroundPoints = this.checkPoint(p, point,  surroundPoints, 2);
			} 
		} 
		
		if (point.y < this.rows - 1)
		{
			// bottom
			p = this.matrix.get(point.y + 1).get(point.x);
			surroundPoints = this.checkPoint(p, point,  surroundPoints, 1);
			
			
			if (point.x > 0)
			{
				p = this.matrix.get(point.y + 1).get(point.x - 1);
				surroundPoints = this.checkPoint(p, point, surroundPoints, 2);
			}
			
			if(point.x < this.columns - 1){
				p = this.matrix.get(point.y + 1).get(point.x + 1);
				surroundPoints = this.checkPoint(p, point,  surroundPoints, 2);
			} 
		}
		
		if (point.x > 0) {
			// left point
			p = this.matrix.get(point.y).get(point.x - 1);
			surroundPoints = this.checkPoint(p, point,  surroundPoints, 1);
		}
		
		// right top point
		if(point.x < this.columns - 1){
			p = this.matrix.get(point.y).get(point.x + 1);
			surroundPoints = this.checkPoint(p,  point, surroundPoints, 1);
		} 
		
		
		return surroundPoints;
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
	
	
	
	public static void p(Object o)
	{
		System.out.println(o.toString());
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


































