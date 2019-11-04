package com.revature.bankprompts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bankdaos.BankAccountDao;
import com.revature.bankdaos.TransactionsDao;
import com.revature.bankmodels.BankAccount;
import com.revature.bankmodels.BankUser;
import com.revature.bankmodels.Transactions;
import com.revature.bankutil.BankAuthUtil;
import com.revature.bankutil.BankConnectionUtility;

public class DepositPrompt implements BankPrompt {

	private Logger log = Logger.getRootLogger();
	private BankAccountDao bankAccountDao = BankAccountDao.currentImplementation;
	private BankAuthUtil bankAuthUtil = BankAuthUtil.instance;
	private Scanner scan = new Scanner(System.in);
	private TransactionsDao transactionDao = TransactionsDao.currentImplementation;

	@Override
	public BankPrompt run() {
		log.debug("attempting to deposit into your account");
		BankUser user = bankAuthUtil.getCurrentUser();
		// if(bankAuthUtil.getCurrentUser().getRole().contentEquals("Customer")) {
		List<BankAccount> accounts = bankAccountDao.findCurrentUser(user.getUserId());
		System.out.println("Select the bank account you wish to deposit into. ");
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getActiveStatus() == 1) {
				System.out.println("Enter " + i + "for: " + accounts.get(i).getBankAccountId() + " "
						+ accounts.get(i).getBankAccountTypeId() + " " + accounts.get(i).getBalance());
			}
		}
		int accountSelection = scan.nextInt();
		scan.nextLine();

		System.out.println("How much do you want to deposit? (Enter in format: 00.00");

		double depositAmount = scan.nextDouble();

		try (Connection c = BankConnectionUtility.getConnection()) {

			String sql = "SELECT balance FROM bankaccounts WHERE BANKACCOUNT_ID = ? AND USER_ID = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, accounts.get(accountSelection).getBankAccountId());
			ps.setInt(2, user.getUserId());
			System.out.println(depositAmount);
			System.out.println(accounts.get(accountSelection).getBankAccountId());
			System.out.println(user.getUserId());
			ResultSet rs = ps.executeQuery();
			double balance = 0;
			if (rs.next()) {
				balance = rs.getDouble("balance");

			}
			balance += depositAmount;
			System.out.println(balance);
			if (depositAmount <= 0) {
				System.out.println("You cannot deposit a negative value. Good day sir/ma'am.");
				return new AdminMainMenuPrompt();
			}

			String sql2 = "UPDATE bankaccounts SET balance = ? WHERE BANKACCOUNT_ID = ? AND USER_ID = ?";

			PreparedStatement ps2 = c.prepareStatement(sql2);
			ps2.setDouble(1, balance);
			ps2.setInt(2, accounts.get(accountSelection).getBankAccountId());
			ps2.setInt(3, user.getUserId());
			Timestamp ts = new Timestamp(System.currentTimeMillis());

			Transactions trans = new Transactions(0, accounts.get(accountSelection).getBankAccountId(),
					user.getUserId(), "Deposited", depositAmount, ts);
			transactionDao.save(trans);

			System.out.print("You have sucessfully made a deposit of " + depositAmount + "! " + "Your new balance is "
					+ balance + "! ");

			ps2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println("Sorry, something went wrong.");
		return new AdminMainMenuPrompt();

	}

}
