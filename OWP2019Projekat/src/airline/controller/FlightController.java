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
import airline.controller.validate.FlightValidator;
import airline.dto.FlightDTO;
import airline.dto.MessageDTO;
import airline.dto.SearchFlightDTO;
import airline.dto.TicketDTO;
import airline.model.Flight;
import airline.model.Ticket;
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
		case FLIGHT_TICKETS:
			doGetTickets(ControllerUtil.flightId, request, response);
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
	
	protected void doGetTickets(Integer flightId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Ticket> tickets = FlightService.getTickets(flightId);
		if(tickets != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(TicketDTO.toDTO(tickets));
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
	
	protected void doGetOccupiedSeats(Integer flightId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
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
				mapper.setDateFormat(df);
				SearchFlightDTO sfDTO = mapper.readValue(reader, SearchFlightDTO.class);
				doSearch(sfDTO, request, response);
				return;
			case FLIGHT_CREATE:
				reader = request.getReader();
				mapper = new ObjectMapper();
				mapper.setDateFormat(df);
				FlightDTO flightDTO = mapper.readValue(reader, FlightDTO.class);
				doCreateFlight(flightDTO, request, response);
				return;
		}
		
	
		

		
	}

	public void doCreateFlight(FlightDTO fDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		
		//authorization:
		String token = request.getHeader("Authorization");
		//for example only admin can use this method
		AuthStatus status = AuthUtil.authorizeToken(token, Role.ADMIN);
		if(status != AuthStatus.AUTHORIZED) {
			MessageDTO message = new MessageDTO("error", "unauthrorized");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(403);
			response.getWriter().write(jsonData);	
			return;
		}
		
		//=======================
		//validation:
		String validationMessage = FlightValidator.validateCreate(fDTO);
		if(validationMessage != "") {
			System.out.println(validationMessage);
			MessageDTO message = new MessageDTO("error", validationMessage);
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(400);
			response.getWriter().write(jsonData);
			return;
		}
		
		//=======================
		
		
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
		
		//Authorize:
		String token = request.getHeader("Authorization");
		//for example only admin can use this method
		AuthStatus status = AuthUtil.authorizeToken(token, Role.ADMIN);
		if(status != AuthStatus.AUTHORIZED) {
			MessageDTO message = new MessageDTO("error", "unauthrorized");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(403);
			response.getWriter().write(jsonData);	
			return;
		}
		
		//Validate:
		String validationMessage = FlightValidator.validateUpdate(fDTO);
		if(validationMessage != "") {
			System.out.println(validationMessage);
			MessageDTO message = new MessageDTO("error", validationMessage);
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(400);
			response.getWriter().write(jsonData);
			return;
		}
		
		
	
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
		String uri = request.getRequestURI();
		GenericUriMeaning uriMeaning = ControllerUtil.genericChecker(uri);
		switch(uriMeaning) {
		case FLIGHT_DELETE:
			doDeleteFlight(ControllerUtil.flightId, request, response);
			break;
		case ERROR:
			MessageDTO message = new MessageDTO("error", "uri error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);
			break;
		
		}		
		//------------------------------
		
		
		
	}
	
	protected void doDeleteFlight(Integer flightId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		//Authorize:
		String token = request.getHeader("Authorization");
		//for example only admin can use this method
		AuthStatus status = AuthUtil.authorizeToken(token, Role.ADMIN);
		if(status != AuthStatus.AUTHORIZED) {
			MessageDTO message = new MessageDTO("error", "unauthrorized");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(403);
			response.getWriter().write(jsonData);	
			return;
		}

		
		Boolean successfullDeletion = FlightService.delete(flightId);
		if(successfullDeletion == true) {
			MessageDTO message = new MessageDTO("success", "succesfull_deletion");
			String jsonData = mapper.writeValueAsString(message);
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
