package airline.dto;

import java.io.Serializable;

import airline.model.Airport;

public class AirportDTO implements Serializable {
	
	private Integer id;
	private String name;
	
	
	
	public AirportDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	public AirportDTO(Airport airport) {
		this(airport.getId(), airport.getName());
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
