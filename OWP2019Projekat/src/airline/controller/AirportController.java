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
import airline.dto.AirportDTO;
import airline.dto.MessageDTO;
import airline.dto.TicketDTO;
import airline.model.Airport;
import airline.model.User;
import airline.model.User.Role;
import airline.security.AuthUtil;
import airline.security.AuthUtil.AuthStatus;
import airline.service.AirportService;

/**
 * Servlet implementation class AirportController
 */
public class AirportController extends HttpServlet {
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
		

		//----------------------
		
		//URI check:
		String uri = request.getRequestURI();
		GenericUriMeaning uriMeaning = ControllerUtil.genericChecker(uri);
		switch(uriMeaning) {
		case AIRPORT_ALL:
			doGetAll(request, response);
			break;
		case AIRPORT_ONE:
			doGetOne(ControllerUtil.airportId, request, response);
		case ERROR:
			MessageDTO message = new MessageDTO("error", "uri error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);	
			break;
		}
		
		
		

		
		
	}
	
	protected void doGetOne(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Airport airport = AirportService.getOne(id);
		if(airport != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(new AirportDTO(airport));
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(200);
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "invalid_airport_id");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(200);
			response.getWriter().write(jsonData);	
		}
	}
	
	protected void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<Airport> airports = AirportService.getAll();
		if(airports != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(AirportDTO.toDTO(airports));
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
		//Auth check -------------------
		
		
		
		
		//------------------------------
		
		//Uri check --------------------
		String uri = request.getRequestURI();
		GenericUriMeaning uriMeaning = ControllerUtil.genericChecker(uri);
		switch(uriMeaning) {
		case AIRPORT_CREATE:
			BufferedReader reader = request.getReader();
			ObjectMapper mapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mapper.setDateFormat(df);
			AirportDTO aDTO = mapper.readValue(reader, AirportDTO.class);
			
			doCreateAirport(aDTO, request, response);
			break;
		case ERROR:
			MessageDTO message = new MessageDTO("error", "uri error");
			mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);
			break;
		
		}
		
		
		//------------------------------


		
	
		
		
		
		
	}
	
	protected void doCreateAirport(AirportDTO aDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Airport insertedAirport;

		insertedAirport = AirportService.create(Airport.AirportFromDTO(aDTO));
		if(insertedAirport != null) {
			String jsonData = mapper.writeValueAsString(new AirportDTO(insertedAirport));
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
		switch(uriMeaning) {
		case AIRPORT_CREATE:
			BufferedReader reader = request.getReader();
			ObjectMapper mapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mapper.setDateFormat(df);
			AirportDTO aDTO = mapper.readValue(reader, AirportDTO.class);
			
			doUpdateAirport(aDTO, request, response);
			break;
		case ERROR:
			MessageDTO message = new MessageDTO("error", "uri error");
			mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);
			break;
		
		}
				
				
		//------------------------------
		
		
		
		
		
	
	
	
	}
	
	protected void doUpdateAirport(AirportDTO aDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Airport changedAirport;
		
		
		changedAirport = AirportService.update(Airport.AirportFromDTO(aDTO));
		if(changedAirport != null) {
			String jsonData = mapper.writeValueAsString(new AirportDTO(changedAirport));
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
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Auth check -------------------
		
		
		
		
		//------------------------------
				
		//Uri check --------------------
		String uri = request.getRequestURI();
		GenericUriMeaning uriMeaning = ControllerUtil.genericChecker(uri);
		switch(uriMeaning) {
		case AIRPORT_DELETE:			
			doDeleteAirport(ControllerUtil.airportId, request, response);
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
	
	protected void doDeleteAirport(Integer airportId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
	
		Boolean succesfullDeletion = AirportService.delete(airportId);
		if(succesfullDeletion == true) {
			MessageDTO message = new MessageDTO("success", "successfull_deletion");
			String jsonData = mapper.writeValueAsString(message);
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

	
	
	

}
