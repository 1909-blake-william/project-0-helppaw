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
		System.out.println("Enter 5 for Administrator login");
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
				// bankLog.info("failed to login due to credentials");
				System.out.println("Invalid Credentials");
				break;
			} else {
				return new CustomerMainMenuPrompt();
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

		case "5":
			System.out.println("Enter username:");
			String username = scan.nextLine();
			System.out.println("Enter password");
			String password = scan.nextLine();
			BankUser bU = bankAuthUtil.login(username, password);
			if (bU != null) {
				// System.out.println("Access Denied");
			}
			if ("Customer".equals(bU.getRole())) {
				System.out.println("Access Denied");
			} else {
				System.out.println("Access Granted");
				return new AdminMainMenuPrompt();
			}
		default:
			System.out.println("invalid option");
			break;
		}
		return this;
	}
//pass in entered variables to databse connection dao.
}