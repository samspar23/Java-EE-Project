package com.airline.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Flight
 *
 */
@NamedQuery(name = "Flight.findById", query = "SELECT f FROM Flight f WHERE f.id = :id")
@Entity

public class Flight implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	public Flight() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private FlightLocations flightOrigin;

	@Enumerated(EnumType.STRING)
	private FlightLocations flightDestination;

	private Integer price;

	@Temporal(TemporalType.TIME)
	private Date flightTime;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "airplane_fk")
	private Airplane airplane;

	@OneToMany(mappedBy = "flight", cascade = { CascadeType.REMOVE })
	private List<Pilot> pilots;

	@ManyToMany
	@JoinTable(name = "f_p_join", joinColumns = @JoinColumn(name = "flight_fk"), inverseJoinColumns = @JoinColumn(name = "passenger_fk"))
	private List<Passenger> passengers;

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public List<Pilot> getPilots() {
		return pilots;
	}

	public void setPilots(List<Pilot> pilots) {
		this.pilots = pilots;
	}

	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FlightLocations getFlightOrigin() {
		return flightOrigin;
	}

	public void setFlightOrigin(FlightLocations flightOrigin) {
		this.flightOrigin = flightOrigin;
	}

	public FlightLocations getFlightDestination() {
		return flightDestination;
	}

	public void setFlightDestination(FlightLocations flightDestination) {
		this.flightDestination = flightDestination;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(Date flightTime) {
		this.flightTime = flightTime;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightOrigin=" + flightOrigin + ", flightDestination=" + flightDestination
				+ ", price=" + price + ", flightTime=" + flightTime + "]";
	}

}
