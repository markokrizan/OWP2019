package airline.model;

import airline.dto.AirportDTO;

public class Airport {
	
	private int id;
	private String name;
	private Boolean deleted;
	
	
	public Airport() {
		
	}


	public Airport(int id, String name, Boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.deleted = deleted;
	}
	
	public static Airport AirportFromDTO(AirportDTO airportDTO) {
		Airport newAirport = new Airport();
		newAirport.setId(airportDTO.getId());
		newAirport.setName(airportDTO.getName());
		newAirport.setDeleted(false);
		return newAirport;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}


	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}


	@Override
	public String toString() {
		return "Airport [id=" + id + ", name=" + name + ", deleted=" + deleted + "]";
	}
	
	
	
	
	
}
