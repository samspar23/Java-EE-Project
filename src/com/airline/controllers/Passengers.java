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

import com.airline.models.Passenger;
import com.airline.service.PassengerService;

/**
 * Servlet implementation class Passengers
 */
@WebServlet("/Passengers")
public class Passengers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private PassengerService passengerService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Passengers() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Passenger> pList = passengerService.getPassengers();

		request.setAttribute("passengerList", pList);

		PrintWriter out = response.getWriter();
//		for (Passenger passenger : pList) {
//			out.println(passenger.getFirstName() + " " + passenger.getLastName());
//		}

		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/passenger_list.jsp");
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
