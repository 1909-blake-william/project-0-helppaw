package com.revature.bankprompts;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bankdaos.BankAccountDao;
import com.revature.bankdaos.BankUserDao;
import com.revature.bankutil.BankAuthUtil;

public class CreateOrCloseBankAccountPrompt implements BankPrompt {

	private Scanner scan = new Scanner(System.in);
	private BankAuthUtil bankAuthUtil = BankAuthUtil.instance;

	Logger bankLog = Logger.getRootLogger();

	@Override
	public BankPrompt run() {
		System.out.println("Enter 1 to create a bank account");
		System.out.println("Enter 2 to close a bank account");
		System.out.println("Enter 3 to go back");
		System.out.println("Enter 4 to logout");
		String choice = scan.nextLine();
		switch (choice) {
		case "1":

			return new CreateBankAccountPrompt();
		case "2":
			return new CloseBankAccountPrompt();

		case "3":
			if (bankAuthUtil.getCurrentUser().getRole().contentEquals("Customer")) {
				return new CustomerMainMenuPrompt();
			} else {

				return new AdminMainMenuPrompt();

			}

		case "4":
			System.out.println("You have successfully logged out. Have a nice day!!");
			bankLog.debug("successfully logged out.");
			return new BankLoginPrompt();
		default:
			System.out.println("invalid selection, try again.");
			break;

		}
		return this;

	}
}