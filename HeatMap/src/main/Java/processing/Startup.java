package processing;

import http.EventSystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Startup implements Servlet {

	private EventProcessor processor = null;
	
	@Override
	public void destroy() {
		this.processor.kill();
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.processor = new EventProcessor();
		this.loadTestData(config);
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// We aren't servicing any requests with this servlet it just gets the prototype running.
	}
	
	/*
	 * Read in default data if it is available
	 */
	private void loadTestData(ServletConfig config) {
		EventSystem eventSystem = new EventSystem();
		String filename = config.getServletContext().getRealPath("/WEB-INF/classes/testdata.txt");
		BufferedReader fileReader = null;
		try {
			fileReader = new BufferedReader(new FileReader(filename));
			try {
				// Assuming all events in the file are deaths for now
				String line;
				while((line = fileReader.readLine()) != null) {
					eventSystem.submitEvent(1, line);
				}
			} catch (IOException e) {
				// If there was an issue reading the data ignore that line 
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// If the file is not found just don't load any default data.
			e.printStackTrace();
		} finally {
			if(fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Not implemented yet since I didn't get to implement any non web based GUI output
	 * systems to view the data.
	 * @param args
	 */
	public static void main(String[] args) {
		Startup startup = new Startup();
		try {
			// Were not using any servlet properties so just add an optional main
			startup.init(null);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
