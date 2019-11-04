
package com.revature.bankprompts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bankdaos.BankAccountDao;
import com.revature.bankdaos.BankUserDao;
import com.revature.bankdaos.TransactionsDao;
import com.revature.bankmodels.BankAccount;
import com.revature.bankmodels.BankUser;
import com.revature.bankmodels.Transactions;
import com.revature.bankutil.BankAuthUtil;
import com.revature.bankutil.BankConnectionUtility;

public class ViewTransactionsPrompt implements BankPrompt {

	private Logger log = Logger.getRootLogger();
	private BankAccountDao bankAccountDao = BankAccountDao.currentImplementation;
	private BankAuthUtil bankAuthUtil = BankAuthUtil.instance;
	private BankUserDao bankUserDao = BankUserDao.currentImplementation;
	private TransactionsDao transactionsDao = TransactionsDao.currentImplementation;

	private Scanner scan = new Scanner(System.in);

	@Override
	public BankPrompt run() {
		// log.debug("attempting to view all of your transactions");
		BankUser user = bankAuthUtil.getCurrentUser();
		// if(bankAuthUtil.getCurrentUser().getRole().contentEquals("Customer")) {
		List<Transactions> transactions = transactionsDao.findAll(user.getUserId(), user.getRole());

		for (int i = 0; i < transactions.size(); i++) {
			Transactions thisTransaction = transactions.get(i);
			int userId = thisTransaction.getUserId();
			BankUser thisUser = bankUserDao.findById(userId);
			String fullname = thisUser.getFullname();

			// bankUserDao.findById(accounts.get(i).getUserId()).getFullname();

			// if (accounts.get(i).getActiveStatus() == 1) {
			System.out.println("Enter " + i + "for: " + transactions.get(i).getUserId() + " " + fullname
					+ transactions.get(i).getBankAccountId() + " " + transactions.get(i).getAction() + " "
					+ transactions.get(i).getAmount());
		}
		// }

		// System.out.println("Sorry, something went wrong.");

		if (bankAuthUtil.getCurrentUser().getRole().contentEquals("Customer")) {
			return new CustomerMainMenuPrompt();
		} else {

			return new AdminMainMenuPrompt();

		}

	}
}