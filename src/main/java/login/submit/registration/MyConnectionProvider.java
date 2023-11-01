package login.submit.registration;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnectionProvider implements MyProvider {
	
	static Connection con;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.driver");
		
			
		        con=DriverManager.getConnection(connectionUrl,Username,password);
		        System.out.println("Printing connection object"+con);
		        
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return con;
	}
}