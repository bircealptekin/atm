package screens;

import java.util.Scanner;

public class CustomerScreen {
	
	public void CustomerDisplay() {
		while (true) {
			Scanner input = new Scanner(System.in);
			System.out.println("Please make a choice.");
			System.out.println("1)Deposit\n2)Withdrawal\n3)Transfer\n4)Exit");
			int choice;
			choice = input.nextInt();

			switch (choice) {
				case 1:
					System.out.println("**********************************");
					
					break;
				case 2:
					System.out.println("**********************************");
					
					break;
				case 3:
					System.out.println("**********************************");
					
					break;
				case 4:
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
