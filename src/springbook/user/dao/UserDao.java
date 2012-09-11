package springbook.user.dao;

import java.util.List;

import springbook.user.domain.User;

public interface UserDao {

	public void add(User user);

	public void deleteAll();

	public User get(String id);

	public List<User> getAll();

	public int getCount();

}