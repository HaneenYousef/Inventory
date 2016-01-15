package com.example.inventoryUI;

import java.util.ArrayList;
import java.util.LinkedList;

import com.example.inventory.R;
import com.example.inventoryBE.Customer;
import com.example.inventoryDB.DBCustomer;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DisplayAllCustomer extends ListActivity  {
	
	DBCustomer DB ;
	ArrayList <String> names = new ArrayList<String>();
	LinkedList<Customer> customers = new LinkedList<Customer>();
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.delete_customer);
		final Context cont = this;
	    DB = new DBCustomer(cont); 
	    
		customers = DB.GetAllCustomers();
		for( int i = 0 ; i < customers.size() ; i++){
			names.add(customers.get(i).GetName());
		}
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names));
	}
	
	public void onListItemClick(
			ListView parent, View v, int position, long id)
			{
				Intent myIntent = new Intent(DisplayAllCustomer.this, DisplayOneCustomer.class).
						putExtra("customer", customers.get(position).toString());
				Bundle b= new Bundle();
				b.putSerializable("order", customers.get(position));
				myIntent.putExtras( b);
				DisplayAllCustomer.this.startActivity(myIntent);
				finish();
			
			}
	public void onBackPressed()
	{
		finish();
		//Start CustomerMain Activity
		Intent myIntent=new Intent(DisplayAllCustomer.this, CustomerUI.class);
		DisplayAllCustomer.this.startActivity(myIntent);
	}



}
