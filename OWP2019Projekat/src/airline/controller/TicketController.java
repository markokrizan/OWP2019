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
import airline.controller.validate.TicketValidator;
import airline.dto.MessageDTO;
import airline.dto.TicketDTO;
import airline.model.Ticket;
import airline.model.User.Role;
import airline.security.AuthUtil;
import airline.security.AuthUtil.AuthStatus;
import airline.service.TicketService;

/**
 * Servlet implementation class TicketService
 */
public class TicketController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Auth check -------------------
		
	
		
		
		//------------------------------
		
		//Uri check --------------------
		
		String uri = request.getRequestURI();
		GenericUriMeaning uriMeaning = ControllerUtil.genericChecker(uri);
		switch(uriMeaning) {
		case TICKET_ALL:
			doGetAll(request, response);
			break;
		case TICKET_ONE:
			doGetOne(ControllerUtil.ticketId, request, response);
			break;
		case ERROR:
			MessageDTO message = new MessageDTO("error", "uri error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);
			break;
		
		}
		
		
	
	}
	
	
	protected void doGetOne(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String token = request.getHeader("Authorization");
		if(token == null || token.equals("null")) {
			ObjectMapper mapper = new ObjectMapper();
			MessageDTO message = new MessageDTO("error", "unauthrorized");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(403);
			response.getWriter().write(jsonData);	
			return;
		}
		
		Ticket ticket = TicketService.getOne(id);
		
		if(ticket == null) {
			MessageDTO message = new MessageDTO("error", "invalid_ticket_id");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(400);
			response.getWriter().write(jsonData);	
			return;
		}
		
		
		//admin and the token user can request:
		AuthStatus adminStatus = AuthUtil.authorizeToken(token, Role.ADMIN);
		Boolean himself = AuthUtil.getUserFromToken(token).getId() == ticket.getUser().getId();
		if(adminStatus != AuthStatus.AUTHORIZED) {
			if(!himself) {
				ObjectMapper mapper = new ObjectMapper();
				MessageDTO message = new MessageDTO("error", "unauthrorized");
				String jsonData = mapper.writeValueAsString(message);
				response.setContentType("application/json");
				response.setStatus(403);
				response.getWriter().write(jsonData);	
				return;
			}
		}
		
		
		if(ticket != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(new TicketDTO(ticket));
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(200);
			response.getWriter().write(jsonData);	
		}
		
	}
	
	protected void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		AuthStatus adminStatus = AuthUtil.authorizeToken(token, Role.ADMIN);
		if(adminStatus != AuthStatus.AUTHORIZED) {
			ObjectMapper mapper = new ObjectMapper();
			MessageDTO message = new MessageDTO("error", "unauthrorized");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(403);
			response.getWriter().write(jsonData);	
			return;
		}
		
		ArrayList<Ticket> tickets = TicketService.getAll();
		System.out.println(tickets);
		if(tickets != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(TicketDTO.toDTO(tickets));
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(200);
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(500);
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
		switch(uriMeaning) {
		case TICKET_CREATE:
			reader = request.getReader();
			mapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mapper.setDateFormat(df);
			TicketDTO ticketDTO = mapper.readValue(reader, TicketDTO.class);
			
			doCreate(ticketDTO, request, response);
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
	
	protected void doCreate(TicketDTO tDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		//no token at all
		String token = request.getHeader("Authorization");
		if(token == null || token.equals("null")) {
			mapper = new ObjectMapper();
			MessageDTO message = new MessageDTO("error", "unauthrorized");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(403);
			response.getWriter().write(jsonData);	
			return;
		}
		
		
		
		//blocked
		Boolean blocked = AuthUtil.getUserFromToken(token).getBlocked();
		if(blocked) {
			mapper = new ObjectMapper();
			MessageDTO message = new MessageDTO("error", "unauthrorized");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(403);
			response.getWriter().write(jsonData);	
			return;
			
		}
		
		
		//validate
		String validationMessage = TicketValidator.validateCreate(tDTO);
		if(validationMessage != "") {
			System.out.println(validationMessage);
			MessageDTO message = new MessageDTO("error", validationMessage);
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(400);
			response.getWriter().write(jsonData);
			return;
		}
		
		
		Ticket insertedTicket;

		insertedTicket = TicketService.create(Ticket.ticketFromDTO(tDTO));
		if(insertedTicket != null) {
			String jsonData = mapper.writeValueAsString(new TicketDTO(insertedTicket));
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
		BufferedReader reader = null;
		ObjectMapper mapper = null;
		//Auth check -------------------
		
		
		
		
		//------------------------------
				
		//Uri check --------------------
		String uri = request.getRequestURI();
		GenericUriMeaning uriMeaning = ControllerUtil.genericChecker(uri);
		switch(uriMeaning) {
		case TICKET_UPDATE:
			reader = request.getReader();
			mapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mapper.setDateFormat(df);
			TicketDTO ticketDTO = mapper.readValue(reader, TicketDTO.class);
			doUpdate(ticketDTO, request, response);
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
	
	protected void doUpdate(TicketDTO tDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		String token = request.getHeader("Authorization");
		if(token == null || token.equals("null")) {
			mapper = new ObjectMapper();
			MessageDTO message = new MessageDTO("error", "unauthrorized");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(403);
			response.getWriter().write(jsonData);	
			return;
		}
		
		
		//admin and user that created the reserveation:
		AuthStatus adminStatus = AuthUtil.authorizeToken(token, Role.ADMIN);
		Boolean himself = AuthUtil.getUserFromToken(token).getId() == tDTO.getUser().getId();
		if(adminStatus != AuthStatus.AUTHORIZED) {
			if(!himself) {
				mapper = new ObjectMapper();
				MessageDTO message = new MessageDTO("error", "unauthrorized");
				String jsonData = mapper.writeValueAsString(message);
				response.setContentType("application/json");
				response.setStatus(403);
				response.getWriter().write(jsonData);	
				return;
			}
		}
		
		
		
		//validate:
		String validationMessage = TicketValidator.validateUpdate(tDTO);
		if(validationMessage != "") {
			System.out.println(validationMessage);
			MessageDTO message = new MessageDTO("error", validationMessage);
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(400);
			response.getWriter().write(jsonData);
			return;
		}
		
		
		
		Ticket changedTicket;
		
		
		changedTicket = TicketService.update(Ticket.ticketFromDTO(tDTO));
		if(changedTicket != null) {
			String jsonData = mapper.writeValueAsString(new TicketDTO(changedTicket));
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
		case TICKET_DELETE:
			doDeleteTicket(ControllerUtil.ticketId, request, response);
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
	
	protected void doDeleteTicket(Integer ticketId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
	
		Boolean successfullDeletion = TicketService.delete(ticketId);
		if(successfullDeletion == true) {
			MessageDTO message = new MessageDTO("success", "succesfull_deletion");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(200);
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
