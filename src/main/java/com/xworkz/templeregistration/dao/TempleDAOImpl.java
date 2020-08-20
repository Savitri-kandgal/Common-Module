package com.xworkz.templeregistration.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xworkz.templeregistration.dto.RegisterDTO;
import com.xworkz.templeregistration.entity.PersonalEntity;
import com.xworkz.templeregistration.entity.TempleEntity;
import com.xworkz.templeregistration.entity.VisitingEntity;

@Component
public class TempleDAOImpl implements TempleDAO {

	private static final Logger logger = Logger.getLogger(TempleDAOImpl.class);

	private int count = 0;

	public TempleDAOImpl() {
		logger.info("LOGGER : TempleDAOImpl :" + this.getClass().getSimpleName() + "object created");
	}

	@Autowired
	private SessionFactory factory;

	@Override
	public List<TempleEntity> fetchByType(String type) {

		logger.info("LOGGER : TempleDAOImpl :----------------fetchByType() invoked---------------------");
		Session session = this.factory.openSession();
		logger.info("LOGGER : TempleDAOImpl : session opened");

		try {
			logger.info("LOGGER : TempleDAOImpl :getting named query from TemplateEntity class");
			Query query = session.getNamedQuery("fetchByType");
			logger.info("LOGGER : TempleDAOImpl :setting the value to the type valirable : prop_type - db attribute ");
			query.setParameter("prop_type", type); // named parameter

			logger.info("LOGGER : TempleDAOImpl : executing the query");
			List<TempleEntity> list = query.list();
			logger.info("LOGGER : TempleDAOImpl : got the db result in the form of list");
			for (TempleEntity entitylist : list) {
				System.out.println(entitylist);
			}
			logger.info("LOGGER : TempleDAOImpl : returning list to service");
			return list;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			session.close();
			logger.info("LOGGER : TempleDAOImpl : session is closed");
			logger.info("LOGGER : TempleDAOImpl :--------------- END: fetchByType()" + type+"------------------------");
		}
		

		return null;
	}

	@Override
	public long fetchCountByEmail(String email) {
		logger.info("LOGGER : TempleDAOImpl :--------------- fetchCountByEmail() invoked------------------");
		Session session = this.factory.openSession();
		logger.info("LOGGER : TempleDAOImpl : session opened");
		long mailCount = 0;
		try {
			logger.info("LOGGER : TempleDAOImpl : getting named query from PersonalEntity class");
			Query query = session.getNamedQuery("fetchCountByEmail");
			logger.info("LOGGER : TempleDAOImpl : setting the value to the emailAddress varirable : EMAIL_ADDRESS");
			query.setParameter("EMAIL_ADDRESS", email); // named parameter
			logger.info("LOGGER : TempleDAOImpl : executing the query");
			Object result = query.uniqueResult();
			logger.info("LOGGER : TempleDAOImpl : got the db result in the form of obj");
			logger.info("LOGGER : TempleDAOImpl : printing query result :" + result);
			mailCount = (long) result;
			logger.info("LOGGER : TempleDAOImpl : printing mailCount :" + mailCount);
			logger.info("LOGGER : TempleDAOImpl : returning mail count to service");
			return mailCount;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			session.close();
			logger.info("LOGGER : TempleDAOImpl : session is closed");
			logger.info("LOGGER : TempleDAOImpl :-------------END: fetchCountByEmail() from dao" + email+"--------------------");
		}
		
		return mailCount;

	}

	@Override
	public VisitingEntity fetchDetailsByemail(String email) {

		logger.info("LOGGER : TempleDAOImpl :------------------ fetchDetailsByemail() invoked----------------------");
		Session session = this.factory.openSession();
		logger.info("LOGGER : TempleDAOImpl : session opened");

		try {
			logger.info("LOGGER : TempleDAOImpl : getting named query from VisitingEntity class");
			Query query = session.getNamedQuery("fetchDetailsByemail");
			logger.info("LOGGER : TempleDAOImpl : setting the value to the emailAddress varirable : EMAIL_ADDRESS ");
			query.setParameter("EMAIL_ADDRESS", email); // named parameter

			logger.info("LOGGER : TempleDAOImpl : executing the query");
			Object result = query.uniqueResult();
			logger.info("LOGGER : TempleDAOImpl : got the db result in the form of obj");
			VisitingEntity entity = (VisitingEntity) result;

			logger.info("LOGGER : TempleDAOImpl : converted db result to VisitingEntity :" + entity);
			logger.info("LOGGER : TempleDAOImpl : returning entity to service");
			return entity;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			session.close();
			logger.info("LOGGER : TempleDAOImpl : returning entity to service");
			logger.info("END: TempleDAOImpl :----------------- fetchDetailsByemail()----------------------");
		}
		

		return null;
	}

