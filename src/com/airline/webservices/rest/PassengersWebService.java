package com.airline.webservices.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.airline.models.Passenger;
import com.airline.service.PassengerService;

@Path("/passengers")
public class PassengersWebService {

	@PersistenceContext(unitName = "airline")
	private EntityManager em;

	@EJB
	private PassengerService passengerService;

	@Context
	private UriInfo uriInfo;

	public PassengersWebService() {

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Passenger> getPassengers() {
		return passengerService.getPassengers();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{passengerId}")
	public Passenger getPassenger(@PathParam("passengerId") Integer passengerId) {

		Passenger passenger = passengerService.getPassenger(passengerId);

		if (passenger == null) {
			throw new NotFoundException("The passenger with id of " + passengerId + " was not found");
		}

		return passenger;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPassenger(Passenger passenger) {
		passenger = passengerService.addPassenger(passenger);

		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();

		URI uri = uriBuilder.path(Integer.toString(passenger.getId())).build();

		return Response.created(uri).build();
	}

	@PUT
	@Path("/edit/{passengerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePassenger(@PathParam("passengerId") Integer passengerId, Passenger updatedPassenger) {
		
		updatedPassenger = passengerService.updatePassenger(passengerId, updatedPassenger);
		
		if (updatedPassenger == null) {
			throw new NotFoundException("The passenger with id of " + passengerId + " was not found.");
		}

		return Response.ok(updatedPassenger).build();
	}
}
