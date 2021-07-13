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
	public boolean create(BankDto bankDto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
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
			PreparedStatement preparedStatement2 = DbConnection.getConnection().prepareStatement(sql);
			preparedStatement2.setInt(1, newBankBalance);
			preparedStatement2.setInt(2, currentBank.getId());
			isBalanceUpdated = preparedStatement2.executeUpdate();
			if(isBalanceUpdated > 0) {
				System.out.println("Operation successful.");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (choice == 2) {
			System.out.println("Please enter the ID of the customer: ");
			transferId = input.nextInt();
			
			String sql1 = "SELECT name, surname, balance FROM customers WHERE id = ?";
			try {
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
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	@Override
	public boolean delete(BankDto bankDto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<BankDto> list() {
		BankDto bankDto;
		ArrayList<BankDto> bankList = new ArrayList<BankDto>();
		String sql = "SELECT * FROM bank";
		try {
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
			return bankList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankList;
	}

	@Override
	public int getBalance(BankDto currentBank) throws SQLException {
		int bankBalance = 0;
		String sql = "SELECT balance FROM bank WHERE id = ?";
		try {
			PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, currentBank.getId());
			this.resultSet = preparedStatement.executeQuery();
			System.out.println("Your balance: ");
			if(resultSet.next()) {
				bankBalance = resultSet.getInt("balance");
				currentBank.setBalance(bankBalance);
			}
			return bankBalance;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankBalance;
	}

}
