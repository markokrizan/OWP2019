package airline.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


import airline.dto.FlightDTO;
import airline.dto.MessageDTO;

import airline.model.Flight;

import airline.service.FlightService;

/**
 * Servlet implementation class FlightController
 */
public class FlightController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Auth check
		
		
		//--------------------------
		
		//URI check:
		String uri = request.getRequestURI();
		Integer lastSlashIndex = uri.lastIndexOf('/');
		String lastUriPart = uri.substring(lastSlashIndex + 1, uri.length());

		
		try {
			Integer id = Integer.parseInt(lastUriPart);
			doGetOne(id, request, response);
			return;
		}catch (NumberFormatException e) {
			if(lastUriPart.equals("flight")){
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
		Flight flight = FlightService.getOne(id);
		if(flight != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(FlightDTO.FlightDTOFactory(flight));
			response.setContentType("application/json");
			response.setStatus(200);
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "invalid_flight_id");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(400);
			response.getWriter().write(jsonData);	
		}
		
	}
	
	protected void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<Flight> flights = FlightService.getAll();
		if(flights != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(FlightDTO.toDTO(flights));
			response.setContentType("application/json");
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
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
		FlightDTO flightDTO = mapper.readValue(reader, FlightDTO.class);
		Flight insertedFlight;

		insertedFlight = FlightService.create(Flight.flightFromDTO(flightDTO));
		if(insertedFlight != null) {
			String jsonData = mapper.writeValueAsString(FlightDTO.FlightDTOFactory(insertedFlight));
			response.setContentType("application/json");
			response.setStatus(201);
			response.getWriter().write(jsonData);
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
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
		FlightDTO flightDTO = mapper.readValue(reader, FlightDTO.class);
		
		
		Flight changedFlight;
		
	
		changedFlight = FlightService.update(Flight.flightFromDTO(flightDTO));
		if(changedFlight != null) {
			String jsonData = mapper.writeValueAsString(FlightDTO.FlightDTOFactory(changedFlight));
			response.setContentType("application/json");
			response.setStatus(201);
			response.getWriter().write(jsonData);
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
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
		FlightDTO flightDTO = mapper.readValue(reader, FlightDTO.class);
		
		Flight flightForDeletion;
		
		
		flightForDeletion = FlightService.delete(Flight.flightFromDTO(flightDTO));
		if(flightForDeletion != null) {
			String jsonData = mapper.writeValueAsString(FlightDTO.FlightDTOFactory(flightForDeletion));
			response.setContentType("application/json");
			response.setStatus(201);
			response.getWriter().write(jsonData);
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(400);
			response.getWriter().write(jsonData);	
		}
		
	}
	
	
	
	
	
	
	
	
	
	

}
