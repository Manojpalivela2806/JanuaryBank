package org.jsp.bank.dao;

public interface UserDAO {
	
	boolean userlogin(String BankEmailId,int Password);
	
	void debit(int accountNumber,int password);
	
	void balanceenquiry(int accountNumber,int password);
	
	void credit(int accountNumber,int password);
	
	void mobileToMobileTransaction(String mobileNumber);
	
	void checkStatement(int bankAccountNumber);
	
	void changePassword(int accountNumber ,String mobileNumber);
}
