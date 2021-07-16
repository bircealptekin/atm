package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dto.BankDto;
import dto.CustomerDto;
import utils.DbConnection;
import utils.IDaoImplements;

public class BankDao implements IDaoImplements<BankDto> {
	ResultSet resultSet;
	Scanner input = new Scanner(System.in);
	
	@Override
	public void create(BankDto bankDto) throws SQLException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public int getBalance(BankDto currentBank) throws SQLException {
		int bankBalance = 0;
		String sql = "SELECT balance FROM bank WHERE id = ?";
		try {
			DbConnection.getConnection().setAutoCommit(false);
			PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, currentBank.getId());
			this.resultSet = preparedStatement.executeQuery();
			System.out.println("Your balance: ");
			if(resultSet.next()) {
				bankBalance = resultSet.getInt("balance");
				currentBank.setBalance(bankBalance);
			}
			DbConnection.getConnection().setAutoCommit(true);
			return bankBalance;
		} catch (Exception e) {
			DbConnection.getConnection().rollback();
			//e.printStackTrace();
		}
		return bankBalance;
	}

	@Override
	public void withdraw(BankDto currentBank) throws SQLException {
		int withdrawAmount = 0, newBankBalance = 0;
		System.out.println("Please enter the amount of money you want to withdraw: ");
		withdrawAmount = input.nextInt();
		while (withdrawAmount > getBalance(currentBank)) {
			System.out.println("Not enough balance. Your balance is: " + getBalance(currentBank));
			System.out.println("Please specify another amount: ");
			withdrawAmount = input.nextInt();
		}
		
		newBankBalance = getBalance(currentBank) - withdrawAmount;
		updateBalance(currentBank, newBankBalance);
	}

	@Override
	public void deposit(BankDto currentBank) throws SQLException {
		int depositAmount = 0, newBankBalance = 0;
		System.out.println("Please enter the amount of money you want to deposit: ");
		depositAmount = input.nextInt();
		
		newBankBalance = getBalance(currentBank) + depositAmount;
		updateBalance(currentBank, newBankBalance);
	}

	public void updateBalance(BankDto currentBank, int newBankBalance) throws SQLException {
		String sql = "UPDATE bank SET balance = ? WHERE id = ?";
		int isBalanceUpdated = 0;
		try {
			DbConnection.getConnection().setAutoCommit(false);
			PreparedStatement preparedStatement2 = DbConnection.getConnection().prepareStatement(sql);
			preparedStatement2.setInt(1, newBankBalance);
			preparedStatement2.setInt(2, currentBank.getId());
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
	public void transfer(BankDto currentBank) throws SQLException {
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
					 transfer(currentBank);
				}
				
				System.out.println("Proceed?\n1. Yes 2. No");
				proceed = input.nextInt();
				if(proceed == 1) {
					System.out.println("Please enter the amount of money you want to transfer: ");
					transferAmount = input.nextInt();
					updateBalance(currentBank, getBalance(currentBank) - transferAmount);
					
					updateBalance(transferBank, getBalance(transferBank) + transferAmount);					
				
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
				CustomerDao customerDao = new CustomerDao();
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
					 transfer(currentBank);
				}
				
				System.out.println("Proceed?\n1. Yes 2. No");
				proceed = input.nextInt();
				if(proceed == 1) {
					System.out.println("Please enter the amount of money you want to transfer: ");
					transferAmount = input.nextInt();
					updateBalance(currentBank, getBalance(currentBank) - transferAmount);
					
					customerDao.updateBalance(transferCustomer, customerDao.getBalance(transferCustomer) + transferAmount);					
					
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
	public void delete(BankDto currentBank) throws SQLException {
		int customerId, proceed, isDeleted = 0;
		System.out.println("Please enter the ID of the customer you want to delete: ");
		customerId = input.nextInt();
		
		String sql = "SELECT * FROM customers WHERE id  = ? and bank_id = ?";
		try {
			DbConnection.getConnection().setAutoCommit(false);
			CustomerDto deleteCustomer = new CustomerDto();
			PreparedStatement statement = DbConnection.getConnection().prepareStatement(sql);
			statement.setInt(1, customerId);
			statement.setInt(2, currentBank.getId());
			this.resultSet = statement.executeQuery();
			if(resultSet.next()) {
				deleteCustomer.setId(customerId);
				deleteCustomer.setName(resultSet.getString("name"));
				deleteCustomer.setSurname(resultSet.getString("surname"));
				System.out.println("Customer" + " " + deleteCustomer.getName() + " " + deleteCustomer.getSurname() + " will be deleted.");
			}
			else {
				 System.out.println("No such ID. Please try again.");
				 delete(currentBank);
			}
			System.out.println("Proceed?\n1. Yes 2. No");
			proceed = input.nextInt();
			if (proceed == 1) {
				String sql2 = "DELETE FROM customers WHERE id = ?";
				PreparedStatement statement2 = DbConnection.getConnection().prepareStatement(sql2);
				statement2.setInt(1, customerId);
				isDeleted = statement2.executeUpdate();
				if(isDeleted > 0) {
					System.out.println("Customer deleted.");
				}
				else {
					System.out.println("Operation failed.");
				}
			}
			else if (proceed == 2){
				System.out.println("Going back to main screen...");
			}
			
			DbConnection.getConnection().setAutoCommit(true);
			
		} catch (Exception e) {
			DbConnection.getConnection().rollback();
			//e.printStackTrace();
		}
	}

	@Override
	public ArrayList<BankDto> list() throws SQLException {
		BankDto bankDto;
		ArrayList<BankDto> bankList = new ArrayList<BankDto>();
		String sql = "SELECT * FROM bank";
		try {
			DbConnection.getConnection().setAutoCommit(false);
			PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
			this.resultSet = preparedStatement.executeQuery();
			System.out.println("Banks list:");
			while(resultSet.next()) {
				bankDto = new BankDto();
				bankDto.setId(resultSet.getInt("id"));
				bankDto.setBank_name(resultSet.getString("bank_name"));
				bankDto.setB_username(resultSet.getString("b_username"));
				bankDto.setEmail(resultSet.getString("email"));
				bankDto.setPassword(resultSet.getString("password"));
				bankDto.setBalance(resultSet.getInt("balance"));
				bankList.add(bankDto);
			}
			DbConnection.getConnection().setAutoCommit(true);
			return bankList;
		} catch (Exception e) {
			DbConnection.getConnection().rollback();
			//e.printStackTrace();
		}
		return bankList;
	}
	
	public ArrayList<CustomerDto> listCustomers(BankDto currentBank) throws SQLException {
		CustomerDto customerDto; //declaration of variable
		ArrayList<CustomerDto> customerList = new ArrayList<CustomerDto>();
		String sql = "SELECT * FROM customers where bank_id = ?";
		try {
			DbConnection.getConnection().setAutoCommit(false);
			PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, currentBank.getId());
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
			DbConnection.getConnection().rollback();
			//e.printStackTrace();
		}
		return customerList;
	}
	
	public void listForCustomer() throws SQLException{
		BankDto bankDto = new BankDto();
		ArrayList<BankDto> bankList = new ArrayList<BankDto>();
		String sql = "SELECT id, bank_name FROM bank";
		try {
			DbConnection.getConnection().setAutoCommit(false);
			PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
			this.resultSet = preparedStatement.executeQuery();
			System.out.println("Banks list:");
			while(resultSet.next()) {
				bankDto = new BankDto();
				bankDto.setId(resultSet.getInt("id"));
				bankDto.setBank_name(resultSet.getString("bank_name"));
				System.out.println("Bank ID: " + bankDto.getId() + " Bank name: " + bankDto.getBank_name());
			}
			DbConnection.getConnection().setAutoCommit(true);
		} catch (Exception e) {
			DbConnection.getConnection().rollback();
			//e.printStackTrace();
		}
	}

}
