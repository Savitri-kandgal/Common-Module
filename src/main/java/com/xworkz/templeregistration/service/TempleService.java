package com.xworkz.templeregistration.service;

import java.util.List;

import com.xworkz.templeregistration.dto.PropDTO;
import com.xworkz.templeregistration.dto.RegisterDTO;
import com.xworkz.templeregistration.entity.PersonalEntity;
import com.xworkz.templeregistration.entity.VisitingEntity;

public interface TempleService {

	public List<PropDTO> validateAndFetchByType(String type);
	public int validateAndCreate(RegisterDTO dto);
	public int sendRegSuccEmail(String toMail, RegisterDTO dto);
	public int resendingEmail(String toMail);
	public RegisterDTO validateAndFetchDetailsByEmail(String email);
	public int validateAndSavingPasswordByEmail(String email);
	public int sendingPswdEmail(String toMail, String password);
	public String validateAndLogin(String email, String password);
	public boolean validateAndAcctLockByEmailId(String email, String password);
	public int validateAndResetPasswordByEmail(String email);
	public boolean checkEmailAndPswdFields(String email, String password);
	public int sendingResetPswdEmail(String toMail, String password);
	public int validateAndBookVisit(RegisterDTO dto);
	public int sendBookingInfoEmail(String toMail, RegisterDTO dto);
}

