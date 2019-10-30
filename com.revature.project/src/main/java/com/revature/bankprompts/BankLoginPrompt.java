package com.revature.bankprompts;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bankdaos.BankUserDao;
import com.revature.bankmodels.BankUser;
import com.revature.bankutil.BankAuthUtil;

public class BankLoginPrompt implements BankPrompt {
	private Logger bankLog = Logger.getRootLogger();
	private Scanner scan = new Scanner(System.in);
	private BankUserDao bankUserDao = BankUserDao.currentImplementation;
	private BankAuthUtil bankAuthUtil = BankAuthUtil.instance;

	@Override
	public BankPrompt run() {
		System.out.println("Enter 1 to login");
		System.out.println("Enter 2 to register");
		String choice = scan.nextLine();
		switch (choice) {
		case "1": {
			// log.debug(attempting to login");}
			System.out.println("Enter username:");
			String username = scan.nextLine();
			System.out.println("Enter password");
			String password = scan.nextLine();

			BankUser bU = bankAuthUtil.login(username, password);
			if (bU == null) {
				bankLog.info("failed to login due to credentials");
				System.out.println("Invalid Credentials");
				break;
			} else {
				System.out.println("You have successfully logged in!");
				 bankLog.info("you successfully logged in");
				if ("Admin".equals(bankAuthUtil.getRole())) {
					return new AdminMainMenuPrompt();
				} else {
					return new CustomerMainMenuPrompt();
				}
			}
		}
		case "2": {
			System.out.println("Enter new username:");
			String username = scan.nextLine();
			BankUser bU = bankUserDao.findByUsername(username);
			if (bU != null) {
				System.out.println("invalid username");
				break;
			}
			System.out.println("Enter new Password:");
			String password = scan.nextLine();
			System.out.println("Enter you Full Name (First name and last name in one line)");
			String fullname = scan.nextLine();
			BankUser newBankUser = new BankUser(1, username, password, "customer", fullname);

			bankUserDao.save(newBankUser);
			System.out.println("You have successfully registered!!");
			 bankLog.info("You have successfully registered!");
			break;
		}
		default:
			System.out.println("invalid option");
			break;
		}
		return this;
	}
//pass in entered variables to databse connection dao.
}