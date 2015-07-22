package top.shares.funny.lru;

/**
 *  cache entity
 * 
 * */

public class CacheEntity {

	protected int data;
	protected long times;
	
	public int getData() {
		this.times = System.nanoTime();
		return data;
	}
	
	public void setData(int data) {
		this.times = System.nanoTime();
		this.data = data;
	}
	
	public long getTimes() {
		return times;
	}
	
}
