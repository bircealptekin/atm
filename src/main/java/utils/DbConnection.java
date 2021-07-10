package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	private static final String user = "root";
	private static final String password = "12345";
	private static final String url = "jdbc:mysql://localhost:3306/atm";
	private static Connection connection;

	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("Driver OK.");
				connection = DriverManager.getConnection(url, user, password);
				System.out.println("Connection successful.");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

}