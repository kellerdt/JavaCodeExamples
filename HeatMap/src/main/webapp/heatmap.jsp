<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Heat Map Prototype 2</title>
</head>
<body>
	<style type="text/css">
		div.heatmap {
			position: relative;
			background: #F8F8F8 url('./img/map1.png') no-repeat center center;
			border: 1px solid black;
			width: 400px;
			height: 400px;
		}
	</style>
	<h2>Example Heat Map with JS library:</h2>
	
	<div id="heatmapArea" class="heatmap">
	</div>
	
	<script type="text/javascript" src="./js/heatmap.js"></script>
	<script type="text/javascript">
	window.onload = function() {
		var config = {
		    "radius": 10,
		    "element": document.getElementById("heatmapArea"),
		    "visible": true,
		    "opacity": 40,
		    "gradient": { 0.45: "rgb(0,0,255)", 0.55: "rgb(0,255,255)", 0.70: "rgb(0,255,0)", 0.80: "yellow", 1.0: "rgb(255,0,0)" }
		};
		
		var heatmap = h337.create(config);
		<%
		int multiple = 4;
		int[][] positions = new int[100][100];
		processing.EventProcessor processor = new processing.EventProcessor();
		java.util.List<data.Event> events = processor.getEventsByType(1);
		for(data.Event event : events) {
			data.Location location = event.getLocation();
			positions[location.getX()][location.getY()] += 1;
		}
		for(int y=0; y < 100; y++) {
			for(int x=0; x < 100; x++) {
				if(positions[x][y] > 0) {%>
					heatmap.store.addDataPoint(<%out.print(x*multiple);%>,<%out.print(y*multiple);%>,<%out.print(positions[x][y]);%>);
				<% }
			}
		} %>
	}
	</script>
</body>
</html>
