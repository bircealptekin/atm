package atm_main;

import dao.BankDao;
import dao.CustomerDao;
import dto.BankDto;
import dto.CustomerDto;
import screens.CustomerScreen;
import utils.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import atm_main.LoginCustomer;

public class Test {

	public static void main(String[] args) throws SQLException {
		
		DbConnection.getConnection();
	
		//CustomerDao customerDao = new CustomerDao();
		//BankDao bankDao = new BankDao();
		
		CustomerDto currentCustomer;
		BankDto currentBank;
		
		//System.out.println(customerDao.list());
		
		//System.out.println(bankDao.list());
		
		Scanner input = new Scanner(System.in);
		int choice = 0;
		while(choice != 3) {
		System.out.println("-------- WELCOME --------");
		System.out.println("Please make a choice:");
		System.out.println("1. Bank Login\n2. Customer Login\n3.Exit App");
		choice = input.nextInt();
		if(choice == 1) {
			LoginBank loginBank = new LoginBank();
			currentBank = loginBank.checkLoginBank();

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
		System.out.println("Closing the app...");
	}

}
