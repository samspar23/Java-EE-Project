package com.airline.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Flight;
import com.airline.service.FlightService;

/**
 * Servlet implementation class Flights
 */
@WebServlet("/Flights")
public class Flights extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private FlightService flightService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Flights() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Flight> list = flightService.getFlights();
		request.setAttribute("flightList", list);

		PrintWriter out = response.getWriter();
//		for (Flight flight: list) {
//			out.println(flight.getFlightOrigin() + " to " + flight.getFlightDestination());
//		}

		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/flight_list.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
