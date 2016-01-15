package com.example.inventoryDB;
import java.util.LinkedList;

import com.example.inventoryBE.Customer;
import com.example.inventoryBE.Product;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

	public class DBProduct extends DBAdapter{
		
	     public DBProduct(Context Cont) {
		    super(Cont);
		
	     }
	     
	     public Exception insertProduct(Product porductOBJ)
	     {
	     	SQLiteDatabase DB;
	     	DB = GETSQLOBJ();
	 		try
	 		{
	 			
	 			String Insert1 = "INSERT INTO Product " + " VALUES (null, " + "'" +  porductOBJ.getName() + "'," +  "'" +  porductOBJ.getStandardPrice() + "'," + "'" +  porductOBJ.getQuantity() + "'," + "'" +  porductOBJ.getUnit() + "'" + ");";
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
	     
	 	public LinkedList<Product> GetAllProducts()
		{
			SQLiteDatabase DB; 
			DB = GETSQLOBJ();
			String Query = ""; 
			LinkedList<Product> LProducts = new LinkedList<Product>();
			
			Query = "SELECT * FROM Product";
			Cursor OBJCursor = DB.rawQuery(Query, null);
			if (OBJCursor != null)
			{
				if (OBJCursor.moveToFirst())
				{	
					do
					{
						String Name = OBJCursor.getString(OBJCursor.getColumnIndex("Name"));
						double sPrice = OBJCursor.getDouble(OBJCursor.getColumnIndex("StandardPrice"));
						int unit = OBJCursor.getInt(OBJCursor.getColumnIndex("Unit"));
						int quantity = OBJCursor.getInt(OBJCursor.getColumnIndex("Quantity"));
						int id =  OBJCursor.getInt(OBJCursor.getColumnIndex("ProductID"));
						Product OBJProduct = new Product(Name,sPrice , quantity , unit);
						OBJProduct.setProdcutID(id);
						LProducts.addFirst(OBJProduct);
					} while (OBJCursor.moveToNext());
				}
			}
			DB.close(); 
			OBJCursor.close(); 
			return LProducts; 
		}
	     


	 	public LinkedList<Product> Search(String value)
		{
			SQLiteDatabase DB; 
			DB = GETSQLOBJ();
			String Query = ""; 
			LinkedList<Product> LProducts = new LinkedList<Product>();
			
		 //Query = "SELECT ROWID, DOCTORS.Specialization, DOCTORS.PhoneNumber, DOCTORS.Address FROM DOCTORS WHERE DOCTORS.Name = " + "'" + Value + "'";
				
			Query = "SELECT * FROM Product WHERE Product.Name = " + "'" + value + "'";
			Cursor OBJCursor = DB.rawQuery(Query, null);
			if (OBJCursor != null)
			{
				if (OBJCursor.moveToFirst())
				{	
					do
					{
						String Name = OBJCursor.getString(OBJCursor.getColumnIndex("Name"));
						double sPrice = OBJCursor.getDouble(OBJCursor.getColumnIndex("StandardPrice"));
						int unit = OBJCursor.getInt(OBJCursor.getColumnIndex("Unit"));
						int quantity = OBJCursor.getInt(OBJCursor.getColumnIndex("Quantity"));
						int id =  OBJCursor.getInt(OBJCursor.getColumnIndex("ProductID"));
						Product OBJProduct = new Product(Name,sPrice , quantity , unit);
						OBJProduct.setProdcutID(id);
						LProducts.addFirst(OBJProduct);
					}   while (OBJCursor.moveToNext());
				}
			}
			
			DB.close(); 
			OBJCursor.close(); 
			
			return LProducts;
		
	 		
		}
	 	public Product SearchByID(int value)
		{
			SQLiteDatabase DB; 
			DB = GETSQLOBJ();
			String Query = ""; 
			Product LProducts =  null;
			
		 //Query = "SELECT ROWID, DOCTORS.Specialization, DOCTORS.PhoneNumber, DOCTORS.Address FROM DOCTORS WHERE DOCTORS.Name = " + "'" + Value + "'";
				
			Query = "SELECT * FROM Product WHERE Product.ProductID = " + "'" + value + "'";
			Cursor OBJCursor = DB.rawQuery(Query, null);
			if (OBJCursor != null)
			{
				if (OBJCursor.moveToFirst())
				{	
						String Name = OBJCursor.getString(OBJCursor.getColumnIndex("Name"));
						double sPrice = OBJCursor.getDouble(OBJCursor.getColumnIndex("StandardPrice"));
						int unit = OBJCursor.getInt(OBJCursor.getColumnIndex("Unit"));
						int quantity = OBJCursor.getInt(OBJCursor.getColumnIndex("Quantity"));
						int id =  OBJCursor.getInt(OBJCursor.getColumnIndex("ProductID"));
						LProducts= new Product(Name,sPrice , quantity , unit);
						LProducts.setProdcutID(id);
						
					
				}
			}
			
			DB.close(); 
			OBJCursor.close(); 
			
			return LProducts;
		
	 		
		}
	 	public Exception DeleteProduct( int ID)
	    {
	    	SQLiteDatabase DB;
	    	DB = GETSQLOBJ();
			try
			{
				String Update1 = "DELETE FROM Product WHERE ROWID =" + ID + ";";
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
		public Exception UpdateProduct(Product porductOBJ, int ID)
	    {
	    	SQLiteDatabase DB;
	    	DB = GETSQLOBJ();
			try
			{
				
				String Update1 = "UPDATE Product SET Name = '" + porductOBJ.getName() + "', StandardPrice = '" +porductOBJ.getStandardPrice()+ "', Unit = '" +  porductOBJ.getUnit() + "', Quantity = '"+ porductOBJ.getQuantity() +"' WHERE ROWID= " + ID + ";";
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
