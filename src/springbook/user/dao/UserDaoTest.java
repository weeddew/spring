package springbook.user.dao;

import java.sql.SQLException;

import springbook.user.domain.User;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ConnectionMaker connectionMaker = new NConnectionMaker();
		UserDao dao = new UserDao(connectionMaker);

		User user = new User();
		user.setId("brownie");
		user.setName("브라우니");
		user.setPassword("anfdj");

		dao.add(user);

		User user2 = dao.get("brownie");
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
	}
}
