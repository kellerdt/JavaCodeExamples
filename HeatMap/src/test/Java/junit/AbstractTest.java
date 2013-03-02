package junit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

import data.Event;
import data.Location;

public class AbstractTest {

	protected int maxX = 100;
	protected int maxY = 100;
	
	private static final String separator = ",";
	private DateFormat format = new SimpleDateFormat("yy/mm/dd hh:MM:ss");
	
	/**
	 * Creates a random location for the event to occur.
	 * @return
	 */
	protected Location generateRandomLocation() {
		Random random = new Random();
		Location location = new Location();
		location.setX(random.nextInt(maxX));
		location.setY(random.nextInt(maxY));
		return location;
	}
	
	/**
	 * Puts together the test params for the API call
	 * @param event
	 * @param extra
	 * @return
	 */
	protected String generateStringParams(Event event, String... extra) {
		StringBuilder builder = new StringBuilder();
		builder.append(String.valueOf(event.getLocation().getX())).append(AbstractTest.separator);
		builder.append(String.valueOf(event.getLocation().getY())).append(AbstractTest.separator);
		builder.append(format.format(event.getDate()));
		for(int i=0; i < extra.length; i++)
			builder.append(AbstractTest.separator).append(extra[i]);
		return builder.toString();
	}
}
