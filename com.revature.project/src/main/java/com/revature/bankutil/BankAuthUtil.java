package com.revature.bankutil;

import com.revature.bankdaos.BankAccountDao;
import com.revature.bankdaos.BankUserDao;
import com.revature.bankmodels.BankUser;


public class BankAuthUtil {

	public static final BankAuthUtil instance = new BankAuthUtil();
	
	private BankUserDao bankUserDao = BankUserDao.currentImplementation;
	private BankUser currentBankUser = null;
	private BankAccountDao bankAccountDao = BankAccountDao.currentImplementation;
	
	private BankAuthUtil() {
		super();
	}

	public BankUser login(String username, String password) {
		BankUser bU = bankUserDao.findByUsernameAndPassword(username, password);
		currentBankUser = bU;
		return bU;
	}

	public BankUser getCurrentUser() {
		return currentBankUser;
	}
	
	public String getRole() {
		return currentBankUser.getRole();
	}

	public BankAccountDao getBankAccountDao() {
		return bankAccountDao;
	}

	public void setBankAccountDao(BankAccountDao bankAccountDao) {
		this.bankAccountDao = bankAccountDao;
	}
}
