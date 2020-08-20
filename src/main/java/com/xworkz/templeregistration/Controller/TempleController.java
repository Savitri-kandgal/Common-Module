package com.xworkz.templeregistration.Controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.templeregistration.dao.TempleDAOImpl;
import com.xworkz.templeregistration.dto.PropDTO;
import com.xworkz.templeregistration.dto.RegisterDTO;
import com.xworkz.templeregistration.service.TempleService;

@Controller
@RequestMapping("/")
public class TempleController {

	private static final Logger logger = Logger.getLogger(TempleDAOImpl.class);

	private List<PropDTO> seList;
	private List<PropDTO> idList;
	private List<PropDTO> ptList;
	private List<PropDTO> prList;
	private int attempts = 3;
	
	@Autowired
	private TempleService service;

	public TempleController() {
		logger.info("LOGGER : CONTROLLER :" + this.getClass().getSimpleName() + "object created");
	}

	@PostConstruct
	public void init() {
		logger.info("LOGGER : CONTROLLER : init() invoked");
		logger.info("LOGGER : CONTROLLER : calling service class methods");
		seList = service.validateAndFetchByType("SE");
		idList = service.validateAndFetchByType("ID");
		ptList = service.validateAndFetchByType("PT");
		prList = service.validateAndFetchByType("PR");
		logger.info("LOGGER : CONTROLLER : init() ending");
	}

