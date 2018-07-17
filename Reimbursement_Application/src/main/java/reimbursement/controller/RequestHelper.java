package reimbursement.controller;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {
	public static Object process(HttpServletRequest request) {
		switch(request.getRequestURI()) {
			case "./login.do":
				return LoginController.login(request);
			case "./sign-out.do":
				return LoginController.logout(request);
			case "./redirect-home.do":
				return NavigationController.sendHome(request);
			case "./create-ticket.do":
				return NavigationController.ticketCreation(request);
			case "./create-employee.do":
				return NavigationController.employeeCreation(request);
			case "./get-user.do":
				return UserController.getUserProfile(request);
			case "./get-users.do":
				return UserController.getAllUsers(request);
			case "./add-user.do":
				return UserController.addUser(request);
			case "./get-tickets.do":
				return ReimbursementController.getAllTickets(request);
			case "./get-pending-tickets.do":
				return ReimbursementController.getPendingTickets(request);
			case "./get-user-tickets.do":
				return ReimbursementController.getUserTickets(request);
			case "./get-approved-tickets.do":
				return ReimbursementController.getApprovedTickets(request);
			case "./get-denied-tickets.do":
				return ReimbursementController.getDeniedTickets(request);
			case "./add-ticket.do":
				return ReimbursementController.requestTicket(request);
			case "./audit-ticket.do":
				return ReimbursementController.auditTicket(request);
			default:
				return "";
		}
	}
}
