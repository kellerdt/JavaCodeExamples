package cache;

import java.util.Map;

public class Cache<K,V> {

	public static final int DEFAULT_SIZE = 10000;
	
	private EvictionStrategy<K> evictionStrat = null;
	private Map<K,V> cache;

	private int maxSize;
	private int hits = 0;
	private int misses = 0;
	
	public Cache(EvictionStrategy<K> evictionStrat) {
		this(evictionStrat, DEFAULT_SIZE);
	}
	
	public Cache(EvictionStrategy<K> evictionStrat, int maxSize) {
		this.evictionStrat = evictionStrat;
		this.maxSize = maxSize;
	}
	
	public void updateEvictionStrategy(EvictionStrategy<K> evictionStrat) {
		this.evictionStrat = evictionStrat;
	}
	
	public V lookup(K key) {
		V value = this.cache.get(key);
		if(value == null) {
			this.misses++;
			// If the value wasn't found in the cache already
			if(this.cache.size() >= maxSize) {
				// If the cache is full we need to evict a value
				K evictKey = this.evictionStrat.evict();
				this.cache.remove(evictKey);
			}
			value = null; // Look up the value from the db or lower cache
			this.cache.put(key, value);
		} else
			this.hits++;
		this.evictionStrat.update(key);
		return value;
	}
	
	public int getHits() {
		return this.hits;
	}
	
	public int getMisses() {
		return this.misses;
	}
}
