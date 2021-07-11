package screens;

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

	public void CustomerDisplay() {
		while (true) {
			Scanner input = new Scanner(System.in);
			System.out.println("Please make a choice.");
			System.out.println("1)Check Balance\n2)Deposit\n3)Withdrawal\n4)Transfer\n5)Exit");
			int choice;
			choice = input.nextInt();

			switch (choice) {
				case 1:
					System.out.println("**********************************");
					customerDao.list();
					break;
				case 2:
					System.out.println("**********************************");
					
					break;
				case 3:
					System.out.println("**********************************");
					
					break;
				case 4:
					System.out.println("**********************************");
					
					break;
				case 5:
					System.out.println("Exiting...");
					System.exit(0);
					break;

				default:
					System.out.println("Please make a choice.");
					break;
			}
		}
	}

}
