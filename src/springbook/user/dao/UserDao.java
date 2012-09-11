package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;

public class UserDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(User user) throws SQLException {

		StatementStrategy strategy = new AddStatement(user);
		jcbcContext(strategy);
	}

	public void deleteAll() throws SQLException {

		executeSql("delete from spring_users");
	}

	private void executeSql(final String sql) throws SQLException {
		jcbcContext(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					PreparedStatement ps = c.prepareStatement(sql);
					return ps;
				}
			}
		);
	}

	public User get(String id) throws SQLException {
		Connection c = dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement("select * from spring_users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();

		User user = null;

		if(rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}

		rs.close();
		ps.close();
		c.close();

		if(user == null)
			throw new EmptyResultDataAccessException(1);

		return user;
	}

	private void jcbcContext(StatementStrategy strategy) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = dataSource.getConnection();

			ps = strategy.makePreparedStatement(c);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			if(ps != null) { try { ps.close(); } catch (SQLException e) { }}
			if(c != null)  { try { c.close();  } catch (SQLException e) { }}
		}
	}

	public int getCount() throws SQLException {
		Connection c = dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement("select count(*) from spring_users");

		ResultSet rs = ps.executeQuery();
		rs.next();

		int count = rs.getInt(1);

		rs.close();
		ps.close();
		c.close();

		return count;
	}
}
