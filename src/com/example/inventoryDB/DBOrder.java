package com.example.inventoryDB;

import java.util.LinkedList;

import com.example.inventoryBE.Order;

import com.example.inventoryBE.Customer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBOrder extends DBAdapter {

	public DBOrder(Context Cont) {
		super(Cont);
		// TODO Auto-generated constructor stub
	}

	public Exception insertOrder(Order objOrder) {
		SQLiteDatabase DB;
		DB = GETSQLOBJ();
		try {

			String Insert1 = "INSERT INTO  OrderTable" + " values (null," + "'"
					+ objOrder.OrderDate + "'," + "'" + objOrder.OrderDueDate
					+ "','" + objOrder.CustomerID + "');";
			DB.execSQL(Insert1);
			DB.close();
			return null;
		} catch (Exception E) {
			DB.close();
			return E;
		}
	}

	public LinkedList<Order> GetAllOrders(String CustomerName) {
		SQLiteDatabase DB;
		DB = GETSQLOBJ();
		String Query = "";
		LinkedList<Order> lstOrder = new LinkedList<Order>();

		Query = "SELECT FROM OrderTable WHERE " + "Name =" + "CustomerName"
				+ ")";
		Cursor objCur = DB.rawQuery(Query, null);
		if (objCur != null) {
			if (objCur.moveToFirst()) {
				do {

					Order objOrder = new Order(objCur.getString(objCur
							.getColumnIndex("OrderDate")),
							objCur.getString(objCur
									.getColumnIndex("OrderDueDate")),
							objCur.getInt(objCur.getColumnIndex("CustomerID")));
					lstOrder.addFirst(objOrder);
				} while (objCur.moveToNext());
			}
		}
		DB.close();
		objCur.close();
		return lstOrder;
	}

	public Exception DeleteOrder(int ID) {
		SQLiteDatabase DB;
		DB = GETSQLOBJ();
		try {
			String Update1 = "DELETE FROM OrderTable WHERE ROWID =" + ID + ";";
			DB.execSQL(Update1);
			DB.close();
			return null;
		} catch (Exception E) {
			DB.close();
			return E;
		}
	}
	
	public LinkedList<Order> getOderByCustomer(int CustomerID){
		SQLiteDatabase DB;
		DB = GETSQLOBJ();
		String Query = "";
		LinkedList<Order> lstOrder = new LinkedList<Order>();

		Query = "SELECT * FROM OrderTable WHERE " + "CustomerID  =" +CustomerID +";";
		
		Cursor objCur = DB.rawQuery(Query, null);
		if (objCur != null) {
			if (objCur.moveToFirst()) {
				do {

					Order objOrder = new Order(objCur.getString(objCur
							.getColumnIndex("OrderDate")),
							objCur.getString(objCur
									.getColumnIndex("OrderDueDate")),
							objCur.getInt(objCur.getColumnIndex("CustomerID")));
					objOrder.setOrderId(objCur.getInt(objCur.getColumnIndex("OrderID")));
					lstOrder.addFirst(objOrder);
				} while (objCur.moveToNext());
			}
		}
		DB.close();
		objCur.close();
		return lstOrder;
		
	}

	public int searchCustomer(int Id) {
		SQLiteDatabase DB;
		DB = GETSQLOBJ();
		String Query = "";
		Order order;
		Customer customer;

		int customerid;
		LinkedList<Customer> LCustomers = new LinkedList<Customer>();

		Query = "SELECT ROWID, customer.CustomerID FROM Customer WHERE customer.CustomerID ="
				+ Id;

		Cursor OBJCursor = DB.rawQuery(Query, null);
		if (OBJCursor != null) {
			if (OBJCursor.moveToFirst()) {
				do {
					Customer OBJCustomer = new Customer(
							OBJCursor.getString(OBJCursor
									.getColumnIndex("Name")),
							OBJCursor.getString(OBJCursor
									.getColumnIndex("Phone")),
							OBJCursor.getString(OBJCursor
									.getColumnIndex("Address")));
					OBJCustomer.SetCustomerID(OBJCursor.getShort(0));
					LCustomers.addFirst(OBJCustomer);
				} while (OBJCursor.moveToNext());
			}
		}

		DB.close();
		OBJCursor.close();

		if (Query == null)
			return 0;
		else
			return 1;

	}

	public int searchCustomerName(String customerName) {
		SQLiteDatabase DB;
		DB = GETSQLOBJ();
		String Query = "";
		Order order;
		Customer customer;
		LinkedList<Customer> LCustomers = new LinkedList<Customer>();

		String customername;
		Query = "SELECT ROWID, customer.Name FROM Customer WHERE customer.Name="
				+ "customerName";
		Cursor OBJCursor = DB.rawQuery(Query, null);
		if (OBJCursor != null) {
			if (OBJCursor.moveToFirst()) {
				do {
					Customer OBJCustomer = new Customer(
							OBJCursor.getString(OBJCursor
									.getColumnIndex("Name")),
							OBJCursor.getString(OBJCursor
									.getColumnIndex("Phone")),
							OBJCursor.getString(OBJCursor
									.getColumnIndex("Address")));
					OBJCustomer.SetCustomerID(OBJCursor.getShort(0));
					LCustomers.addFirst(OBJCustomer);
				} while (OBJCursor.moveToNext());
			}
		}

		DB.close();
		OBJCursor.close();

		if (Query == null)
			return 0;
		else
			return 1;

	}

	public int getOderID() {
		SQLiteDatabase DB;
		DB = GETSQLOBJ();
		String Query = "";
		int ID = 0;
		;
		Query = "SELECT OrderID FROM OrderTable ORDER BY OrderID DESC LIMIT 1";
		Cursor OBJCursor = DB.rawQuery(Query, null);
		if (OBJCursor != null) {
			if (OBJCursor.moveToFirst()) {
				ID = OBJCursor.getInt(OBJCursor.getColumnIndex("OrderID"));
			}
		}

		return ID;
	}

}
