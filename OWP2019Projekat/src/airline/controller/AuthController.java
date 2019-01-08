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
			response.setContentType("application/json");
			response.setStatus(401);
			response.getWriter().write(jsonData);
		}else {
			User user = AuthUtil.checkCredentials(username, password);
			if(user !=null) {
				//return token
				String token = AuthUtil.generateToken(user);
				response.setStatus(200);
				//There is a restriction to access response headers when you are using Fetch API over CORS. Due to this restriction, you can access only following standard headers:
				//Cache-Control
				//Content-Language
				//Content-Type
				//Expires
				//Last-Modified
				//Pragma
				System.out.println(token);
				response.setHeader("Pragma", token);
				
			}else {
				MessageDTO message = new MessageDTO("error", "Wront credentials!");
				ObjectMapper mapper = new ObjectMapper();
				String jsonData = mapper.writeValueAsString(message);
				response.setContentType("application/json");
				response.setStatus(403);
				response.getWriter().write(jsonData);
				
			}
		}
		
		
		
	}

}
