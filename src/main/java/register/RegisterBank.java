package register;

import java.sql.PreparedStatement;
import java.util.Scanner;

import dto.BankDto;
import utils.DbConnection;

public class RegisterBank {
	
	public void bankRegister() {
		BankDto registerBank;
		registerBank = new BankDto();
		String bank_name, b_username, email, password;
		int balance = 0, isBankRegistered = 0;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter bank name: ");
		bank_name = input.nextLine();
		System.out.println("Enter bank username: ");
		b_username = input.nextLine();
		System.out.println("Enter email: ");
		email = input.nextLine();
		System.out.println("Enter password: ");
		password = input.nextLine();
		
		String sql = "INSERT INTO bank(bank_name, b_username, email, password, balance) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = DbConnection.getConnection().prepareStatement(sql);
			statement.setString(1, bank_name);
			statement.setString(2, b_username);
			statement.setString(3, email);
			statement.setString(4, password);
			statement.setInt(5, balance);
			isBankRegistered = statement.executeUpdate();
			if (isBankRegistered > 0) {
				System.out.println("Registered successfully.");
			} else {
				System.out.println("Operation failed.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
