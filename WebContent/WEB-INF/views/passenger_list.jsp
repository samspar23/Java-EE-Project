<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.airline.models.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Passenger List</title>
</head>
<body>
	<h1>List of Passengers:</h1>
	<table>
		<tr>
			<th>Id</th>
			<th>First name</th>
			<th>Last name</th>
			<th>Date of birth</th>
			<th>Gender</th>
		</tr>

		<%
			List<Passenger> list = (List<Passenger>) request.getAttribute("passengerList");
			for (Passenger passenger : list) {
		%>
		<tr>
			<td><%=passenger.getId() %>
			<td><%=passenger.getFirstName()%></td>
			<td><%=passenger.getLastName()%></td>
			<td><%=passenger.getDob()%></td>
			<td><%=passenger.getGender()%></td>
		</tr>
		
		<tr>
			<td colspan="5">
				<%
					if (passenger.getFlights().size() > 0) {
						List<Flight> fList = passenger.getFlights();
						
						for (Flight flight : fList) {
				%>
							<%=flight.getFlightOrigin() + " to " + flight.getFlightDestination() %> <br/>
				<%
						}
					} else {
				%>
						No flight tickets yet
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