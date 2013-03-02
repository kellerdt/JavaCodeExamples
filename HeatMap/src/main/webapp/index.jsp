<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Heat Map Prototype</title>
</head>
<body>
	<style type="text/css">
		table.heatmap {
			border: 1px solid black;
			margin: 0;
			padding: 0;
		}
		table tr {
			margin: 0 auto;
			padding: 0px;
		}
		table tr td {
			margin: 0 auto;
			padding: 0px;
		}
	</style>
	
	<h2>Example Heat Map:</h2>
	
	<%
	int[][] heatMap = new int[100][100];
	
	processing.EventProcessor processor = new processing.EventProcessor();
	java.util.List<data.Event> events = processor.getEventsByType(1);
	for(data.Event event : events) {
		data.Location location = event.getLocation();
		heatMap[location.getX()][location.getY()] += 1;
	}
	%>
	<table class="heatmap">
		<% for(int y=0; y < 100; y++) { %>
		<tr>
			<% for(int x=0; x < 100; x++) { %>
			<td 
			<%
			// Setting the class wasn't working to color the cells so I fell back to using
			// the old bgcolor attribute which appears to be working.
			int value = heatMap[x][y];
			if(value == 1) {
				out.print("bgcolor=\"#FFFF99\"");
			} else if(value == 2) {
				out.print("bgcolor=\"#FFFF33\"");
			} else if(value == 3) {
				out.print("bgcolor=\"#FFCC33\"");
			} else if(value == 4) {
				out.print("bgcolor=\"#FF9900\"");
			} else if(value == 5) {
				out.print("bgcolor=\"#FF6633\"");
			} else if(value >= 6) {
				out.print("bgcolor=\"#FF0000\"");
			}
			%>
			>&nbsp;</td>
			<% } %>
		</tr>
		<% } %>
	</table>
</body>
</html>