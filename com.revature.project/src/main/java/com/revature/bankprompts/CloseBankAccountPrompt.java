package com.revature.bankprompts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.revature.bankdaos.BankAccountDao;
import com.revature.bankmodels.BankAccount;
import com.revature.bankmodels.BankUser;
import com.revature.bankutil.BankAuthUtil;
import com.revature.bankutil.BankConnectionUtility;

public class CloseBankAccountPrompt implements BankPrompt {

	private Scanner scan = new Scanner(System.in);
	private BankAccountDao bankAccountDao = BankAccountDao.currentImplementation;
	private BankAuthUtil bU = BankAuthUtil.instance;
	private BankAccount bA = BankAccount.currentImplementation;

	@Override
	public BankPrompt run() {
		BankUser user = bU.getCurrentUser();
		List<BankAccount> accounts = bankAccountDao.findAll(user.getUserId(), user.getRole());
		System.out.println("Select the bank account you wish to close by account id");
		for (int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getActiveStatus() == 1) {
			System.out.println("Enter " + i + "for: " + accounts.get(i).getBankAccountId()
					+ " " + accounts.get(i).getBankAccountTypeId() + " " + accounts.get(i).getBalance());
			}
		}
		int accountSelection = scan.nextInt();
		scan.nextLine();

		try (Connection c = BankConnectionUtility.getConnection()) {

			String sql = "UPDATE bankaccounts SET ACTIVE_STATUS = 2 WHERE BANKACCOUNT_ID = ? AND USER_ID = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, accounts.get(accountSelection).getBankAccountId());
			ps.setInt(2, user.getUserId());
			System.out.print("You just closed your account");

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Sorry, something went wrong.");
		return new AdminMainMenuPrompt();

	}
}