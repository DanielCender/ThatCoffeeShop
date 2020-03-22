package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.Product;
import beans.User;
import database.Database;

public class test {

	public static void main(String[] args) throws SQLException {
		
		Database db = new Database();
		
		User u = new User();
		u.setUsername("muser");
		u.setPassword("mpass");
		
		db.checkCredentials(u);
		
		List<Product> products = new ArrayList<Product>();
		products = db.loadProducts();
		int x = 1;
		for (Product pr : products) {
			System.out.println(x + ". Name = " + pr.getName() + " | Price = " + pr.getPrice() + " | Desc = " + pr.getDescription());
		}
		
	}

}
