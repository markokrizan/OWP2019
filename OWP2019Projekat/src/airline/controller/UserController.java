package airline.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import airline.controller.ControllerUtil.GenericUriMeaning;
import airline.dto.MessageDTO;
import airline.dto.TicketDTO;
import airline.dto.UserDTO;
import airline.model.Ticket;
import airline.model.User;
import airline.service.UserService;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//URI check:
		String uri = request.getRequestURI();
		GenericUriMeaning uriMeaning = ControllerUtil.genericChecker(uri);
		
		switch(uriMeaning) {
			case USER_ALL:
				doGetAll(request, response);
				break;
			case USER_ONE:
				doGetOne(ControllerUtil.userId, request, response);
				break;
			case ERROR:
				MessageDTO message = new MessageDTO("error", "uri error");
				ObjectMapper mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(message);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write(jsonData);	
				break;
			case USER_TICKETS:
				doGetTickets(ControllerUtil.userId, request, response);
				break;	
			case USER_SEARCH:
				doFindOne(ControllerUtil.userSearchQuery, request, response);
				break;
			}
		
		
		
		//------------------------------------------------
		
	}
	
	protected void doGetOne(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = UserService.getOne(id);
		System.out.println(user);
		if(user != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(new UserDTO(user));
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(200);
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "invalid_user_id");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(400);
			response.getWriter().write(jsonData);	
		}
		
	}
	
	protected void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> users = UserService.getAll();
		if(users != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(UserDTO.toDTO(users));
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(500);
			response.getWriter().write(jsonData);	
		}
		
	}
	
	protected void doFindOne(String query, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<User> users = UserService.findOne(query);
		if(users != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(UserDTO.toDTO(users));
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(500);
			response.getWriter().write(jsonData);	
		}
	}
	
	protected void doGetTickets(Integer userId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<Ticket> tickets = UserService.getUserTickets(userId);
		if(tickets != null) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(TicketDTO.toDTO(tickets));
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jsonData);	
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(500);
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
		UserDTO userDTO = mapper.readValue(reader, UserDTO.class);
		User insertedUser;

		insertedUser = UserService.create(User.userFromDTO(userDTO));
		if(insertedUser != null) {
			String jsonData = mapper.writeValueAsString(new UserDTO(insertedUser));
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(201);
			response.getWriter().write(jsonData);
		}else {
			MessageDTO message = new MessageDTO("error", "processing_error");
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json");
			response.setStatus(500);
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
		UserDTO userDTO = mapper.readValue(reader, UserDTO.class);
		
		
		User changedUser;
		
	
		changedUser = UserService.update(User.userFromDTO(userDTO));
		if(changedUser != null) {
			String jsonData = mapper.writeValueAsString(new UserDTO(changedUser));
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
		
		
		
		//------------------------------
		
		BufferedReader reader = request.getReader();
		ObjectMapper mapper = new ObjectMapper();
		UserDTO userDTO = mapper.readValue(reader, UserDTO.class);
		
		User userForDeletion;
		
		
		userForDeletion = UserService.delete(User.userFromDTO(userDTO));
		if(userForDeletion != null) {
			String jsonData = mapper.writeValueAsString(new UserDTO(userForDeletion));
			response.setContentType("application/json");
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
