package com.revature.bankprompts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

public class CreateBankAccountPrompt implements BankPrompt {
	private BankAccountDao bankAccountDao = BankAccountDao.currentImplementation;
	private BankUserDao bankUserDao = BankUserDao.currentImplementation;
	private BankAuthUtil bankAuthUtil = BankAuthUtil.instance;
	private Scanner scan = new Scanner(System.in);
	// private BankAccount bA = BankAccount.currentImplementation;
	//Logger bankLog = Logger.getRootLogger();
	private TransactionsDao transactions = TransactionsDao.currentImplementation;

	@Override
	public BankPrompt run() {
		boolean keepGoing = true;
		int accountTypeId = 0;
		while (keepGoing) {
			System.out.println("Enter 1 to create a Checking Account");
			System.out.println("Enter 2 to create a Savings Account");
			System.out.println("Enter 3 to go back");
			System.out.println("Enter 4 to logout");
			String choice = scan.nextLine();
			switch (choice) {
			case "1":
				accountTypeId = 1;
				keepGoing = false;
				break;
			case "2":
				accountTypeId = 2;
				keepGoing = false;
				break;
			case "3":
				return new CreateOrCloseBankAccountPrompt();
			case "4":
				System.out.println("You have successfully logged out. Have a nice day!!");
				//bankLog.debug("successfully logged out.");
				return new BankLoginPrompt();

			default:
				System.out.println("invalid selection, try again.");
				break;
			}
		}

		try (Connection c = BankConnectionUtility.getConnection()) {

			String sql = "INSERT INTO bankaccounts (bankaccount_id, balance, bankaccount_type_id, user_id, ACTIVE_STATUS)"
					+ " VALUES (BANKACCOUNT_ID_SEQ.nextval, ?,?,?, 1)";

			BankUser user = bankAuthUtil.getCurrentUser();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setDouble(1, 0);
			ps.setInt(2, accountTypeId);
			ps.setInt(3, user.getUserId());
			System.out.print("You just created a ");
			if(accountTypeId == 1) {
				System.out.println("Checking Account!!");
			} else if (accountTypeId == 2) {
				System.out.println("Savings Account!!");
			}
			ps.executeUpdate();
			return this;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Sorry, something went wrong.");
		return this;

	}
}