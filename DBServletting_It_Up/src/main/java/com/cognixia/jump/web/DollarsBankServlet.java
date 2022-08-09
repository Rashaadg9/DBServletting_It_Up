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

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.UserDao;
import com.cognixia.jump.dao.UserDaoImp;
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

}
