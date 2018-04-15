package pl.coderslab;

import java.sql.Connection;
import java.sql.Date;

import pl.coderslab.administrative.HotelManager;
import pl.coderslab.administrative.UsersManager;
import pl.coderslab.dao.HotelDao;
import pl.coderslab.dao.ReservationDao;
import pl.coderslab.dao.RoomDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.entity.Hotel;
import pl.coderslab.entity.Reservation;
import pl.coderslab.entity.Room;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserGroup;

public class Main {
	public static void main(String[] args) {
		
		UsersManager.userManager();
		
	}
}
