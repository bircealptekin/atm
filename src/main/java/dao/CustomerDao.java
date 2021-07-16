package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dto.BankDto;
import dto.CustomerDto;
import utils.DbConnection;
import utils.IDaoImplements;

public class CustomerDao implements IDaoImplements<CustomerDto> {
	ResultSet resultSet;
	//private CustomerDto customerDto;
	Scanner input = new Scanner(System.in);
	
	@Override
	public void create(CustomerDto customerDto) throws SQLException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public int getBalance(CustomerDto currentCustomer) throws SQLException {
		int customerBalance = 0;
		String sql = "SELECT balance FROM customers WHERE id = ?";
		try {
			DbConnection.getConnection().setAutoCommit(false);
			PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, currentCustomer.getId());
			this.resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				customerBalance = resultSet.getInt("balance");
				currentCustomer.setBalance(customerBalance);
			}
			DbConnection.getConnection().setAutoCommit(true);
			return customerBalance;
		} catch (Exception e) {
			DbConnection.getConnection().rollback();
			//e.printStackTrace();
		}
		return customerBalance;
	}
	
	@Override
	public void withdraw(CustomerDto currentCustomer) throws SQLException {
		int withdrawAmount = 0, newCustomerBalance = 0;
		System.out.println("Please enter the amount of money you want to withdraw: ");
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
			DbConnection.getConnection().setAutoCommit(false);
			PreparedStatement preparedStatement2 = DbConnection.getConnection().prepareStatement(sql);
			preparedStatement2.setInt(1, newCustomerBalance);
			preparedStatement2.setInt(2, currentCustomer.getId());
			isBalanceUpdated = preparedStatement2.executeUpdate();
			if(isBalanceUpdated > 0) {
				System.out.println("Operation successful.");
			}
			DbConnection.getConnection().setAutoCommit(true);
		} catch (Exception e) {
			DbConnection.getConnection().rollback();
			//e.printStackTrace();
		}
	}
	
	@Override
	public void transfer(CustomerDto currentCustomer) throws SQLException {
		int choice, proceed, transferId, transferAmount;
		System.out.println("Please make a choice:\n1. Transfer to Bank 2. Transfer to Customer");
		choice = input.nextInt();
		if(choice == 1) {
			System.out.println("Please enter the ID of the bank: ");
			transferId = input.nextInt();
			
			String sql1 = "SELECT bank_name, balance FROM bank WHERE id = ?";
			try {
				DbConnection.getConnection().setAutoCommit(false);
				BankDto transferBank = new BankDto();
				BankDao bankDao = new BankDao();
				PreparedStatement statement1 = DbConnection.getConnection().prepareStatement(sql1);
				statement1.setInt(1, transferId);
				this.resultSet = statement1.executeQuery();
				if(resultSet.next()) {
					transferBank.setId(transferId);
					transferBank.setBank_name(resultSet.getString("bank_name"));
					transferBank.setBalance(resultSet.getInt("balance"));
					System.out.println("Transfer will be made to: " + transferBank.getBank_name());
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
					updateBalance(currentCustomer, getBalance(currentCustomer) - transferAmount);
					
					bankDao.updateBalance(transferBank, bankDao.getBalance(transferBank) + transferAmount);					
				
				}	
				else {
					System.out.println("Going back to main screen...");
					
				}
				
				DbConnection.getConnection().setAutoCommit(true);
			} catch (Exception e) {
				DbConnection.getConnection().rollback();
				//e.printStackTrace();
			}
		} 
		else if (choice == 2) {
			System.out.println("Please enter the ID of the customer: ");
			transferId = input.nextInt();
			
			String sql1 = "SELECT name, surname, balance FROM customers WHERE id = ?";
			try {
				DbConnection.getConnection().setAutoCommit(false);
				CustomerDto transferCustomer = new CustomerDto();
				PreparedStatement statement1 = DbConnection.getConnection().prepareStatement(sql1);
				statement1.setInt(1, transferId);
				this.resultSet = statement1.executeQuery();
				if(resultSet.next()) {
					transferCustomer.setId(transferId);
					transferCustomer.setName(resultSet.getString("name"));
					transferCustomer.setSurname(resultSet.getString("surname"));
					transferCustomer.setBalance(resultSet.getInt("balance"));
					System.out.println("Transfer will be made to: " + transferCustomer.getName() + " " + transferCustomer.getSurname());
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
					updateBalance(currentCustomer, getBalance(currentCustomer) - transferAmount);
					
					updateBalance(transferCustomer, getBalance(transferCustomer) + transferAmount);					
					
				}	
				else {
					System.out.println("Going back to main screen...");
					
				}
				
				DbConnection.getConnection().setAutoCommit(true);
			} catch (Exception e) {
				DbConnection.getConnection().rollback();
				//e.printStackTrace();
			}
			
		}
				
	}
	
	@Override
	public ArrayList<CustomerDto> list() {
		CustomerDto customerDto; //declaration of variable
		ArrayList<CustomerDto> customerList = new ArrayList<CustomerDto>();
		String sql = "SELECT * FROM customers";
		try {
			DbConnection.getConnection().setAutoCommit(false);
			PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
			this.resultSet = preparedStatement.executeQuery();
			System.out.println("Customers list: ");
			while(resultSet.next()) {
				customerDto = new CustomerDto();
				customerDto.setId(resultSet.getInt("id"));
				customerDto.setBank_id(resultSet.getInt("bank_id"));
				customerDto.setName(resultSet.getString("name"));
				customerDto.setSurname(resultSet.getString("surname"));
				customerDto.setUsername(resultSet.getString("username"));
				customerDto.setEmail(resultSet.getString("email"));
				customerDto.setPassword(resultSet.getString("password"));
				customerDto.setBalance(resultSet.getInt("balance"));
				customerList.add(customerDto);
			}
			DbConnection.getConnection().setAutoCommit(true);
			return customerList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerList;
	}


	@Override
	public void delete(CustomerDto currentCustomer) throws SQLException {
		int proceed, isDeleted = 0;
		String password;
		System.out.println("Please enter password to confirm: ");
		password = input.nextLine();
		
		if(currentCustomer.getPassword().equals(password)) {
			System.out.println(currentCustomer.getName() + " " + currentCustomer.getSurname() + ", your account will be deleted.");
			System.out.println("Proceed?\n1. Yes 2. No");
			proceed = input.nextInt();
			if(proceed == 1) {
				String sql = "DELETE FROM customers WHERE id = ?";
				try {
					DbConnection.getConnection().setAutoCommit(false);
					PreparedStatement statement = DbConnection.getConnection().prepareStatement(sql);
					statement.setInt(1, currentCustomer.getId());
					isDeleted = statement.executeUpdate();
					if (isDeleted > 0) {
						System.out.println("Your account has been deleted.");
					}
					else {
						System.out.println("Operation failed.");
					}
					DbConnection.getConnection().setAutoCommit(true);
				}
				catch (Exception e) {
					DbConnection.getConnection().rollback();
					//e.printStackTrace();
				}
				
			}
			else if(proceed == 2){
				System.out.println("Going back to main screen...");
			}
			
		}
		else {
			System.out.println("Password incorrect.");
		}
		
	}

	
}