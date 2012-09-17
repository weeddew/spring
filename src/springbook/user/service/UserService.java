package springbook.user.service;

import java.util.List;

import springbook.user.domain.User;

public interface UserService {

	public void addUsers(List<User> users) throws Exception;
}
