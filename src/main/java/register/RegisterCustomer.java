package register;

import java.sql.PreparedStatement;
import java.util.Scanner;

import dao.BankDao;
import dto.CustomerDto;
import utils.DbConnection;

public class RegisterCustomer {

	public void customerRegister() {
		BankDao bankDao = new BankDao();
		CustomerDto registerCustomer;
		registerCustomer = new CustomerDto();
		String name, surname, username, email, password;
		int bank_id, balance = 0, isCustomerRegistered = 0;
		Scanner input = new Scanner(System.in);
		
		bankDao.listForCustomer();
		System.out.println("Please enter the ID of the bank you want to use: ");
		bank_id = input.nextInt();
		input.nextLine();
		System.out.println("Enter your name: ");
		name = input.nextLine();
		System.out.println("Enter your surname: ");
		surname = input.nextLine();
		System.out.println("Enter your username: ");
		username = input.nextLine();
		System.out.println("Enter email: ");
		email = input.nextLine();
		System.out.println("Enter password: ");
		password = input.nextLine();
		
		String sql = "INSERT INTO customers(bank_id, name, surname, username, email, password, balance) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = DbConnection.getConnection().prepareStatement(sql);
			statement.setInt(1, bank_id);
			statement.setString(2, name);
			statement.setString(3, surname);
			statement.setString(4, username);
			statement.setString(5, email);
			statement.setString(6, password);
			statement.setInt(7, balance);
			isCustomerRegistered = statement.executeUpdate();
			if (isCustomerRegistered > 0) {
				System.out.println("Registered successfully.");
			} else {
				System.out.println("Operation failed.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
