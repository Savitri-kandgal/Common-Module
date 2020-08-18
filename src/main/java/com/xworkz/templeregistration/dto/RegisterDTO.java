package com.xworkz.templeregistration.dto;

import java.io.Serializable;
import org.apache.log4j.Logger;

public class RegisterDTO implements Serializable{
		private static final long serialVersionUID = 1L;

		private static final Logger logger = Logger.getLogger(RegisterDTO.class);
		
		private String name;
		private String age;
		private String address;
		private String phoneNumber;
		private String emailAddress;
		private String noOfPeople;
		private String date;
		private String seLt;
		private String prLt;
		private String ptLt;
		private String idLt;
		private String idNumber;
		//private String password;
		//private int loginAttempts;
		
		public RegisterDTO() {
			logger.info("LOGGER :" + this.getClass().getSimpleName() + " object created");
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getEmailAddress() {
			return emailAddress;
		}
		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}
		public String getNoOfPeople() {
			return noOfPeople;
		}
		public void setNoOfPeople(String noOfPeople) {
			this.noOfPeople = noOfPeople;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getSeLt() {
			return seLt;
		}
		public void setSeLt(String seLt) {
			this.seLt = seLt;
		}
		public String getPrLt() {
			return prLt;
		}
		public void setPrLt(String prLt) {
			this.prLt = prLt;
		}
		public String getPtLt() {
			return ptLt;
		}
		public void setPtLt(String ptLt) {
			this.ptLt = ptLt;
		}
		public String getIdLt() {
			return idLt;
		}
		public void setIdLt(String idLt) {
			this.idLt = idLt;
		}
		public String getIdNumber() {
			return idNumber;
		}
		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}

		/*
		 * public String getPassword() { return password; } public void
		 * setPassword(String password) { this.password = password; } public int
		 * getLoginAttempts() { return loginAttempts; } public void setLoginAttempts(int
		 * loginAttempts) { this.loginAttempts = loginAttempts; }
		 */
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public static Logger getLogger() {
			return logger;
		}
		@Override
		public String toString() {
			return "RegisterDTO [name=" + name + ", age=" + age + ", address=" + address + ", phoneNumber=" + phoneNumber
					+ ", emailAddress=" + emailAddress + ", noOfPeople=" + noOfPeople + ", date=" + date + ", seLt=" + seLt
					+ ", prLt=" + prLt + ", ptLt=" + ptLt + ", idLt=" + idLt + ", idNumber=" + idNumber +"]";
		}
}
