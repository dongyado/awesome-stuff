package top.shares.util.test;

import top.shares.util.linkedlist.LinkedList;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		
		// link list
		test.testLinkedList();
		
	}

	
	/***
	 *  test for linked list
	 * 
	 * */
	public void testLinkedList(){
		
		//LinkedList<Integer> list = new LinkedList<Integer>();
		LinkedList<String> list = new LinkedList<String>();
		
		list.addFirst("I");
		list.addFirst("Love");
		list.addLast("U");
		
		
		list.printList();
	}
}
