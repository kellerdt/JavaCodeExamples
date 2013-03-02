HeatMap Prototype Project ReadMe:

 ---

For the demo project I have set up several ways to test the functionality of the system.
The first is a JUnit automated test case which just inserts a single event into the prototype
system and checks to ensure that the value is passed back correctly.

 ---

Second I set up the project as a war file API which should be able to be deployed on any
web server.  The build process is set up using maven and it will create a heatmap.war file which
when deployed on my local tomcat server created the default context of heatmap.  Using this
schema as a map the following urls should work to demonstrate the functionality of the system.

Example URL used to populate an event.
	http://localhost/heatmap/api/submitEvent?type=1&arg=0,0,13,3,5
	- Max values for X and Y are currently set up as 100

Test URL to view basic heatmap.
	http://localhost/heatmap/

A better version using a js library I found can be viewed here:
	http://localhost/heatmap/heatmap.jsp

 ---

Finally there is a file located within the WEB-INF/classes directory of the war file which allows
initial data to be loaded into the system at startup.  Each line of the file represents a single
call to the submitEvent API call.  Each of the events in the testdata file are assumed to be
deaths at this time for simplicity.  The file can be renamed in order to prevent the initial data
from being loaded.  The results of the loaded file can be expanded upon using the above API
call after it is loaded as well to see dynamic functionality.
	- Max values of 100 for X and Y coordinates

 ---

NOTES:
1. The third parameter in the submitEvent call and testdata file is a date.  For the purposes of the
	prototype this data was not being used so the formatting shouldn't matter.  I used the text "13"
	for each of the tests I was running.
2. Adding a description field would be easy as just another value in the comma separated list.  If
	commas are a desired value in the description the delimiting character can be changed to another
	character which is less common such as ~.