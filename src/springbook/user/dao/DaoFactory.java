package springbook.user.dao;

public class DaoFactory {

	public UserDao userDao() {
		UserDao dao = new UserDao(connectionMaker());
		return dao;
	}

	public ConnectionMaker connectionMaker() {
		ConnectionMaker connectionMaker = new NConnectionMaker();
		return connectionMaker;
	}
}