	@RequestMapping(value = "/loading.do", method = RequestMethod.GET)
	public String onLoading(Model model) {
		try {
			logger.info("LOGGER : CONTROLLER :-------------STARTS : onLoading()--------------------");

			model.addAttribute("seLt", seList);
			logger.info(
					"LOGGER : CONTROLLER : onLoading() seList sending to Special Entrance field of registration page :"
							+ seList);
			model.addAttribute("idLt", idList);
			logger.info("LOGGER : CONTROLLER : onLoading() idList sending to Id Card field of registration page :"
					+ idList);
			model.addAttribute("ptLt", ptList);
			logger.info("LOGGER : CONTROLLER : onLoading() ptList sending to pooja type field of registration page :"
					+ ptList);
			model.addAttribute("prLt", prList);
			logger.info("LOGGER : CONTROLLER : onLoading() prList sending to Prasadha field of registration page :"
					+ prList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("LOGGER : CONTROLLER : returning to the Registration page");
		logger.info("LOGGER : CONTROLLER :-------------ENDS : onLoading()--------------------");
		return "Registration";
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String onSaving(RegisterDTO dto, Model model) {
		logger.info("LOGGER : CONTROLLER :-------------STARTS : onSaving()--------------------");
		logger.info("LOGGER : CONTROLLER :printing dto");
		logger.info(dto);
		try {
			logger.info("LOGGER : CONTROLLER :calling service.validateAndCreate()");
			int status = this.service.validateAndCreate(dto);

			logger.info(
					"LOGGER : CONTROLLER :printing status from controller after executing service.validateAndCreate(dto) :"
							+ status);
			if (status == 0) {
				logger.info("LOGGER : CONTROLLER :saving data to RegSuccess page");

				model.addAttribute("dto", dto);
				logger.info("LOGGER : CONTROLLER :-------------ENDS : onSaving()--------------------");
				return "RegSuccess";
			} else {
				logger.info(
						"LOGGER : CONTROLLER :Fields are missing or Email id is already exist, Please enter valid details");
				logger.info("LOGGER : CONTROLLER :-------------ENDS : onSaving()--------------------");
				return "error";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.addAttribute("error", "Please enter missing fields or unique email address");

		}
		logger.info("LOGGER : CONTROLLER :-------------ENDS : onSaving()--------------------");
		return "Registration";

	}

	@RequestMapping(value = "/resendingmail.do", method = RequestMethod.POST)
	public String onResendingMail(@RequestParam String email, Model model) {
		logger.info("LOGGER : CONTROLLER :-------------STARTS : onResendingMail()--------------------");
		try {
			logger.info("LOGGER : CONTROLLER :value of @RequestParam String email :" + email);
			int result = service.resendingEmail(email);
			if (result == 1) {
				model.addAttribute("resent", "Mail sent successfully to your email address :" + email);
				logger.info("LOGGER : CONTROLLER :-------------ENDS : onResendingMail()--------------------");
				return "ResendSuccess";
			} else {
				model.addAttribute("error",
						"Data not available for the given email address, Please enter the valid email address");
				logger.info("LOGGER : CONTROLLER :-------------ENDS : onResendingMail()--------------------");
				return "ResendingMail";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("LOGGER : CONTROLLER :-------------ENDS : onResendingMail()--------------------");
		return "ResendingMail";
	}

	@RequestMapping(value = "/generatePassword.do", method = RequestMethod.POST)
	public String onGeneratePassword(@RequestParam String email, Model model) {
		logger.info("LOGGER : CONTROLLER :-------------STARTS : onGeneratePassword()--------------------");
		try {
			int status = service.validateAndSavingPasswordByEmail(email);
			if (status == 0) {
				model.addAttribute("success", "Password sent successfully to your email id for the first time login.");
				logger.info("LOGGER : CONTROLLER :-------------ENDS : onGeneratePassword()--------------------");
				return "PasswordStatus";
			} else {
				model.addAttribute("error", "Not first time login, Kindly login with password or reset the password");
				logger.info("LOGGER : CONTROLLER :-------------ENDS : onGeneratePassword()--------------------");
				return "PasswordStatus";
			} // model.addAttribute("error", "Email id is invalid, Please enter the correct
				// email id");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("LOGGER : CONTROLLER :-------------ENDS : onGeneratePassword()--------------------");
		return email;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String onLogin(@RequestParam String email, @RequestParam String pswd, HttpSession session, Model model) {
		logger.info("LOGGER : CONTROLLER :-------------STARTS : onLogin()--------------------");

		try {
			boolean status = service.checkEmailAndPswdFields(email, pswd);
			if (status != true) {
				Object dto = service.validateAndLogin(email, pswd);
				if (dto != null) {
					session.setAttribute("EMAILID", email);
					logger.info("LOGGER : CONTROLLER :saving data to RegSuccess page");

					model.addAttribute("dto", dto);
					logger.info("LOGGER : CONTROLLER :-------------ENDS : onLogin()--------------------");
					return "LoginSuccess";
				} else {
					boolean acctLock = service.validateAndAcctLockByEmailId(email, pswd);
					logger.info("LOGGER : CONTROLLER :checking acctLock status");
					if (acctLock != true) {
						logger.info("LOGGER : CONTROLLER :acctLock status :" + acctLock);
						model.addAttribute("error",
								"invalid email id or password, please enter valid details, left with " + --attempts
										+ " attempts");
						logger.info("LOGGER : CONTROLLER :-------------ENDS : onLogin()--------------------");
						return "Login";

					} else {
						logger.info("LOGGER : CONTROLLER :checking acctLock status :" + acctLock);
						logger.info("LOGGER : CONTROLLER :Account has been locked after 3 incorrect attempts");
						model.addAttribute("error1", "Your account has been locked, please reset your password");
						logger.info("LOGGER : CONTROLLER :-------------ENDS : onLogin()--------------------");
						return "Login";
					}
				}

			} else {
				model.addAttribute("error", "Missing email id or password, please enter valid details");
				logger.info("LOGGER : CONTROLLER :-------------ENDS : onLogin()--------------------");
				return "Login";
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("LOGGER : CONTROLLER :-------------ENDS : onLogin()--------------------");
		return email;

	}

	@RequestMapping(value = "/resetPssword.do", method = RequestMethod.POST)
	public String onResettingPassword(@RequestParam String email, Model model) {
		logger.info("LOGGER : CONTROLLER :-------------STARTS : onResettingPassword()--------------------");
		try {
			int status = service.validateAndResetPasswordByEmail(email);
			if (status == 0) {
				model.addAttribute("success", "New Password sent successfully to your email id.");
				logger.info("LOGGER : CONTROLLER :-------------ENDS : onResettingPassword()--------------------");
				return "PasswordStatus";
			} else {
				model.addAttribute("error", "Email id is invalid, Please enter the correct email id");
				logger.info("LOGGER : CONTROLLER :-------------ENDS : onResettingPassword()--------------------");
				return "PasswordStatus";
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("LOGGER : CONTROLLER :-------------ENDS : onResettingPassword()--------------------");
		return email;
	}

	@RequestMapping(value = "/loadingforbook.do", method = RequestMethod.GET)
	public String onLoadingForBook(Model model) {
		try {
			logger.info("LOGGER : CONTROLLER :-------------STARTS : onLoading()--------------------");

			model.addAttribute("seLt", seList);
			logger.info(
					"LOGGER : CONTROLLER : onLoading() seList sending to Special Entrance field of registration page :"
							+ seList);
			model.addAttribute("idLt", idList);
			logger.info("LOGGER : CONTROLLER : onLoading() idList sending to Id Card field of registration page :"
					+ idList);
			model.addAttribute("ptLt", ptList);
			logger.info("LOGGER : CONTROLLER : onLoading() ptList sending to pooja type field of registration page :"
					+ ptList);
			model.addAttribute("prLt", prList);
			logger.info("LOGGER : CONTROLLER : onLoading() prList sending to Prasadha field of registration page :"
					+ prList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("LOGGER : CONTROLLER : returning to the Registration page");
		logger.info("LOGGER : CONTROLLER :-------------ENDS : onLoading()--------------------");
		return "Booking";
	}

	@RequestMapping(value = "/bookingVisit.do", method = RequestMethod.POST)
	public String onBookingVisit(RegisterDTO dto, Model model) {
		logger.info("LOGGER : CONTROLLER :-------------STARTS : onBookingVisit()--------------------");
		logger.info("LOGGER : CONTROLLER :printing dto");
		logger.info(dto);
		try {
			logger.info("LOGGER : CONTROLLER :calling service.validateAndCreate()");
			int status = this.service.validateAndBookVisit(dto);

			logger.info(
					"LOGGER : CONTROLLER :printing status from controller after executing service.validateAndBookVisit(dto) :"
							+ status);
			if (status == 0) {
				logger.info("LOGGER : CONTROLLER :saving data to bookingSuccess.jsp page");

				model.addAttribute("dto", dto);
				logger.info("LOGGER : CONTROLLER :-------------ENDS : onBookingVisit()--------------------");
				return "bookingSuccess";
			} else {
				logger.info("LOGGER : CONTROLLER :Fields are missing, Please enter valid details");
				logger.info("LOGGER : CONTROLLER :-------------ENDS : onBookingVisit()--------------------");
				return "error";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.addAttribute("error", "Please enter missing fields");

		}
		logger.info("LOGGER : CONTROLLER :-------------ENDS : onBookingVisit()--------------------");
		return "bookingSuccess";

	}

}
