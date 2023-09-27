package com.scb.dao;

import java.util.ArrayList;
import java.util.Scanner;
import com.scb.Account;

public class Main {
	
	private  AccountsDao accountDao=new AccountsDao();
	public static void main(String args[]) {
		Main main=new Main();
		main.execute();
	}
	
	
	public  void execute() {
			Scanner sc = new Scanner(System.in);
	        ArrayList<Account>  list = new ArrayList<>();
//	        list.add(new Account("Sai","Berhampur", 5000));
//	        list.add(new Account("Ram","Delhi", 6000));
//	        list.add(new Account("Anil","Jammu", 1000));

	        System.out.println("Select Operations to perform: ");
	        System.out.println("1. Open Account ");
	        System.out.println("2. Deposit ");
	        System.out.println("3. Withdraw ");
	        System.out.println("4. Balance Enquiry ");
	        System.out.println("5. List All Accounts ");
	        System.out.println("6. Exit");
	        int choice, amt = 0, ac_no = 0;
	        String name = null, city = null;
	        boolean found;
	        do{
	            System.out.print("Enter your choice: ");
	            choice = sc.nextInt();
	            switch (choice){
	                case 1:
	                        System.out.println("-----Open Account-----");
	                        System.out.print("Enter name : ");
	                        name= sc.next();
	                        System.out.print("Enter city : ");
	                        city = sc.next();
	                        System.out.print("Enter amount : ");
	                        amt = sc.nextInt();
	                        Account ac = new Account(name, city, amt);
	                        accountDao.saveAccountDetails(ac);
	                        System.out.println("---Account Created Successfully----");
	                        System.out.println("Account number ->"+ac.getCustomerAccNumber());
	                        break;
	                case 2:
	                	
	                		// Assuming you have an AccountDao class
	                		Account act = new Account();
	                    	System.out.println("-----Deposit Account-----");
	                    	System.out.println("Enter Account Number: ");
	                    	int accountNo = sc.nextInt();
	                    	System.out.println("Enter Amount to Deposit: ");
	                    	int amount = sc.nextInt();
	                    	try {
	                    		int balance = accountDao.updateDepositeValue(amount, accountNo);
	                    		System.out.println("Amount deposited successfully");
		                    	System.out.println("Total Balance is ->"+ balance);
	                    	}catch (Exception e) {
	                    		System.out.println(e.getMessage());
	                    	}
	                    	

	                case 3:
	                		//Account acc = new Account();
	                		System.out.println("-----Withdraw Account-----");
	                		System.out.println("Enter Account Number");
	                		ac_no = sc.nextInt();
	                		System.out.print("Enter the amount to Withdraw: ");
	                		amt = sc.nextInt();

	                		try {
	                		int balance=accountDao.updateWithdrawValue(amt, ac_no);
	                			System.out.println("Amount Withdrawn successfully");
	                			System.out.println("Total Balance remail: " + balance);
	                		}catch (Exception e) {
	                			System.out.print(e.getMessage());
	                		}

	                		System.out.println();
	                		break;

	                case 4:
	                		System.out.println("-----Balance Enquiry-----");
	                		found = false;
	                		System.out.println("Enter Account Number to check Balance: ");
	                		ac_no = sc.nextInt();
	                		Account act2 = new Account();
	                		try {
	                			accountDao.balanceEnquiry(ac_no);
	                			act2.getCustomerBalance();
	                		}catch (Exception e) {
	                			System.out.print(e.getMessage());
	                		}

	                		break;
	                case 5:
	                		System.out.println("-----List of Accounts-----");
	                		Account acc1 = new Account();
	                		accountDao.updateTableList(acc1);

	                		if (acc1.getCustomerAccNumber() != 0) {
	                			System.out.println("Account Number: " + acc1.getCustomerAccNumber());
	                			System.out.println("Name: " + acc1.getCustomerName());
	                			System.out.println("City: " + acc1.getCustomerAddress());
	                			System.out.println("Balance: " + acc1.getCustomerBalance());
	                		}else {
	                			System.out.println("No accounts found.");
	                		}

	                       
				case 6:
	                        System.out.println("Thank you"); break;
	                default:
	                    System.out.println("Invalid Choice"); break;
	            }
	        }while(choice != 6);
			
		}
		
		
}