	@Override
	public void create(VisitingEntity vEntity) {
		logger.info("LOGGER : TempleDAOImpl :---------------- invoking create()----------------------");
		Session session = null;

		try {
			session = this.factory.openSession();
			logger.info("LOGGER : TempleDAOImpl : session opened");
			session.beginTransaction();
			logger.info("LOGGER: TempleDAOImpl : transaction began");
			logger.info("LOGGER: TempleDAOImpl : saving entity to db");
			session.save(vEntity);
			session.getTransaction().commit();
			logger.info("LOGGER: TempleDAOImpl : entity saved in db");
		} catch (Exception e) {
			logger.error("LOGGER: TempleDAOImpl : Failed to save entity into DB");
			logger.error(e.getMessage(), e);

			session.getTransaction().rollback();
		} finally {
			session.close();
			logger.info("END: TempleDAOImpl :----------------- create()----------------------");
		}

	}
	
	@Override
	public void saveBooking(VisitingEntity vEntity) {
		logger.info("LOGGER : TempleDAOImpl :---------------- invoking saveBooking()----------------------");
		Session session = null;

		try {
			session = this.factory.openSession();
			logger.info("LOGGER : TempleDAOImpl : session opened");
			session.beginTransaction();
			logger.info("LOGGER: TempleDAOImpl : transaction began");
			logger.info("LOGGER: TempleDAOImpl : saving entity to db");
			//vEntity.setvId(id);
			session.save(vEntity);
			session.getTransaction().commit();
			logger.info("LOGGER: TempleDAOImpl : entity saved in db");
		} catch (Exception e) {
			logger.error("LOGGER: TempleDAOImpl : Failed to save entity into DB");
			logger.error(e.getMessage(), e);

			session.getTransaction().rollback();
		} finally {
			session.close();
			logger.info("END: TempleDAOImpl :----------------- saveBooking()----------------------");
		}

	}

	@Override
	public String fetchPswdByEmailId(String email) {
		logger.info("LOGGER : TempleDAOImpl :------------- fetchPswdByEmailId() invoked-----------------");
		Session session = this.factory.openSession();
		logger.info("LOGGER : TempleDAOImpl : session opened");

		try {
			logger.info("LOGGER : TempleDAOImpl : getting named query from PersonalEntity class");
			Query query = session.getNamedQuery("fetchPswdByEmailId");
			logger.info("LOGGER : TempleDAOImpl : setting the value to the emailAddress varirable : " + email);
			query.setParameter("EMAIL_ADDRESS", email); // named parameter

			logger.info("LOGGER : TempleDAOImpl : executing the query");
			Object result = query.uniqueResult();
			logger.info("LOGGER : TempleDAOImpl : got the db result in the form of obj");
			String password = (String) result;

			logger.info("LOGGER : TempleDAOImpl : converted obj db result to String password :" + password);
			logger.info("LOGGER : TempleDAOImpl : returning password to service");
			return password;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			session.close();
			logger.info("LOGGER : TempleDAOImpl : session closed");
			logger.info("LOGGER : TempleDAOImpl :------------------END: fetchPswdByEmailId()" + email+"--------------------------");
		}
		

		return null;
	}

	public int savingPasswordByEmail(String emailId, String password) {
		logger.info("LOGGER : TempleDAOImpl :---------------STARTS : savingPasswordByEmail() invoked-------------------");
		Session session = null;
		try {
			session = this.factory.openSession();
			logger.info("LOGGER : TempleDAOImpl : TempleDAOImpl : session opened");
			logger.info("LOGGER : TempleDAOImpl : starting transaction");
			Transaction transaction = session.beginTransaction();
			logger.info("LOGGER : TempleDAOImpl : getting named query from PersonalEntity class");
			Query query = session.getNamedQuery("savingPasswordByEmail");
			logger.info("LOGGER : TempleDAOImpl : setting the value to the email id and password varirable : " + emailId + "," + password);
			query.setParameter("EMAIL_ADDRESS", emailId); // named parameter
			query.setParameter("RANDOM_PASSWORD", password);
			logger.info("LOGGER : TempleDAOImpl : executing the query");
			int result = query.executeUpdate();
			logger.info("LOGGER : TempleDAOImpl : effected row :" + result);
			logger.info("LOGGER : TempleDAOImpl :committed transaction");
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("LOGGER : TempleDAOImpl : exception while updating password" + e.getMessage());
			return 1;
		} finally {
			session.close();
			logger.info("LOGGER : TempleDAOImpl : session closed");
			logger.info("LOGGER : TempleDAOImpl :-------------- ENDS : savingPasswordByEmail()-----------------------");
		}
		return 0;
	}

