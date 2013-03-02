package processing;

import java.util.ArrayList;
import java.util.List;

import data.Event;
import data.Location;

/**
 * A simple in memory singleton cache for storing results for the prototype.  This would be replaced with
 * a caching system such as Hibernate and eh-cache in front of a database solution.
 * 
 * The limitations of this solution are performance related in that each time a "query" is made
 * we have to search the entire data structure to filter results.  This should be fine however in
 * a simple proof of concept solution.
 */
public class PrototypeCache {

	private static PrototypeCache me = null;
	private List<Event> events = null;
	
	protected PrototypeCache() {
		this.events = new ArrayList<Event>();
	}
	
	public static PrototypeCache getInstance() {
		if(me == null) {
			me = new PrototypeCache();
		}
		return me;
	}
	
	public void addEvent(Event event) {
		this.events.add(event);
	}
	
	/**
	 * Return all of the events currently stored in the database.
	 * @return
	 */
	public List<Event> getEvents() {
		return this.events;
	}
	
	/**
	 * Return all events of a certain type stored in the database.
	 * @param type
	 * @return
	 */
	public List<Event> getEventsByType(int type) {
		List<Event> results = new ArrayList<Event>();
		for(int i=0; i < this.events.size(); i++) {
			if(this.events.get(i).getType().getType() == type)
				results.add(this.events.get(i));
		}
		return results;
	}
	
	/**
	 * Returns all the events which occur within a bounding box.
	 * @param minX
	 * @param minY
	 * @param maxX
	 * @param maxY
	 * @return
	 */
	public List<Event> getEventsInRange(int minX, int minY, int maxX, int maxY) {
		List<Event> results = new ArrayList<Event>();
		for(int i=0; i < this.events.size(); i++) {
			Location location = this.events.get(i).getLocation();
			if(location.getX() >= minX && location.getX() < maxX &&
					location.getY() >= minY && location.getY() < maxY)
				results.add(this.events.get(i));
		}
		return results;
	}
	
	/**
	 * Return all the events of a certain type which occur within a bounding box.
	 * @param type
	 * @param minX
	 * @param minY
	 * @param maxX
	 * @param maxY
	 * @return
	 */
	public List<Event> getEventsByTypeInRange(int type, int minX, int minY, int maxX, int maxY) {
		List<Event> results = new ArrayList<Event>();
		for(int i=0; i < this.events.size(); i++) {
			Location location = this.events.get(i).getLocation();
			if(this.events.get(i).getType().getType() == type &&
					location.getX() >= minX && location.getX() < maxX &&
					location.getY() >= minY && location.getY() < maxY)
				results.add(this.events.get(i));
		}
		return results;
	}
}
