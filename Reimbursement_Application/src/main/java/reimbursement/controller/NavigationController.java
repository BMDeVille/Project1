package reimbursement.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import reimbursement.dao.ReimbursementDAOImpl;
import reimbursement.service.ReimbursementService;
import reimbursement.service.ReimbursementServiceImpl;

public class NavigationController {
	private static ReimbursementService reimb = new ReimbursementServiceImpl();
	final static Logger logger = Logger.getLogger(ReimbursementDAOImpl.class);
	
	public static String sendHome(HttpServletRequest request) {
		if(!request.getMethod().equals("POST")) {
			return "/index.html";
		}
		String username = (String) request.getSession().getAttribute("loggedusername");
		int status = reimb.getUserByUsername(username).getStatus();
		if(status == 1) {
			logger.info("User: " + username + " redirected to employee home.");
			return "/resources/html/employee-home.html";
		} else if(status == 2){
			logger.info("User: " + username + " redirected to admin home.");
			return "/resources/html/admin-home.html";
		}else {
			logger.info("User attempted to access site without logging in.");
			return "/index.html";
		}
	}
	
	public static String ticketCreation(HttpServletRequest request) {
		if(!request.getMethod().equals("POST")) {
			return "/index.html";
		}
		String username = (String) request.getSession().getAttribute("loggedusername");
		int status = reimb.getUserByUsername(username).getStatus();
		if(status == 1) {
			logger.info("User: " + username + " has navigated to ticket request page.");
			return "/resources/html/request-ticket.html";
		} else if(status == 2){
			logger.info("Admin: " + username + " attempted to access the ticket request page without permission.");
			return "/resources/html/admin-home.html";
		}else {
			logger.info("User attempted to access site without logging in.");
			return "/index.html";
		}
	}
	
	public static String employeeCreation(HttpServletRequest request) {
		if(!request.getMethod().equals("POST")) {
			return "/index.html";
		}
		String username = (String) request.getSession().getAttribute("loggedusername");
		int status = reimb.getUserByUsername(username).getStatus();
		if(status == 2) {
			logger.info("Admin: " + username + " has navigated to employee creation page.");
			return "/resources/html/create-employee.html";
		} else if(status == 1){
			logger.info("User: " + username + " has attempte to access the ticket request page without permission.");
			return "/resources/html/employee-home.html";
		}else {
			logger.info("User attempted to access site without logging in.");
			return "/index.html";
		}
	}

}
