package atm_main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import dto.BankDto;
import dto.CustomerDto;
import utils.DbConnection;

public class LoginBank {
	
	public BankDto checkLoginBank() {
		BankDto currentBank;
    	currentBank = new BankDto();
		
		System.out.println("-------- BANK LOGIN --------");
		
		Scanner input = new Scanner(System.in);
		String b_usernameLogin, passwordLogin;
		
		System.out.println("Please enter your username: ");
		b_usernameLogin = input.nextLine();
		System.out.println("Please enter your password: ");
		passwordLogin = input.nextLine();
		
		try {
			PreparedStatement statement = (PreparedStatement) DbConnection.getConnection().prepareStatement("SELECT b_username, password FROM bank WHERE b_username=? AND password=?");
			statement.setString(1, b_usernameLogin);
            statement.setString(2, passwordLogin);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
            	System.out.println("Login successful.");
            	currentBank.setId(resultSet.getInt("id"));
            	currentBank.setBank_name(resultSet.getString("bank_name"));
            	currentBank.setB_username(resultSet.getString("b_username"));
            	currentBank.setPassword(resultSet.getString("password"));
            	currentBank.setBalance(resultSet.getInt("balance"));
            }
            else {
            	  System.out.println("Invalid user credentials.");
              }
			
        } catch (Exception e) {
			e.printStackTrace();
		}
		return currentBank;
	}

}
