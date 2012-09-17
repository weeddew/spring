package springbook.user.service;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import springbook.user.domain.User;

public class UserServiceTx implements UserService {

	private PlatformTransactionManager transactionManager;
	private UserService userService;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public void addUsers(List<User> users) throws Exception {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

		try {
			userService.addUsers(users);

			transactionManager.commit(status);
		} catch(Exception e) {
			transactionManager.rollback(status);
			throw e;
		}

	}
}
