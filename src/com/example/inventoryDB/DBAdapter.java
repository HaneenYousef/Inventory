package com.example.inventoryDB;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
	
	private  Context Context1 = null; 
	
	private static final String DATABASE_CREATE_CUSTOMERS =
            "CREATE TABLE IF NOT EXISTS Customer (CustomerID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "Name VARCHAR NOT NULL, " + "Address VARCHAR NOT NULL, " + "Phone VARCHAR NOT NULL ); ";
     
    private static final String DATABASE_CREATE_ORDERS =
 "CREATE TABLE IF NOT EXISTS OrderTable (OrderID INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "OrderDate VARCHAR NOT NULL, " + "OrderDueDate VARCHAR NOT NULL, " + "CustomerID INTEGER, " + "FOREIGN KEY (CustomerID) REFERENCES INSURANCE ON DELETE CASCADE );";
    
    private static final String DATABASE_CREATE_ORDERDETAILS =
            "CREATE TABLE IF NOT EXISTS OrderDetails (OrderDetailID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "OrderID INTEGER, "  + "ProductID INTEGER, "  + "FinalPrice DOUBLE, "+ "Quantity INTEGER, "+ "FOREIGN KEY (ProductID) REFERENCES INSURANCE ON DELETE CASCADE"
            +"FOREIGN KEY (OrderID) REFERENCES INSURANCE ON DELETE CASCADE );";
    
   private static final String DATABASE_CREATE_PRODUCTS =
            "CREATE TABLE IF NOT EXISTS Product (ProductID INTEGER PRIMARY KEY AUTOINCREMENT, " + "Name VARCHAR NOT NULL, "
            + "StandardPrice DOUBLE, " + "Quantity INTEGER, " + "Unit INTEGER );";
    
 	public DBAdapter(Context Cont)
    {
        Context1 = Cont; 
    }
	
	 
    public Exception DBCreate()
    {
    	SQLiteDatabase DB = null;
        try 
        { 
        	ContextWrapper OBJ = new ContextWrapper(Context1);
            DB = OBJ.openOrCreateDatabase("Haneen", android.content.Context.MODE_PRIVATE, null);
            DB.execSQL(DATABASE_CREATE_CUSTOMERS);
            DB.execSQL(DATABASE_CREATE_PRODUCTS);
           DB.execSQL(DATABASE_CREATE_ORDERS);
           DB.execSQL(DATABASE_CREATE_ORDERDETAILS);
          
            DB.close();
            return null;
    	}
    	catch (Exception E)
    	{
    		DB.close();
    		return E;
    	} 
        
    }
    
    public SQLiteDatabase GETSQLOBJ()
    {
    	ContextWrapper OBJ = new ContextWrapper(Context1);
    	return OBJ.openOrCreateDatabase("Haneen", android.content.Context.MODE_PRIVATE, null);
    }

}
