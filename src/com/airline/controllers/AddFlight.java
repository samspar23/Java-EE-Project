package com.airline.controllers;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.FlightLocations;
import com.airline.service.FlightService;

/**
 * Servlet implementation class AddFlight
 */
@WebServlet("/AddFlight")
public class AddFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FlightService flightService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Flight flight = new Flight();
		
		String origin = request.getParameter("from_destination");
		flight.setFlightOrigin(FlightLocations.Mumbai);
		
		String destination = request.getParameter("to_destinaton");
		flight.setFlightDestination(FlightLocations.Bangalore);
		
		flight.setPrice(Integer.parseInt(request.getParameter("price")));
		
		flight.setFlightTime(new Date());
		
		
		Airplane airplane = new Airplane();
		
		airplane.setMake(request.getParameter("airplane_make"));
		airplane.setModel(request.getParameter("airplane_model"));
		airplane.setCapacity(Integer.parseInt(request.getParameter("airplane_seating")));
		
		flight.setAirplane(airplane);
//		airplane.setFlight(flight);
		
		flightService.addFlight(flight, airplane);
		
		response.sendRedirect("Flights");
	}

}
