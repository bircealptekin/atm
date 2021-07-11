package dto;

public class BankDto {
	
	// declare the variables
	private String bank_name;
	private String b_username;
	private String password;
	private int balance;
	
	// getters and setters
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getB_username() {
		return b_username;
	}
	public void setB_username(String b_username) {
		this.b_username = b_username;
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
	public BankDto() {
		
	}
	
	// constructor with parameters
	public BankDto(String bank_name, String b_username, String password, int balance) {
		super();
		this.bank_name = bank_name;
		this.b_username = b_username;
		this.password = password;
		this.balance = balance;
	}
	
	
}
