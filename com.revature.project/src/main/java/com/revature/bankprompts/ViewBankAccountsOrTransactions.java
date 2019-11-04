package com.revature.bankprompts;

import java.util.Scanner;

public class ViewBankAccountsOrTransactions implements BankPrompt {

	private Scanner scan = new Scanner(System.in);
	
	@Override
	public BankPrompt run() {
		System.out.println("Enter 1 to view bank account(s)");
		System.out.println("Enter 2 to view transactions");
		System.out.println("Enter 3 to go back");
		System.out.println("Enter 4 to logout");
		String selection = scan.nextLine();
		
		switch (selection) {
		case "1":
			
			return new ViewBankAccountPrompt();
		case "2":
			
			return new ViewTransactionsPrompt();
			
		case "3":
			
			return new CustomerMainMenuPrompt();
			
		case "4":
			
			
			System.out.println("You have successfully logged out. Have a nice day!!");
			return new BankLoginPrompt();
		default:
			System.out.println("invalid selection, try again.");
				
		}
		
		return null;
	}

}
