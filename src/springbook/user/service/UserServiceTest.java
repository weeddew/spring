package springbook.user.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.learningtest.BeanDefinitionUtils;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext_test.xml")
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDao dao;

	private User user1;
	private User user2;

	@Test
	public void addUsers() throws Exception {

		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		userService.addUsers(users);

		assertThat(dao.getCount(), is(2));
	}

	@Test
	public void transactionTest() {

		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		try {
			List<User> users = new ArrayList<User>();
			users.add(user1);
			users.add(user2);
			users.add(user2);

			userService.addUsers(users);

			fail("DuplicateKeyException expected");
		} catch (Exception e) {
			assertThat(e, is(DuplicateKeyException.class));
		}

		assertThat(dao.getCount(), is(0));
	}

	@Before
	public void setUp() {
		user1 = new User("brownie", "브라우니", "anfdj");
		user2 = new User("aaa", "홍길동", "asdf");
	}


	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void beanTest() throws Exception {
		BeanDefinitionUtils.printBeanDefinitions((GenericApplicationContext) applicationContext);
	}
}
