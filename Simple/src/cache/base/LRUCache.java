package cache.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUCache<K,V> {

	private int maxSize;
	
	private List<K> timeOrdering;
	private Map<K,V> cache;
	
	public LRUCache() {
		this(100);
	}
	public LRUCache(int maxSize) {
		this.timeOrdering = new ArrayList<K>();
		this.cache = new HashMap<K,V>();
	}
	
	public V lookup(K key) {
		V value = this.cache.get(key);
		if(value != null) {
			// Move to the end of the list
			this.timeOrdering.remove(key);
			this.timeOrdering.add(key);
		} else {
			// value = <database lookup>
			if(this.timeOrdering.size() >= maxSize) {
				K removing = this.timeOrdering.remove(0);
				this.cache.remove(removing);
			}
			this.timeOrdering.add(key);
			this.cache.put(key, value);
		}
		return value;
	}
}
