package airline.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import airline.controller.ControllerUtil.FlightUriMeaning;
import airline.controller.ControllerUtil.UserUriMeaning;
import airline.dto.FlightDTO;
import airline.dto.MessageDTO;
import airline.dto.SearchFlightDTO;
import airline.model.Flight;
import airline.model.User.Role;
import airline.security.AuthUtil;
import airline.security.AuthUtil.AuthStatus;
import airline.service.FlightService;

/**
 * Servlet implementation class FlightController
 */
public class FlightController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Auth check:
//		String token = request.getHeader("Authorization");
//		//for example only admin can use this method
//		AuthStatus status = AuthUtil.authorizeToken(token, Role.ADMIN);
//		if(status != AuthStatus.AUTHORIZED) {
//			ObjectMapper mapper = new ObjectMapper();
//			MessageDTO message = new MessageDTO("error", "unauthrorized");
//			String jsonData = mapper.writeValueAsString(message);
//			response.setContentType("application/json");
//			response.setStatus(403);
//			response.getWriter().write(jsonData);	
//			return;
//		}
				
		
		//--------------------------
		
		//URI check:
		String uri = request.getRequestURI();
		FlightUriMeaning uriResult = ControllerUtil.checkFlightURI(uri);
		switch(uriResult) {
		case ALL:
			doGetAll(request, response);
			break;
		case ONE:
			doGetOne(ControllerUtil.flightId, request, response);
			break;
		case ERROR:
			MessageDTO message = new MessageDTO("error", ControllerUtil.flightErrorMessage);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);	
			break;
		case CURRENT:
			doGetCurrent(request, response);
			break;	
		case DEPARTURE_AIRPORT:
			
			break;
		case ARRIVAL_AIRPORT:
			
			break;
		}
		
		
		
		//------------------------------------------------
		
		
	}
	
	protected void doSearch(SearchFlightDTO sfDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println(sfDTO);
	
		ArrayList<Flight> flights = FlightService.search(sfDTO);
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
	
	protected void doGetCurrent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<Flight> flights = FlightService.getCurrent();
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
		String uri = request.getRequestURI();
		FlightUriMeaning uriResult = ControllerUtil.checkFlightURI(uri);
		if(uriResult == FlightUriMeaning.SEARCH) {
			//you can onlu read the request body once, and its being read here by default
			//in do search it is already read, so if you try to read it again it throws io exception
			BufferedReader reader = request.getReader();
			ObjectMapper mapper = new ObjectMapper();
			SearchFlightDTO sfDTO = mapper.readValue(reader, SearchFlightDTO.class);
			doSearch(sfDTO, request, response);
			return;
		}
		
		
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
