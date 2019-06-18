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

import com.airline.models.Flight;
import com.airline.models.Passenger;

/**
 * Session Bean implementation class PassengerService
 */
@Stateless
@LocalBean
public class PassengerService {

	/**
	 * Default constructor.
	 */
	public PassengerService() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "airline")
	private EntityManager em;

	public Passenger addPassenger(Passenger passenger) {
		em.persist(passenger);
		return passenger;
	}

	public List<Passenger> getPassengers() {
		TypedQuery<Passenger> query = em.createQuery("SELECT p FROM Passenger p", Passenger.class);
		return query.getResultList();
	}
	
	public Passenger getPassenger(Integer passengerId) {
		try {
			return em.createNamedQuery("Passenger.findById", Passenger.class).setParameter("id", passengerId).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public void addFlightTicketToPassenger(String flightId, String passengerId) {
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

		// associate flight with passenger
		passenger.getFlights().add(flight);
		
		flight.getPassengers().add(passenger);

	}
	
	public Passenger updatePassenger(Integer passengerId, Passenger updatedPassenger) {
		
		updatedPassenger.setId(passengerId);
		
		Passenger existingPassenger = em.find(Passenger.class, passengerId);
		
		if (existingPassenger == null) {
			return null;
		}
		
		em.merge(updatedPassenger);
		
		return updatedPassenger;
	}
}
