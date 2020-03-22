package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.Product;
import beans.User;
import business.OrderService;
import database.Database;

public class test {

	public static void main(String[] args) throws SQLException {
		
		Database db = new Database();
		OrderService os = new OrderService();
		
		User uU = new User();
		uU.setUsername("muser");
		uU.setPassword("mpass");
		
		db.checkCredentials(uU);
		
		List<Product> products = new ArrayList<Product>();
		products = db.searchForProduct("C");
		for (Product pr : products) {
			System.out.println(pr.getId() + ". Name = " + pr.getName() + " | Price = " + pr.getPrice() + " | Desc = " + pr.getDescription());
		}
		
		ArrayList<User> users = new ArrayList<User>();
		users = db.searchUsers("m");
		System.out.println(users.size());
		for (User u : users) {
			System.out.println(u.getUsername() + " | " + u.getPassword());
		}
		
		//Product p = os.findByID(5);
		//System.out.println(p.getName());
	}

}
