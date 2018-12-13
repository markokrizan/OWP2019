package airline.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import airline.dto.AirportDTO;
import airline.dto.MessageDTO;
import airline.model.Airport;
import airline.service.AirportService;

/**
 * Servlet implementation class AirportController
 */
public class AirportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Auth check:
		
		//forward za authentication check
		//forward za authorization check
		
		
		//----------------------
		
		//URI check:
		String uri = request.getRequestURI();
		Integer lastSlashIndex = uri.lastIndexOf('/');
		String lastUriPart = uri.substring(lastSlashIndex + 1, uri.length());

		
		try {
			Integer id = Integer.parseInt(lastUriPart);
			doGetOne(id, request, response);
			return;
		}catch (NumberFormatException e) {
			if(lastUriPart.equals("airport")){
				doGetAll(request, response);
				return;
			}else {
				MessageDTO message = new MessageDTO("error", e.getMessage());
				ObjectMapper mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(message);
				response.setContentType("application/json");
				response.getWriter().write(jsonData);	
				return;
			}
		
		}
		
		//------------------------------------------------
		
		
		
		
		
	}
	
	protected void doGetOne(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Airport airport = AirportService.getOne(id);
		if(airport != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(new AirportDTO(airport));
			response.setContentType("application/json");
			response.setStatus(200);
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "No such id!");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(200);
			response.getWriter().write(jsonData);	
		}
	}
	
	protected void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<Airport> airports = AirportService.getAll();
		if(airports != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(AirportDTO.toDTO(airports));
			response.setContentType("application/json");
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "Error while getting airports!");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);	
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Auth check -------------------
		
		
		
		
		//------------------------------
		
		//Uri check --------------------
		
		
		
		//------------------------------
		
		BufferedReader reader = request.getReader();
		ObjectMapper mapper = new ObjectMapper();
		AirportDTO airportDTO = mapper.readValue(reader, AirportDTO.class);
		Airport insertedAirport;

		insertedAirport = AirportService.create(Airport.AirportFromDTO(airportDTO));
		if(insertedAirport != null) {
			String jsonData = mapper.writeValueAsString(new AirportDTO(insertedAirport));
			response.setContentType("application/json");
			response.setStatus(201);
			response.getWriter().write(jsonData);
		}else {
			MessageDTO message = new MessageDTO("error", "Error while storing airport!");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(400);
			response.getWriter().write(jsonData);	
		}
	
		
		
		
		
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Auth check -------------------
		
		
		
		
		//------------------------------
				
		//Uri check --------------------
				
				
				
		//------------------------------
		
		BufferedReader reader = request.getReader();
		ObjectMapper mapper = new ObjectMapper();
		AirportDTO airportDTO = mapper.readValue(reader, AirportDTO.class);
		
		
		Airport changedAirport;
		
	
		changedAirport = AirportService.update(Airport.AirportFromDTO(airportDTO));
		if(changedAirport != null) {
			String jsonData = mapper.writeValueAsString(new AirportDTO(changedAirport));
			response.setContentType("application/json");
			response.setStatus(201);
			response.getWriter().write(jsonData);
		}else {
			MessageDTO message = new MessageDTO("error", "Error while changing airport!");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(400);
			response.getWriter().write(jsonData);	
		}
	
	
	
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Auth check -------------------
		
		
		
		
		//------------------------------
				
		//Uri check --------------------
				
				
				
		//------------------------------
		
		
		BufferedReader reader = request.getReader();
		ObjectMapper mapper = new ObjectMapper();
		AirportDTO airportDTO = mapper.readValue(reader, AirportDTO.class);
		
		Airport airportForDeletion;
		
		
		airportForDeletion = AirportService.delete(Airport.AirportFromDTO(airportDTO));
		if(airportForDeletion != null) {
			String jsonData = mapper.writeValueAsString(new AirportDTO(airportForDeletion));
			response.setContentType("application/json");
			response.setStatus(201);
			response.getWriter().write(jsonData);
		}else {
			MessageDTO message = new MessageDTO("error", "Error while deleting airport!");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(400);
			response.getWriter().write(jsonData);	
		}
		
		
	}

	
	
	

}
