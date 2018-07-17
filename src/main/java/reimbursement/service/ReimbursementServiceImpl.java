package reimbursement.service;

import java.util.List;

import reimbursement.application.Employee;
import reimbursement.application.ReimbursementTicket;
import reimbursement.dao.ReimbursementDAO;
import reimbursement.dao.ReimbursementDAOImpl;

public class ReimbursementServiceImpl implements ReimbursementService{
	ReimbursementDAO r = new ReimbursementDAOImpl();
	
	@Override
	public Employee userLogin(String username, String password) {
		return r.userLogin(username, password);
	}

	@Override
	public Employee getUserByUsername(String username) {
		return r.selectUserUsername(username);
	}

	@Override
	public List<Employee> getAllUsers() {
		return r.selectAllUsers();
	}

	@Override
	public List<ReimbursementTicket> getAllRequests() {
		return r.selectAllTickets();
	}
	
	@Override
	public List<ReimbursementTicket> getPendingRequests() {
		return r.selectPendingTickets();
	}

	@Override
	public int insertUser(Employee user) {
		return r.insertUser(user);
	}

	@Override
	public int insertRequest(ReimbursementTicket Reimb) {
		return r.insertTicket(Reimb);
	}

	@Override
	public List<ReimbursementTicket> getUserTickets(int userID) {
		return r.getUserTickets(userID);
	}

	@Override
	public int updateUser(int userID, Employee user) {
		return r.updateUser(userID, user);
	}

	@Override
	public int approveTicket(int ticID, int adminID) {
		return r.approveTicket(ticID, adminID);
	}

	@Override
	public int denyTicket(int ticID, int adminID) {
		return r.denyTicket(ticID, adminID);
	}

	@Override
	public List<ReimbursementTicket> getApprovedRequests() {
		return r.selectApprovedTickets();
	}

	@Override
	public List<ReimbursementTicket> getDeniedRequests() {
		return r.selectDeniedTickets();
	}

}
