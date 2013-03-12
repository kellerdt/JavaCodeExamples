package random;

import java.util.Set;
import java.util.TreeSet;

public class ShardUsers {

	private Integer prevNumServers = null;
	private Integer numServers = 4;
	private boolean updating = false;
	
	private Set<Integer> users = null;
	
	public ShardUsers() {
		// Use TreeSet for O(log(n)) for all operations
		this.users = new TreeSet<Integer>();
	}
	
	// Can't calculate a shard if were in the process of changing so we have
	// to wait until its ready for notification
	public int calculateShard(Integer userId) {
		try {
			while(this.updating) {
				this.wait();
			}
			return this.calculateShard(userId, this.numServers);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Integer.MIN_VALUE;
	}
	
	// Only one thread can update the number of servers at a time since it is an
	// expensive operation.  Also check if the value is actually different
	public synchronized void setNumberOfServers(int num) {
		if(num != this.numServers) {
			this.updating = true;
			this.prevNumServers = this.numServers;
			this.numServers = num;
			this.rehashUsers();
			this.updating = false;
			this.notifyAll();
		}
	}
	
	/* Private implementation methods */
	
	@SuppressWarnings("unused")
	private void rehashUsers() {
		for(Integer userId : users) {
			Integer oldShard = this.calculateShard(userId, prevNumServers);
			Integer newShard = this.calculateShard(userId);
			
			// Pull data out of oldShard database
			// Put data in newShard database
		}
	}
	
	private int calculateShard(Integer userId, int num) {
		if(!users.contains(userId))
			this.users.add(userId);
		return userId % num;
	}
}
