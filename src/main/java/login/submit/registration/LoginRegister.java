package login.submit.registration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/loginRegister")
public class LoginRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginRegister() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDAO sd=new StudentDAOImpl();
		String userName=request.getParameter("username");
		String password=request.getParameter("password1");
		String submitType=request.getParameter("submit");
		Student s=sd.getStudent(userName, password);
		//System.out.println(s.getFullName()+s.getPassword()+s.getUsername());
		if(submitType.equals("login") && s!=null && s.getFullName() !=null){
			request.setAttribute("message", "introduce youself, hobbies");
			request.getRequestDispatcher("introdution.jsp").forward(request, response);
		
		}else if(submitType.equals("register")) {
			s.setFullName(request.getParameter("fullName"));
			s.setPassword(password);
			s.setUsername(userName);
			sd.insertStudent(s);
			request.setAttribute("successmessage", "Registration Done, login to continue!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}else {
			request.setAttribute("message", "Data Not Found, Click On Register!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

}
