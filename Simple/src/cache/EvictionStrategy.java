package cache;

public interface EvictionStrategy<K> {

	public void update(K key);
	public K evict();
}
