package springbook.user.service;

import java.util.List;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;


public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void addUsers(List<User> users) throws Exception {

		for(User user : users) {
			userDao.add(user);
		}
	}

}
