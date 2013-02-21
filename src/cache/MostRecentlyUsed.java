package cache;

import java.util.Stack;

public class MostRecentlyUsed<K> implements EvictionStrategy<K> {

	private Stack<K> keys = null;
	
	public MostRecentlyUsed() {
		this.keys = new Stack<K>();
	}
	
	@Override
	public void update(K key) {
		this.keys.remove(key);
		this.keys.push(key);
	}

	@Override
	public K evict() {
		return this.keys.pop();
	}

}
