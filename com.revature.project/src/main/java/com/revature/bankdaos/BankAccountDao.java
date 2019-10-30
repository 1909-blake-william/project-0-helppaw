package com.revature.bankdaos;

import java.util.List;

import com.revature.bankmodels.BankAccount;

public interface BankAccountDao {

	BankAccountDao currentImplementation = new BankAccountDaoSQL();
	
	int save(BankAccount bA);
	
	List<BankAccount> findAll();
	
	BankAccount findByBankAccountId(int bankAccountId);
	
	BankAccount findByUsername(String username);
	
	BankAccount findByFullname(String fullname);
	
	
}
//cascading deletes on the foreign key (string?)