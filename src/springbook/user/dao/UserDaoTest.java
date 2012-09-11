package springbook.user.dao;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserDaoTest {

	private UserDao dao;
	private User user1;
	private User user2;

	@Test
	public void addAndGet() throws SQLException {

		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));

		User user3 = dao.get(user1.getId());
		assertThat(user3.getName(), is(user1.getName()));
		assertThat(user3.getPassword(), is(user1.getPassword()));

		User user4 = dao.get(user2.getId());
		assertThat(user4.getName(), is(user2.getName()));
		assertThat(user4.getPassword(), is(user2.getPassword()));
	}

	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {

		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.get("unknown_id");
	}

	@Before
	public void setUp() {
		ApplicationContext context = new GenericXmlApplicationContext("/applicationContext_test.xml");
		dao = context.getBean("userDao", UserDao.class);

		user1 = new User("brownie", "브라우니", "anfdj");
		user2 = new User("aaa", "홍길동", "asdf");
	}
}
