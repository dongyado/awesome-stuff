package top.shares.funny.astar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map.Entry;

/***
 * A star search
 * 	find the least way from one point to another point
 *  
 * 
 * @author dongyado<dongyado@gmail.com>
 * 
 * */


public class AStarSearch {
	
	private ArrayList<ArrayList<Point>> matrix;
	private int matrixRows;
	private int matrixColumns;
	
	private Point start;
	private Point end;
			
	private HashMap<String, Point> openedList;
	private HashMap<String, Point> closedList;
	private HashMap<String, Point> pathMap;
	private LinkedList<Point> path = null;
	
	private int normalStepCost = 10;
	private int diagonallyStepCost = 14;
	
	
	public AStarSearch(Point start, Point end, int[][] map)
	{
		this.start 	= start;
		this.end	= end;
		
		this.openedList = new HashMap<String, Point>();
		this.closedList = new HashMap<String, Point>();
		
		// get size of matrix
		this.matrixRows = map.length;
		this.matrixColumns = map[0].length;
		
		this.matrix = new ArrayList<ArrayList<Point>>();
		
		
		// create matrix and calculate h of each point 
		// 	according to start point and end point
		for( int i = 0; i < this.matrixRows; i++)
		{
			ArrayList<Point> list = new ArrayList<Point>();
			
			for(int j = 0; j < this.matrixColumns; j++)
			{
				Point point = new Point(j, i, map[i][j]);
				
				// calculate h
				point.h = Math.abs(this.end.x - j ) * this.normalStepCost + Math.abs(this.end.y - i) * this.normalStepCost;
				
				// add unwalkable point to closed list
				//if (map[i][j] == 1) this.closedList.put(point.key(), point);
				list.add(point);
			}
			this.matrix.add(list);
		}
	}
	
	
	/***
	 * search
	 * 
	 * */
	public LinkedList<Point> search()
	{
		this.openedList.put(this.start.key, this.start);
		
		Point currentPoint;
		ArrayList<Point> surroundPoints;
		Iterator<Point> it;
		Point p;
		
		while(this.openedList.size() > 0)
		{
			currentPoint = getMinPoint(this.openedList);
//			System.out.print(currentPoint.getPosition() + "\n");
			
			this.closedList.put(currentPoint.key, currentPoint);
			this.openedList.remove(currentPoint.key);
			
			// get surrounded points
			surroundPoints = this.traversalSurroundPoints(currentPoint);
		
			it = surroundPoints.iterator();
			
			while(it.hasNext())
			{
				p = it.next();
				
				if (p.walkable == 1 || this.closedList.containsKey(p.key)) continue;
				
				// add new point to opened list
				if (this.openedList.containsKey(p.key) == false)
				{
//					System.out.print("add:" + p.getPosition() + "\n");
					p.parent = currentPoint;
					p.g = 	this.getMoveType(p, currentPoint) == 1 
							?  currentPoint.g + this.normalStepCost 
							: currentPoint.g + this.diagonallyStepCost;
					
					this.openedList.put(p.key, p);
				}
				
			
				if (this.openedList.containsKey(p.key)) {
					int tempCost = 	this.getMoveType(p, currentPoint) == 1 
									? currentPoint.g + this.normalStepCost 
									: currentPoint.g + this.diagonallyStepCost;
					
					if (p.parent != null)
						tempCost += p.parent.g;
					
					// find a least step
					if (tempCost < p.g) {
						
						p.g = tempCost;
						p.parent = currentPoint;
					}
				}
				
				// found
				if (this.openedList.containsKey(this.end.key)){
					this.generatePaths(this.openedList.get(this.end.key));
					return this.path;
				}
			}
		}
		
		return null;
	}
	
	
	/***
	 * generator paths
	 * 
	 * */
	public void generatePaths(Point p){
		this.pathMap = new HashMap<String, Point>();
		this.path = new LinkedList<Point>();
		while(p != null )
		{
			this.pathMap.put(p.getKey(), p);
			this.path.addFirst(p);
			p = p.parent;
		}
	}
	
	
	/**
	 *  get move type: directly or diagonally
	 * 
	 * */
	public int getMoveType( Point next, Point parent)
	{
		return parent.x == next.x || parent.y == next.y ? 1 : 2;
	}
	
	
	
