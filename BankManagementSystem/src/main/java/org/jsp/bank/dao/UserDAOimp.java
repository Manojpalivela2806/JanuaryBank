package org.jsp.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

public class UserDAOimp implements UserDAO {
	final private String url ="jdbc:mysql://localhost:3306/advancejavaprojects?user=root&password=12345";
	final private String select="select * from bank_user_details where User_BankEmail_Id=? and User_Passwordl=?";
	final private String selectAccountNumAndPassword="select * from bank_user_details where User_Account_Number=? and User_Passwordl=?";
	final private String updateamount= "update bank_user_details set User_Amount=? where User_Passwordl=?";
	final private String selectMoblieNumber = "select * from bank_user_details where User_Mobile_Number=?";
	final private String selectSenderData = "update bank_user_details set User_Amount=? where User_Mobile_Number=?";
	final private String insertStatement="insert into statement values(?,?,?,?,?,?,?,?,?)";
	final private String balanceEnquiry="select * from bank_user_details where User_Account_Number=? and User_Passwordl=?";
	final private String checkState = "SELECT * FROM statement WHERE BankAccount = ?";
	final private String accountAndMobileNo="select * from bank_user_details where User_Account_Number=? and User_Mobile_Number=?";
	final private String updatePassword= "update bank_user_details set User_Passwordl=? where User_Account_Number=? ";
	Scanner scan = new Scanner(System.in);
	@Override
	public boolean userlogin(String BankEmailId, int Password) {
		try {
			Connection connect = DriverManager.getConnection(url);
			PreparedStatement ps = connect.prepareStatement(select);
			ps.setString(1, BankEmailId);
			ps.setInt(2, Password);
			ResultSet set =	ps.executeQuery();
			if (set.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return false;
	}

	@Override
	public void debit(int accountNumber, int password) {
		try {
				Connection connect =DriverManager.getConnection(url);
				PreparedStatement ps = connect.prepareStatement(selectAccountNumAndPassword);
				ps.setInt(1, accountNumber);
				ps.setInt(2, password);
				ResultSet set =	ps.executeQuery();
				if (set.next()) {
					System.out.println("Enter your amount");
					double debitingAmount = scan.nextDouble();
					if (debitingAmount>=0) {
						double databaseAmount = set.getDouble("User_Amount");
						if (databaseAmount>=debitingAmount) {
							double reminingAmount = databaseAmount-debitingAmount;
							PreparedStatement ps1 = connect.prepareStatement(updateamount);
							ps1.setDouble(1, reminingAmount);
							ps1.setInt(2, password);
							int result =ps1.executeUpdate();
							if (result!=0) {
								PreparedStatement ps2 = connect.prepareStatement(insertStatement);
								ps2.setString(1, "Debit");
								ps2.setDate(2,Date.valueOf(LocalDate.now()));
								ps2.setString(3, "Online");
								Random random = new Random();
								int transactionid=random.nextInt(10000000);
								if(transactionid<1000000)
								{
									transactionid+=1000000;
								}
								ps2.setInt(4, transactionid);
								ps2.setString(5,debitingAmount+"dbt");
								int id = set.getInt("User_Id");
								ps2.setInt(6, id);
								ps2.setInt(7, accountNumber);
								ps2.setTime(8, Time.valueOf(LocalTime.now()));
								ps2.setString(9, reminingAmount+"avl bal");
								int update2=ps2.executeUpdate();
								if(update2!=0)
								{
									System.out.println("amount debited sucessfully");
								}
								
								
							} 
							else {
								
								System.out.println("server error 404");
							}
							
						} else {
							System.out.println("Insufficent amount");
						}
					} else {
						System.out.println("invalid account");
					}
				} else {
					System.out.println("invalid details");
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void balanceenquiry(int accountNumber, int password) {
		try {
			Connection connect =DriverManager.getConnection(url);
			PreparedStatement ps =connect.prepareStatement(balanceEnquiry);
			ps.setInt(1, accountNumber);
			ps.setInt(2, password);
			ResultSet set =ps.executeQuery();
			if (set.next()) {
				System.out.println("your's Available balance = "+set.getDouble("User_Amount"));
			} else {
				System.out.println("no data found related to credentials");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void credit(int accountNumber, int password) {
		try {
			Connection connect=DriverManager.getConnection(url);
			PreparedStatement ps = connect.prepareStatement(selectAccountNumAndPassword);
			ps.setInt(1, accountNumber);
			ps.setInt(2, password);
			ResultSet set=ps.executeQuery();
			if (set.next()) {
				System.out.println("Enter your amount: ");
				double creditingAmount = scan.nextDouble();
				if (creditingAmount>=0) {
					double databaseAmount = set.getDouble("User_Amount");
					double reminingAmount = databaseAmount+creditingAmount;
					PreparedStatement ps1 = connect.prepareStatement(updateamount);
					ps1.setDouble(1, reminingAmount);
					ps1.setInt(2, password);
					int result =ps1.executeUpdate();
					if (result!=0) {
						PreparedStatement ps2 = connect.prepareStatement(insertStatement);
						ps2.setString(1, "Credit");
						ps2.setDate(2,Date.valueOf(LocalDate.now()));
						ps2.setString(3, "Online");
						Random random = new Random();
						int transactionid=random.nextInt(10000000);
						if(transactionid<1000000)
						{
							transactionid+=1000000;
						}
						ps2.setInt(4, transactionid);
						ps2.setString(5,creditingAmount+"crd");
						int id = set.getInt("User_Id");
						ps2.setInt(6, id);
						ps2.setInt(7, accountNumber);
						ps2.setTime(8, Time.valueOf(LocalTime.now()));
						ps2.setString(9, reminingAmount+"avl bal");
						int update2=ps2.executeUpdate();
						if(update2!=0)
						{
							System.out.println("amount credited sucessfully");
						}
					} else {
						System.out.println("serveer error 404");
					}	
				} else {
					System.out.println("invalid amount");
				}	
			} else {
				System.out.println("invalid credentials");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@Override
	public void mobileToMobileTransaction(String mobileNumber) 
	{
		 try {
			Connection connection= DriverManager.getConnection(url);
			PreparedStatement prest=connection.prepareStatement(selectMoblieNumber);
			prest.setString(1,mobileNumber);
			ResultSet set3=prest.executeQuery();
			if (set3.next()) {
				System.out.println("Enter Receiver Mobile Nmber :");
				String receiverMobileNumber=scan.next();
				PreparedStatement prest2=connection.prepareStatement(selectMoblieNumber);
				prest2.setString(1, receiverMobileNumber);
				ResultSet set4=prest2.executeQuery();
				if (set4.next()) {
					             System.out.println("Enter the Amount :");
					             double userAmount=scan.nextDouble();
					             if (userAmount>=0) {
									double userDataBaseamount=set3.getDouble("user_Amount");
									if (userDataBaseamount>=userAmount) {
										double debit=userDataBaseamount-userAmount;
										PreparedStatement preUp=connection.prepareStatement(selectSenderData);
										preUp.setDouble(1, debit);
										preUp.setString(2, mobileNumber);
										int amountDebited=preUp.executeUpdate();
										if (amountDebited!=0) {
											PreparedStatement senderStatement=connection.prepareStatement(insertStatement);
											senderStatement.setString(1,"debit");
											senderStatement.setDate(2,Date.valueOf(LocalDate.now()));
											senderStatement.setString(3,"Mobile To Mobile transaction");
											Random random=new Random();
											int transactionId=random.nextInt(10000000);
											if (transactionId<1000000) {
												transactionId+=1000000;
											}
											senderStatement.setInt(4, transactionId);
											senderStatement.setString(5,userAmount+"dbt");
											senderStatement.setInt(6,set3.getInt("user_id"));
											senderStatement.setInt(7,set3.getInt("user_Account_number"));
											senderStatement.setTime(8,Time.valueOf(LocalTime.now()));
											senderStatement.setString(9,debit+"Cr");
										int senderStatementUpdate=senderStatement.executeUpdate();
										if (senderStatementUpdate!=0) {
											
											System.out.println("amount send succesfuly....;)");
											
											PreparedStatement senderStatementr=connection.prepareStatement(insertStatement);
											senderStatementr.setString(1,"credit");
											senderStatementr.setDate(2,Date.valueOf(LocalDate.now()));
											senderStatementr.setString(3,"Mobile to mobile ");
											Random random1=new Random();
											int transactionIdd=random1.nextInt(10000000);
											if (transactionIdd<1000000) {
												transactionIdd+=1000000;
											}
											senderStatementr.setInt(4, transactionIdd);
											senderStatementr.setString(5,userAmount+"Cr");
											senderStatementr.setInt(6,set4.getInt("user_id"));
											senderStatementr.setInt(7,set4.getInt("user_Account_number"));
											senderStatementr.setTime(8,Time.valueOf(LocalTime.now()));
											senderStatementr.setString(9,userAmount+set4.getDouble("user_Amount")+"Avl Bal");
										int ReceiverStatementUpdate=senderStatementr.executeUpdate();
										if (ReceiverStatementUpdate!=0) {
											
											System.out.println("receiverd amount sccessfully");
										} else {

											System.out.println("amount not received ...refund will initiated");
										}		
											
										}
										else {
											System.out.println("ststement update filed");
										}	
											
											
										} else {

											
											System.out.println("amount debit not equals");
											
											
										}
											
									} 
									else {
										System.out.println("Insufficient Amount");
									}
					            	 
					            	 
								} else {
									System.out.println("Invalid Amount");
								}
					             
				                 } 
				else {
                                   System.out.println("receiver with number "+receiverMobileNumber+" doesn't exist");
				     }
			                 
				
				
			}
			else {
					System.out.println("individual with number "+mobileNumber+" doest exist");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void checkStatement(int bankAccountNumber) 
	{
		
		try {
			Connection connect=	DriverManager.getConnection(url);
			PreparedStatement ps = connect.prepareStatement(checkState);
			ps.setInt(1, bankAccountNumber);
			ResultSet set=	ps.executeQuery();
			if(set.next())
			{
			do{
				System.out.println("Date of Transaction: "+set.getDate("DateOfTransation"));
				System.out.println("Transaction Id: "+set.getInt("TransationId"));
				System.out.println("Transaction amount: "+set.getString("Amount"));
				System.out.println("Status of transaction: "+set.getString("Status"));
				System.out.println("Mode of transaction: "+set.getString("TypeOfPayment"));
				System.out.println("Available Balance: "+set.getString("RemainingBalance"));
                System.out.println("================== *** ==================");    
            } while (set.next());
			}
			else {
				System.out.println("No records found on this accountNumber :"+bankAccountNumber);
				}
			}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void changePassword(int accountNumber, String mobileNumber) {
		// TODO Auto-generated method stub
		try {
			Connection connect = DriverManager.getConnection(url);
			PreparedStatement ps = connect.prepareStatement(accountAndMobileNo);
			ps.setInt(1, accountNumber);
			ps.setString(2, mobileNumber);
			ResultSet set = ps.executeQuery();
			if (set.next()) {
				System.out.println("Enter password: ");
				int password =scan.nextInt();
				System.out.println("Enter accountNumber: ");
				int account1Number = scan.nextInt();
				PreparedStatement ps1 = connect.prepareStatement(updatePassword);
				ps1.setInt(1, password);
				ps1.setInt(2, account1Number);
				int result= ps1.executeUpdate();
				if (result!=0) {
					System.out.println("password updated sucessfully");
				} else {
					System.out.println("password not updated");
				}
			} else {

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
		
	
	
	


