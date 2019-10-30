package com.revature.bankutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankConnectionUtility {

	public static Connection getConnection() throws SQLException {
		String url = System.getenv("POKEMON_DB_URL");
		String username = System.getenv("BANKAPP_DB_USERNAME");
		String password = System.getenv("BANKAPP_DB_PASSWORD");
		return DriverManager.getConnection(url, username, password);
	}
}


