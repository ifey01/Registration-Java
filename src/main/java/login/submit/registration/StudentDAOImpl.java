package login.submit.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAOImpl implements StudentDAO {
	
	static Connection con;
	static PreparedStatement ps;

	@Override
	public int insertStudent(Student s) {
		int status=0;
		try {
			con=MyConnectionProvider.getConnection();
			ps=con.prepareStatement("insert into registration values(?,?)");
		    ps.setString(1,s.getUsername());
		    ps.setString(2,s.getPassword());
		    ps.setString(3,s.getFullName());
		    
		    status=ps.executeUpdate();
		    con.close();
		    
		}catch(Exception e) {
				System.out.println(e);
			}
		
		return status;
	}

	@Override
	public Student getStudent(String username, String password) {
		Student s=new Student();
		try {
			con=MyConnectionProvider.getConnection();
			ps=con.prepareStatement("select * from registration where username=? and password=?");
			ps.setString(1,  username);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				s.setFullName(rs.getString(1));
				s.setPassword(rs.getString(2));
				
			}
		}catch(Exception e) {
				System.out.println(e);
			}

		return null;
	}
	
	
}