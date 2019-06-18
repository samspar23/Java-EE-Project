package com.airline.webservices.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.airline.models.Flight;
import com.airline.service.FlightService;

@Path("/flights")
public class FlightsWebService {

	@PersistenceContext(unitName = "airline")
	private EntityManager em;

	@EJB
	private FlightService flightService;

	@Context
	private UriInfo uriInfo;

	public FlightsWebService() {

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getFlights() {
		return flightService.getFlights();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{flightId}")
	public Flight getFlight(@PathParam("flightId") Integer flightId) {
		
		Flight flight = flightService.getFlight(flightId);
		
		if (flight == null) {
			throw new NotFoundException("The flight with id of " + flightId + " was not found");
		}
		
		return flight;
	}
	
	@DELETE
	@Path("{flightId}")
	public Response deleteFlight(@PathParam("flightId") Integer flightId) {
		
		Flight flight = flightService.deleteFlight(flightId);
		
		if (flight == null) {
			throw new NotFoundException("The flight with id of " + flightId + " was not found.");
		}
		
		return Response.noContent().build();
	}
}
