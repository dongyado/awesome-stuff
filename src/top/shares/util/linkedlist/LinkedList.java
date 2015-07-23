package top.shares.util.linkedlist;

public class LinkedList<T> {

	private ListNode<T> head;
	private ListNode<T> tail;
	private int length = 0;
	
	
	public  LinkedList(){
	
		this.head = new ListNode<T>(null, null, null);
		this.tail = new ListNode<T>(null, null, null);
		this.head.next = this.tail;
		this.tail.prev = this.head;
		
	}
	
	
	public void addFirst(ListNode node)
	{
		
	}

	public void addLast(ListNode node)
	{
		
	}
	
	
	public void removeNode(int index)
	{
		
	}
}
