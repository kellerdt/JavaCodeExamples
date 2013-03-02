package data;

import java.util.Date;

/**
 * Class representing an event in the system.  Each event has some common properties so they can
 * be handled with the same system and organized properly in the database.
 */
public abstract class Event {

	protected Location location = null;
	protected Type type = null;
	protected Date date = null;
	
	protected Event() {
		// Default constructor used for jpa / hibernate
	}
	
	protected Event(Location location, Type type, Date date) {
		this.location = location;
		this.type = type;
		this.date = date;
	}
	
	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder("Event [location=").append(location).append(", type=").append(type)
				.append(", date=").append(date).append("]").toString();
	}

	/**
	 * Class to define the types of Events possible.  Just creating
	 * deaths for now.
	 */
	public static class Type {
		public static final int DEATH = 1;
		public static final int QUADRAKILL = 2;
		
		public int type = Integer.MIN_VALUE;
		
		public Type(int id) {
			this.type = id;
		}
		
		public int getType() {
			return this.type;
		}
		
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Type other = (Type) obj;
			if (type == Integer.MIN_VALUE) {
				if (other.type != Integer.MIN_VALUE)
					return false;
			} else if (!(type == other.type))
				return false;
			return true;
		}
	}
}
