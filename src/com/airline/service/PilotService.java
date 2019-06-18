package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airline.models.Flight;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class PilotService
 */
@Stateless
@LocalBean
public class PilotService {

    /**
     * Default constructor. 
     */
    public PilotService() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName = "airline")
    private EntityManager em;
    
    public void addPilot(Pilot pilot) {
    	em.persist(pilot);
    }
    
    public void addNewPilotToFlight(Pilot pilot, String flightId) {
    	
    	addPilot(pilot);
    	
		TypedQuery<Flight> fQuery = em.createNamedQuery("Flight.findById", Flight.class).setParameter("id",
				Integer.parseInt(flightId));
		Flight flight = fQuery.getSingleResult();

		List<Pilot> list = flight.getPilots();
		list.add(pilot);
		flight.setPilots(list);

		pilot.setFlight(flight);

	}


}
