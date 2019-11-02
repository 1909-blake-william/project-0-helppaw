	package com.revature.bankprompts;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.List;
	import java.util.Scanner;

	import org.apache.log4j.Logger;

	import com.revature.bankdaos.BankAccountDao;
import com.revature.bankdaos.BankUserDao;
import com.revature.bankmodels.BankAccount;
	import com.revature.bankmodels.BankUser;
	import com.revature.bankutil.BankAuthUtil;
	import com.revature.bankutil.BankConnectionUtility;
	
public class ViewBankAccountPrompt implements BankPrompt {

		private Logger log = Logger.getRootLogger();
		private BankAccountDao bankAccountDao = BankAccountDao.currentImplementation;
		private BankAuthUtil bankAuthUtil = BankAuthUtil.instance;
		private BankUserDao bankUserDao = BankUserDao.currentImplementation;
		
		private Scanner scan = new Scanner(System.in);

		@Override
		public BankPrompt run() {
			log.debug("attempting to view all of your accounts");
			BankUser user = bankAuthUtil.getCurrentUser();
			// if(bankAuthUtil.getCurrentUser().getRole().contentEquals("Customer")) {
			List<BankAccount> accounts = bankAccountDao.findAll(user.getUserId(), user.getRole());
			
			for (int i = 0; i < accounts.size(); i++) {
				BankAccount thisAccount = accounts.get(i);
				int userId = thisAccount.getUserId();
				BankUser thisUser = bankUserDao.findById(userId);
				String fullname = thisUser.getFullname();
				
			//	bankUserDao.findById(accounts.get(i).getUserId()).getFullname();
				
				//if (accounts.get(i).getActiveStatus() == 1) {
					System.out.println("Enter " + i + "for: " + fullname + accounts.get(i).getBankAccountId() + " "
							+ accounts.get(i).getBankAccountTypeId() + " " + accounts.get(i).getBalance() + " " + accounts.get(i).getUserId());
				}
			//}
			

		
			

			// System.out.println("Sorry, something went wrong.");
			return new AdminMainMenuPrompt();

		}

	}
