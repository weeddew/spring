package springbook.user.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserService {

	private UserDao userDao;
	private DataSource dataSource;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}



	public void addUsers(List<User> users) throws Exception {

		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
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
