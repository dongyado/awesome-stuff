package top.shares.funny.astar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class AstarTest {

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
		int[][] simpleMap  = new int[][]{
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,1,0,0,0,0,0,0},
				{0,0,0,1,0,0,0,0,0,0},
				{0,0,0,1,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0}
			};
		
		int[][] bigMap = new int[][]{
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
		};
		
//		ArrayList<String> barriers = new ArrayList<String>();
		
//		barriers.add("4_5");
//		barriers.add("5_5");
//		barriers.add("6_5");
//		barriers.add("7_5");
//		
//		barriers.add("5_10");
//		barriers.add("6_10");
//		barriers.add("7_10");
//		
//		
//		barriers.add("5_11");
//		barriers.add("5_12");
//		barriers.add("5_13");
//		barriers.add("5_14");
		
		//int[][] newMap = createMap(10,20, barriers);
		//printMap(newMap);
		
		// big map test start
//		Point startPoint = new Point(1, 3, 0);
//		Point endPoint = new Point(18, 3, 0);
//
//		AStarSearch search = new AStarSearch(startPoint, endPoint, bigMap);
//		
//		LinkedList<Point> path = search.search();
//		
//		printPath(path);
//		
//		search.printMatrixWithPath();
//		
		
		// small map test start
		Point startPoint = new Point(1, 2, 0);
		Point endPoint = new Point(5, 2, 0);

		AStarSearch search = new AStarSearch(startPoint, endPoint, simpleMap);
		
		LinkedList<Point> path = search.search();
		
		printPath(path);
		
		search.printMatrixWithPath();

	}
	
	public static int[][] createMap(int rows, int columns, ArrayList<String> notWalkable )
	{
		int[][] map = new int[rows][columns];
		
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				map[i][j] = notWalkable.contains(i+"_"+j) ? 1 : 0;
			}
		}
		return map;
	}
	
	
	
	public static void printMap(int[][] map)
	{
		int rows = map.length;
		int columns = map[0].length;
		
		p("map:");
		for(int i = 0; i < rows; i++)
		{
			System.out.print("{");
			for(int j = 0; j < columns; j++)
			{
				if (j != columns - 1)
					System.out.print(" " +map[i][j] + ",");
				else 
					System.out.print(" " +map[i][j] + " ");
			}
			
			if (i != rows -1 )
				System.out.println("},");
			else 
				System.out.println("}");
		}
	}
	
	
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
	
	public static void p(Object o)
	{
		System.out.println(o.toString());
	}

}
