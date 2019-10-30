package com.revature.bankdaos;

import java.util.List;

import com.revature.bankmodels.BankUser;
import com.revature.bankdaos.BankUserDaoSQL;
		

public interface BankUserDao {
	
	BankUserDao currentImplementation = new BankUserDaoSQL();
	
	int save(BankUser c);
	
	List<BankUser> findAll();
	
	BankUser findById();
	
	BankUser findByUsernameAndPassword(String username, String password);
	
	BankUser findByUsername(String username);
	
	BankUser findByFullname(String fullname);

}
