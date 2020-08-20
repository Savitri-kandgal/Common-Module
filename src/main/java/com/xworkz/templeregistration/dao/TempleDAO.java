package com.xworkz.templeregistration.dao;

import java.util.List;

import com.xworkz.templeregistration.dto.RegisterDTO;
import com.xworkz.templeregistration.entity.PersonalEntity;
import com.xworkz.templeregistration.entity.TempleEntity;
import com.xworkz.templeregistration.entity.VisitingEntity;

public interface TempleDAO {

	
	public List<TempleEntity> fetchByType(String type);
	public void create(VisitingEntity vEntity);
	public long fetchCountByEmail(String email);
	public VisitingEntity fetchDetailsByemail(String email);
	public String fetchPswdByEmailId(String email);
	//public Object fetchLoginAttemptsByEmail(String email);
	public int savingPasswordByEmail(String emailId, String password);
	//public void savingLoginAttemptById(int id, int attempt);
	public int updateLoginAttemptByEmailId(String emailId);
	public int fetchLoginAttemptByEmailId(String emailId);
	public int updateAcctLockByEmailId(String emailId); 
	public boolean fetchAcctLockByEmail(String emailId);
	public int updateLoginAttemptAndAcctLockbyEmailId(String emailId);
	public PersonalEntity fetchPersonalEntitybyEmail(String email);
	public void saveBooking(VisitingEntity vEntity);
	}

