package pl.coderslab;

import java.sql.Connection;

import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;

public class Main {
	public static void main(String[] args) {
		
		
		UserDao.showUser(UserDao.readAllByGroupId(1)[0]);
		User user1 = UserDao.readByEmail("janiNAnowaK@onet.pl");
		user1.setEmail("JaninaNowak@gmail.com");
		UserDao.update(user1);
		UserDao.showUser(UserDao.readByEmail("janinanowak@Gmail.com"));
	}
}
