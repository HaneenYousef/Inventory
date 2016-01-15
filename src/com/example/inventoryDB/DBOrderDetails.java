package com.example.inventoryDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.inventoryBE.Customer;
import com.example.inventoryBE.OrderDetails;

public class DBOrderDetails extends DBAdapter {

	public DBOrderDetails(Context Cont) {
		super(Cont);
		// TODO Auto-generated constructor stub
	}

	
	public Exception insertOrderDetails(OrderDetails OrderDetailsOBJ)
    {
    	SQLiteDatabase DB;
    	DB = GETSQLOBJ();
		try
		{
						String Insert1 = "INSERT INTO OrderDetails" + " VALUES (null, " + "'" + OrderDetailsOBJ.getOrderID() + "'," + "'" + OrderDetailsOBJ.getProductID() + "'," + "'" +
					OrderDetailsOBJ.getFinalPrice()+ "'" + OrderDetailsOBJ.getQuantity() + "'," + "'" + ");";
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
}