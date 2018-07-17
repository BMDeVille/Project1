package reimbursement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import reimbursement.application.Employee;
import reimbursement.dao.ReimbursementDAOImpl;
import reimbursement.service.ReimbursementService;
import reimbursement.service.ReimbursementServiceImpl;

public class UserController {
	static ReimbursementService reimb = new ReimbursementServiceImpl();
	final static Logger logger = Logger.getLogger(ReimbursementDAOImpl.class);
	
	public static Employee getUserProfile(HttpServletRequest request) {
		if(!request.getMethod().equals("GET")) {
			return null;
		}
		
		String username = (String)request.getSession().getAttribute("loggedusername");
		
		return reimb.getUserByUsername(username);
	}
	
	public static List<Employee> getAllUsers(HttpServletRequest request) {
		if(!request.getMethod().equals("GET")) {
			return null;
		}
		String username = (String)request.getSession().getAttribute("loggedusername");
		Employee admin = reimb.getUserByUsername(username);
		if(admin.getStatus() != 2) {
			logger.info("User without admin permissions accessed user view method");
			return null;
		}
		return reimb.getAllUsers();
	}
	
	public static String addUser(HttpServletRequest request) {
		if(!request.getMethod().equals("POST")) {
			return "/index.html";
		}
		String username = (String)request.getSession().getAttribute("loggedusername");
		Employee admin = reimb.getUserByUsername(username);
		if(admin.getStatus() != 2) {
			logger.info("User without admin permissions accessed employee creation page");
			return "/index.html";
		}
		Employee newUser = new Employee(request);
		reimb.insertUser(newUser);
		logger.info("Admin: " + username + " has added new User: " + newUser.getUsername());
		
		return "/resources/html/admin-home.html";
	}
	
	public static String updateUserInfo(HttpServletRequest request) {
		if(!request.getMethod().equals("POST")) {
			return "/index.html";
		}
		String username = (String)request.getSession().getAttribute("loggedusername");
		Employee user = reimb.getUserByUsername(username);
		int id = new Integer(request.getParameter("userID"));
		if(user.getStatus() != 2 || user.getEmployeeID() != id) {
			logger.info("Non-related user without admin permissions attempted to update a user's information.");
			return "/index.html";
		}
		Employee newUser = new Employee(request);
		reimb.updateUser(id, newUser);
		
		logger.info("Admin or User: " + username + " has updated user info of user with id #" + id);
		
		return "/resources/html/admin-home.html";
	}
}
