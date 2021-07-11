package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDaoImplements<T> {
	boolean create(T t) throws SQLException;
	boolean withdraw(T t) throws SQLException;
	boolean deposit(T t) throws SQLException;
	boolean delete(T t) throws SQLException;
	ArrayList<T> list();
	
}