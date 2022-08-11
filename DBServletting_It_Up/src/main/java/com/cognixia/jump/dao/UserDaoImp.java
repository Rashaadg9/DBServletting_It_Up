package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Transaction;
import com.cognixia.jump.model.User;

public class UserDaoImp implements UserDao
{
	public static final Connection conn = ConnectionManager.getConnection();
	
	private static String SELECT_ALL_USERS = "select * from users";
	private static String SELECT_USER_LOGIN = "select * from users where username = ? and pass = ?";
	private static String SELECT_USER_BY_ID = "select * from users where user_id = ?";
	private static String SELECT_TRANSACTIONS_BY_ID = "select * from TRANSACTIONS where user_id = ?";
	private static String UPDATE_CASH = "update users set cash = ? where user_id = ?";
	
	@Override
	public List<User> listAllUsers()
	{
		List<User> allUsers = new ArrayList<User>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_USERS);
				ResultSet rs = pstmt.executeQuery() ) {
			
			while(rs.next())
			{	
				int id = rs.getInt("user_id");
				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("pass");
				String pin = rs.getString("pin");
				Double cash = rs.getDouble("cash");
				
				allUsers.add(new User(id, fname, lname, username, password, pin, cash));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allUsers;
	}

	@Override
	public User getUser(String username, String password)
	{
		User user = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_USER_LOGIN)) {
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			// if product found, if statement run, if not null returned as product
			if(rs.next()) {
				int id = rs.getInt("user_id");
				
				user =(new User(id, null, null, null, null, null, null));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	@Override
	public User getUserById(int id)
	{
		User user = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_USER_BY_ID )) {
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			// if product found, if statement run, if not null returned as product
			if(rs.next()) {
				int user_id = rs.getInt("user_id");
				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				String userName = rs.getString("username");
				String passWord = rs.getString("pass");
				String pin = rs.getString("pin");
				Double cash = rs.getDouble("cash");
				
				user =(new User(user_id, fname, lname, userName, passWord, pin, cash));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public List<Transaction> getUserTransactions(int id)
	{
		
		List<Transaction> allTransactions = new ArrayList<Transaction>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_TRANSACTIONS_BY_ID)) {
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			// if product found, if statement run, if not null returned as product
			if(rs.next()) {
				int trans_id = Integer.valueOf(rs.getString("trans_id"));
				Date date = Date.valueOf(rs.getString("trans_date"));
				String type = rs.getString("trans_type");
				Double amount = Double.valueOf(rs.getString("trans_amount"));
				int user_id = Integer.valueOf(rs.getString("user_id"));
				
				allTransactions.add(new Transaction(trans_id, date, type, amount, user_id));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return allTransactions;
	}

	@Override
	public Boolean addMoney(int id, Double amount)
	{
		User user = getUserById(id);
		Double newBal = 0.0;
		
		if(amount >= 1.0)
		{
			newBal = user.getCash() + amount;
			try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_CASH)) {

				pstmt.setDouble(1, newBal);
				pstmt.setInt(2, user.getUser_id());

				// at least one row updated
				if (pstmt.executeUpdate() > 0) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		return false;
		}

	@Override
	public Boolean takeMoney(int id, Double amount)
	{
		User user = getUserById(id);
		Double newBal = 0.0;
		
		if(amount >= 1.0 && amount <= user.getCash())
		{
			newBal = user.getCash() - amount;
			try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_CASH)) {

				pstmt.setDouble(1, newBal);
				pstmt.setInt(2, user.getUser_id());

				// at least one row updated
				if (pstmt.executeUpdate() > 0) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		return false;
	}
		
}

