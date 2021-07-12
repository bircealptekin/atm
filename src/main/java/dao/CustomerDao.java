package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dto.CustomerDto;
import utils.DbConnection;
import utils.IDaoImplements;

public class CustomerDao implements IDaoImplements<CustomerDto> {
	ResultSet resultSet;
	//private CustomerDto customerDto;
	Scanner input = new Scanner(System.in);
	
	@Override
	public ArrayList<CustomerDto> list() {
		CustomerDto customerDto; //declaration of variable writerDto
		ArrayList<CustomerDto> customerList = new ArrayList<CustomerDto>();
		String sql = "SELECT * FROM customers";
		try {
			PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
			this.resultSet = preparedStatement.executeQuery();
			System.out.println("Customers list.");
			while(resultSet.next()) {
				customerDto = new CustomerDto();
				customerDto.setId(resultSet.getInt("id"));
				customerDto.setName(resultSet.getString("name"));
				customerDto.setSurname(resultSet.getString("surname"));
				customerDto.setUsername(resultSet.getString("username"));
				customerDto.setPassword(resultSet.getString("password"));
				customerDto.setBalance(resultSet.getInt("balance"));
				customerList.add(customerDto);
			}
			return customerList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerList;
	}


	@Override
	public boolean create(CustomerDto customerDto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void withdraw(CustomerDto currentCustomer) throws SQLException {
		int withdrawAmount = 0, newCustomerBalance = 0;
		System.out.println("Please enter the amount of money you want to deposit: ");
		withdrawAmount = input.nextInt();
		while (withdrawAmount > getBalance(currentCustomer)) {
			System.out.println("Not enough balance. Your balance is: " + getBalance(currentCustomer));
			System.out.println("Please specify another amount: ");
			withdrawAmount = input.nextInt();
		}
		
		newCustomerBalance = getBalance(currentCustomer) - withdrawAmount;
		updateBalance(currentCustomer, newCustomerBalance);
		
	}
	
	@Override
	public void deposit(CustomerDto currentCustomer) throws SQLException {
		int depositAmount = 0, newCustomerBalance = 0;
		System.out.println("Please enter the amount of money you want to deposit: ");
		depositAmount = input.nextInt();
		
		newCustomerBalance = getBalance(currentCustomer) + depositAmount;
		updateBalance(currentCustomer, newCustomerBalance);
	}
	
	public void updateBalance(CustomerDto currentCustomer, int newCustomerBalance) throws SQLException {
		String sql = "UPDATE customers SET balance = ? WHERE id = ?";
		int isBalanceUpdated = 0;
		try {
			PreparedStatement preparedStatement2 = DbConnection.getConnection().prepareStatement(sql);
			preparedStatement2.setInt(1, newCustomerBalance);
			preparedStatement2.setInt(2, currentCustomer.getId());
			isBalanceUpdated = preparedStatement2.executeUpdate();
			if(isBalanceUpdated > 0) {
				System.out.println("Operation successful.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public boolean delete(CustomerDto customerDto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int getBalance(CustomerDto currentCustomer) throws SQLException {
		int customerBalance = 0;
		String sql = "SELECT balance FROM customers WHERE id = ?";
		try {
			PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, currentCustomer.getId());
			this.resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				customerBalance = resultSet.getInt("balance");
				currentCustomer.setBalance(customerBalance);
			}
			return customerBalance;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerBalance;
	}
	
	public void transfer(CustomerDto currentCustomer) throws SQLException {
		int choice, proceed, transferId, transferAmount, currentCustomerBalance, transferCustomerBalance, transferCustomerNewBalance;
		String transferName, transferSurname;
		System.out.println("Please make a choice:\n1. Transfer to Bank 2. Transfer to Customer");
		choice = input.nextInt();
		if(choice == 1) {
			
		} 
		else if (choice == 2) {
			System.out.println("Please enter the ID of the customer: ");
			transferId = input.nextInt();
			
			String sql1 = "SELECT name, surname FROM customers WHERE id = ?";
			try {
				PreparedStatement statement1 = DbConnection.getConnection().prepareStatement(sql1);
				statement1.setInt(1, transferId);
				this.resultSet = statement1.executeQuery();
				if(resultSet.next()) {
					transferName = resultSet.getString("name");
					transferSurname = resultSet.getString("surname");
					System.out.println("Transfer will be made to: " + transferName + " " + transferSurname);
				}
				else {
					 System.out.println("No such ID. Please try again");
					 transfer(currentCustomer);
				}
				
				System.out.println("Proceed?\n1. Yes 2. No");
				proceed = input.nextInt();
				if(proceed == 1) {
					System.out.println("Please enter the amount of money you want to transfer: ");
					transferAmount = input.nextInt();
					currentCustomerBalance = getBalance(currentCustomer) - transferAmount;
					updateBalance(currentCustomer, currentCustomerBalance);
					String sql2 = "SELECT balance FROM customers WHERE id = ?";
					PreparedStatement statement2 = DbConnection.getConnection().prepareStatement(sql2);
					statement2.setInt(1, transferId);
					this.resultSet = statement2.executeQuery();
					
					if(resultSet.next()) {
						int isBalanceUpdated = 0;
						transferCustomerBalance = resultSet.getInt("balance");
						transferCustomerNewBalance = transferCustomerBalance + transferAmount;
						String sql3 = "UPDATE customers SET balance = ? WHERE id = ?";
						PreparedStatement statement3 = DbConnection.getConnection().prepareStatement(sql3);
						statement3.setInt(1, transferCustomerNewBalance);
						statement3.setInt(2, transferId);
						isBalanceUpdated = statement3.executeUpdate();
						if(isBalanceUpdated > 0) {
							System.out.println("Operation successful.");
						} else {
							System.out.println("Operation failed.");
						}
						
					}
				}	
				else if (proceed == 2) {
					System.out.println("Going back to main screen...");
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
				
	}

	
}