	@Override
	public int updateLoginAttemptByEmailId(String emailId) {
		logger.info("LOGGER : TempleDAOImpl :-------------- STARTS : updateLoginAttemptByEmailId() invoked-----------------------");
		Session session = null;
		int result=0;
		try {
			session = this.factory.openSession();
			logger.info("LOGGER : TempleDAOImpl : session opened");
			logger.info("LOGGER : TempleDAOImpl : starting transaction");
			Transaction transaction = session.beginTransaction();
			logger.info("LOGGER : TempleDAOImpl : getting named query from PersonalEntity class");
			Query query = session.getNamedQuery("updateLoginAttemptsByEmail");
			logger.info("LOGGER : TempleDAOImpl : setting the value to the email id and LoginAttempt varirable : " + emailId + "," + count++);
			query.setParameter("EMAIL_ADDRESS", emailId); // named parameter
			query.setParameter("LOGIN_ATTEMPTS", count);
			logger.info("LOGGER : TempleDAOImpl : executing the query");
			result = query.executeUpdate();
			logger.info("LOGGER : TempleDAOImpl : effected row :" + result);
			logger.info("LOGGER : TempleDAOImpl :committed transaction");
			transaction.commit();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("LOGGER : TempleDAOImpl : exception while updating LoginAttempt" + e.getMessage());
			return result;
		} finally {
			session.close();
			logger.info("LOGGER : TempleDAOImpl : session closed");
			logger.info("LOGGER : TempleDAOImpl :------------------ ENDS :-- updateLoginAttemptByEmailId()--------------");
		}
	}

	@Override
	public int fetchLoginAttemptByEmailId(String emailId) {
		logger.info("LOGGER : TempleDAOImpl :------------STARTS: fetchLoginAttemptByEmailId() invoked----------------");
		Session session = this.factory.openSession();
		logger.info("LOGGER : TempleDAOImpl : session opened");

		try {
			logger.info("LOGGER : TempleDAOImpl : getting named query from PersonalEntity class");
			Query query = session.getNamedQuery("fetchLoginAttemptsByEmail");
			logger.info("LOGGER : TempleDAOImpl : setting the value to the emailAddress field : " + emailId);
			query.setParameter("EMAIL_ADDRESS", emailId); // named parameter

			logger.info("LOGGER : TempleDAOImpl : executing the query");
			Object result = query.uniqueResult();
			logger.info("LOGGER : TempleDAOImpl : got the db result in the form of obj");
			int loginAttempt = (int) result;

			logger.info("LOGGER : TempleDAOImpl : converted obj db result to integer loginAttempt :" + loginAttempt);
			logger.info("LOGGER : TempleDAOImpl : returning LoginAttempt to service");
			return loginAttempt;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			session.close();
			logger.info("LOGGER : TempleDAOImpl : session closed");
			logger.info("LOGGER : TempleDAOImpl :----------------END: fetchLoginAttemptByEmailId()---------------");
		}
		

		return 0;
	}

	@Override
	public int updateAcctLockByEmailId(String emailId) {
		logger.info("LOGGER : updateAcctLockByEmailId() invoked");
		Session session = null;
		try {
			session = this.factory.openSession();
			logger.info("LOGGER : session opened");
			logger.info("LOGGER : starting transaction");
			Transaction transaction = session.beginTransaction();
			logger.info("LOGGER : getting named query from PersonalEntity class");
			Query query = session.getNamedQuery("updateAcctLockByEmail");
			logger.info("LOGGER : setting the value to the email id and isAcctLocked fields : " + emailId + "," + true);
			query.setParameter("EMAIL_ADDRESS", emailId); // named parameter
			query.setParameter("ACCOUNT_LOCKED", true);
			logger.info("LOGGER : executing the query");
			int result = query.executeUpdate();
			logger.info("LOGGER : effected row :" + result);
			logger.info("LOGGER :committed transaction");
			transaction.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("LOGGER : exception while updating LoginAttempt" + e.getMessage());
		} finally {
			session.close();
			logger.info("LOGGER : session closed");
			logger.info("LOGGER : updateLoginAttemptByEmailId() ends");
		}
		return 0;
	}

