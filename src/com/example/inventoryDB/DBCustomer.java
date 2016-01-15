package com.example.inventoryDB;

import java.util.LinkedList;
import com.example.inventoryBE.Customer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBCustomer extends DBAdapter {

	public DBCustomer(Context Cont) {
		super(Cont);
		// TODO Auto-generated constructor stub
	}
	
	public Exception insertCustomer(Customer customerOBJ)
    {
    	SQLiteDatabase DB;
    	DB = GETSQLOBJ();
		try
		{
			String Insert1 = "INSERT INTO Customer" + " VALUES (null, " + "'" + customerOBJ.GetName() + "'," + "'" + customerOBJ.GetAddress() + "'," + "'" +customerOBJ.GetPhoneNumber() + "'" + ");";
	    	DB.execSQL(Insert1);
	    	DB.close(); 
	    	return null;
	    }
		catch (Exception E)
		{
			DB.close();
	    	return E; 
	    }
	}
	
	public LinkedList<Customer> SearchCustomer (int Key, String Value)
	{
		
	
		SQLiteDatabase DB; 
		DB = GETSQLOBJ();
		String Query = ""; 
		LinkedList<Customer> LCustomers = new LinkedList<Customer>();
		if ( Key == 1){
	 //Query = "SELECT ROWID, DOCTORS.Specialization, DOCTORS.PhoneNumber, DOCTORS.Address FROM DOCTORS WHERE DOCTORS.Name = " + "'" + Value + "'";
			
		Query = "SELECT * FROM Customer WHERE Customer.Name = " + "'" + Value + "'";
		Cursor OBJCursor = DB.rawQuery(Query, null);
		if (OBJCursor != null)
		{
			if (OBJCursor.moveToFirst())
			{	
				do
				{
					Customer OBJCustomer = new Customer(OBJCursor.getString(OBJCursor.getColumnIndex("Name")), OBJCursor.getString(OBJCursor.getColumnIndex("Phone")), OBJCursor.getString(OBJCursor.getColumnIndex("Address")));
					OBJCustomer.SetCustomerID(OBJCursor.getShort(0));
					LCustomers.addFirst(OBJCustomer);
				}   while (OBJCursor.moveToNext());
			}
		}
		
		DB.close(); 
		OBJCursor.close(); 
		
	}
		if ( Key == 2){
	 //Query = "SELECT ROWID, DOCTORS.Specialization, DOCTORS.PhoneNumber, DOCTORS.Address FROM DOCTORS WHERE DOCTORS.Name = " + "'" + Value + "'";
			
		Query = "SELECT * FROM Customer WHERE Customer.Address = " + "'" + Value + "'";
		Cursor OBJCursor = DB.rawQuery(Query, null);
		if (OBJCursor != null)
		{
			if (OBJCursor.moveToFirst())
			{	
				do
				{
					Customer OBJCustomer = new Customer(OBJCursor.getString(OBJCursor.getColumnIndex("Name")), OBJCursor.getString(OBJCursor.getColumnIndex("Phone")), OBJCursor.getString(OBJCursor.getColumnIndex("Address")));
					OBJCustomer.SetCustomerID(OBJCursor.getShort(0));
					LCustomers.addFirst(OBJCustomer);
				}   while (OBJCursor.moveToNext());
			}
		}
		
		DB.close(); 
		OBJCursor.close(); 
		
	}
	
		
		return LCustomers; 
		 
	}
	
	public LinkedList<Customer> GetAllCustomers()
	{
		SQLiteDatabase DB; 
		DB = GETSQLOBJ();
		String Query = ""; 
		LinkedList<Customer> LCustomers = new LinkedList<Customer>();
		
		Query = "SELECT * FROM Customer";
		Cursor OBJCursor = DB.rawQuery(Query, null);
		if (OBJCursor != null)
		{
			if (OBJCursor.moveToFirst())
			{	
				do
				{
					Customer OBJCustomer = new Customer(OBJCursor.getString(OBJCursor.getColumnIndex("Name")), OBJCursor.getString(OBJCursor.getColumnIndex("Phone")), OBJCursor.getString(OBJCursor.getColumnIndex("Address")));
					OBJCustomer.SetCustomerID(OBJCursor.getShort(0));
					LCustomers.addFirst(OBJCustomer);
				} while (OBJCursor.moveToNext());
			}
		}
		DB.close(); 
		OBJCursor.close(); 
		return LCustomers; 
	}
	
	public Exception DeleteCustomer( int ID)
    {
    	SQLiteDatabase DB;
    	DB = GETSQLOBJ();
		try
		{
			String Update1 = "DELETE FROM Customer WHERE ROWID =" + ID + ";";
	    	DB.execSQL(Update1);
	    	DB.close(); 
	    	return null;
	    }
		catch (Exception E)
		{
			DB.close();
	    	return E; 
	    }
	}
	

}
