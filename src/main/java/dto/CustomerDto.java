package dto;

public class CustomerDto {
	
	// declare the variables
	private int id;
	private String name;
	private String surname;
	private String username;
	private String email;
	private String password;
	private int balance;
	
	// getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public CustomerDto() {
		
	}
	
	// constructor with parameter
	public CustomerDto(int id, String name, String surname, String username, String email, String password, int balance) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.balance = balance;
	}
	
	// to string
	@Override
	public String toString() {
		return "CustomerDto [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username
				+ ", email=" + email + ", password=" + password + ", balance=" + balance + "]\n";
	}
	
	
}
