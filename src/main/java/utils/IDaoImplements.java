package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDaoImplements<T> {
	boolean create(T t) throws SQLException;
	boolean update(T t) throws SQLException;
	boolean delete(T t) throws SQLException;
	ArrayList<T> list();
	
	default Connection dbConnection() {
		DbConnection dbConnection = new DbConnection();
		return dbConnection.getConnection();
	}
}