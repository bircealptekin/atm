package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BankDto;
import dto.CustomerDto;
import utils.DbConnection;
import utils.IDaoImplements;

public class BankDao implements IDaoImplements<BankDto> {
	ResultSet resultSet;

	@Override
	public boolean create(BankDto bankDto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void withdraw(BankDto bankDto) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public void deposit(BankDto bankDto) throws SQLException {
		// TODO Auto-generated method stub
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
				bankBalance = currentBank.getBalance();
			}
			return bankBalance;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankBalance;
	}

}
