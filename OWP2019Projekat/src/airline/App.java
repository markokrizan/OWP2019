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
		//maybe redirect to front app uri
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		
		
	}
	



}
