package springbook.user.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ApplicationContext context = new GenericXmlApplicationContext("/applicationContext_test.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);

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
