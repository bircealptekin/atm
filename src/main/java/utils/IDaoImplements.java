package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDaoImplements<T> {
	void create(T t) throws SQLException;
	void withdraw(T t) throws SQLException;
	void deposit(T t) throws SQLException;
	void delete(T t) throws SQLException;
	int getBalance(T t) throws SQLException;
	public void transfer(T t) throws SQLException;
	ArrayList<T> list();
	
}