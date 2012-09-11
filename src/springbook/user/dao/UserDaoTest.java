package springbook.user.dao;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.domain.User;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserDaoTest {

	@Test
	public void addAndGet() throws ClassNotFoundException, SQLException {

		ApplicationContext context = new GenericXmlApplicationContext("/applicationContext_test.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);

		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		User user = new User();
		user.setId("brownie");
		user.setName("브라우니");
		user.setPassword("anfdj");

		dao.add(user);
		assertThat(dao.getCount(), is(1));

		User user2 = dao.get("brownie");

		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
	}
}
