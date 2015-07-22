package top.shares.funny.lru;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and set.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, 
 * it should invalidate the least recently used item before inserting a new item.
 * 
 * @note this implement is very slow, cause the lru algorithm is very slow
 * 
 * @author dongyado<dongyado@gmail.com>
 * */

public class LRUCachePool {
	
	protected LinkedHashMap<Integer, CacheEntity> cachePool = new LinkedHashMap<Integer, CacheEntity>();

	int capacity = 32;
	int length = 0;
	
	public int getCapacity() {
		return capacity;
	}

	public int getLength() {
		return length;
	}

	
	public LRUCachePool(int capacity) {
        this.capacity = capacity;
	}
    
    public int get(int key) {
    	CacheEntity entity = cachePool.get(key);
    	
    	if (entity != null) return entity.getData();
    	
    	return -1;
    }

    public long getTimes(int key) {
    	CacheEntity entity = cachePool.get(key);
    	
    	if (entity != null) return entity.getTimes();
    	
    	return -1;
    }
    
    public void set(int key, int value) 
    {
    	if (this.capacity <= this.length )
    	{
    		this.lru();
    	}
    	
    	// add new entity
    	if (cachePool.get(key) != null) 
    	{
    		cachePool.get(key).setData(value);
    	} 
    	else 
    	{
    		CacheEntity entity = new CacheEntity();
    		entity.setData(value);
    		this.length++;
    		cachePool.put(key, entity);
    	}
    }
    
    // invalid the least used entity
    public void lru(){
    	long last = System.nanoTime();
    	int key = 0;
    	int lastKey = 0;
    	Set<Integer> keys = cachePool.keySet();
    	Iterator<Integer> it = keys.iterator();
    	while(it.hasNext())
    	{
    		key = it.next();
    		if (cachePool.get(key).getTimes() < last)
    		{
    			last = cachePool.get(key).getTimes();
    			lastKey = key;
    		}
    	}
    	
    	System.out.println("remove:" + lastKey);
    	cachePool.remove(lastKey);
    	this.length--;
    }
}
