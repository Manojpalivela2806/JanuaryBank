package org.jsp.bank.dao;

public class AdminHelperClass {

	public static AdminDAO AdminHelperMethod()
	{
		AdminDAO dao = new AdminDAOimp();
		return dao;
	}
}
