package data;

import java.util.Date;

public class Death extends Event {

	private int death = Integer.MIN_VALUE;
	private int killer = Integer.MIN_VALUE;
	
	public Death() {
		// Default constructor for compatibility with jpa / hibernate
	}
	
	public Death(Location location, Type type, Date date, String... args) {
		super(location, type, date);
		if(args.length == 2) {
			death = Integer.valueOf(args[0]);
			killer = Integer.valueOf(args[1]);
		}
	}

	/**
	 * @return the death
	 */
	public int getDeath() {
		return death;
	}

	/**
	 * @param death the death to set
	 */
	public void setDeath(int death) {
		this.death = death;
	}

	/**
	 * @return the killer
	 */
	public int getKiller() {
		return killer;
	}

	/**
	 * @param killer the killer to set
	 */
	public void setKiller(int killer) {
		this.killer = killer;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder(super.toString()).append(" Death [death=").append(death).append(", killer=")
				.append(killer).append("]").toString();
	}
}
