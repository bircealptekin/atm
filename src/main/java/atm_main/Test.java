package atm_main;

import dao.CustomerDao;
import utils.DbConnection;

import java.sql.Connection;
import java.util.Scanner;

import atm_main.LoginCustomer;

public class Test {

	public static void main(String[] args) {
		
		DbConnection.getConnection();
	
		CustomerDao customerDao = new CustomerDao();
		
		//System.out.println(customerDao.list());
		
		Scanner input = new Scanner(System.in);
		int choice;
		System.out.println("-------- WELCOME --------");
		System.out.println("Please make a choice:");
		System.out.println("1. Bank Login\n2.Customer Login");
		choice = input.nextInt();
		if(choice == 1) {
			LoginBank loginBank = new LoginBank();
			loginBank.checkLoginBank();

		}
		else if(choice ==2) {
			LoginCustomer loginCustomer = new LoginCustomer();
			loginCustomer.checkLoginCustomer();
		}

	}

}
