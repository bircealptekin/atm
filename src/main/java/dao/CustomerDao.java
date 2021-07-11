package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CustomerDto;
import utils.DbConnection;
import utils.IDaoImplements;

public class CustomerDao implements IDaoImplements<CustomerDto> {
	ResultSet resultSet;
	//private CustomerDto customerDto;

	
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
	public boolean withdraw(CustomerDto customerDto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean deposit(CustomerDto customerDto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean delete(CustomerDto customerDto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int getBalance(CustomerDto currentCustomer) throws SQLException {
		//CustomerDto customerDto; //declaration of variable customerDto
		int customerBalance = 0;
		String sql = "SELECT balance FROM customers WHERE id = ?";
		try {
			PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, currentCustomer.getId());
			this.resultSet = preparedStatement.executeQuery();
			System.out.println("Your balance: ");
			if(resultSet.next()) {
				customerBalance = currentCustomer.getBalance();
			}
			return customerBalance;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerBalance;
	}

	
}