package reimbursement.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import reimbursement.application.Employee;
import reimbursement.dao.ReimbursementDAOImpl;
import reimbursement.service.ReimbursementService;
import reimbursement.service.ReimbursementServiceImpl;

public class LoginController {
	
	private static ReimbursementService reimb = new ReimbursementServiceImpl();
	final static Logger logger = Logger.getLogger(ReimbursementDAOImpl.class);
	
	public static String login(HttpServletRequest request) {
		if(!request.getMethod().equals("POST")) {
			return "/index.html";
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee emp = reimb.userLogin(username, password);
		
		if(emp.getStatus() == 1) {
			request.getSession().setAttribute("loggedusername", username);
			request.getSession().setAttribute("loggedpassword", password);
			logger.info("User: "+username+" has logged in!");
			return "/resources/html/employee-home.html";
		}else if(emp.getStatus() == 2){
			request.getSession().setAttribute("loggedusername", username);
			request.getSession().setAttribute("loggedpassword", password);
			logger.info("User: "+username+" has logged in!");
			return "/resources/html/admin-home.html";
		}else {
			logger.info("Unsuccessful login attempt made.");
			return "/resources/html/login-failed.html";
		}
	}
	
	public static String logout(HttpServletRequest request) {
		if(!request.getMethod().equals("POST")) {
			return "/index.html";
		}
		String username = (String)request.getSession().getAttribute("loggedusername");
		logger.info("User: "+username+" has logged out!");
		request.getSession().invalidate();
		return "/index.html";
	}

}
