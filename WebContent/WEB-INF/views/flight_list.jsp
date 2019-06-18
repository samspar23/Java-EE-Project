<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.airline.models.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight List</title>
</head>
<body>
	<h1>List of flights:</h1>
	<table>
		<tr>
			<th>Id</th>
			<th>From</th>
			<th>To</th>
			<th>Time</th>
			<th>Price</th>
			<th>Airplane</th>
			<th>Pilots</th>
			<th>Pilot name</th>
		</tr>
		
		<%
			List<Flight> list = (List<Flight>) request.getAttribute("flightList");
			for (Flight flight : list) {
				
		%>
			<tr>
				<td><%=flight.getId() %>
				<td><%=flight.getFlightOrigin()%></td>
				<td><%=flight.getFlightDestination()%></td>
				<td><%=flight.getFlightTime()%></td>
				<td><%=flight.getPrice()%></td>
				<td><%=flight.getAirplane().getMake() + " " + flight.getAirplane().getModel() %></td>
				<td><%=flight.getPilots().size() %></td>
			</tr>
			
			<tr>
				<td colspan="8">
					<%
						if (flight.getPassengers().size() > 0) {
							List<Passenger> pList = flight.getPassengers();
							
							for (Passenger passenger : pList) {
					%>
								<%=passenger.getFirstName() + " " + passenger.getLastName() %> </br>
					<%
							}
						} else {
					%>
							No passengers on this flight yet.
					<%
						}
					%>
				</td>
			</tr>
		<%
			}
		%>
	</table>
</body>
</html>