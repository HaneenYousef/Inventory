package com.example.inventoryUI;

import java.io.Serializable;
import java.util.LinkedList;

import com.example.inventory.R;
import com.example.inventoryBE.Customer;
import com.example.inventoryBE.Product;
import com.example.inventoryDB.DBCustomer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerSearch extends Activity  {
	DBCustomer DB;
	LinkedList<Customer> customers = new LinkedList<Customer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchcustomer);
		Button btn = (Button) findViewById(R.id.search);
		final RadioButton name = (RadioButton)findViewById(R.id.name);
		final RadioButton address = (RadioButton)findViewById(R.id.address);
		final EditText value  = (EditText)findViewById(R.id.value);

		DB = new DBCustomer(this);
		btn.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				
				if(! name.isChecked()&& !address.isChecked() || value.getText().toString().isEmpty())
					Toast.makeText(CustomerSearch.this, "select a choice and enter a value please",Toast.LENGTH_SHORT).show();
				//int selectedId = myGr.getCheckedRadioButtonId();
				else {
					Intent intent = new Intent(CustomerSearch.this , SearchResult.class);
					if(name.isChecked()){
				
					customers = DB.SearchCustomer(1, value.getText().toString());
					if (customers.size() == 0){
						Toast.makeText(CustomerSearch.this, "No Record founded",Toast.LENGTH_SHORT).show();
						
					}
					else {
						for( int i = 0 ; i < customers.size() ; i++){
					
	
						
						intent.putExtra("result"+i, customers.get(i));
						
					
						
					}
					intent.putExtra("size", customers.size());
					CustomerSearch.this.startActivity(intent);
					}
					
				}
			
				if(address.isChecked()){
					customers = DB.SearchCustomer(2, value.getText().toString());
					if (customers.size() == 0){
						Toast.makeText(CustomerSearch.this, "No Record founded",Toast.LENGTH_SHORT).show();
						
					}
					else{
					for( int i = 0 ; i < customers.size() ; i++){
					
						
						intent.putExtra("result"+i, customers.get(i));
						
					
						
					}
					intent.putExtra("size", customers.size());
					CustomerSearch.this.startActivity(intent);
					}
		
					}
				}
				
						
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_customer_search, menu);
		return true;
	}
	
	public void onBackPressed()
	{
		finish();
		//Start CustomerMain Activity
		Intent myIntent=new Intent(CustomerSearch.this, CustomerUI.class);
		CustomerSearch.this.startActivity(myIntent);
	}
	

}
