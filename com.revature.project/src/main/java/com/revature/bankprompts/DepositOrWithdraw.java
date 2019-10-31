package com.revature.bankprompts;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class DepositOrWithdraw implements BankPrompt {

private Scanner scan = new Scanner(System.in);
	
	Logger bankLog = Logger.getRootLogger();

	@Override
	public BankPrompt run() {
		System.out.println("Enter 1 to withdraw from a bank account");
		System.out.println("Enter 2 to deposit to a bank account");
		System.out.println("Enter 3 to go back");
		System.out.println("Enter 4 to logout");
		String choice = scan.nextLine();
		switch (choice) {
		case "1": 
			
			return new WithdrawPrompt();
		case "2":
			return new DepositPrompt();
			
		case "3":
			return new AdminMainMenuPrompt();
				
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