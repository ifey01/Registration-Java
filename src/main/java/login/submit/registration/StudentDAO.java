package login.submit.registration;

public interface StudentDAO {
	
	public int insertStudent(Student s);
	public Student getStudent(String username, String password);

}