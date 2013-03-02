package http;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import messaging.PrototypeQueue;
import data.Death;
import data.Event;
import data.Location;

/**
 * Submits events which happen in the game to the platform for processing.
 */
@Path(EventSystem.RESOURCE_PATH)
public class EventSystem {
	public static final String RESOURCE_PATH = "submitEvent";
	private static final String TYPE = "type";
	private static final String ARGS = "arg";
	
	private static PrototypeQueue queue = PrototypeQueue.getInstance();
	private DateFormat format = new SimpleDateFormat("yy-mm-dd hh:MM:ss");
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String submitEvent(@QueryParam(TYPE)Integer typeId, @QueryParam(ARGS)String arg) {
		if(typeId != null && arg != null) {
			String[] args = arg.split(",");
			Event event = null;
			Location location = new Location(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
			Date date = null;
			try {
				date = format.parse(args[2]);
			} catch (ParseException e) {
				// Swallow the exception for now.  It may be acceptable to just use the date the server
				// processed the request instead of requesting this data.
			}
			
			// Create a new array for event args.  May not need to do this by just passing the correct index
			// value to start at but for now I am.
			String[] eventArgs = new String[args.length - 3];
			for(int i=3; i < args.length; i++)
				eventArgs[i-3] = args[i];
				
			if(typeId == Event.Type.DEATH) {
				Event.Type type = new Event.Type(typeId);
				event = new Death(location, type, date, eventArgs);
			}
			if(event != null) {
				queue.pushMessage(event);
				return "True";
			}
		}
		return "False";
	}
}
