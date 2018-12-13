package airline.dto;

import java.io.Serializable;
import java.util.ArrayList;

import airline.model.Airport;

public class AirportDTO implements Serializable {
	
	private Integer id;
	private String name;
	
	
	
	public AirportDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public AirportDTO() {
		
	}
	
	
	public AirportDTO(Airport airport) {
		this(airport.getId(), airport.getName());
	}
	
	public static ArrayList<AirportDTO> toDTO(ArrayList<Airport> airports){
		if(airports != null) {
			ArrayList<AirportDTO> airportDTOs = new ArrayList<AirportDTO>();
			for(Airport airport : airports) {
				AirportDTO aDTO = new AirportDTO(airport);
				airportDTOs.add(aDTO);
			}
			return airportDTOs;
		}else {
			return null;
		}
		
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

	@Override
	public String toString() {
		return "AirportDTO [id=" + id + ", name=" + name + "]";
	}
	
	
	
	

}
