package org.jsp.bank.dao;

import org.jsp.bank.model.BankUserDetails;

public interface AdminDAO {

  boolean adminlogin(String adminEmailId,String adminPassword );
  
  void userRegistration(BankUserDetails bankUserDetails);
  
  void selectingAllUserDetails();
  
  void updateDetails(int accountNumber);
  
  void deleteUserDetails(int accountNumber);
}

