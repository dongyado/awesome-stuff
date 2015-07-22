package top.shares.funny.lru;


public class LruCacheTest {

	public static LRUCache cache = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cache = new LRUCache(2);
		
//		cache.set(1, 11);
//		cache.set(2, 22);
//		cache.set(1, 33);
//		System.out.println(cache.getEntry(1).next.next.value);
//		
		//2,[get(2),set(2,6),get(1),set(1,5),set(1,2),get(1),get(2)]
		
		p(2);
		set(2, 6);
		p(1);
		set(1,5);
		set(1,2);
		p(1);
		p(2);
		

	}
	
	public static void set(int key, int value)
	{
		cache.set(key, value);
	}
	
	public static void p( int key){
		System.out.println(cache.get(key));
	}

}
