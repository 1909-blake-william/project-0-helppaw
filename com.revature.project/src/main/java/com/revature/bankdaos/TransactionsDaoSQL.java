package com.revature.bankdaos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.revature.bankmodels.BankUser;
import com.revature.bankmodels.Transactions;
import com.revature.bankmodels.BankAccount;
import com.revature.bankutil.BankAuthUtil;
import com.revature.bankutil.BankConnectionUtility;

public class TransactionsDaoSQL implements TransactionsDao {
	private BankAuthUtil bankAuthUtil = BankAuthUtil.instance;
	private BankUserDaoSQL bSQL = (BankUserDaoSQL) BankUserDaoSQL.currentImplementation;

	//private Logger bankLog = Logger.getRootLogger();

	private Transactions extractTransactions(ResultSet rs) throws SQLException {
		int rsTransactionId = rs.getInt("transactions_id");
		int rsBankAccountId = rs.getInt("bank_account_id");
		int rsUserId = rs.getInt("user_id");
		double rsAmount = rs.getDouble("amount");
		String rsAction = rs.getString("action");
		Timestamp rsTimestamp = rs.getTimestamp("timestamp");
		return new Transactions(rsTransactionId, rsBankAccountId, rsUserId, rsAction, rsAmount, rsTimestamp);

	}

	@Override
	public int save(Transactions transaction) {
		// bankLog.debug("attempting to find accounts from DB");
		try (Connection c = BankConnectionUtility.getConnection()) {

			String sql = "INSERT INTO transactions (transactions_id, bank_account_id, user_id, amount, action, timestamp) "
					+ " VALUES (transactions_id_seq.nextval , ?,?,?,?,CURRENT_TIMESTAMP)";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, transaction.getBankAccountId());
			ps.setInt(2, transaction.getUserId());
			ps.setDouble(3, transaction.getAmount());
			ps.setString(4, transaction.getAction());

			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public List<Transactions> findAll(int userId, String role) {
		if ("Admin".equals(role)) {
			//bankLog.debug("attempting to find all transactions from DB");
			try (Connection c = BankConnectionUtility.getConnection()) {

				String sql = "SELECT * FROM transactions";

				PreparedStatement ps = c.prepareStatement(sql);

				ResultSet rs = ps.executeQuery();
				List<Transactions> transaction = new ArrayList<Transactions>();
				while (rs.next()) {
					transaction.add(extractTransactions(rs));
				}
				return transaction;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else { // make a selection
			//bankLog.debug("attempting to find all transaction from your bank accounts");
			try (Connection c = BankConnectionUtility.getConnection()) {

				String sql = "SELECT * FROM transactions WHERE user_id = ?";

				PreparedStatement ps = c.prepareStatement(sql);
				ps.setInt(1, userId); //might break the code

				ResultSet rs = ps.executeQuery();

				List<Transactions> transactions = new ArrayList<Transactions>();
				while (rs.next()) {
					transactions.add(extractTransactions(rs));
				}
				return transactions;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Transactions findById(int transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transactions> findByAccountId(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transactions> findByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
