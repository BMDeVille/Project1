package reimbursement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import reimbursement.application.Employee;
import reimbursement.application.ReimbursementTicket;
import reimbursement.dao.ReimbursementDAOImpl;
import reimbursement.service.ReimbursementService;
import reimbursement.service.ReimbursementServiceImpl;

public class ReimbursementController {
	static ReimbursementService reimb = new ReimbursementServiceImpl();
	final static Logger logger = Logger.getLogger(ReimbursementController.class);
	
	public static List<ReimbursementTicket> getAllTickets(HttpServletRequest request){
		if(!request.getMethod().equals("GET")) {
			return null;
		}
		String username = (String)request.getSession().getAttribute("loggedusername");
		Employee admin = reimb.getUserByUsername(username);
		if(admin.getStatus() != 2) {
			logger.info("User without administrator permissions accessed getAllTickets method.");
			return null;
		}
		return reimb.getAllRequests();
	}
	
	public static List<ReimbursementTicket> getPendingTickets(HttpServletRequest request){
		if(!request.getMethod().equals("GET")) {
			return null;
		}
		String username = (String)request.getSession().getAttribute("loggedusername");
		Employee admin = reimb.getUserByUsername(username);
		if(admin.getStatus() != 2) {
			logger.info("User without administrator permissions accessed admin page.");
			return null;
		}
		return reimb.getPendingRequests();
	}
	
	public static List<ReimbursementTicket> getApprovedTickets(HttpServletRequest request){
		if(!request.getMethod().equals("GET")) {
			return null;
		}
		String username = (String)request.getSession().getAttribute("loggedusername");
		Employee admin = reimb.getUserByUsername(username);
		if(admin.getStatus() != 2) {
			logger.info("User without administrator permissions accessed getApprovedTickets method.");
			return null;
		}
		return reimb.getApprovedRequests();
	}
	
	public static List<ReimbursementTicket> getDeniedTickets(HttpServletRequest request){
		if(!request.getMethod().equals("GET")) {
			return null;
		}
		String username = (String)request.getSession().getAttribute("loggedusername");
		Employee admin = reimb.getUserByUsername(username);
		if(admin.getStatus() != 2) {
			logger.info("User without administrator permissions accessed getDeniedTickets method.");
			return null;
		}
		return reimb.getDeniedRequests();
	}
	
	
	public static String requestTicket(HttpServletRequest request) {
		if(!request.getMethod().equals("POST")) {
			return "/index.html";
		}
		String username = (String)request.getSession().getAttribute("loggedusername");
		Employee user = reimb.getUserByUsername(username);
		
		ReimbursementTicket ticket = new ReimbursementTicket(user.getEmployeeID(), request);
		if(ticket != new ReimbursementTicket()) {
			reimb.insertRequest(ticket);
			logger.info("User: " + username + " has inserted ticket #" + ticket.getId());
		}
		return "/resources/html/employee-home.html";
	}
	
	public static List<ReimbursementTicket> getUserTickets(HttpServletRequest request){
		if(!request.getMethod().equals("GET")) {
			return null;
		}
		String username = (String)request.getSession().getAttribute("loggedusername");
		int id = reimb.getUserByUsername(username).getEmployeeID();
		
		return reimb.getUserTickets(id);
	}
	
	public static String auditTicket(HttpServletRequest request) {
		if(!request.getMethod().equals("POST")) {
			return "/resources/html/error.html";
		}
		String username = (String)request.getSession().getAttribute("loggedusername");
		Employee admin = reimb.getUserByUsername(username);
		if(admin.getStatus() != 2) {
			logger.info("User without administrator permissions accessed auditTicket method.");
			return "/index.html";
		}
		String auditChoice = request.getParameter("choice");
		String ids[] = request.getParameterValues("ticket");
		for(String id : ids) {
			if(auditChoice.equals("Approve")) {
				reimb.approveTicket(new Integer(id), admin.getEmployeeID());
				logger.info("Admin: " + username + " has approved ticket #" + id);
			} else {
				reimb.denyTicket(new Integer(id), admin.getEmployeeID());
				logger.info("Admin: " + username + " has denied ticket #" + id);
			}
		}
		
		return "/resources/html/admin-home.html";
	}
}
