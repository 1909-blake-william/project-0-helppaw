package com.revature.bankdaos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.bankmodels.BankAccount;
import com.revature.bankutil.BankAuthUtil;
import com.revature.bankutil.BankConnectionUtility;

import org.apache.log4j.Logger;

public class BankAccountDaoSQL implements BankAccountDao {
	private BankAuthUtil bankAuthUtil = BankAuthUtil.instance;

	private Logger bankLog = Logger.getRootLogger();

	BankAccount extractBankAccount(ResultSet rs) throws SQLException {
		int rsBankAccountId = rs.getInt("bankaccount_id");
		double rsBalance = rs.getDouble("balance");
		int rsBankAccountTypeId = rs.getInt("bankaccount_type_id");
		int rsUserId = rs.getInt("user_id");
		int rsActiveStatus = rs.getInt("active_status");
		return new BankAccount(rsBankAccountId, rsBalance, rsBankAccountTypeId, rsUserId, rsActiveStatus);
	}

	@Override
	public int save(BankAccount bA) {
		// bankLog.debug("attempting to find accounts from DB");
		try (Connection c = BankConnectionUtility.getConnection()) {

			String sql = "INSERT INTO bankaccounts (bankaccount_id, balance, bankaccount_type_id, user_id) "
					+ " VALUES (bankaccounts_id_seq.nextval , ?,?,?)";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, bA.getBankAccountId());
			ps.setDouble(2, bA.getBalance());
			ps.setInt(3, bA.getBankAccountTypeId());
			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public List<BankAccount> findAll(int userId, String role) {
		if ("Admin".equals(role)) {
			bankLog.debug("attempting to find all bank accounts from DB");
			try (Connection c = BankConnectionUtility.getConnection()) {

				String sql = "SELECT * FROM bankaccounts";

				PreparedStatement ps = c.prepareStatement(sql);

				ResultSet rs = ps.executeQuery();
				List<BankAccount> bA = new ArrayList<BankAccount>();
				while (rs.next()) {
					bA.add(extractBankAccount(rs));
				}
				return bA;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else { //make a selection
			bankLog.debug("attempting to find all of your bank accounts");
			try (Connection c = BankConnectionUtility.getConnection()) {

				String sql = "SELECT * FROM bankaccounts WHERE user_id = '?'";

				PreparedStatement ps = c.prepareStatement(sql);
				ps.setInt(userId, userId);

				ResultSet rs = ps.executeQuery();
				
				List<BankAccount> bA = new ArrayList<BankAccount>();
				while (rs.next()) {
					bA.add(extractBankAccount(rs));
				}
				return bA;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return null;
	}
		
	@Override
	public BankAccount findByBankAccountId(int bankAccountId) {
		bankLog.debug("attempting to find bank account by credentials from DB");
		try (Connection c = BankConnectionUtility.getConnection()) {

			String sql = "SELECT * FROM bankaccounts " + "WHERE bankaccount_id = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, bankAccountId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return extractBankAccount(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount findByFullname(String fullname) {
		// TODO Auto-generated method stub
		return null;
	}

}
