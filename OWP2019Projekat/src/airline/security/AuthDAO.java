package airline.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.Statement;

import airline.dal.ConnectionManager;
import airline.dal.GenericDAO.Table;
import airline.model.Airport;
import airline.model.Flight;
import airline.model.Ticket;
import airline.model.User;
import airline.model.User.Role;

public class AuthDAO {
	
	public static User getOne(String username){
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			System.out.println(conn);
			
			
			try {
				String query = "SELECT * FROM user WHERE user_name LIKE ? AND deleted = 0";
	
				pstmt = conn.prepareStatement(query);
				
				
				pstmt.setString(1, username);
				System.out.println(pstmt);
	
				rset = pstmt.executeQuery();
				
				if (rset.next()) {
					Integer userId = rset.getInt("user_id");
					String userName = rset.getString("user_name");
					String password = rset.getString("password");
					String firstName = rset.getString("first_name");
					String lastName = rset.getString("last_name");
					Date registrationDate = rset.getDate("registration_date");
					Role role = Role.valueOf(rset.getString("role"));
					Boolean blocked = rset.getBoolean("blocked");
					Boolean deleted = rset.getBoolean("deleted");
					
				
					
					return new User(userId, userName, password, firstName, lastName, registrationDate, role, blocked, deleted);
				}else {
					return null;
				}
				
		
				
				
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}
	
			return null;
			
		}
	
	

}
