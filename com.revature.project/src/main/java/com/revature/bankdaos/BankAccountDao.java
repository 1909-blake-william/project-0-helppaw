package com.revature.bankdaos;

import java.util.List;

import com.revature.bankmodels.BankAccount;

public interface BankAccountDao {

	BankAccountDao currentImplementation = new BankAccountDaoSQL();
	
	int save(BankAccount bA);
	
	List<BankAccount> findAll(int userId, String role);
	
	BankAccount findByBankAccountId(int bankAccountId);
	
	BankAccount findByUsername(String username);//make it a list for multiple accounts w/ same user
	
	BankAccount findByFullname(String fullname);
	
	//withdraw (BankAccount double)
	//deposit	(BankAccount double)
	
}
//cascading deletes on the foreign key (string?)