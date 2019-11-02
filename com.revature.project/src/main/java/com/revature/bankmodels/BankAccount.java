package com.revature.bankmodels;

public class BankAccount {
	public static BankAccount currentImplementation;

	BankUser bU = new BankUser();
	
	private int bankAccountId;
	private double balance;
	private int bankAccountTypeId;
	private int userId;
	private int activeStatus;
	//should I call for BankUser.getUserId()?
	

	public BankAccount() {
		super();
		
	}

	public BankAccount(int bankAccountId, double balance, int bankAccountTypeId, int userId, int activeStatus) {
		super();
		this.bankAccountId = bankAccountId;
		this.balance = balance;
		this.bankAccountTypeId = bankAccountTypeId;
	//	this.userId = bU.getUserId();
		this.userId = userId;
		this.activeStatus = activeStatus;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public int getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(int bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getBankAccountTypeId() {
		return bankAccountTypeId;
	}

	public void setBankAccountTypeId(int bankAccountTypeId) {
		this.bankAccountTypeId = bankAccountTypeId;
	}

	@Override
	public String toString() {
		return "BankAccount [bU=" + bU + ", bankAccountId=" + bankAccountId + ", balance=" + balance
				+ ", bankAccountTypeId=" + bankAccountTypeId + ", userId=" + userId + ", activeStatus=" + activeStatus
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + activeStatus;
		result = prime * result + ((bU == null) ? 0 : bU.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + bankAccountId;
		result = prime * result + bankAccountTypeId;
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
		BankAccount other = (BankAccount) obj;
		if (activeStatus != other.activeStatus)
			return false;
		if (bU == null) {
			if (other.bU != null)
				return false;
		} else if (!bU.equals(other.bU))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (bankAccountId != other.bankAccountId)
			return false;
		if (bankAccountTypeId != other.bankAccountTypeId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
