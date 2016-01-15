package com.example.inventoryUI;

import com.example.inventory.MainActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CustomerUI extends ListActivity {
	String[] Labels = { "Display All Customers", "Add a new customer",
			"Delete a customer", "Search for a customer" };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, Labels));
		
		
	}
	
	public void onListItemClick(
			ListView parent, View v, int position, long id)
			{
			Intent myIntent;
			switch (position) 
			{
				case 0:
					myIntent = new Intent(CustomerUI.this, DisplayAllCustomer.class);
					CustomerUI.this.startActivity(myIntent);
					finish();
					break;
				case 1:
					myIntent = new Intent(CustomerUI.this, AddCustomer.class);
					CustomerUI.this.startActivity(myIntent);
					finish();
					break;
				case 2:
					myIntent = new Intent(CustomerUI.this, DeleteCustomer.class);
					CustomerUI.this.startActivity(myIntent);
					finish();
					break;
				case 3:
					myIntent = new Intent(CustomerUI.this, CustomerSearch.class);
					CustomerUI.this.startActivity(myIntent);
					finish();
					break;
			}
					
			
			}
	
	@Override
	public void onBackPressed()
	{
		finish();
		//Start CustomerMain Activity
		Intent myIntent=new Intent(CustomerUI.this, MainActivity.class);
		CustomerUI.this.startActivity(myIntent);
	}

}
