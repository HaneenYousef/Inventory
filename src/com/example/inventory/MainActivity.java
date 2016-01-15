package com.example.inventory;

import com.example.inventoryDB.DBAdapter;

import com.example.inventoryUI.CustomerUI;
import com.example.inventoryUI.ProductUI;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button customerInvoice ;
	Button customer ;
	Button product ;
	Button order ;
	Button orderDetials ;
	DBAdapter DBA ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		DBAdapter DBA = new DBAdapter(this); 
		DBA.DBCreate();
		
		customer = (Button) findViewById(R.id.customer);
		order = (Button) findViewById(R.id.order);
		orderDetials = (Button) findViewById(R.id.orderDetails);
		product = (Button) findViewById(R.id.product);
		
		customer.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v)
			{
				Intent myIntent = new Intent(MainActivity.this, CustomerUI.class);
				MainActivity.this.startActivity(myIntent);
				finish();
			}
			});
		order.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
			}
		});
		
		product.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(MainActivity.this,ProductUI.class);
				MainActivity.this.startActivity(myIntent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
