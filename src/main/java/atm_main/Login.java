package atm_main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import utils.DbConnection;

public class Login {
	
	public void checkLogin() {
		System.out.println("-------- LOGIN --------");
	
		Scanner input = new Scanner(System.in);
		String usernameLogin, passwordLogin;
		
		System.out.println("Please enter your username: ");
		usernameLogin = input.nextLine();
		System.out.println("Please enter your password: ");
		passwordLogin = input.nextLine();
		
		try (Connection connection = dbConnection()) {
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement("SELECT username, password FROM customers WHERE username=? AND password=?");
			statement.setString(1, usernameLogin);
            statement.setString(2, passwordLogin);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) { // Until next row is present otherwise it return false
            	System.out.println("Login successful.");
            }
            else {
            	  System.out.println("Invalid user credentials");
              }
			
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
}
