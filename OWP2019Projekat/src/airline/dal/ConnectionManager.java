package airline.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	
	private static Connection connection;
	private static Boolean connectionDone;
	
	public static boolean open(String database, String userName, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database + "?useSSL=false&useUnicode=true&characterEncoding=utf8", userName, password);
			return true;
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			return false;
		}
	}

	public static Connection getConnection() {
		return connection;
	}
	
	public static void connectionDone() {
		connectionDone = true;
	}

	public static void close() {
		try {
			connection.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	
	

}
