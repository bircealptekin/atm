package atm_main;

import dao.CustomerDao;
import atm_main.Login;;

public class Test {

	public static void main(String[] args) {
		CustomerDao customerDao = new CustomerDao();
		
		//System.out.println(customerDao.list());
		
		Login login = new Login();
		login.checkLogin();

	}

}
