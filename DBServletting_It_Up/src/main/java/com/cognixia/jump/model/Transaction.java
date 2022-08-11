package com.cognixia.jump.model;

import java.sql.Date;

public class Transaction
{
	private int trans_id;
	private Date trans_date;
	private String trans_type;
	private Double trans_amount;
	private int user_id;
	
	public Transaction(int trans_id, Date trans_date, String trans_type, Double trans_amount, int user_id) {
		super();
		this.trans_id = trans_id;
		this.trans_date = trans_date;
		this.trans_type = trans_type;
		this.trans_amount = trans_amount;
		this.user_id = user_id;
	}

	public Date getTrans_date() {
		return trans_date;
	}

	public void setTrans_date(Date trans_date) {
		this.trans_date = trans_date;
	}

	public String getTrans_type() {
		return trans_type;
	}

	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}

	public Double getTrans_amount() {
		return trans_amount;
	}

	public void setTrans_amount(Double trans_amount) {
		this.trans_amount = trans_amount;
	}
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Transaction [trans_id=" + trans_id + ", trans_date=" + trans_date + ", trans_type=" + trans_type
				+ ", trans_amount=" + trans_amount + "]";
	}
	
	
}
