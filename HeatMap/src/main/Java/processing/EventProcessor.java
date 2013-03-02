package processing;

import java.util.List;

import messaging.PrototypeQueue;
import data.Event;

public class EventProcessor implements Runnable {
	
	private static final long TIMEOUT = 1000;
	private boolean alive = true;
	
	private PrototypeQueue queue = PrototypeQueue.getInstance();
	private PrototypeCache cache = PrototypeCache.getInstance();
	
	public EventProcessor() {
		Thread processor = new Thread(this);
		processor.start();
	}
	
	public List<Event> getEvents() {
		return cache.getEvents();
	}
	
	public List<Event> getEventsByType(int type) {
		return cache.getEventsByType(type);
	}
	
	public List<Event> getEventsInRange(int minX, int minY, int maxX, int maxY) {
		return cache.getEventsInRange(minX, minY, maxX, maxY);
	}
	
	public List<Event> getEventsByTypeInRange(int type, int minX, int minY, int maxX, int maxY) {
		return cache.getEventsByTypeInRange(type, minX, minY, maxX, maxY);
	}
	
	/**
	 * Continuously poll for new results in a separate thread for simplicity.  If we were
	 * utilizing a JMS solution, this would register to receive notifications when messages
	 * are on the queue.
	 */
	@Override
	public void run() {
		while(alive) {
			try {
				synchronized(this) {
					wait(TIMEOUT);
				}
			} catch(InterruptedException ex) {
				System.err.println("Error while waiting to process next messsages");
			}
			if(alive) {
				while(!queue.isEmpty()) {
					cache.addEvent(queue.getMessage());
				}
			}
		}
		System.out.println("Ending processing thread");
	}
	
	/**
	 * Ends the processing of messages
	 */
	public void kill() {
		synchronized(this) {
			this.alive = false;
			notifyAll();
		}
	}
}
