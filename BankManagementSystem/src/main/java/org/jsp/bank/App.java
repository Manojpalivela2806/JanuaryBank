package org.jsp.bank;

import java.time.LocalDate;
import java.util.Scanner;

import org.jsp.bank.dao.AdminDAO;
import org.jsp.bank.dao.AdminHelperClass;
import org.jsp.bank.dao.UserDAO;
import org.jsp.bank.dao.UserHelperClass;
import org.jsp.bank.model.BankUserDetails;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserDAO userdao = UserHelperClass.UserHelperMethod();
        AdminDAO admindao = AdminHelperClass.AdminHelperMethod();
        Scanner scan = new Scanner(System.in);
        
//        userdao.debit(2753344, 2767);
      
        
        boolean welcomStatus = true;
        while(welcomStatus)
      {
        System.out.println("Enter \n 1.for Admin login \n 2.For User login");
        
        
        int welcome = scan.nextInt();
        
        switch(welcome)
        {
        
        	case 1: 
        	int	count=1;
        	boolean run= true;
        	
        	while(count<=3 && run) {
        	System.out.println("Enter admin emailid");
//        	adminteca53@gmail.com
        	String email = scan.next();
        	System.out.println("enter admin password");
//        	teca53
        	String password = scan.next();
        		if (admindao.adminlogin(email,password)) {
        			System.out.println("login sucessfull");
        			System.out.println(" Enter \n 1.For User Registration"
        								   + " \n 2.For to select All User Details"
        								   + " \n 3.For Update Details"
        								   + " \n 4.For delete user Details");		
        			int adminopt =scan.nextInt();
        			 			
        			switch(adminopt) 
        			{
        			case 1: BankUserDetails userDetails = new BankUserDetails();
        			System.out.println("Enter your Name ");
        			String name = scan.next();
        			userDetails.setUsername(name);
        			System.out.println("enter your EmailId ");
        			String emailid = scan.next();
        			userDetails.setUseremailid(emailid);
        			System.out.println("Enter your Gender");
        			String gender=scan.next();
        			userDetails.setGender(gender);
        			System.out.println("Enter your address");
        			String address = scan.next();
        			userDetails.setAddress(address);
        			System.out.println("Enter your DateOfBirth like yyyy-MM-DD");
        			String date = scan.next();
        			userDetails.setUserdateofbirth(LocalDate.parse(date));
//        			while running the code date has to be given in american format like  : yyyy-mm-dd on run time.
        			System.out.println("Enter your Amount");
        			Double amount = scan.nextDouble();
        			userDetails.setUseramount(amount);
        			System.out.println("Enter your MobileNumber");
        			String mobileNumber=scan.next();
        			userDetails.setUsermobilenumber(mobileNumber);
//        				admindao.userRegistration();
        			admindao.userRegistration(userDetails);
        			break;
        			
        			case 2: admindao.selectingAllUserDetails();
        			break;
        			
        			case 3:System.out.println("Enter Account no which you want to update");
        			int accountNo=scan.nextInt();
        				admindao.updateDetails(accountNo);
        			break;
  
        			case 4:
        				System.out.println("Enter user account number");
        				int accountnumber = scan.nextInt();
        				if(accountnumber>=1000000){
        				System.out.println("Do you want to delete account? "
        						+ "\n yes "
        						+ "\n no ");
        				String delopt = scan.next();
        				if (delopt.equalsIgnoreCase("yes")) {
        					admindao.deleteUserDetails(accountnumber);
						} else {
							System.out.println("account not deleted");
						}
        				}
        				else {System.out.println("Enter valid account number");}
        				
        				break;
        			
        			default:System.out.println("options are upto 4 you gave wrong input");
        			}
        			
        			count=4;
        			
                	} else {
                		System.out.println("invalid credentials");
                		count++;
                	}	
        		System.out.println("***---***---***----****");
        	welcomStatus = false;
        		}
        	break;
        
        
        	case 2: System.out.println("Enter user bank email id");
        	String bankemailid = scan.next();
        	System.out.println("Enter user password");
        	int password = scan.nextInt();
        		if (userdao.userlogin(bankemailid, password)) {
					System.out.println("Enter "
							+ "\n 1. For Balance Enquiry"
							+ "\n 2. For Withdraw "
							+ "\n 3. For Credit "
							+ "\n 4. For Change Password "
							+ "\n 5. For check Statement "
							+ "\n 6. For Mobile To Mobile Transation");
					int useroption = scan.nextInt();
					switch (useroption) {
					case 1:System.out.println("Enter your account number");
							int accountNo=scan.nextInt();
							System.out.println("Enter your password");
							int userpassword = scan.nextInt();
							userdao.balanceenquiry(accountNo, userpassword);
//						System.out.println("This is for balance enquiry ");
						break;
					case 2:System.out.println("Enter your account number");
					       int accountNumber = scan.nextInt();
					       System.out.println("Enter your password");
					       int accountpassword=scan.nextInt();
					       userdao.debit(accountNumber, accountpassword);
//						System.out.println("this is for withdraw");
						break;
					case 3: System.out.println("Enter your account number");
							int accountNumber1 = scan.nextInt();
							System.out.println("Enter your password");
							int accountpassword1=scan.nextInt(); 
							userdao.credit(accountNumber1, accountpassword1);
//						System.out.println("This is for credit");
						break;
					case 4:System.out.println("Enter your accountNumber");
							int accno=scan.nextInt();
					       System.out.println("Enter your mobile number");
					       String mbNo = scan.next();
					       userdao.changePassword(accno, mbNo);
//						System.out.println("This is for changing password");
						break;
					case 5:System.out.println("Enter your account number: ");
					int bankAccountNumber = scan.nextInt();
						userdao.checkStatement(bankAccountNumber);
//						System.out.println("This is for check statement");
						break;
					case 6:System.out.println("Enter your mobile number"); 
							String mobileNumber = scan.next();
							userdao.mobileToMobileTransaction(mobileNumber);
//						System.out.println("This is for mobile to mobile transaction");
						break;
					default:
					}
				}
        		else {
        			System.out.println("invalid credentials");
				}
        	welcomStatus = false;
        	break;
        
        
        	default : System.out.println("Enter valid choise");
        	welcomStatus = false;
        
        }
        boolean x= true;
       while(x)
       {
    	   System.out.println(" Do you want to contiue \n yes \n no" );
           String option = scan.next();
           if(option.equalsIgnoreCase("yes"))
           {
           	welcomStatus = true;
           	x=false;
           }
           else if(option.equalsIgnoreCase("no")) {
           	welcomStatus = false;
           	try {
           		x=false;
   				Thread.sleep(1500);
   				System.out.println("thank you visit again...!");
   			} catch (InterruptedException e) {
   				e.printStackTrace();
   			}
           }
           else {
           	welcomStatus = true;
           	System.out.println("you have to choose only yes or no");
           }
       }
      }
             
    }
}
