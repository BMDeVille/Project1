package reimbursement.service;

import java.util.List;

import reimbursement.application.Employee;
import reimbursement.application.ReimbursementTicket;

public interface ReimbursementService {
	public Employee userLogin(String username, String password);
	public Employee getUserByUsername(String username);
	public List<Employee> getAllUsers();
	public List<ReimbursementTicket> getAllRequests();
	public List<ReimbursementTicket> getPendingRequests();
	public List<ReimbursementTicket> getApprovedRequests();
	public List<ReimbursementTicket> getDeniedRequests();
	public int insertUser(Employee user);
	public int insertRequest(ReimbursementTicket Reimb);
	public List<ReimbursementTicket> getUserTickets(int userID);
	public int updateUser(int userID, Employee user);
	public int approveTicket(int ticID, int adminID);
	public int denyTicket(int ticID, int adminID);
}
