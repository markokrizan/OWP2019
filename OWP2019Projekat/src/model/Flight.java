package model;

import java.util.Date;

public class Flight {
	
	private Integer id;
	private String number;
	private Date departure;
	private Date arrival;
	private Airport flyingFrom;
	private Airport flyingTo;
	private Integer numberOfSeats;
	private Double price;
	


	public Flight(Integer id, String number, Date departure, Date arrival, Airport flyingFrom, Airport flyingTo,
			int numberOfSeats, Double price) {
		super();
		this.id = id;
		setNumber(number);
		setDeparture(departure);
		setArrival(arrival);
		setFlyingFrom(flyingFrom);
		setFlyingTo(flyingTo);
		setNumberOfSeats(numberOfSeats);
		setPrice(price);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		if(number == null) {
			throw new NullPointerException("Number cannot be null!");
		}else {
			this.number = number;
		}
		
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		if(departure == null) {
			throw new NullPointerException("Departure date cannot be null!");
		}else {
			this.departure = departure;
		}
		
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		if(arrival == null) {
			throw new NullPointerException("Arrival date cannot be null!");
		}else {
			this.arrival = arrival;
		}
		
	}

	public Airport getFlyingFrom() {
		return flyingFrom;
	}

	public void setFlyingFrom(Airport flyingFrom) {
		if(flyingFrom == null) {
			throw new NullPointerException("Departure airport cannot be null!");
		}else {
			this.flyingFrom = flyingFrom;
		}
		
	}

	public Airport getFlyingTo() {
		return flyingTo;
	}

	public void setFlyingTo(Airport flyingTo) {
		if(flyingTo == null) {
			throw new NullPointerException("Arrival airport cannot be null!");
		}else {
			this.flyingTo = flyingTo;
		}
		
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		if(numberOfSeats == null) {
			throw new NullPointerException("Number of seats cannot be null!");
		}else if(numberOfSeats <= 0) {
			throw new IllegalArgumentException("Number of seats must be greater than 0!");
		}else {
			this.numberOfSeats = numberOfSeats;
		}
		
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		if(price == null) {
			throw new NullPointerException("Price cannot be null!");
		}else if(price <= 0) {
			throw new IllegalArgumentException("Price must be greater than 0!");
		}else {
			this.price = price;
		}
	
		
		
		
	}
	
	
	
	
	
	
	

	
	
	
	
	

}
