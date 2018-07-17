package reimbursement.application;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import reimbursement.dao.ReimbursementDAOImpl;

public class ReimbursementTicket {
	private int id;
	private double amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private Blob image;
	private int requester_id;
	private int handler_id;
	private int status;
	private int type;
	final static Logger logger = Logger.getLogger(ReimbursementDAOImpl.class);
	
	public ReimbursementTicket() {
		this.id = 0;
		this.amount = 0;
		this.submitted = null;
		this.resolved = null;
		this.description = null;
		this.image = null;
		this.requester_id = 0;
		this.handler_id = 0;
		this.status = 0;
		this.type = 0;
	}
	
	public ReimbursementTicket(ResultSet rs) throws SQLException {
		this.id = rs.getInt(1);
		this.amount = rs.getDouble(2);
		this.submitted = rs.getTimestamp(3);
		this.resolved = rs.getTimestamp(4);
		this.description = rs.getString(5);
		this.image = rs.getBlob(6);
		this.requester_id = rs.getInt(7);
		this.handler_id = rs.getInt(8);
		this.status = rs.getInt(9);
		this.type = rs.getInt(10);
	}
	
	public ReimbursementTicket(int userId, HttpServletRequest request) {
		try
		{
			amount = new Double(request.getParameter("amount"));
			submitted = new Timestamp(System.currentTimeMillis());
			description = request.getParameter("description");
			requester_id = userId;
			status = 1;
			type = new Integer(request.getParameter("type"));
		}catch(NumberFormatException e) {
			logger.info("Bad Input given by user.");
			this.setValuesInit();
		}
	}
	
	public void setValuesInit() {
		this.id = 0;
		this.amount = 0;
		this.submitted = null;
		this.resolved = null;
		this.description = null;
		this.image = null;
		this.requester_id = 0;
		this.handler_id = 0;
		this.status = 0;
		this.type = 0;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}
	public Timestamp getResolved() {
		return resolved;
	}
	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public int getRequester_id() {
		return requester_id;
	}
	public void setRequester_id(int requester_id) {
		this.requester_id = requester_id;
	}
	public int getHandler_id() {
		return handler_id;
	}
	public void setHandler_id(int handler_id) {
		this.handler_id = handler_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
