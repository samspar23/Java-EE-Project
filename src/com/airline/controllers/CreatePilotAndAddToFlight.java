package com.airline.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Pilot;
import com.airline.models.Rank;
import com.airline.service.PilotService;

/**
 * Servlet implementation class CreatePilotAndAddToFlight
 */
@WebServlet("/CreatePilotAndAddToFlight")
public class CreatePilotAndAddToFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PilotService pilotService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePilotAndAddToFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pilot pilot = new Pilot();
		pilot.setFirstName(request.getParameter("first_name"));
		pilot.setLastName(request.getParameter("last_name"));
		pilot.setLicense(Integer.parseInt(request.getParameter("license")));
		
		String rank = request.getParameter("pilot_rank");
		pilot.setRank(Rank.Captain);
		
		String flightId = request.getParameter("fid");
		
		pilotService.addNewPilotToFlight(pilot, flightId);
		
		response.sendRedirect("Flights");
		
	}

}
