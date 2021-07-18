package controller;

import java.sql.SQLException;
import java.util.Scanner;

import dao.BankDao;
import dto.BankDto;

public class BankScreen {
	
	BankDao bankDao = new BankDao();
	BankDto currentBank;
	
	public BankScreen(BankDto currentBank) {
		super();
		this.currentBank = currentBank;
	}

	public void BankDisplay() throws SQLException {
		int choice = 0;
		while (choice != 6) {
			Scanner input = new Scanner(System.in);
			System.out.println("Please make a choice.");
			System.out.println("1)Check Balance\n2)Deposit\n3)Withdrawal\n4)Transfer\n5)List Customers\n6)Delete Customer\n7)Send Email\n8)Exit");
			choice = input.nextInt();

			switch (choice) {
				case 1:
					System.out.println("**********************************");
					System.out.println(bankDao.getBalance(currentBank));
					break;
				case 2:
					System.out.println("**********************************");
					bankDao.deposit(currentBank);
					break;
				case 3:
					System.out.println("**********************************");
					bankDao.withdraw(currentBank);
					break;
				case 4:
					System.out.println("**********************************");
					bankDao.transfer(currentBank);
					break;
				case 5:
					System.out.println("**********************************");
					System.out.println(bankDao.listCustomers(currentBank));
					break;
				case 6:
					System.out.println("**********************************");
					bankDao.delete(currentBank);
					break;
				case 7:
					System.out.println("**********************************");
					bankDao.email(currentBank);
					break;
				case 8:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Please make a choice.");
					break;
			}
		}
	}
	
}
