package top.shares.funny.lru;

public class Entry{
	public Entry prev;
	public Entry next;
	public int value;
	public int key;
	
	public Entry(int value, int key, Entry prev, Entry next){
		this.value  = value;
		this.key 	= key;
		this.prev   = prev;
		this.next   = next;
	}
}