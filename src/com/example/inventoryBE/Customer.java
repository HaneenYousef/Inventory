package com.example.inventoryBE;

import java.io.Serializable;


public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Name ; 
	private String PhoneNumber; 
	private String Address;
	private int CustomerID; 
	
	public Customer(String Name, String PhoneNumber, String Address)
	{
		this.Name = Name; 
		this.PhoneNumber = PhoneNumber;
		this.Address = Address; 
	}
	
	@Override
	public String toString()
	{
		return ("\n" + "Customer's Name: " + Name + "\n" + "Customer's PhoneNumber: " + PhoneNumber + "\n" + "Customer's Address: " + Address + "\n" + "Customer's ID: " + CustomerID + "\n");
	}
	@Override
	public boolean equals (Object P)
	{
		if (P instanceof Customer) 
		{
			if ((((Customer)P).Name.equals(Name) && ((Customer)P).PhoneNumber.equals(PhoneNumber)) && ((Customer)P).Address.equals(Address))
				return true; 
			else 
				return false; 
		}
		else 
			return false; 
	}
	
	public void SetName(String Name) {
		this.Name = Name; 
	}
	public void SetPhoneNumber(String PhoneNumber) {
		this.PhoneNumber = PhoneNumber; 
	}
	public void SetAddress(String Address) {
		this.Address = Address; 
	}
	public void SetCustomerID (int CustomerID) {
		this.CustomerID = CustomerID; 
	}
	
	public String GetName() {
		return this.Name; 
	}
	public String GetPhoneNumber() {
		return this.PhoneNumber; 
	}
	public String GetAddress() {
		return this.Address; 
	}
	public int GetCustomerID() {
		return this.CustomerID; 
	}


}
