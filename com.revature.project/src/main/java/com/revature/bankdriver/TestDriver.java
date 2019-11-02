package com.revature.bankdriver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bankdaos.BankUserDao;
import com.revature.bankprompts.DepositPrompt;
import com.revature.bankutil.BankConnectionUtility;

public class TestDriver {

	public static void main(String[] args) {

		DepositPrompt dp = new DepositPrompt();
	
		dp.run();
		
		//try (Connection c = BankConnectionUtility.getConnection()) {

			//PreparedStatement ps = c.prepareStatement("SELECT * FROM bankapp");
			//ResultSet rs = ps.executeQuery();

		//} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

//}
