package com.arcd.inventory.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnReports
{
	public static Connection conectar() {
		Connection con = null;
 
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/inventory?user=root&password=";
			con = DriverManager.getConnection(url);
			if (con != null) {
				System.out.println("Conexion Satisfactoria");
			}
 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}
}
