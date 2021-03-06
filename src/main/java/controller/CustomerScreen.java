package controller;

import java.sql.SQLException;
import java.util.Scanner;

import dao.CustomerDao;
import dto.CustomerDto;
import email.Email;

public class CustomerScreen {
	CustomerDao customerDao = new CustomerDao();
	CustomerDto currentCustomer;
	Email email = new Email();
	
	public CustomerScreen(CustomerDto currentCustomer) {
		super();
		this.currentCustomer = currentCustomer;
	}

	public void CustomerDisplay() throws SQLException {
		int choice = 0;
		while (choice != 7) {
			Scanner input = new Scanner(System.in);
			System.out.println("Please make a choice.");
			System.out.println("1)Check Balance\n2)Deposit\n3)Withdrawal\n4)Transfer\n5)Send Email\n6)Delete Account\n7)Exit");
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
					email.sendEmail();
					break;
				case 6:
					System.out.println("**********************************");
					customerDao.delete(currentCustomer);
					break;
				case 7:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Please make a choice.");
					break;
			}
		}
	}

}
