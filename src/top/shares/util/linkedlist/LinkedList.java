package top.shares.util.linkedlist;

/**
 * Simple Linked list implement.
 * 
 * @author dongyado<dongyado@gmail.com>
 * */
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
	
	private void insertNode(ListNode<T> node, ListNode<T> prev, ListNode<T> next){
		node.prev = prev;
		node.next = next;
		
		prev.next = node;
		next.prev = node;
		
		this.length++;
	}
	
	
	public void addFirst(T value)
	{
		ListNode<T> node = new ListNode<T> (value, null, null);
		
		this.insertNode(node, this.head, this.head.next);
	}

	public void addLast(T value)
	{
		ListNode<T> node = new ListNode<T> (value, null, null);
		
		this.insertNode(node, this.tail.prev, this.tail);
	}
	
	
	public void printList()
	{
		ListNode<T> node = this.head.next;
		while(node.next != null)
		{
			System.out.println((T) node.data);
			node = node.next;
		}
	}
	
	
	public void removeNode(int index)
	{
		
	}
}
