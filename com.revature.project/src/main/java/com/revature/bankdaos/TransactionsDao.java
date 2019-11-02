package com.revature.bankdaos;

import java.util.List;
import com.revature.bankdaos.TransactionsDaoSQL;

import com.revature.bankmodels.Transactions;

public interface TransactionsDao {

	TransactionsDao currentImplementation = new TransactionsDaoSQL();
	
	int save (Transactions transaction);
	
	List<Transactions> findAll(int transactionId, String action);
	
	Transactions findById(int transactionId);
	
	List<Transactions> findByAccountId(int accountId);
	
	List<Transactions> findByUserId(int userId);
	
}
