package junit;

import static org.junit.Assert.fail;
import http.EventSystem;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import processing.EventProcessor;
import data.Death;
import data.Event;
import data.Event.Type;

public class TestPrototype extends AbstractTest {

	EventProcessor eventProcessor;
	
	@Before
	public void setup() {
		System.out.println("Setting up resources");
		EventSystem submit = new EventSystem();
		eventProcessor = new EventProcessor();
		Type death = new Type(Type.DEATH);
		
		Event death1 = new Death();
		death1.setType(death);
		death1.setLocation(generateRandomLocation());
		death1.setDate(new Date());
		String[] args = new String[2];
		args[0] = "1";
		args[1] = "3";
		
		submit.submitEvent(death.getType(), generateStringParams(death1, args));
		
		synchronized(this) {
			try { // Allow 3 sec for processor to get data
				this.wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("The HeatMap Prototype should be set up.");
	}
	
	@Test
	public void test() {
		if(eventProcessor == null)
			fail("EventProcessor was null");
		// Make sure that events are returned
		assert(!eventProcessor.getEvents().isEmpty());
		// Make sure that at least one event returned is of type Death
		assert(!eventProcessor.getEventsByType(Event.Type.DEATH).isEmpty());
		// Make sure that no events are of type Quadrakill
		assert(eventProcessor.getEventsByType(Event.Type.QUADRAKILL).isEmpty());
	}

	@After
	public void cleanup() {
		System.out.println("Cleaning up resources");
		eventProcessor.kill();
	}
}
