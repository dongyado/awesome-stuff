package top.shares.funny.lru;

import java.util.HashMap;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and set.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, 
 * it should invalidate the least recently used item before inserting a new item.
 * 
 * @note 
 * 1. I'm very proud to say this implement accepted,
 * 	 I found a way to implement linkedHashMap myself after 3 hours, and that feel really great.
 * 2. I used HashMap, maybe i should implement it on my own way.
 * 3. this implement has much performance improvement space.
 * 4. Uh, it not thread safe too;
 * 
 * @author dongyado<dongyado@gmail.com>
 * */

public class LRUCache {
	
	/**
	 *  entry class
	 * 
	 * */
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
	
	protected HashMap<Integer, Entry> map = new HashMap<Integer, Entry>();

	
	int capacity = 32; // default capacity
	int length = 0;
	
	protected Entry head;
	protected Entry tail;
	
	public LRUCache(int capacity) {
        this.capacity = capacity;
        
        this.head = new Entry(-1, -1,  null, null);
        this.tail = new Entry(-2, -1, this.head, null);
        this.head.next = this.tail;
	}
    
    public int get(int key ) {
    	Entry entry = map.get(key);
    	
    	if (map.get(key) == null)
    		return -1;
    
    	entry.prev.next = entry.next;
    	entry.next.prev = entry.prev;
    	
    	entry.prev = this.head;
    	entry.next = this.head.next;
    	this.head.next.prev = entry;
    	this.head.next = entry;
    	
    	return entry.value;
    }

    
    public Entry getEntry(int key){
    	return map.get(key);
    }
    
    public void set(int key, int value) 
    {
    	if (map.get(key) == null) {
    		
        	if (this.length >= this.capacity) {
        		this.lru();
        	}
        	
        	Entry entry = new Entry(value, key, null, null);
        	entry.prev = this.head;
        	entry.next = this.head.next;
        	this.head.next.prev = entry;
        	this.head.next = entry;
        	
        	this.length++;
        	map.put(key, entry);
        	
    	} else {
    		Entry entry = map.get(key);
    		
    		entry.value = value;
    		
    		entry.prev.next = entry.next;
    		entry.next.prev = entry.prev;
    		
    		entry.prev = this.head;
    		entry.next = this.head.next;
    		this.head.next.prev = entry;
    		this.head.next = entry;
    	}
    }
    
    // invalid the least used entity
    public void lru(){
    	// here is a way to improve performance, 
    	// if we remove last 10% entries, then this function may called less.
    	
    	// remove the last one
    	Entry entry = this.tail.prev;
    	
    	this.tail.prev = entry.prev;
    	entry.prev.next = this.tail;
    	map.remove(entry.key);
    	this.length--;

    }
}
