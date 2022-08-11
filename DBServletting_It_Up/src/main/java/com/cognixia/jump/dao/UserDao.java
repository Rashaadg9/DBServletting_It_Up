package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Transaction;
import com.cognixia.jump.model.User;

public interface UserDao
{
	public List<User> listAllUsers();
	
	public User getUser(String username, String password);
	
	public User getUserById(int id);
	
	public List<Transaction> getUserTransactions(int id);
	
	public Boolean addMoney(int id, Double amount);
	
	public Boolean takeMoney(int id, Double amount);
}
