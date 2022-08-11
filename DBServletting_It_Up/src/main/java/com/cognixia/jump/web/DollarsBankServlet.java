package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.UserDao;
import com.cognixia.jump.dao.UserDaoImp;
import com.cognixia.jump.model.Transaction;
import com.cognixia.jump.model.User;

@WebServlet("/")
public class DollarsBankServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao;
	
	public void init(ServletConfig config) throws ServletException
	{
		userDao = new UserDaoImp();
	}

	public void destroy() {
		
		try {
			ConnectionManager.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String action = request.getServletPath();
		
		switch (action) {
		case "/list": // go to product list page
			listUsers(request, response);
			break;
		case "/login": // 
			login(request, response);
			break;
		case "/home": // 
			sendHome(request, response);
			break;
		case "/transactions": // 
			listTransactions(request, response);
			break;
		case "/deposit": // 
			deposit(request, response);
			break;
		case "/toWithdrawal": //
			sendToWithdrawal(request, response);
			break;
		case "/withdrawal":
			withdrawal(request, response);
			break;
		case "/accInfo":
			accInfo(request, response);
			break;
		default:
			response.sendRedirect("/");
			break;
		}
	}
	
	private void listUsers(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<User> allUsers = userDao.listAllUsers();

		// add in data you're going to send, through the request object
		request.setAttribute("allUsers", allUsers);
		
		// redirect to jsp page and send data we just pulled
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User valid = userDao.getUser(username, password);
		
		if (valid == null) {
			HttpSession session = request.getSession();
			session.setAttribute("valid", false);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login-form.jsp");
			dispatcher.forward(request, response);
		}
		else {
			
			User valid2 = userDao.getUserById(valid.getUser_id());
			
			HttpSession session = request.getSession();
			session.setAttribute("id", valid2.getUser_id());
			response.sendRedirect("home");

		}

	}
	
	private void listTransactions(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getSession().getAttribute("id").toString());
		List<Transaction> allTransactions = userDao.getUserTransactions(id);

		// add in data you're going to send, through the request object
		request.setAttribute("allTransactions", allTransactions);
		
		// redirect to jsp page and send data we just pulled
		RequestDispatcher dispatcher = request.getRequestDispatcher("transaction.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void deposit(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getSession().getAttribute("id").toString());
		Double amount = Double.valueOf(request.getParameter("deposit"));
		userDao.addMoney(id, amount);
		
		// redirect to jsp page and send data we just pulled
		response.sendRedirect("home");
		
	}
	
	private void sendToWithdrawal(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getSession().getAttribute("id").toString());
		User user = userDao.getUserById(id);
		request.setAttribute("cash", user.getCash());
		// redirect to jsp page and send data we just pulled
		RequestDispatcher dispatcher = request.getRequestDispatcher("/withdrawal.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void withdrawal(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getSession().getAttribute("id").toString());
		Double amount = Double.valueOf(request.getParameter("withdrawal"));
		userDao.takeMoney(id, amount);
		
		// redirect to jsp page and send data we just pulled
		response.sendRedirect("home");
		
	}
	
	private void accInfo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getSession().getAttribute("id").toString());
		User user = userDao.getUserById(id);
		
		request.setAttribute("first_name", user.getFirst_name());
		request.setAttribute("last_name", user.getLast_name());
		request.setAttribute("username", user.getUsername());
		request.setAttribute("pass", user.getPass());
		
		request.setAttribute("pin", user.getPin());
		request.setAttribute("cash", user.getCash());
		
		// redirect to jsp page and send data we just pulled
		RequestDispatcher dispatcher = request.getRequestDispatcher("accInfo.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void sendHome(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getSession().getAttribute("id").toString());
		User user = userDao.getUserById(id);
		
		request.setAttribute("firstname", user.getFirst_name());
		request.setAttribute("cash", user.getCash());
		// redirect to jsp page and send data we just pulled
		RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
		dispatcher.forward(request, response);
		
	}
	

}
