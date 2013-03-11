package cache;

import java.util.LinkedList;
import java.util.List;

public class Adaptive<K> implements EvictionStrategy<K> {

	private int maxListSize;
	
	private List<K> levelOneInitial;
	private List<K> levelOneRecuring;
	private List<K> levelTwoInitial;
	private List<K> levelTwoRecuring;
	
	public Adaptive(int cacheSize) {
		this.maxListSize = cacheSize / 2;
		this.levelOneInitial = new LinkedList<K>();
		this.levelOneRecuring = new LinkedList<K>();
		this.levelTwoInitial = new LinkedList<K>();
		this.levelTwoRecuring = new LinkedList<K>();
	}
	
	@Override
	public void update(K key) {
		if(this.levelOneRecuring.contains(key)) {
			// Move to the end as most recently used
			this.levelOneRecuring.remove(key);
			this.levelOneRecuring.add(key);
		} else if(this.levelOneInitial.contains(key)) {
			// Check the recuring list for max size
			if(this.levelOneRecuring.size() >= maxListSize) {
				this.levelTwoRecuring.add(this.levelOneRecuring.remove(0));
			}
			// Move this value to the recuring list at the end
			this.levelOneInitial.remove(key);
			this.levelOneRecuring.add(key);
		} else if(this.levelTwoRecuring.contains(key)) {
			// Check level one recuring for max size
			if(this.levelOneRecuring.size() >= maxListSize) {
				this.levelTwoRecuring.add(this.levelOneRecuring.remove(0));
			}
			// Move back to level one
			this.levelTwoRecuring.remove(key);
			this.levelOneRecuring.add(key);
		} else if(this.levelTwoInitial.contains(key)) {
			// Check the recuring list for max size
			if(this.levelOneRecuring.size() >= maxListSize) {
				this.levelTwoRecuring.add(this.levelOneRecuring.remove(0));
			}
			this.levelTwoInitial.remove(key);
			this.levelOneRecuring.add(key);
		} else {
			// Check if level one is full
			if(this.levelOneInitial.size() >= maxListSize) {
				this.levelTwoInitial.add(this.levelOneInitial.remove(0));
			}
			this.levelOneInitial.add(key);
		}
	}

	@Override
	public K evict() {
		K key = null;
		if(!this.levelTwoInitial.isEmpty())
			key = this.levelTwoInitial.remove(0);
		else if(!this.levelTwoRecuring.isEmpty())
			key = this.levelTwoRecuring.remove(0);
		else if(!this.levelOneInitial.isEmpty())
			key = this.levelOneInitial.remove(0);
		else if(!this.levelOneRecuring.isEmpty())
			key = this.levelOneRecuring.remove(0);
		return key;
	}

}
