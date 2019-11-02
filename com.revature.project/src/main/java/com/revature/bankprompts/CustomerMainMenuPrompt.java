package com.revature.bankprompts;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bankutil.BankAuthUtil;

public class CustomerMainMenuPrompt implements BankPrompt {
	private Scanner scan = new Scanner(System.in);
	private BankAuthUtil bU = BankAuthUtil.instance;
	Logger bankLog = Logger.getRootLogger();

	@Override
	public BankPrompt run() {
		System.out.println("Welcome " + bU.getCurrentUser() + ", please choose an option");
		System.out.println("Enter 1 to view bank account(s) and transactions");
		System.out.println("Enter 2 to create/remove bank account(s)");
		System.out.println("Enter 3 to deposit/withdraw to/from bank account(s)");
		System.out.println("Enter 4 to logout");
		// get user input
		String selection = scan.nextLine();

		switch (selection) {
		case "1":
			
			return new ViewBankAccountPrompt();
		case "2":
			
			return new CreateOrCloseBankAccountPrompt();
		case "3":
			
			return new DepositOrWithdraw();
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
