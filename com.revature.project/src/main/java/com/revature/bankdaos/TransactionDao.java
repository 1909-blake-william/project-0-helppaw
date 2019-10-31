package com.revature.bankdaos;

import java.util.List;

import com.revature.bankmodels.Transactions;

public interface TransactionDao {

	TransactionDao currentImplementation = new TransactionDaoSQL();
	
	int save (Transactions transaction);
	List<Transactions> findAll(int transactionId, String action);
	Transactions findById(int transactionId);
	List<Transactions> findByAccountId(int accountId);
	List<Transactions> findByUserId(int userId);
}
