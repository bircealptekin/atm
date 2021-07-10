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
		ArrayList<CustomerDto> customers = new ArrayList<CustomerDto>();
		String sql = "SELECT * FROM customers";
		try (Connection connection = DbConnection.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			this.resultSet = preparedStatement.executeQuery();
			System.out.println("Customers list.");
			while(resultSet.next()) {
				customerDto = new CustomerDto();
				customerDto.setName(resultSet.getString("name"));
				customerDto.setSurname(resultSet.getString("surname"));
				customerDto.setUsername(resultSet.getString("username"));
				customerDto.setPassword(resultSet.getString("password"));
				customerDto.setBalance(resultSet.getInt("balance"));
				customers.add(customerDto);
			}
			return customers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customers;
	}


	@Override
	public boolean create(CustomerDto customerDto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean update(CustomerDto writerDto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean delete(CustomerDto writerDto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}