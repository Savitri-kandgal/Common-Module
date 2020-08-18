package com.xworkz.templeregistration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.xworkz.templeregistration.dao.TempleDAO;
import com.xworkz.templeregistration.dto.PropDTO;
import com.xworkz.templeregistration.dto.RegisterDTO;
import com.xworkz.templeregistration.entity.PersonalEntity;
import com.xworkz.templeregistration.entity.TempleEntity;
import com.xworkz.templeregistration.entity.VisitingEntity;

@Service
public class TempleServiceImpl implements TempleService {

	private static final Logger logger = Logger.getLogger(TempleServiceImpl.class);
	boolean acctLock = false;

	@Autowired
	public TempleDAO dao;

	@Autowired
	private MailSender mailSender;

	public TempleServiceImpl() {
		logger.info("LOGGER : SERVICE:" + this.getClass().getSimpleName() + " created");
	}

	@Override
	public List<PropDTO> validateAndFetchByType(String type) {

		logger.info("LOGGER : SERVICE:------------ validateAndFetchByType() invoked for type :" + type
				+ "-----------------");

		try {
			List<TempleEntity> entityList = null;
			logger.info("LOGGER : checking if type is valid ");
			if (Objects.nonNull(type) && !type.isEmpty()) {
				logger.info("LOGGER : type is valid");
				logger.info("LOGGER : list fetching from dao");
				entityList = dao.fetchByType(type);
				logger.info("LOGGER : checking if list is valid ");
				if (Objects.nonNull(entityList) && !entityList.isEmpty()) {
					logger.info("LOGGER : list is valid");
					logger.info("LOGGER : creating dto list for converting entity to dto list");
					List<PropDTO> dtoList = new ArrayList<PropDTO>();
					logger.info("LOGGER : printing dtoList before adding dto :" + dtoList);
					logger.info("LOGGER : itterating the entities list came from database");
					for (TempleEntity entity : entityList) {
						PropDTO dto = new PropDTO();
						logger.info("LOGGER : converting entity to dto");
						BeanUtils.copyProperties(entity, dto);
						dtoList.add(dto);
						logger.info("LOGGER : printing dto :" + dto);
						logger.info("LOGGER : printing dtoList before adding dto :" + dtoList);
					}
					logger.info("LOGGER : -----------------validateAndFetchByType() ending for type :" + type
							+ "--------------------");
					return dtoList;

				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		logger.info("LOGGER :" + this.getClass().getSimpleName() + " ends");
		return null;
	}

	@Override
	public int validateAndCreate(RegisterDTO dto) {
		logger.info("LOGGER : SERVICE:------------STARTS : validateAndCreate()-------------------------------");
		int valid = 1;
		try {
			if (Objects.nonNull(dto)) {
				logger.info("starting to validate fields");

				if (dto.getName() != null && !dto.getName().isEmpty()) {
					logger.info("LOGGER : name is valid :" + dto.getName());

					valid = 0;
				} else {
					logger.info("LOGGER : name is invalid :" + dto.getName());
					valid = 1;
				}

				if (valid == 0 && dto.getAge() != null && !dto.getAge().isEmpty()) {
					logger.info("LOGGER : age is valid :" + dto.getAge());

					valid = 0;
				} else {
					logger.info("LOGGER : age is invalid :" + dto.getAge());
					valid = 1;
				}

				if (valid == 0 && dto.getAddress() != null && !dto.getAddress().isEmpty()) {
					logger.info("LOGGER : address is valid :" + dto.getAddress());
					valid = 0;
				} else {
					logger.info("LOGGER : address is invalid :" + dto.getAddress());
					valid = 1;
				}

				if (valid == 0 && dto.getPhoneNumber() != null && !dto.getPhoneNumber().isEmpty()) {
					logger.info("LOGGER : phoneNumber is valid :" + dto.getPhoneNumber());
					valid = 0;
				} else {
					logger.info("LOGGER : phoneNumber is invalid :" + dto.getPhoneNumber());
					valid = 1;
				}
				if (valid == 0 && dto.getEmailAddress() != null && !dto.getEmailAddress().isEmpty()) {
					logger.info("LOGGER : emailAddress is valid :" + dto.getEmailAddress());
					valid = 0;
				} else {
					logger.info("LOGGER : emailAddress is invalid :" + dto.getEmailAddress());
					valid = 1;
				}
				if (valid == 0 && dto.getNoOfPeople() != null && !dto.getNoOfPeople().isEmpty()) {
					logger.info("LOGGER : noOfPeople is valid :" + dto.getNoOfPeople());
					valid = 0;
				} else {
					logger.info("LOGGER : noOfPeople is invalid :" + dto.getNoOfPeople());
					valid = 1;
				}
				if (valid == 0 && dto.getDate() != null && !dto.getDate().isEmpty()) {
					logger.info("LOGGER : date is valid :" + dto.getDate());
					valid = 0;
				} else {
					logger.info("LOGGER : date is invalid :" + dto.getDate());
					valid = 1;
				}
				if (valid == 0 && dto.getSeLt() != null && !dto.getSeLt().isEmpty()) {
					logger.info("LOGGER : SE type is valid :" + dto.getSeLt());
					valid = 0;
				} else {
					logger.info("LOGGER :  SE type is invalid :" + dto.getSeLt());
					valid = 1;
				}
				if (valid == 0 && dto.getPrLt() != null && !dto.getPrLt().isEmpty()) {
					logger.info("LOGGER : prashada type is valid :" + dto.getPrLt());
					valid = 0;
				} else {
					logger.info("LOGGER : prashada type is invalid :" + dto.getPrLt());
					valid = 1;
				}
				if (valid == 0 && dto.getPtLt() != null && !dto.getPtLt().isEmpty()) {
					logger.info("LOGGER : pooja type is valid :" + dto.getPtLt());
					valid = 0;
				} else {
					logger.info("LOGGER : pooja type is invalid :" + dto.getPtLt());
					valid = 1;
				}
				if (valid == 0 && dto.getIdLt() != null && !dto.getIdLt().isEmpty()) {
					logger.info("LOGGER : id type is valid :" + dto.getIdLt());
					valid = 0;
				} else {
					logger.info("LOGGER : id type is invalid :" + dto.getIdLt());
					valid = 1;
				}

			}
			logger.info("LOGGER : calling dao.fetchCountByEmail() to fetching the count of email address");
			long emailCt = dao.fetchCountByEmail(dto.getEmailAddress());
			// long phoneCount = dao.fetchCountByphone(dto.getPhoneNumber());

			logger.info("LOGGER : Checking if all fields are entered");
			if (valid == 0) {
				logger.info("LOGGER : informations are valid :" + valid);
				logger.info("LOGGER : Checking if email id is already exist in the database");
				if (emailCt == 0) {
					logger.info("LOGGER : Email id is not exist in the database, count :" + emailCt);
					// logger.info("LOGGER : printing phoneCount from service() :" + phoneCount);
					logger.info("LOGGER : data and email id are valid, can pass dto to DAO ");

					PersonalEntity pEntity = new PersonalEntity();
					VisitingEntity vEntity = new VisitingEntity();
					logger.info("LOGGER : before copying data from dto to Personal entity :" + pEntity);
					logger.info("LOGGER : before copying data from dto to visiting entity :" + vEntity);
					logger.info("LOGGER : copying data from dto to visiting entity");
					BeanUtils.copyProperties(dto, pEntity);
					BeanUtils.copyProperties(dto, vEntity);
					logger.info("LOGGER : after copying data from dto to Personal entity :" + pEntity);
					logger.info("LOGGER : after copying data from dto to visiting entity :" + vEntity);
					logger.info("LOGGER : passing the pEntity to the setpEntity()");
					vEntity.setpEntity(pEntity);
					logger.info("LOGGER : calling create() to save vEntity");
					dao.create(vEntity);
					logger.info("LOGGER : sending registered details to the user mail");
					sendRegSuccEmail(dto.getEmailAddress(), dto);
					logger.info(
							"LOGGER : SERVICE:------------ENDS : validateAndCreate()-------------------------------");
					return 0;
				} else {
					logger.info("LOGGER : email id is already exist in the database");
					return 1;
				}
			} else {
				logger.info("LOGGER : fields are missing, please enter the missing fields");
				return 1;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("LOGGER : SERVICE:------------ENDS : validateAndCreate()-------------------------------");
		return valid;

	}

	@Override
	public int sendRegSuccEmail(String toMail, RegisterDTO dto) {
		logger.info("LOGGER : SERVICE:-------------------STARTS : sendRegSuccEmail()----------------------");
		int status = 1;
		try {
			if (Objects.nonNull(dto)) {
				status = 0;
				logger.info("dto object is  not null");
				if (Objects.nonNull(toMail) && !toMail.isEmpty() && status == 0) {
					status = 0;
					logger.info("Email id is valid");
				} else {
					status = 1;
					logger.warn("Email id is invalid");
				}

			} else {
				status = 1;
				logger.warn("dto object is null");
			}

			if (status == 0) {
				SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				simpleMailMessage.setTo(toMail);
				StringBuilder str = new StringBuilder();
				str.append("Congratulation, Registration successful \r\n");
				str.append("\r\n");
				str.append("Visiting date :" + dto.getDate() + "\r\n");
				str.append("Special Entry :" + dto.getSeLt() + "\r\n");
				str.append("Pooja Type 	  :" + dto.getPtLt() + "\r\n");
				str.append("Prasadha 	  :" + dto.getPrLt() + "\r\n");
				str.append("ID card 	  :" + dto.getIdLt() + "\r\n");
				str.append("ID number     :" + dto.getIdNumber() + "\r\n");
				simpleMailMessage.setText(str.toString());
				simpleMailMessage.setSubject("Congratulations, Temple Visiting Registration Successfull");

				logger.info("sending an email :");
				mailSender.send(simpleMailMessage);
				logger.info("sent an email :");
				logger.info("End : sendMail()");
				logger.info("LOGGER : SERVICE:-------------------ENDS : sendRegSuccEmail()----------------------");
				return status;

			} else {
				return status;
			}
		} catch (Exception e) {
			logger.error("Some thing went wrong in Service", e);
		}
		logger.info("LOGGER : SERVICE:-------------------ENDS : sendRegSuccEmail()----------------------");
		return 0;
	}

	@Override
	public int resendingEmail(String toMail) {
		logger.info("LOGGER : SERVICE:-------------------STARTS : resendingEmail()----------------------");
		long emailCt = 0;
		try {
			emailCt = dao.fetchCountByEmail(toMail);
			if (emailCt == 1) {
				RegisterDTO dto = validateAndFetchDetailsByEmail(toMail);
				int status = sendRegSuccEmail(toMail, dto);
				if (status == 0) {
					logger.info("LOGGER : mail has been resent successfully");
					logger.info("LOGGER : SERVICE:-------------------ENDS : sendRegSuccEmail()----------------------");
					return (int) emailCt;
				}

			} else {
				logger.info("LOGGER : duplicate email ids found i db");
				return (int) emailCt;
			}

		} catch (Exception e) {
			logger.error("Some thing went wrong whike resending mail", e);

		}
		logger.info("LOGGER : SERVICE:-------------------ENDS : resendingEmail()----------------------");
		return (int) emailCt;
	}

	public RegisterDTO validateAndFetchDetailsByEmail(String email) {

		logger.info("LOGGER : SERVICE:----------------STARTS : validateAndFetchDetailsByEmail()-------------------");

		try {
			RegisterDTO dto = null;
			VisitingEntity entity = null;
			logger.info("LOGGER : checking if email is valid ");
			if (Objects.nonNull(email) && !email.isEmpty()) {
				logger.info("LOGGER : email is valid :" + email);
				entity = dao.fetchDetailsByemail(email);
				logger.info("LOGGER : VisitingEntity fetched from dao :" + entity);
				logger.info("LOGGER : checking if entity is valid ");
				if (Objects.nonNull(entity)) {
					logger.info("LOGGER : entity is valid");
					dto = new RegisterDTO();
					BeanUtils.copyProperties(entity, dto);
					logger.info("LOGGER : converted VisitingEntity to RegisterDTO :" + dto.toString());
				}
				logger.info(
						"LOGGER : SERVICE:-------------------ENDS : validateAndFetchDetailsByEmail()----------------------");
				return dto;

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		logger.info("LOGGER : ----------------ENDS :validateAndFetchDetailsByEmail()--------------------");
		return null;
	}

	public int validateAndSavingPasswordByEmail(String email) {
		logger.info("LOGGER : ----------------STARTS :validateAndSavingPasswordByEmail()--------------------");
		int status = 1;

		try {
			logger.info("LOGGER : invoked ongeneratingPassword() :" + email);
			if (Objects.nonNull(email) && !email.isEmpty()) {
				logger.info("LOGGER : email is valid :" + email);
				long emailCt = dao.fetchCountByEmail(email);
				logger.info("LOGGER : fetched email id count from db :" + emailCt);
				if (emailCt > 0) {
					String dbPswd = dao.fetchPswdByEmailId(email);
					logger.info("LOGGER : cheking if random password already exist in the database :" + dbPswd);
					if (dbPswd.isEmpty()) {
						logger.info("LOGGER : ramdom password is not exist in the database, can generate pswd");
						status = 0;
						int pswdLength =10;
						char[] password = generatingRandomPassword(pswdLength);
						String Pswd = String.valueOf(password);
						logger.info("LOGGER : generated random password :" + Pswd);
						PswdProtect protect = new PswdProtectImpl();
						String encript = new String(protect.encript(Pswd.getBytes()));
						logger.info("LOGGER : encripted pswd:" + encript);
						int pswdSaveStatus = dao.savingPasswordByEmail(email, encript);
						logger.info("LOGGER : Password saved status :" + pswdSaveStatus);
						sendingPswdEmail(email, Pswd);
						logger.info(
								"LOGGER : ----------------ENDS :validateAndSavingPasswordByEmail()--------------------");
						return status;
					} else {
						logger.info("LOGGER : random password already exist in the database :" + email + ", " + dbPswd);
					}
				} else {
					logger.info("LOGGER : email id not fount in db :" + email + ":emailCt");
				}
			} else {
				logger.info("LOGGER : email id is invalid :" + email);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("LOGGER : ----------------ENDS :validateAndSavingPasswordByEmail()--------------------");
		return status;
	}

	public int sendingPswdEmail(String toMail, String password) {
		logger.info("LOGGER : ----------------STARTS :sendingPswdEmail()--------------------");
		int status = 1;
		try {
			logger.info("LOGGER : mail :" + toMail + ", password" + password);
			if (Objects.nonNull(toMail) && Objects.nonNull(password)) {
				status = 0;
				logger.info("LOGGER : Mail id and password are valid");
				SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				simpleMailMessage.setTo(toMail);
				simpleMailMessage.setText(
						"Please find your password to login for the first time :\r\n" + "Password :" + password);
				simpleMailMessage.setSubject("First time Login Password");

				logger.info("LOGGER : sending an email :");
				mailSender.send(simpleMailMessage);
				logger.info("LOGGER : sent an email :");
				logger.info("LOGGER : sendMail() ends");
				logger.info("LOGGER : ----------------ENDS :sendingPswdEmail()--------------------");
				return status;
			} 
			else {
				logger.warn("LOGGER : Email id or password id is invalid");
				return status;
			}
		} catch (Exception e) {
			logger.error("LOGGER : Some thing went wrong in Service", e);
		}
		logger.info("LOGGER : ----------------ENDS :sendingPswdEmail()--------------------");
		return 0;
	}

	public static char[] generatingRandomPassword(int len) {
		logger.info("LOGGER : ----------------STARTS :generatingRandomPassword()--------------------");

		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Small_chars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String symbols = "@#$%&*?";

		String values = Capital_chars + Small_chars + numbers + symbols;

		System.out.println("Generating password using random() : ");
		// Using random method
		Random rndm_method = new Random();

		char[] password = new char[len];

		for (int i = 0; i < len; i++) {

			password[i] = values.charAt(rndm_method.nextInt(values.length()));

		}
		logger.info("LOGGER : ----------------ENDS :generatingRandomPassword()--------------------");
		return password;
	}

	@Override
	public Object validateAndLogin(String email, String password) {
		logger.info("LOGGER : ----------------STARTS :validateAndLogin()--------------------");
		String decPswd = null;
		try {
			if (Objects.nonNull(email) && !email.isEmpty() && Objects.nonNull(password) && !password.isEmpty()) {
				logger.info("LOGGER : email and password is entered :" + email + ", " + password);
				long emailCt = dao.fetchCountByEmail(email);
				logger.info("LOGGER : fetched email id count from db :" + emailCt);
				if (emailCt > 0) {
					String encPswd = dao.fetchPswdByEmailId(email);
					logger.info("LOGGER : fetched encripted password from database :" + encPswd);
					PswdProtect protect = new PswdProtectImpl();
					decPswd = new String(protect.decript(encPswd.getBytes()));
					logger.info("LOGGER :Password has been decripted :" + decPswd);
					if (password.equals(decPswd)) {
						boolean acctLock = dao.fetchAcctLockByEmail(email);
						logger.info("LOGGER : checking if acct has been locked already :" + acctLock);
						if (acctLock != true) {
							logger.info("LOGGER : Account not locked :" + acctLock);
							int status = dao.updateLoginAttemptAndAcctLockbyEmailId(email);
							if (status == 1) {
								logger.info(
										"LOGGER : updated LoginAttempt And AcctLockbyEmailId fields for default values :0 and false");
								RegisterDTO dto = validateAndFetchDetailsByEmail(email);
								logger.info("LOGGER : fetched dto bases on email and pwsd :" + dto.toString());
								logger.info("LOGGER : returning dto");
								logger.info("LOGGER : ----------------ENDS :validateAndLogin()--------------------");
								return dto;

							} else {
								logger.info(
										"LOGGER :exception while updating login attempts and acct Lock field to default value");
							}
						} else {
							logger.info("LOGGER : account has already been locked, reset the password and login");
						}

					} else {
						logger.info("LOGGER : password is not matching ");
					}
				} else {
					logger.info("LOGGER : invalid email address:");

				}
			} else {
				logger.info("LOGGER : missing email id or password");
			}

		} catch (Exception e) {
			logger.error("LOGGER : Some thing went wrong in Service", e);
		}
		logger.info("LOGGER : ----------------ENDS :validateAndLogin()--------------------");
		return null;
	}

	@Override
	public boolean validateAndAcctLockByEmailId(String email, String password) {
		logger.info("LOGGER : ----------------STARTS :validateAndAcctLockByEmailId()--------------------");

		try {
			if (Objects.nonNull(email) && !email.isEmpty() && Objects.nonNull(password) && !password.isEmpty()) {
				boolean acctLockStatus = dao.fetchAcctLockByEmail(email);
				logger.info("LOGGER : checking if acct has been locked already :" + acctLockStatus);
				logger.info("LOGGER : email and password is entered :" + email + ", " + password);
				if (acctLockStatus != true) {
					dao.updateLoginAttemptByEmailId(email);
					int noOfAttempts = dao.fetchLoginAttemptByEmailId(email);
					logger.info("LOGGER : login attempt count :" + noOfAttempts);
					if (noOfAttempts >= 3) {
						int updatedRows1 = dao.updateAcctLockByEmailId(email);
						if (updatedRows1 > 0) {
							acctLockStatus = dao.fetchAcctLockByEmail(email);
							logger.info(
									"LOGGER : ----------------ENDS :validateAndAcctLockByEmailId()--------------------");
							return acctLockStatus;

						} else {
							logger.info(
									"LOGGER : error in service - no of rows updated for updateAcctLockByEmailId() is :"
											+ updatedRows1);
						}
					}
				} else {
					logger.info("LOGGER : returning acctLockStatus to controller :" + acctLockStatus);
					return acctLockStatus;
				}

			}
		} catch (Exception e) {
			logger.error("LOGGER : Some thing went wrong in Service", e);
		}
		logger.info("LOGGER : ----------------ENDS :validateAndAcctLockByEmailId()--------------------");
		return false;
	}

	@Override
	public boolean checkEmailAndPswdFields(String email, String password) {
		logger.info("LOGGER : ----------------STARTS :checkEmailAndPswdFields()--------------------");
		try {
			logger.info("LOGGER : checking either field is missing to enter");
			if (email.isEmpty() || password.isEmpty()) {
				logger.info("LOGGER : one or both fields are missing");
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("LOGGER : ----------------ENDS :checkEmailAndPswdFields()--------------------");
		return false;
	}

	@Override
	public int validateAndResetPasswordByEmail(String email) {
		logger.info("LOGGER : ----------------STARTS :validateAndupdateResetPasswordByEmail()--------------------");
		int status = 1;
		try {
			if (Objects.nonNull(email) && !email.isEmpty()) {
				logger.info("LOGGER : email is valid :" + email);
				long emailCt = dao.fetchCountByEmail(email);
				logger.info("LOGGER : fetched email id count from db :" + emailCt);
				if (emailCt > 0) {
					String dbPswd = dao.fetchPswdByEmailId(email);
					logger.info("LOGGER : cheking if member is already having a pswd in the database");
					if (!dbPswd.isEmpty()) {
						logger.info("LOGGER : Password exist in the database, can reset the pswd");
						status = 0;
						int pswdLength = 10;
						char[] password = generatingRandomPassword(pswdLength);
						String Pswd = String.valueOf(password);
						logger.info("LOGGER : generated random password :" + Pswd);
						PswdProtect protect = new PswdProtectImpl();
						String encript = new String(protect.encript(Pswd.getBytes()));
						logger.info("LOGGER : encripted pswd:" + encript);
						int pswdSaveStatus = dao.savingPasswordByEmail(email, encript);
						logger.info("LOGGER : Password saved status :" + pswdSaveStatus);
						int update = dao.updateLoginAttemptAndAcctLockbyEmailId(email);
						logger.info("LOGGER : updated LoginAttempt And AcctLockbyEmailId fields for default values :0 and false");
						logger.info("LOGGER : calling sendingResetPswdEmail() to send mail");
						sendingResetPswdEmail(email, Pswd);
						logger.info("LOGGER : ----------------ENDS :validateAndupdateResetPasswordByEmail()--------------------");
						return status;
					}
				} else {
					logger.info("LOGGER : email id not fount in db :" + email + ":emailCt");
				}
			} else {
				logger.info("LOGGER : email id is invalid :" + email);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("LOGGER : ----------------ENDS :validateAndupdateResetPasswordByEmail()--------------------");
		return 0;
	}

	public int sendingResetPswdEmail(String toMail, String password) {
		logger.info("LOGGER : ----------------STARTS :sendingResetPswdEmail()--------------------");
		int status = 1;
		try {
			logger.info("LOGGER : mail :" + toMail + ", password" + password);
			if (Objects.nonNull(toMail) && Objects.nonNull(password)) {
				status = 0;
				logger.info("LOGGER : Mail id and password are valid");
				SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				simpleMailMessage.setTo(toMail);
				simpleMailMessage
						.setText("Please find your reset new password for login :\r\n" + "Password :" + password);
				simpleMailMessage.setSubject("Temple Registration - New reset Password");

				logger.info("LOGGER : sending an email :");
				mailSender.send(simpleMailMessage);
				logger.info("LOGGER : sent email");
				logger.info("LOGGER : ----------------ENDS :sendingResetPswdEmail()--------------------");
				return status;

			} else {
				logger.warn("LOGGER : Email id or password is invalid");
				return status;

			}

		} catch (Exception e) {
			logger.error("LOGGER : Some thing went wrong in Service", e);
		}
		logger.info("LOGGER : ----------------ENDS :sendingPswdEmail()--------------------");
		return 0;
	}

}
