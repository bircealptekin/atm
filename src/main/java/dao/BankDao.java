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
	public boolean withdraw(BankDto bankDto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deposit(BankDto bankDto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
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

}
