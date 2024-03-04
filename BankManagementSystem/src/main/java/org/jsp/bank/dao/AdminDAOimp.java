package org.jsp.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Random;
//import java.util.Random;
import java.util.Scanner;

import org.jsp.bank.model.BankUserDetails;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.a.NativeConstants.StringLengthDataType;

public class AdminDAOimp implements AdminDAO {
	Date date=Date.valueOf("1997-12-31");
	final private String select = "SELECT * FROM bank_admin WHERE Admin_Email_Id=? AND Admin_Password=?";
	final private String url ="jdbc:mysql://localhost:3306/advancejavaprojects?user=root&password=12345";
	final private String select1 ="insert into bank_user_details(User_Name, User_BankEmail_Id, User_Email_Id, User_Passwordl, User_Gender, User_Address, User_Date_Of_Birth, User_Account_Number, User_Amount, User_Mobile_Number, IFSC_Code) "
												+ "VALUES('JAGADEESH','jagadeeshsbibank@gmail.com','jagadeesh1231@gmail.com',12345,'male','mandapeta','1998-12-31',5768482,38000.45,'9854672523','SBI5432' )";
	final private String select2 ="insert into bank_user_details(User_Name, User_BankEmail_Id, User_Email_Id, User_Passwordl, User_Gender, User_Address, User_Date_Of_Birth, User_Account_Number, User_Amount, User_Mobile_Number, IFSC_Code)"
			+ " VALUES(?,?,?,?,?,?,?,?,?,?,?) ";
	final private String selectall="select * from bank_user_details ";
	final private String delete ="delete from bank_user_details where User_Account_Number=?";
	final private String update1 ="UPDATE bank_user_details SET User_Name=?, User_Email_Id=?, User_Gender=?, User_Address=?, User_Date_Of_Birth=?, User_Mobile_Number=? WHERE User_Account_Number=?";
	final private String select3 = "SELECT * FROM bank_user_details WHERE User_Account_Number=?";
	Scanner scan= new Scanner(System.in);
	@Override
	public boolean adminlogin(String adminEmailId, String adminPassword) {
		
		try {
			
			Connection connect = DriverManager.getConnection(url);
				PreparedStatement ps =connect.prepareStatement(select);
				ps.setString(1,adminEmailId);
				ps.setString(2, adminPassword);
				ResultSet set = ps.executeQuery();		
				if (set.next()) {
					return true;
//					Random random = new Random();
//					int otp = random.nextInt(10000);
//					if(otp<1000)
//					{
//						otp+=1000;
//					}
//					System.out.println("your otp is: "+otp);
//					System.out.println("Enter your OTP");
//					int uotp = scan.nextInt();
//					if(uotp==otp) {
//						System.out.println("login sucessfully....!");
//					}
//					else {
//						System.out.println("ivalid OTP Try again");
//					}
				} 
				else {
					return false;		
				}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	@Override
	public void userRegistration(BankUserDetails bankUserDetails) {
		try {
			Connection connect = DriverManager.getConnection(url);
			PreparedStatement ps2 =connect.prepareStatement(select2);
			ps2.setString(1, bankUserDetails.getUsername());
			Random random = new Random();
			int emailidnum=random.nextInt(100);
			if(emailidnum<10)
			{
				emailidnum*=10;
			}
			String bankEmailId =bankUserDetails.getUsername()+emailidnum+"@teca53.com";
			ps2.setString(2,bankEmailId);
			ps2.setString(3, bankUserDetails.getUseremailid());
			int password =random.nextInt(10000);
			if(password<1000)
			{
				password+=1000;
			}
			ps2.setInt   (4,password);
			ps2.setString(5, bankUserDetails.getGender());
			ps2.setString(6, bankUserDetails.getAddress());
			LocalDate userDateOfBirth =bankUserDetails.getUserdateofbirth();
			ps2.setDate  (7,Date.valueOf(userDateOfBirth));
			int accountNumber = random.nextInt(10000000);
			if(accountNumber<1000000)
			{
				accountNumber+=1000000;
			}
			ps2.setInt   (8, accountNumber);
			ps2.setDouble(9, bankUserDetails.getUseramount());
			ps2.setString(10, bankUserDetails.getUsermobilenumber());
			ps2.setString(11, "IFSCTECA53");
			  int result = ps2.executeUpdate();
			  
			  if(result!=0)
			  {
				  System.out.println("User Registration sucessfull....!");
			  }
			  else {
				  System.out.println("User Registration failed");
			  }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void selectingAllUserDetails() {
		try {
			Connection connect=	DriverManager.getConnection(url);
			Statement st =	connect.createStatement();
			ResultSet set =	st.executeQuery(selectall);
			if(set.isBeforeFirst())
			{
				while(set.next())
				{
					System.out.println("NAME OF THE USER         :"+set.getString("User_Name"));
					System.out.println("USER BANK EMAIL ID       :"+set.getString("User_BankEmail_Id"));
//					System.out.println("PASSWORD OF THE USER     :"+set.getInt("User_Passwordl"));
					int password = set.getInt("User_Passwordl");
		                String passwordString = String.valueOf(password);
		                StringBuilder maskedPassword = new StringBuilder();
		                for (int i = 0; i < passwordString.length(); i++) {
		                    maskedPassword.append('*');
		                }
		             System.out.println("PASSWORD OF THE USER     :" + maskedPassword.toString());
					 String mobileNumber = set.getString("User_Mobile_Number");
		                String maskedMobile = mobileNumber.substring(0, 2) + "xxxxx" + mobileNumber.substring(7);
		            System.out.println("MOBILE NUMBER OF THE USER:"+maskedMobile);
//					System.out.println("MOBILE NUMBER OF THE USER:"+set.getString("User_Mobile_Number"));
					System.out.println("AMOUNT OF THE USER       :"+set.getDouble("User_Amount"));
					System.out.println("*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+");
					
				}
			}
			else {
				System.out.println("DATA NOT FOUND");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateDetails(int accountNumber) {
		try {
			Connection connect = DriverManager.getConnection(url);
			PreparedStatement ps1 =connect.prepareStatement(select3);
			ps1.setInt(1, accountNumber);
			ResultSet set=ps1.executeQuery();
			if(set.next())
			{
				PreparedStatement ps =connect.prepareStatement(update1);
			System.out.println("Enter userName");
			String userName = scan.next();
			ps.setString(1,userName);
			System.out.println("Enter userEmailId");
			String emailId = scan.next();
			ps.setString(2, emailId);
			System.out.println("Enter Gender");
			String gender=scan.next();
			ps.setString(3, gender);
			System.out.println("Enter updaed address");
			String address = scan.next();
			ps.setString(4, address);
			System.out.println("Enter Updated DOB like yyyy-MM-DD");
			String date = scan.next();
			ps.setDate(5, Date.valueOf(date));
			System.out.println("Enter updated mobileNumber");
			String mbNo = scan.next();
			ps.setString(6, mbNo);
			ps.setInt(7, accountNumber);
			int result= ps.executeUpdate();
			if (result!=0) {
				System.out.println("details updated sucessfully");
			} else {
				System.out.println("Details not updated due to invalid data");
			}
			}
			else {
				System.out.println("Invalid account Number");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUserDetails(int accountNumber) {
		
		try {
			Connection connect=	DriverManager.getConnection(url);
			PreparedStatement ps =	connect.prepareStatement(delete);
			ps.setInt(1, accountNumber);
			
			int result = ps.executeUpdate();
			if (result!=0) {
				System.out.println("Account deleted");
			} else {
				System.out.println("No data found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
