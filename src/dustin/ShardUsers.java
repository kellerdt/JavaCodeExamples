package dustin;

public class ShardUsers {

	private Integer numServers = 4;
	
	public int calculateShard(Integer userId) {
		return userId % numServers;
	}
}
