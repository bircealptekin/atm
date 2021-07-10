package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	private String user = "root";
	private String password = "12345";
	private String url = "jdbc:mysql://localhost:3306/atm";
	private Connection connection;

	public Connection getConnection() {
		try {
			if (this.connection == null || connection.isClosed()) {
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