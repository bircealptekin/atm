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
			System.out.println("Pleas specify another amount: ");
			withdrawAmount = input.nextInt();
		}
		
		newCustomerBalance = getBalance(currentCustomer) - withdrawAmount;
		update(currentCustomer, newCustomerBalance);
		
	}
	
	@Override
	public void deposit(CustomerDto currentCustomer) throws SQLException {
		int depositAmount = 0, newCustomerBalance = 0;
		System.out.println("Please enter the amount of money you want to deposit: ");
		depositAmount = input.nextInt();
		
		newCustomerBalance = getBalance(currentCustomer) + depositAmount;
		update(currentCustomer, newCustomerBalance);
	}
	
	public void update(CustomerDto currentCustomer, int newCustomerBalance) throws SQLException {
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

	
}