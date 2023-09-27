package com.scb;

import java.sql.*;

public class Connections {
	public static Connection getConnections() {
		Connection c=null;
		try {
			Class.forName("org.postgresql.Driver");
			 c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MyDB","postgres","Saiswarup2001");
			
		}catch(SQLException | ClassNotFoundException e) {
			System.out.println("Error....!!");
			System.out.println(e);
			throw new RuntimeException("Could not acquire new connection");
		}
		return c;
	}


public static void closeConnection(Connection c) {
	if(c!=null) {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


public static void closeStatement(Statement s) {
	if(s!=null) {
		
		try {
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}


public static void closeResultSet(ResultSet rs) {
	
	if(rs!=null) {
		try {
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}

//Class.forName("org.postgresql.Driver");
//con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MyDB","postgres","Saiswarup2001");
}