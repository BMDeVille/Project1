package reimbursement.dao;

import java.util.List;

import reimbursement.application.Employee;
import reimbursement.application.ReimbursementTicket;

public interface ReimbursementDAO {
		//CREATE
		public int insertUser(Employee user);
		public int insertTicket(ReimbursementTicket Reimb);
		//READ
		public Employee userLogin(String userName, String passWord);
		public Employee selectUserUsername(String userName);
		public List<ReimbursementTicket> selectAllTickets();
		public List<ReimbursementTicket> selectPendingTickets();
		public List<ReimbursementTicket> selectApprovedTickets();
		public List<ReimbursementTicket> selectDeniedTickets();
		public List<Employee> selectAllUsers();
		public List<ReimbursementTicket> getUserTickets(int userID);
		//UPDATE
		public int updateUser(int userID, Employee user);
		public int approveTicket(int ticID, int adminID);
		public int denyTicket(int ticID, int adminID);
}
