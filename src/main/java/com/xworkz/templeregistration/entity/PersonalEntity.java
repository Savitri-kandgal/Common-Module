package com.xworkz.templeregistration.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name = "personal_info")
@NamedQueries({
		@NamedQuery(name = "fetchCountByEmail", query = "select count(*) from PersonalEntity where emailAddress=:EMAIL_ADDRESS"),
		@NamedQuery(name = "fetchCountByphone", query = "select count(*) from PersonalEntity where phoneNumber=:CONTACT_NUMBER"),
		@NamedQuery(name = "fetchIdByEmail", query = "select pEntity.pId from PersonalEntity pEntity where emailAddress=:EMAIL_ADDRESS"),
		@NamedQuery(name = "fetchPswdByEmailId", query = "select pEntity.password from PersonalEntity pEntity where emailAddress=:EMAIL_ADDRESS"),
		@NamedQuery(name = "savingPasswordByEmail", query = "update PersonalEntity set password=:RANDOM_PASSWORD where emailAddress=:EMAIL_ADDRESS"),
		@NamedQuery(name = "updateLoginAttemptsByEmail", query = "update PersonalEntity set loginAttempts=:LOGIN_ATTEMPTS where emailAddress=:EMAIL_ADDRESS"),
		@NamedQuery(name = "fetchLoginAttemptsByEmail", query = "select pEntity.loginAttempts from PersonalEntity pEntity where emailAddress=:EMAIL_ADDRESS"),
		@NamedQuery(name = "updateAcctLockByEmail", query = "update PersonalEntity set isAcctLocked=:ACCOUNT_LOCKED where emailAddress=:EMAIL_ADDRESS"),
		@NamedQuery(name = "fetchAcctLockByEmail", query = "select pEntity.isAcctLocked from PersonalEntity pEntity where emailAddress=:EMAIL_ADDRESS"),
		@NamedQuery(name = "updateLoginAttemptAndAcctLockbyEmailId", query = "update PersonalEntity set loginAttempts=:LOGIN_ATTEMPTS, isAcctLocked=:ACCOUNT_LOCKED where emailAddress=:EMAIL_ADDRESS"),
		@NamedQuery(name = "fetchPersonalEntitybyEmail", query = "select pEntity from PersonalEntity pEntity where emailAddress=:EMAIL_ADDRESS") })
public class PersonalEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(PersonalEntity.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @GeneratedValue
	@Column(name = "P_ID")
	private int pId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "AGE")
	private String age;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "CONTACT_NUMBER")
	private String phoneNumber;
	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;
	@Column(name = "RANDOM_PASSWORD")
	private String password;
	@Column(name = "LOGIN_ATTEMPTS")
	private int loginAttempts;
	@Column(name = "ACCOUNT_LOCKED")
	private boolean isAcctLocked;

	//@JoinColumn(name = "P_ID")
	// @OneToOne(cascade = CascadeType.ALL)
	
	@OneToMany(mappedBy = "pEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<VisitingEntity> visitingEntity = new ArrayList<VisitingEntity>();

	public PersonalEntity() {
		logger.info("LOGGER: " + this.getClass().getSimpleName() + "obj created");
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Logger getLogger() {
		return logger;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<VisitingEntity> getVisitingEntity() {
		return visitingEntity;
	}

	public void setVisitingEntity(List<VisitingEntity> visitingEntity) {
		this.visitingEntity = visitingEntity;
	}

	public int getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public boolean isAcctLocked() {
		return isAcctLocked;
	}

	public void setAcctLocked(boolean isAcctLocked) {
		this.isAcctLocked = isAcctLocked;
	}

	@Override
	public String toString() {
		return "PersonalEntity [pId=" + pId + ", name=" + name + ", age=" + age + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", emailAddress=" + emailAddress + ", password=" + password
				+ ", loginAttempts=" + loginAttempts + ", isAcctLocked=" + isAcctLocked + "]";
	}

}
