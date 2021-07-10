package dto;

public class CustomerDto {
	
	// declare the variables
	private String name;
	private String surname;
	private String password;
	private int balance;
	
	// getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	// constructor without parameters
	public CustomerDto( ) {
		
	}
	
	// constructor with parameters
	public CustomerDto(String name, String surname, String password, int balance) {
		super();
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "[name=" + name + ", surname=" + surname + ", password=" + password + ", balance=" + balance
				+ "]\n";
	}
	
	
	

}
