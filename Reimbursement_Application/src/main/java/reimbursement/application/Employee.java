package reimbursement.application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Employee {
	
	private int employeeID;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int status; 
	
	public Employee(){
		employeeID = 0;
		username = "";
		password = "";
		firstName = "";
		lastName = "";
		email = "";
		status = 0;
	}
	
	public Employee(ResultSet rs) throws SQLException {
		employeeID = rs.getInt(1);
		username = rs.getString(2);
		password = rs.getString(3);
		firstName = rs.getString(4);
		lastName = rs.getString(5);
		email = rs.getString(6);
		status = rs.getInt(7);
	}
	
	public Employee(HttpServletRequest request) {
		username = request.getParameter("username");
		password = request.getParameter("password");
		if(password.length() > 8 && username.length() > 8) {
			firstName = request.getParameter("first");
			lastName = request.getParameter("last");
			email = request.getParameter("email");
			status = new Integer(request.getParameter("status"));
		} else {
			username = "@33retry";
			password = "@33retry";
		}
	}
	
	public Employee(String username, String password, String firstName, String lastName, String email,
			int status) {
		this.employeeID = 0;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.status = status;
	}

	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}	
}
