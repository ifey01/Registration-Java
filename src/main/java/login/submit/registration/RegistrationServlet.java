package login.submit.registration;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upassword = request.getParameter("pass");
		RequestDispatcher dispatcher = null;
		Connection con = null;
		
		PreparedStatement pst = null; // Declare PreparedStatement outside the try block

		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration?useSSL=false", "root", "Daddys01");
		    pst = con.prepareStatement("insert into user(username, useremail, userpassword) values(?, ?, ?)");
		    pst.setString(1, uname);
		    pst.setString(2, uemail);
		    pst.setString(3, upassword);

		    int rowCount = pst.executeUpdate();
		    dispatcher = request.getRequestDispatcher("signup.jsp");

		    if (rowCount > 0) {
		        request.setAttribute("status", "success");
		    } else {
		        request.setAttribute("status", "failed");
		    }
		} catch (Exception e) {
		    // Handle exceptions here
		} finally {
		    try {
		        if (pst != null) {
		            pst.close();
		        }
		    } catch (SQLException e) {
		        // Handle SQLException from closing PreparedStatement
		    }

		    try {
		        if (con != null) {
		            con.close();
		        }
		    } catch (SQLException e) {
		        // Handle SQLException from closing Connection
		    }
		
		}
		
	}
	
}
		