package springbook.user.service;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserService {

	private UserDao userDao;
	private PlatformTransactionManager transactionManager;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}


	public void addUsers(List<User> users) throws Exception {

		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

		try {
			for(User user : users) {
				userDao.add(user);
			}

			transactionManager.commit(status);
		} catch(Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
	}

}
