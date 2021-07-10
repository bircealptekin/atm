package atm_main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import dto.CustomerDto;
import utils.DbConnection;

public class Login {
	
	public static void main(String[] args) {
		
		System.out.println("-------- LOGIN --------");
		
	}
	
	public Connection dbConnection() {
		DbConnection dbConnection = new DbConnection();
		return dbConnection.getConnection();
	}
	
	public void checkLogin() {
		String username = CustomerDto.getUsername(); //Assign user entered values to temporary variables.
        String password = CustomerDto.getPassword();
		
		Scanner input = new Scanner(System.in);
		String usernameLogin, passwordLogin;
		
		System.out.println("Please enter your password: ");
		usernameLogin = input.nextLine();
		System.out.println("Please enter your password: ");
		passwordLogin = input.nextLine();
		
		try (Connection connection = dbConnection()) {
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement("SELECT username, password FROM customers WHERE username=? AND password=?");
			statement.setString(1, usernameLogin);
            statement.setString(2, passwordLogin);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) // Until next row is present otherwise it return false
            {
             username = resultSet.getString("username"); //fetch the values present in database
             password = resultSet.getString("password");

              if(username.equals(usernameLogin) && password.equals(passwordLogin))
              {
                 System.out.println("Login successful."); ////If the user entered values are already present in the database, which means user has already registered so return a SUCCESS message.
              }
              else {
            	  System.out.println("Invalid user credentials");
              }
			
            }
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
}
