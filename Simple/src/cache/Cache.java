package cache;

import java.util.Map;

public class Cache<K,V> {

	private int maxSize;
	private EvictionStrategy<K> evictionStrat = null;
	private Map<K,V> cache;
	
	public Cache(EvictionStrategy<K> evictionStrat) {
		this.evictionStrat = evictionStrat;
	}
	
	public void updateEvictionStrategy(EvictionStrategy<K> evictionStrat) {
		this.evictionStrat = evictionStrat;
	}
	
	public V lookup(K key) {
		V value = this.cache.get(key);
		if(value == null) {
			// If the value wasn't found in the cache already
			if(this.cache.size() >= maxSize) {
				// If the cache is full we need to evict a value
				K evictKey = this.evictionStrat.evict();
				this.cache.remove(evictKey);
			}
			value = null; // Look up the value from the db or lower cache
			this.cache.put(key, value);
		}
		this.evictionStrat.update(key);
		return value;
	}
}
