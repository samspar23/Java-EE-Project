package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.Passenger;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class FlightService
 */
@Stateless
@LocalBean
public class FlightService {

	/**
	 * Default constructor.
	 */
	public FlightService() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "airline")
	private EntityManager em;

	public void addFlight(Flight flight, Airplane airplane) {
		em.persist(flight);
//		em.persist(airplane);
	}

	public void addPilotToFlight(String pilotId, String flightId) {
		TypedQuery<Flight> fQuery = em.createNamedQuery("Flight.findById", Flight.class).setParameter("id",
				Integer.parseInt(flightId));
		Flight flight = fQuery.getSingleResult();

		TypedQuery<Pilot> pQuery = em.createNamedQuery("Pilot.findById", Pilot.class).setParameter("id",
				Integer.parseInt(pilotId));
		Pilot pilot = pQuery.getSingleResult();

		List<Pilot> list = flight.getPilots();
		list.add(pilot);
		flight.setPilots(list);

		pilot.setFlight(flight);

	}

	public List<Flight> getFlights() {
		TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight f", Flight.class);
		return query.getResultList();
	}
	
	public void addPassengerToFlight(String passengerId, String flightId) {
		// get passenger by id
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Passenger> pQuery = builder.createQuery(Passenger.class);
		
		Root<Passenger> pRoot = pQuery.from(Passenger.class);
		
		pQuery.select(pRoot).where(builder.equal(pRoot.get("id").as(Integer.class), passengerId));
		
		Passenger passenger = em.createQuery(pQuery).getSingleResult();
		
		// get flight by id
		builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Flight> fQuery = builder.createQuery(Flight.class);
		
		Root<Flight> fRoot = fQuery.from(Flight.class);
		
		fQuery.select(fRoot).where(builder.equal(fRoot.get("id").as(Integer.class), flightId));
		
		Flight flight = em.createQuery(fQuery).getSingleResult();
		
		// associate passenger with flight
		flight.getPassengers().add(passenger);
		
		passenger.getFlights().add(flight);
	}
	
	public Flight getFlight(Integer flightId) {
		try {
			return em.createNamedQuery("Flight.findById", Flight.class).setParameter("id", flightId).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
	
	public Flight deleteFlight(Integer flightId) {
		
		Flight flight = em.find(Flight.class, flightId);
		
		em.remove(flight);
		
		return flight;
	}

}
