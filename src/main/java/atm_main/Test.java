package atm_main;

import dao.CustomerDao;

public class Test {

	public static void main(String[] args) {
		CustomerDao customerDao = new CustomerDao();
		
		System.out.println(customerDao.list());

	}

}
