package cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LeastFrequentlyUsed<K> implements EvictionStrategy<K> {

	private Map<K,Integer> frequency = null;
	
	public LeastFrequentlyUsed() {
		this.frequency = new HashMap<K,Integer>();
	}
	
	@Override
	public void update(K key) {
		this.frequency.put(key, this.frequency.get(key) + 1);
	}

	@Override
	public K evict() {
		K key = null;
		int min = Integer.MAX_VALUE;
		for(Entry<K,Integer> entry : this.frequency.entrySet()) {
			if(entry.getValue() < min) {
				key = entry.getKey();
				min = entry.getValue();
			}
		}
		if(key != null)
			this.frequency.remove(key);
		return key;
	}

}
