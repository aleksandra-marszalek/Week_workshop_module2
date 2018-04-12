package pl.coderslab;

import java.sql.Connection;

public class Main {
	public static void main(String[] args) {
		try (Connection c = DbUtil.getConnection();) {
			
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
	}
}
