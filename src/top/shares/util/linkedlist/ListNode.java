package top.shares.util.linkedlist;

/**
 *  list node enty
 * 
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

	public ListNode getPrev() {
		return prev;
	}

	public void setPrev(ListNode prev) {
		this.prev = prev;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
