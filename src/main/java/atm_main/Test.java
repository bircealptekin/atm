package atm_main;

import dto.BankDto;
import dto.CustomerDto;
import utils.DbConnection;

import java.sql.SQLException;
import java.util.Scanner;

import controller.BankScreen;
import controller.CustomerScreen;
import controller.LoginBank;
import controller.LoginCustomer;
import controller.RegisterBank;
import controller.RegisterCustomer;

public class Test {

	public static void main(String[] args) throws SQLException {
		
		DbConnection.getConnection();
		
		CustomerDto currentCustomer;
		BankDto currentBank;
		
		Scanner input = new Scanner(System.in);
		int choice = 0;
		while(choice != 3) {
		System.out.println("-------- WELCOME --------");
		System.out.println("Please make a choice:");
		System.out.println("1. Register\n2. Login\n3. Exit App");
		choice = input.nextInt();
		if(choice == 1) {
			System.out.println("1. Bank Register\n2. Customer Register\n3. Exit App");
			choice = input.nextInt();
			if (choice == 1) {
				RegisterBank registerBank = new RegisterBank();
				registerBank.bankRegister();
			}
			else if (choice == 2) {
				RegisterCustomer registerCustomer = new RegisterCustomer();
				registerCustomer.customerRegister();
			}
		}
		else if (choice == 2) {
			System.out.println("1. Bank Login\n2. Customer Login\n3.Exit App");
			choice = input.nextInt();
			if(choice == 1) {
				LoginBank loginBank = new LoginBank();
				currentBank = loginBank.checkLoginBank();
				if (currentBank != null) {
					BankScreen bankScreen = new BankScreen(currentBank);
					bankScreen.BankDisplay();
				}
				else
					System.out.println("Invalid user credentials.");

			}
			else if(choice == 2) {
				LoginCustomer loginCustomer = new LoginCustomer();
				currentCustomer = loginCustomer.checkLoginCustomer();
				if (currentCustomer != null) {
					CustomerScreen customerScreen = new CustomerScreen(currentCustomer);
					customerScreen.CustomerDisplay();
				}
				else
					System.out.println("Invalid user credentials.");
			}
		}
		

		}
		System.out.println("Closing the app...");
		
	}

}
