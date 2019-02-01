package airline.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import airline.controller.ControllerUtil.GenericUriMeaning;
import airline.dto.FlightDTO;
import airline.dto.MessageDTO;
import airline.dto.SearchFlightDTO;
import airline.model.Flight;
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
		GenericUriMeaning uriMeaning = ControllerUtil.genericChecker(uri);
		switch(uriMeaning) {
		case FLIGHT_ALL:
			doGetAll(request, response);
			break;
		case FLIGHT_ONE:
			doGetOne(ControllerUtil.flightId, request, response);
			break;
		case ERROR:
			MessageDTO message = new MessageDTO("error", "uri error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);	
			break;
		case FLIGHT_CURRENT:
			doGetCurrent(request, response);
			break;
		case FLIGHT_RETURNING:
			doGetReturning(ControllerUtil.flightId, request, response);
			break;
		case FLIGHT_OCCUPIED_SEATS:
			doGetOccupiedSeats(ControllerUtil.flightId, request, response);
			break;
		case FLIGHT_DEPARTURE_AIRPORT:
			
			break;
		case FLIGHT_ARRIVAL_AIRPORT:
			
			break;
		}
		
		
		
		//------------------------------------------------
		
		
	}
	
	protected void doGetOccupiedSeats(Integer flightId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(flightId);
		ArrayList<Integer> seats = FlightService.getOccupiedSeats(flightId);
		System.out.println(seats);
		if(seats != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(seats);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}
	}
	
	
	protected void doGetReturning(Integer flightId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(flightId);
		ArrayList<Flight> flights = FlightService.returning(flightId);
		System.out.println(flights);
		if(flights != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(FlightDTO.toDTO(flights));
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}
	}

	protected void doSearch(SearchFlightDTO sfDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println(sfDTO);
	
		ArrayList<Flight> flights = FlightService.search(sfDTO);
		if(flights != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(FlightDTO.toDTO(flights));
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}
		
	}
	
	protected void doGetCurrent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<Flight> flights = FlightService.getCurrent();
		if(flights != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(FlightDTO.toDTO(flights));
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}
	
		
		
	}
	
	
	protected void doGetOne(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Flight flight = FlightService.getOne(id);
		if(flight != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(FlightDTO.FlightDTOFactory(flight));
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(200);
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "invalid_flight_id");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(400);
			response.getWriter().write(jsonData);	
		}
		
	}
	
	protected void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<Flight> flights = FlightService.getAll();
		if(flights != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(FlightDTO.toDTO(flights));
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BufferedReader reader = null;
		ObjectMapper mapper = null;
		
		//Auth check -------------------
		
		
		
		
		//------------------------------
		
		//Uri check --------------------
		String uri = request.getRequestURI();
		GenericUriMeaning uriMeaning = ControllerUtil.genericChecker(uri);
		switch(uriMeaning){
			case ERROR:
				MessageDTO message = new MessageDTO("error", "uri error");
				mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(message);
				response.setContentType("application/json");
				response.getWriter().write(jsonData);
				return;
			case FLIGHT_SEARCH:
				//you can onlu read the request body once, and its being read here by default
				//in do search it is already read, so if you try to read it again it throws io exception
				reader = request.getReader();
				mapper = new ObjectMapper();
				SearchFlightDTO sfDTO = mapper.readValue(reader, SearchFlightDTO.class);
				doSearch(sfDTO, request, response);
				return;
			case FLIGHT_CREATE:
				reader = request.getReader();
				mapper = new ObjectMapper();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				mapper.setDateFormat(df);
				FlightDTO flightDTO = mapper.readValue(reader, FlightDTO.class);
				doCreateFlight(flightDTO, request, response);
				return;
		}
		
	
		

		
	}

	public void doCreateFlight(FlightDTO fDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper mapper = null;
		Flight insertedFlight;
		

		insertedFlight = FlightService.create(Flight.flightFromDTO(fDTO));
		if(insertedFlight != null) {
			String jsonData = mapper.writeValueAsString(FlightDTO.FlightDTOFactory(insertedFlight));
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(201);
			response.getWriter().write(jsonData);
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(400);
			response.getWriter().write(jsonData);	
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Auth check -------------------
		
		
		
		
		//------------------------------
				
		//Uri check --------------------
		String uri = request.getRequestURI();
		GenericUriMeaning uriMeaning = ControllerUtil.genericChecker(uri);
		switch(uriMeaning){
			case ERROR:
				MessageDTO message = new MessageDTO("error", "uri error");
				ObjectMapper mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(message);
				response.setContentType("application/json");
				response.getWriter().write(jsonData);
				return;
			case FLIGHT_UPDATE:
				BufferedReader reader = request.getReader();
				mapper = new ObjectMapper();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				mapper.setDateFormat(df);
				FlightDTO flightDTO = mapper.readValue(reader, FlightDTO.class);
				doUpdate(flightDTO, request, response);
				return;
		}
				
				
		//------------------------------
		
		
		
	}
	
	protected void doUpdate(FlightDTO fDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Flight changedFlight;
		
	
		changedFlight = FlightService.update(Flight.flightFromDTO(fDTO));
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
