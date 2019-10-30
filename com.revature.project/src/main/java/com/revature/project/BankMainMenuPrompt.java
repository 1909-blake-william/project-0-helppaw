package com.revature.project;

import java.util.Scanner;

import com.revature.bankprompts.BankPrompt;
import com.revature.bankprompts.MakeWithdrawalPrompt;
import com.revature.bankprompts.ViewAccountPrompt;
import com.revature.bankutil.BankAuthUtil;



public class BankMainMenuPrompt implements BankPrompt {

	private Scanner scan = new Scanner(System.in);
	private BankAuthUtil authUtil = BankAuthUtil.instance;
	
	
	public BankPrompt run() {
		System.out.println("Welcome " + authUtil.getCurrentCustomer() + ", please choose an option");
		System.out.println("Enter 1 to view account(s)");
		System.out.println("Enter 2 to make a withdrawal");
		System.out.println("Enter 3 to make a deposit");

		// get user input
		String selection = scan.nextLine();

		switch (selection) {
		case "1":
			return new ViewAccountPrompt();
		case "2":
			return new MakeWithdrawalPrompt();
		case "3":
			return new MakeDepositPrompt();
		default:
			System.out.println("invalid selection, try again.");
			break;
		}
		return this;
	}

}