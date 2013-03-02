package cache;

import java.util.LinkedList;
import java.util.List;

public class LeastRecentlyUsed<K> implements EvictionStrategy<K> {

	private List<K> keys = null;
	
	public LeastRecentlyUsed() {
		this.keys = new LinkedList<K>();
	}
	
	@Override
	public void update(K key) {
		this.keys.remove(key);
		this.keys.add(key);
	}
	
	@Override
	public K evict() {
		return this.keys.remove(0);
	}
}
