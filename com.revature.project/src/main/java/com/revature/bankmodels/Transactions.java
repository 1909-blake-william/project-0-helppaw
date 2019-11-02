package com.revature.bankmodels;

import java.sql.Timestamp;

public class Transactions {
	private int transactionid;
	private int bankAccountId;
	private int userId;
	private String action;
	private double amount;
	private Timestamp timestamp;
	
	
	
	
	public Transactions(int transactionid, int bankAccountId, int userId, String action, double amount,
			Timestamp timestamp) {
		super();
		this.transactionid = transactionid;
		this.bankAccountId = bankAccountId;
		this.userId = userId;
		this.action = action;
		this.amount = amount;
		this.timestamp = timestamp;
	}
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}
	public int getBankAccountId() {
		return bankAccountId;
	}
	public void setBankAccountId(int bankAccountId) {
		this.bankAccountId = bankAccountId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Transactions [transactionid=" + transactionid + ", bankAccountId=" + bankAccountId + ", userId="
				+ userId + ", action=" + action + ", amount=" + amount + ", timestamp=" + timestamp + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + bankAccountId;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + transactionid;
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transactions other = (Transactions) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (bankAccountId != other.bankAccountId)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (transactionid != other.transactionid)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	}
	
	



