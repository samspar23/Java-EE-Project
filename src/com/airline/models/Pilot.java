package com.airline.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pilot
 *
 */
@NamedQuery(name = "Pilot.findById", query = "SELECT p FROM Pilot p WHERE p.id = :id")
@Entity

public class Pilot implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	public Pilot() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private Integer license;
	
	@Enumerated(EnumType.STRING)
	private Rank rank;
	
	@ManyToOne
	@JoinColumn(name = "flight_fk")
	private Flight flight;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getLicense() {
		return license;
	}

	public void setLicense(Integer license) {
		this.license = license;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	@Override
	public String toString() {
		return "Pilot [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", license=" + license
				+ ", rank=" + rank + ", flight=" + flight + "]";
	}
	
}
