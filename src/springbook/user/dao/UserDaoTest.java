package springbook.user.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.domain.User;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext_test.xml")
public class UserDaoTest {

	@Autowired
	private UserDao dao;

	private User user1;
	private User user2;

	@Test
	public void addAndGet() {

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
	public void getUserFailure() {

		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.get("unknown_id");
	}

	@Test
	public void getAll() {

		dao.deleteAll();

		dao.add(user1);
		dao.add(user2);

		List<User> users = dao.getAll();
		assertThat(users.size(), is(2));
	}

	@Before
	public void setUp() {
		user1 = new User("brownie", "브라우니", "anfdj");
		user2 = new User("aaa", "홍길동", "asdf");
	}
}
