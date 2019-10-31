package com.revature.bankmodels;

public class Transactions {
	private int transactionid;
	private int bankAccountId;
	private int userId;
	@Override
	public String toString() {
		return "Transactions [transactionid=" + transactionid + ", bankAccountId=" + bankAccountId + ", userId="
				+ userId + ", amount=" + amount + ", action=" + action + ", role=" + role + "]";
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
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (transactionid != other.transactionid)
			return false;
		if (userId != other.userId)
			return false;
		return true;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private double amount;
	private String action;
	private String role;
	
	public Transactions() {
		super();
	}

	public Transactions(int transactionId, int bankAccountId, int userId, double amount, String action, String role) {
		super();
		this.transactionid = transactionId;
		this.bankAccountId = bankAccountId;
		this.userId = userId;
		this.amount = amount;
		this.action = action;
		this.role = role;
	}
}