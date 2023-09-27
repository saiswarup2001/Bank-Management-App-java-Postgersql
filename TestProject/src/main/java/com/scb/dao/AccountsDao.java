package com.scb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.scb.Account;
import com.scb.Connections;

public class AccountsDao {
	PreparedStatement s;
	public void saveAccountDetails(Account ac) {
		
		try {
			Connection con  = Connections.getConnections(); //created the connection
			  String query = "select nextval('accountnumber')"; //increment the account number by query

		        Statement qStatement = con.createStatement();
		        ResultSet rs=qStatement.executeQuery(query); //pass the query
		    int newAcctNumber=0;
			if(rs.next()) {
				newAcctNumber=rs.getInt(1);
			}
			if(newAcctNumber==0) {
				throw new Exception("Unable to get new Account numner");
			}
			
			s = con.prepareStatement("insert into bankManagement(Name, City, Balance, accountNo) values(?, ?, ?, ?)");
			s.setString(1,ac.getCustomerName());
			s.setString(2, ac.getCustomerAddress());
			s.setInt(3, ac.getCustomerBalance());
			s.setInt(4, newAcctNumber);	
			s.executeUpdate();
			ac.setCustomerAccNumber(newAcctNumber);
			Connections.closeStatement(s);
			Connections.closeConnection(con);
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

	//----------deposit-----------------------
	public int updateDepositeValue(int amount, int accountNo) {
		// TODO Auto-generated method stub
		try {
			Connection con = Connections.getConnections();
			String query = "SELECT name, city, balance, accountno\r\n"
					+ "	FROM public.bankmanagement where accountno = ?";
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, accountNo);
			
			ResultSet rs = statement.executeQuery();
			Account acc = null;
			if(rs.next()) {
				acc = new Account();
				acc.setCustomerAccNumber(rs.getInt("accountno"));
				acc.setCustomerName(rs.getString("name"));
				acc.setCustomerAddress(rs.getString("city"));
				acc.setCustomerBalance(rs.getInt("balance"));
			}else {
				throw new RuntimeException("Account not found");
			}
			Connections.closeStatement(statement);
			
			query = "update public.bankmanagement set balance=? where accountno=?";
			int balance = acc.getCustomerBalance()+ amount;
			PreparedStatement state2 = con.prepareStatement(query);
			acc.setCustomerBalance(balance);
			state2.setInt(1, acc.getCustomerBalance());
			state2.setInt(2, accountNo);
			state2.executeUpdate();
			
			//acc.setCustomerBalance(acc.getCustomerBalance());
			
			Connections.closeStatement(state2);	
	        Connections.closeConnection(con);
	        
	        return balance;
				
		}catch(Exception e) {
			System.out.print("ERROR.....");
			System.out.print(e);
			return 0;
		}
	}
	
	
	// --------------Withdraw------------------
	public int updateWithdrawValue(int amount, int accountNo) {
		try {
			Connection con = Connections.getConnections();
			String query = "SELECT name, city, balance, accountno\r\n"
					+ "	FROM public.bankmanagement where accountno = ?";
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, accountNo);
			
			ResultSet rs = statement.executeQuery();
			Account acc = null;
			if(rs.next()) {
				acc = new Account();
				acc.setCustomerAccNumber(rs.getInt("accountno"));
				acc.setCustomerName(rs.getString("name"));
				acc.setCustomerAddress(rs.getString("city"));
				acc.setCustomerBalance(rs.getInt("balance"));
			}else {
				throw new RuntimeException("Account not found");
			}
			Connections.closeStatement(statement);
			
			query = "update public.bankmanagement set balance=? where accountno=?";
			int balance = acc.getCustomerBalance()-amount;
			PreparedStatement state2 = con.prepareStatement(query);
			acc.setCustomerBalance(balance);
			state2.setInt(1, acc.getCustomerBalance());
			state2.setInt(2, accountNo);
			state2.executeUpdate();
			
			//acc.setCustomerBalance(acc.getCustomerBalance());
			
			Connections.closeStatement(state2);	
	        Connections.closeConnection(con);
	        
	        return balance;
				
		}catch(Exception e) {
			System.out.print("ERROR.....");
			System.out.print(e);
			return 0;
		}
		
	}
	
//	-----Balance Inquiry-----
	public void balanceEnquiry(int accountNO) {
	    try {
	        Connection con = Connections.getConnections();
	        String query = "SELECT balance FROM bankmanagement WHERE accountno=?";
	        PreparedStatement statement = con.prepareStatement(query);
	        statement.setInt(1, accountNO); // Set the account number parameter

	        ResultSet rs = statement.executeQuery();
	        int balance = 0;
	        Account acc = null;
	        if (rs.next()) {
	        	acc = new Account();
	            balance = rs.getInt("balance");
	            acc.setCustomerBalance(balance);
	            System.out.println("Account balance --> "+balance);
	        } else {
	            System.out.println("Account not found.");
	        }

	        Connections.closeResultSet(rs);
	        Connections.closeStatement(statement);
	        Connections.closeConnection(con);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	//----------------list of accounts-------------------
	public void updateTableList(Account acc) {
	    try {
	        Connection con = Connections.getConnections();
	        String query = "SELECT * FROM bankmanagement";
	        Statement statement = con.createStatement();
	        ResultSet rs = statement.executeQuery(query);

	        while (rs.next()) {
	            int accountNo = rs.getInt("accountno");
	            String accountName = rs.getString("name");
	            String city = rs.getString("city");
	            int balance = rs.getInt("balance");

	            acc.setCustomerAccNumber(accountNo);
	            acc.setCustomerName(accountName);
	            acc.setCustomerAddress(city);
	            acc.setCustomerBalance(balance);

	            
	            System.out.println("Account Number: " + acc.getCustomerAccNumber());
	            System.out.println("Account Name: " + acc.getCustomerName());
	            System.out.println("City: " + acc.getCustomerAddress());
	            System.out.println("Balance: " + acc.getCustomerBalance());
	            System.out.println("--------------------");
	        }

	        Connections.closeStatement(statement);
	        Connections.closeConnection(con);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	

}
