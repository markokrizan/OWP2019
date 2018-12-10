package airline;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import airline.dal.ConnectionManager;

/**
 * URI: /airline
 */
public class App extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static boolean connected = false;
       
   
    public App() {
        super();
       
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//called even if its the welcome file uri
		
		ServletContext context = getServletContext();
		
		
		//until development done:
		
		if(ConnectionManager.open("Airline", "root", "")) {
			Boolean connected = (Boolean)context.getAttribute("connected");
			connected = true;
			response.addHeader("connected", "true");
		}else {
			response.addHeader("connected", "false");
		}
		
		
		
		
		
		
		//at the end of the dev process implement this:
		//first deplyoment - setup form db connection
		
//		if((Boolean)context.getAttribute("connected") == false) {
//			response.addHeader("connected", "false");
//		}else {
//			response.addHeader("connected", "true");
//		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dbname = request.getParameter("database");
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		
		Boolean success = false;
		
		if(dbname != null & uname != null & pass != null) {
			success = ConnectionManager.open(dbname, uname, pass);
		}
		
		if(success) {
			ServletContext context = getServletContext();
			Boolean connected = (Boolean)context.getAttribute("connected");
			connected = true;
			response.addHeader("connected", "true");
		}else {
			response.addHeader("connected", "false");
		}
		
		
		
	}
	



}
