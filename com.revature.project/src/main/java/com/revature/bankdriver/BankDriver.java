package com.revature.bankdriver;

import com.revature.bankprompts.BankPrompt;
import com.revature.bankprompts.BankLoginPrompt;

import org.apache.log4j.Logger;

public class BankDriver {
	//private static Logger log = Logger.getRootLogger();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		BankPrompt p = new BankLoginPrompt();
		while (true) { 
			//log.debug("starting prompt of type: " + p.getClass());
			p = p.run();
			//log.debug("next prompt is of type: " + p.getClass());
		}

	}

}
