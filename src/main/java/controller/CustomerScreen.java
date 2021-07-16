package controller;

import java.sql.SQLException;
import java.util.Scanner;

import dao.CustomerDao;
import dto.CustomerDto;

public class CustomerScreen {
	CustomerDao customerDao = new CustomerDao();
	CustomerDto currentCustomer;
	
	public CustomerScreen(CustomerDto currentCustomer) {
		super();
		this.currentCustomer = currentCustomer;
	}

	public void CustomerDisplay() throws SQLException {
		int choice = 0;
		while (choice != 5) {
			Scanner input = new Scanner(System.in);
			System.out.println("Please make a choice.");
			System.out.println("1)Check Balance\n2)Deposit\n3)Withdrawal\n4)Transfer\n5)Delete Account\n6)Exit");
			choice = input.nextInt();

			switch (choice) {
				case 1:
					System.out.println("**********************************");
					System.out.println("Your balance: ");
					System.out.println(customerDao.getBalance(currentCustomer));
					break;
				case 2:
					System.out.println("**********************************");
					customerDao.deposit(currentCustomer);
					break;
				case 3:
					System.out.println("**********************************");
					customerDao.withdraw(currentCustomer);
					break;
				case 4:
					System.out.println("**********************************");
					customerDao.transfer(currentCustomer);
					break;
				case 5:
					System.out.println("**********************************");
					customerDao.delete(currentCustomer);
					break;
				case 6:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Please make a choice.");
					break;
			}
		}
	}

}
