package airline.model;

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
	private Boolean deleted;
	
	
	public Flight() {
		
	}
	
	
	

	
	public Flight(Integer id, String number, Date departure, Date arrival, Airport flyingFrom, Airport flyingTo,
			Integer numberOfSeats, Double price, Boolean deleted) {
		super();
		this.id = id;
		this.number = number;
		this.departure = departure;
		this.arrival = arrival;
		this.flyingFrom = flyingFrom;
		this.flyingTo = flyingTo;
		this.numberOfSeats = numberOfSeats;
		this.price = price;
		this.deleted = deleted;
	}




	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getDeparture() {
		return departure;
	}
	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	public Airport getFlyingFrom() {
		return flyingFrom;
	}
	public void setFlyingFrom(Airport flyingFrom) {
		this.flyingFrom = flyingFrom;
	}
	public Airport getFlyingTo() {
		return flyingTo;
	}
	public void setFlyingTo(Airport flyingTo) {
		this.flyingTo = flyingTo;
	}
	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getDeleted() {
		return deleted;
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//with validation:

//	//Private constructor - factory method calls it when its sure the parameters are valid
//	//Constructor shouldnt throw an exception - partially created object
//	private Flight(Integer id, String number, Date departure, Date arrival, Airport flyingFrom, Airport flyingTo,
//			Integer numberOfSeats, Double price) {
//		super();
//		this.id = id;
//		this.number = number;
//		this.departure = departure;
//		this.arrival = arrival;
//		this.flyingFrom = flyingFrom;
//		this.flyingTo = flyingTo;
//		this.numberOfSeats = numberOfSeats;
//		this.price = price;
//	}
//	
//	
//	public Flight construct(Integer id, String number, Date departure, Date arrival, Airport flyingFrom, Airport flyingTo,
//			Integer numberOfSeats, Double price) {
//		
//		if(
//				setId(id) &
//				setNumber(number) &
//				setDeparture(departure) &
//				setArrival(arrival) &
//				setFlyingFrom(flyingFrom) &
//				setFlyingTo(flyingTo) &
//				setNumberOfSeats(numberOfSeats) &
//				setPrice(price)	
//		) {
//			return new Flight(id, number, departure, arrival, flyingFrom, flyingTo, numberOfSeats, price);
//		}else {
//			return null;
//		}
//		
//	}
//	
//
//	public int getId() {
//		return id;
//	}
//
//	public boolean setId(Integer id) {
//		if(id == null) {
//			return false;
//		}else {
//			this.id = id;
//			return true;
//		}
//		
//	}
//
//	public String getNumber() {
//		return number;
//	}
//
//	public boolean setNumber(String number) {
//		if(number == null) {
//			return false;
//		}else {
//			this.number = number;
//			return true;
//		}
//		
//	}
//
//	public Date getDeparture() {
//		return departure;
//	}
//
//	public boolean setDeparture(Date departure) {
//		if(departure == null) {
//			return false;
//		}else {
//			this.departure = departure;
//			return true;
//		}
//		
//	}
//
//	public Date getArrival() {
//		return arrival;
//	}
//
//	public boolean setArrival(Date arrival) {
//		if(arrival == null) {
//			return false;
//		}else {
//			this.arrival = arrival;
//			return true;
//		}
//		
//	}
//
//	public Airport getFlyingFrom() {
//		return flyingFrom;
//	}
//
//	public boolean setFlyingFrom(Airport flyingFrom) {
//		if(flyingFrom == null) {
//			return false;
//		}else {
//			this.flyingFrom = flyingFrom;
//			return true;
//		}
//		
//	}
//
//	public Airport getFlyingTo() {
//		return flyingTo;
//	}
//
//	public boolean setFlyingTo(Airport flyingTo) {
//		if(flyingTo == null) {
//			return false;
//		}else {
//			this.flyingTo = flyingTo;
//			return true;
//		}
//		
//	}
//
//	public int getNumberOfSeats() {
//		return numberOfSeats;
//	}
//
//	public boolean setNumberOfSeats(Integer numberOfSeats) {
//		if(numberOfSeats == null) {
//			return false;
//		}else if(numberOfSeats <= 0) {
//			return false;
//		}else {
//			this.numberOfSeats = numberOfSeats;
//			return true;
//		}
//		
//	}
//
//	public double getPrice() {
//		return price;
//	}
//
//	public boolean setPrice(Double price) {
//		if(price == null ) {
//			return false;
//		}else if(price <= 0) {
//			return false;
//		}else {
//			this.price = price;
//			return true;
//		}
//	
//
//	}
	
	
	
}
