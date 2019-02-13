package airline.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import airline.dto.MessageDTO;
import airline.model.User;
import airline.security.AuthUtil;


public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		if(username == null || password == null) {
			MessageDTO message = new MessageDTO("error", "You didn't send your credentials!");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(message);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(401);
			response.getWriter().write(jsonData);
		}else {
			User user = AuthUtil.checkCredentials(username, password);
			if(user !=null) {
				//return token
				String token = AuthUtil.generateToken(user);
				
//				response.setStatus(200);
//				System.out.println(token);
//				response.setContentType("charset=UTF-8");
//				response.setHeader("Pragma", token);
				
				MessageDTO message = new MessageDTO("success", token);
				ObjectMapper mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(message);
				response.setContentType("application/json;charset=UTF-8");
				response.setStatus(200);
				response.getWriter().write(jsonData);
				
			}else {
				MessageDTO message = new MessageDTO("error", "Wrong credentials!");
				ObjectMapper mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(message);
				response.setContentType("application/json;charset=UTF-8");
				response.setStatus(403);
				response.getWriter().write(jsonData);
				
			}
		}
		
		
		
	}

}
