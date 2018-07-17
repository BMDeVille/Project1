package reimbursement.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import reimbursement.application.Employee;
import reimbursement.application.ReimbursementTicket;

public class ReimbursementDAOImpl implements ReimbursementDAO{
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private static String url = "jdbc:oracle:thin:@wvudatabase.czciwzthx2it.us-east-2.rds.amazonaws.com:1521:orcl"; 
	private static String username = "p1_admin";
	private static String password = "p4ssw0rd";
	
	
	@Override
	public Employee userLogin(String userName, String passWord) {
		Employee user = new Employee();
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			String sql2 = "{? = call check_password(?, ?)";
			CallableStatement cs = conn.prepareCall(sql2);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, userName);
			cs.setString(3, passWord);
			cs.execute();
			int output = cs.getInt(1);
			while(rs.next()) {
				if(output == 0) {
					user = new Employee(rs);
				}
			};
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


	@Override
	public Employee selectUserUsername(String userName) {
		Employee user = new Employee();
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new Employee(rs);
			};
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


	@Override
	public List<ReimbursementTicket> selectAllTickets() {
		List<ReimbursementTicket> reimb = new ArrayList<ReimbursementTicket>();
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "SELECT * FROM ERS_REIMBURSEMENT";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				reimb.add(new ReimbursementTicket(rs));
			};
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return reimb;
	}
	
	@Override
	public List<ReimbursementTicket> selectPendingTickets() {
		List<ReimbursementTicket> reimb = new ArrayList<ReimbursementTicket>();
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_STATUS_ID = 1";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				reimb.add(new ReimbursementTicket(rs));
			};
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return reimb;
	}
	
	@Override
	public List<ReimbursementTicket> selectApprovedTickets() {
		List<ReimbursementTicket> reimb = new ArrayList<ReimbursementTicket>();
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_STATUS_ID = 2";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				reimb.add(new ReimbursementTicket(rs));
			};
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return reimb;
	}
	
	@Override
	public List<ReimbursementTicket> selectDeniedTickets() {
		List<ReimbursementTicket> reimb = new ArrayList<ReimbursementTicket>();
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_STATUS_ID = 3";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				reimb.add(new ReimbursementTicket(rs));
			};
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return reimb;
	}

	@Override
	public List<Employee> selectAllUsers() {
		List<Employee> users = new ArrayList<Employee>();
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "SELECT * FROM ERS_USERS";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				users.add(new Employee(rs));
			};
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
	}


	@Override
	public int insertUser(Employee user) {
		int rows = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "call insert_user(?, ?, ?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, user.getUsername());
			cs.setString(2, user.getPassword());
			cs.setString(3, user.getFirstName());
			cs.setString(4, user.getLastName());
			cs.setString(5, user.getEmail());
			cs.setInt(6, user.getStatus());
			rows = cs.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}


	@Override
	public int insertTicket(ReimbursementTicket Reimb) {
		int rows = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "call insert_tickets(?, ?, ?, ?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setDouble(1, Reimb.getAmount());
			cs.setTimestamp(2, Reimb.getSubmitted());
			cs.setString(3, Reimb.getDescription());
			cs.setBlob(4, Reimb.getImage());
			cs.setInt(5, Reimb.getRequester_id());
			cs.setInt(6, Reimb.getStatus());
			cs.setInt(7, Reimb.getType());
			rows = cs.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}


	@Override
	public List<ReimbursementTicket> getUserTickets(int userID) {
		List<ReimbursementTicket> reimb = new ArrayList<ReimbursementTicket>();
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				reimb.add(new ReimbursementTicket(rs));
			};
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return reimb;
	}


	@Override
	public int updateUser(int userID, Employee user) {
		int rows = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "{ call update_user(?, ?, ?, ?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, userID);
			cs.setString(2, user.getUsername());
			cs.setString(3, user.getPassword());
			cs.setString(4, user.getFirstName());
			cs.setString(5, user.getLastName());
			cs.setString(6, user.getEmail());
			cs.setInt(7, user.getStatus());
			rows = cs.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}


	@Override
	public int approveTicket(int ticID, int adminID) {
		int rows = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "{ call approve_ticket(?, ?, ?)";
			Timestamp resolved = new Timestamp(System.currentTimeMillis());
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, ticID);
			cs.setTimestamp(2, resolved);
			cs.setInt(3, adminID);
			rows = cs.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	@Override
	public int denyTicket(int ticID, int adminID) {
		int rows = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "{ call deny_ticket(?, ?, ?)";
			Timestamp resolved = new Timestamp(System.currentTimeMillis());
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, ticID);
			cs.setTimestamp(2, resolved);
			cs.setInt(3, adminID);
			rows = cs.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

}
