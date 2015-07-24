package top.shares.util.linkedlist;

/**
 *  list node entry
 * 
 *  @author dongyado<dongyado@gmail.com>
 * */
public class ListNode<T>{
	
	public ListNode<T> prev;
	public ListNode<T> next;
	
	public T data;
	
	public ListNode(T data, ListNode<T> prev, ListNode<T> next){
		super();
		this.data = data;
		this.prev = prev;
		this.next = next;
	}
}
