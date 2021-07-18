package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import dao.CustomerDao;
import dto.CustomerDto;
import utils.DbConnection;

public class LoginCustomer {
	
	public CustomerDto checkLoginCustomer() {
		CustomerDto currentCustomer;
    	currentCustomer = new CustomerDto();
		System.out.println("-------- CUSTOMER LOGIN --------");
	
		Scanner input = new Scanner(System.in);
		String usernameLogin, passwordLogin;
		
		System.out.println("Please enter your username: ");
		usernameLogin = input.nextLine();
		System.out.println("Please enter your password: ");
		passwordLogin = input.nextLine();
		
		try {
			PreparedStatement statement = (PreparedStatement) DbConnection.getConnection().prepareStatement("SELECT * FROM customers WHERE username=? AND password=?");
			statement.setString(1, usernameLogin);
            statement.setString(2, passwordLogin);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
            	System.out.println("Login successful.");
            	currentCustomer.setId(resultSet.getInt("id"));
            	currentCustomer.setBank_id(resultSet.getInt("bank_id"));
            	currentCustomer.setName(resultSet.getString("name"));
            	currentCustomer.setSurname(resultSet.getString("surname"));
            	currentCustomer.setUsername(resultSet.getString("username"));
            	currentCustomer.setEmail(resultSet.getString("email"));
            	currentCustomer.setPassword(resultSet.getString("password"));
            	currentCustomer.setBalance(resultSet.getInt("balance"));
            }
            else {
            	currentCustomer = null;
              }
			
        } catch (Exception e) {
			e.printStackTrace();
		}
		return currentCustomer;
	}
	
}
