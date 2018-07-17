package reimbursement.controller;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {
	public static Object process(HttpServletRequest request) {
		switch(request.getRequestURI()) {
			case "/Reimbursement_Application/login.do":
				return LoginController.login(request);
			case "/Reimbursement_Application/sign-out.do":
				return LoginController.logout(request);
			case "/Reimbursement_Application/redirect-home.do":
				return NavigationController.sendHome(request);
			case "/Reimbursement_Application/create-ticket.do":
				return NavigationController.ticketCreation(request);
			case "/Reimbursement_Application/create-employee.do":
				return NavigationController.employeeCreation(request);
			case "/Reimbursement_Application/get-user.do":
				return UserController.getUserProfile(request);
			case "/Reimbursement_Application/get-users.do":
				return UserController.getAllUsers(request);
			case "/Reimbursement_Application/add-user.do":
				return UserController.addUser(request);
			case "/Reimbursement_Application/get-tickets.do":
				return ReimbursementController.getAllTickets(request);
			case "/Reimbursement_Application/get-pending-tickets.do":
				return ReimbursementController.getPendingTickets(request);
			case "/Reimbursement_Application/get-user-tickets.do":
				return ReimbursementController.getUserTickets(request);
			case "/Reimbursement_Application/get-approved-tickets.do":
				return ReimbursementController.getApprovedTickets(request);
			case "/Reimbursement_Application/get-denied-tickets.do":
				return ReimbursementController.getDeniedTickets(request);
			case "/Reimbursement_Application/add-ticket.do":
				return ReimbursementController.requestTicket(request);
			case "/Reimbursement_Application/audit-ticket.do":
				return ReimbursementController.auditTicket(request);
			default:
				return "";
		}
	}
}
