package com.scb;

public class Account {
	 	private String customerName;
	    private String customerAddress;
	    private int customerBalance;
	    private int customerAccNumber;

//	    public static  int accno = 1001;

	   

	    public Account(String customerName, String customerAddress, int customerBalance) {
			this.customerName = customerName;
			this.customerAddress = customerAddress;
			this.customerBalance = customerBalance;
		}
	    
	    


		public Account() {
			super();
		}




		public String getCustomerName() {
			return customerName;
		}


		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}


		public String getCustomerAddress() {
			return customerAddress;
		}


		public void setCustomerAddress(String customerAddress) {
			this.customerAddress = customerAddress;
		}


		public int getCustomerBalance() {
			return customerBalance;
		}


		public int setCustomerBalance(int customerBalance) {
			return this.customerBalance = customerBalance;
		}


		public int getCustomerAccNumber() {
			return customerAccNumber;
		}


		public void setCustomerAccNumber(int customerAccNumber) {
			this.customerAccNumber = customerAccNumber;
		}


		@Override
		public String toString() {
			return "[customerName=" + customerName + ", customerAddress=" + customerAddress + ", customerBalance=" + customerBalance + ", customerAccNumber=" + customerAccNumber + "]";
		}



		
}
