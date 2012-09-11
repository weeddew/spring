package springbook.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import springbook.user.domain.User;

public class UserDao {

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper<User> userMapper =
		new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rownum) throws SQLException {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		};

	public void add(User user) {
		jdbcTemplate.update("insert into spring_users(id, name, password) values (?, ?, ?)",
				user.getId(), user.getName(), user.getPassword());
	}

	public void deleteAll() {
		jdbcTemplate.update("delete from spring_users");
	}

	public User get(String id) {
		return jdbcTemplate.queryForObject("select * from spring_users where id = ?",
				new Object[] {id}, userMapper);
	}

	public List<User> getAll() {
		return jdbcTemplate.query("select * from spring_users order by id", userMapper);
	}

	public int getCount() {
		return jdbcTemplate.queryForInt("select count(*) from spring_users");
	}
}