	/**
	 * find the min F point
	 *  not a good implement
	 * 
	 * */
	public Point getMinPoint(HashMap<String, Point> list)
	{
		Point p = null;
		Set<Entry<String, Point>> set = list.entrySet();
		
		Iterator<Entry<String, Point>> it = set.iterator();
		
		Entry<String, Point> e = null;
		if(it.hasNext())
			p = it.next().getValue();
//		System.out.println("!++++ min   ++!");
		while(it.hasNext())
		{
			e = it.next();
			
			
			if(e.getValue().getF() < p.getF())
				p = e.getValue();
			
//			System.out.print( p.getPosition() + " - ");

		}
//		System.out.println("");
		return p;
	}
	

	
	/**
	 * get points surround the specified point
	 * 
	 * @param point
	 * */
	
	public ArrayList<Point> traversalSurroundPoints( Point point )
	{	
		ArrayList<Point> surroundPoints = new ArrayList<Point>();
		
		// Find out point around specified point and push it to openList
		Point p = null;
		
		// top
		if (point.y > 0 )
		{
			// top point 
			p = this.matrix.get(point.y -1).get(point.x);
			surroundPoints.add(p);
			
			// left top point
			if (point.x > 0)
			{
				p = this.matrix.get(point.y - 1).get(point.x - 1);
				surroundPoints.add(p);
			}
			
			// right top point
			if(point.x < this.matrixColumns - 1){
				p = this.matrix.get(point.y - 1).get(point.x + 1);
				surroundPoints.add(p);
			} 
		} 
		
		if (point.y < this.matrixRows - 1)
		{
			// bottom
			p = this.matrix.get(point.y + 1).get(point.x);
			surroundPoints.add(p);
			
			
			if (point.x > 0)
			{
				p = this.matrix.get(point.y + 1).get(point.x - 1);
				surroundPoints.add(p);
			}
			
			if(point.x < this.matrixColumns - 1){
				p = this.matrix.get(point.y + 1).get(point.x + 1);
				surroundPoints.add(p);
			} 
		}
		
		if (point.x > 0) {
			// left point
			p = this.matrix.get(point.y).get(point.x - 1);
			surroundPoints.add(p);
		}
		
		// right top point
		if(point.x < this.matrixColumns - 1){
			p = this.matrix.get(point.y).get(point.x + 1);
			surroundPoints.add(p);
		} 
		
		
		return surroundPoints;
	}
	
	
	/***
	 * print verbose information of specified matrix
	 * 
	 * */
	public void printMatrix()
	{
		Iterator<ArrayList<Point>> rowIt = this.matrix.iterator();
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
				//System.out.print(p.getPosition());
			}
			System.out.println("}");
		}
		
	}
	
	
	
	/***
	 * print verbose information of specified matrix
	 * 
	 * 
	 * */
	public void printMatrixWithPath()
	{
		Iterator<ArrayList<Point>> rowIt = this.matrix.iterator();
		ArrayList<Point> row = null;
		
		Iterator<Point> it = null;
		int rowNum = 0;
		int colNum = 0;
		Point p = null;
		System.out.println(" ");
		
		System.out.print("   ");
		for( int col = 0; col < this.matrixColumns; col++)
		{
			System.out.print(" "+col+" ");
		}
		System.out.println(" ");
		
		while(rowIt.hasNext())
		{
			row  = rowIt.next();
			it = row.iterator();
			System.out.print(" "+rowNum+" ");

			while(it.hasNext())
			{
				p = it.next();
				//System.out.print(p);
				if (this.pathMap.containsKey(p.getKey())) {
					System.out.print("[*]");
				}else if(p.walkable == 1){
					System.out.print("[x]");
				} else {
					System.out.print("[ ]");
				}				
			}
			rowNum++;
			
			System.out.println("");
		}
	}
	
	
}
