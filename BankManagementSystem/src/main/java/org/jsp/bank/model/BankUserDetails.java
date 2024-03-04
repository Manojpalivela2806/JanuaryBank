package org.jsp.bank.model;

import java.time.LocalDate;

public class BankUserDetails {
	private int userid;
	private String username;
	private String userbankemailid;
	private String useremailid;
	private int userpassword;
	private String gender;
	private String address;
	private LocalDate userdateofbirth;
	private int useraccountnumber;
	private double useramount;
	private String usermobilenumber;
	private String ifsccode;
	
	public BankUserDetails() {
		
	}

	public BankUserDetails(int userid, String username, String userbankemailid, String useremailid, int userpassword,
			String gender, String address, LocalDate userdateofbirth, int useraccountnumber, double useramount,
			String usermobilenumber, String ifsccode) {
		super();
		this.userid = userid;
		this.username = username;
		this.userbankemailid = userbankemailid;
		this.useremailid = useremailid;
		this.userpassword = userpassword;
		this.gender = gender;
		this.address = address;
		this.userdateofbirth = userdateofbirth;
		this.useraccountnumber = useraccountnumber;
		this.useramount = useramount;
		this.usermobilenumber = usermobilenumber;
		this.ifsccode = ifsccode;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserbankemailid() {
		return userbankemailid;
	}

	public void setUserbankemailid(String userbankemailid) {
		this.userbankemailid = userbankemailid;
	}

	public String getUseremailid() {
		return useremailid;
	}

	public void setUseremailid(String useremailid) {
		this.useremailid = useremailid;
	}

	public int getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(int userpassword) {
		this.userpassword = userpassword;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getUserdateofbirth() {
		return userdateofbirth;
	}

	public void setUserdateofbirth(LocalDate userdateofbirth) {
		this.userdateofbirth = userdateofbirth;
	}

	public int getUseraccountnumber() {
		return useraccountnumber;
	}

	public void setUseraccountnumber(int useraccountnumber) {
		this.useraccountnumber = useraccountnumber;
	}

	public double getUseramount() {
		return useramount;
	}

	public void setUseramount(double useramount) {
		this.useramount = useramount;
	}

	public String getUsermobilenumber() {
		return usermobilenumber;
	}

	public void setUsermobilenumber(String usermobilenumber) {
		this.usermobilenumber = usermobilenumber;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}
	
	
	

}
