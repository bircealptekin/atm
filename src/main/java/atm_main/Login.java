package atm_main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import utils.ClearConsole;
import utils.DbConnection;

public class Login {
	
	public void checkLogin() {
		System.out.println("-------- LOGIN --------");
	
		Scanner input = new Scanner(System.in);
		String usernameLogin, passwordLogin;
		
		System.out.println("Please enter your username: ");
		usernameLogin = input.nextLine();
		System.out.println("Please enter your password: ");
		passwordLogin = input.nextLine();
		
		try {
			PreparedStatement statement = (PreparedStatement) DbConnection.getConnection().prepareStatement("SELECT username, password FROM customers WHERE username=? AND password=?");
			statement.setString(1, usernameLogin);
            statement.setString(2, passwordLogin);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
            	System.out.println("Login successful.");
            	ClearConsole.clearConsole();
            	//loginSuccess();
            }
            else {
            	  System.out.println("Invalid user credentials.");
              }
			
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loginSuccess() {
		while (true) {
			Scanner input = new Scanner(System.in);
			System.out.println("lütfen seçim yapýnýz");
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
