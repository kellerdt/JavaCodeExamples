package messaging;

import java.util.LinkedList;
import java.util.Queue;

import data.Event;

/**
 * A simple singleton queue that just implements an in memory linked list casted as
 * a queue to handle test requests.
 */
public class PrototypeQueue {

	private static PrototypeQueue me = null;
	private Queue<Event> queue = null;
	
	protected PrototypeQueue() {
		this.queue = new LinkedList<Event>();
	}
	
	public static PrototypeQueue getInstance() {
		if(me == null) {
			me = new PrototypeQueue();
		}
		return me;
	}
	
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}
	
	public boolean pushMessage(Event message) {
		return this.queue.offer(message);
	}
	
	public Event getMessage() {
		return this.queue.poll();
	}
}