	@Override
	public boolean fetchAcctLockByEmail(String emailId) {
		logger.info("LOGGER : fetchAcctLockByEmail() invoked");
		Session session = this.factory.openSession();
		logger.info("LOGGER : session opened");
		boolean actLocked =false;
		try {
			logger.info("LOGGER : getting named query from PersonalEntity class");
			Query query = session.getNamedQuery("fetchAcctLockByEmail");
			logger.info("LOGGER : setting the value to the emailAddress field : " + emailId);
			query.setParameter("EMAIL_ADDRESS", emailId); // named parameter

			logger.info("LOGGER : executing the query");
			Object result = query.uniqueResult();
			logger.info("LOGGER : got the db result in the form of obj");
			actLocked=(boolean) result;

			logger.info("LOGGER : converted obj db result to boolean actLocked :" + actLocked);
			logger.info("LOGGER : returning actLocked to service");
			return actLocked;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			session.close();
		}
		logger.info("END: fetchAcctLockByEmail()");
		return actLocked;
	}

	@Override
	public int updateLoginAttemptAndAcctLockbyEmailId(String emailId) {
		logger.info("LOGGER : updateLoginAttemptAndAcctLockbyEmailId() invoked");
		Session session = null;
		try {
			session = this.factory.openSession();
			logger.info("LOGGER : session opened");
			logger.info("LOGGER : starting transaction");
			Transaction transaction = session.beginTransaction();
			logger.info("LOGGER : getting named query from PersonalEntity class");
			Query query = session.getNamedQuery("updateLoginAttemptAndAcctLockbyEmailId");
			logger.info("LOGGER : setting the value to the email id, LoginAttempt and isAcctLocked fields : " + emailId + "," +0+ false);
			query.setParameter("EMAIL_ADDRESS", emailId); // named parameter
			query.setParameter("LOGIN_ATTEMPTS", 0);
			query.setParameter("ACCOUNT_LOCKED", false);
			logger.info("LOGGER : executing the query");
			int result = query.executeUpdate();
			logger.info("LOGGER : effected row :" + result);
			logger.info("LOGGER :committed transaction");
			transaction.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("LOGGER : exception while updating LoginAttempt" + e.getMessage());
		} finally {
			session.close();
			logger.info("LOGGER : session closed");
			logger.info("LOGGER : updateLoginAttemptByEmailId() ends");
		}
		return 0;
		
	}

	
	  @Override 
	  public PersonalEntity fetchPersonalEntitybyEmail(String email) {
	  logger.info("LOGGER : TempleDAOImpl :------------------ fetchPidbyEmail() invoked----------------------"); 
	  Session session = this.factory.openSession();
	  logger.info("LOGGER : TempleDAOImpl : session opened");
	  
	  try { logger.
	  info("LOGGER : TempleDAOImpl : getting named query from PersonalEntity class"); 
	  Query query = session.getNamedQuery("fetchPersonalEntitybyEmail");
	  logger.info("LOGGER : TempleDAOImpl : setting the value to the emailAddress varirable : EMAIL_ADDRESS "); 
	  query.setParameter("EMAIL_ADDRESS", email); // named parameter
	  
	  logger.info("LOGGER : TempleDAOImpl : executing the query"); 
	  Object result =query.uniqueResult();
	  logger.info("LOGGER : TempleDAOImpl : got the db result in the form of obj");
	  PersonalEntity entity = (PersonalEntity) result;
	  
	  logger.info("LOGGER : TempleDAOImpl : converted db result to personal enity :" +entity.toString()); 
	  logger.info("LOGGER : TempleDAOImpl : returning personal enity to service");
	  return entity;
	  
	  } catch (Exception e) {
		  logger.error(e.getMessage(), e);
	  } finally {
	  session.close(); 
	  logger.info("END: TempleDAOImpl :----------------- fetchPersonalEntitybyEmail()----------------------"); 
	  }
	  
	  return null; }

	
}
