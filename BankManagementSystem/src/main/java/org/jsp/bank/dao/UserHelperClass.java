package org.jsp.bank.dao;

public class UserHelperClass {
	public static UserDAO UserHelperMethod()
	{
		UserDAO userdao = new UserDAOimp();
		return userdao;
	}

}
