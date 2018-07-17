package reimbursement.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import reimbursement.controller.RequestHelper;
import reimbursement.dao.ReimbursementDAOImpl;

public class ReimbursementServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ReimbursementServlet.class);
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("URI = " + req.getRequestURI());
		resp.setContentType("application/json");
		resp.getWriter().write(new ObjectMapper().writeValueAsString(RequestHelper.process(req)));
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("URI = " + req.getRequestURI());
		req.getRequestDispatcher((String)RequestHelper.process(req)).forward(req, resp);
	}
}
