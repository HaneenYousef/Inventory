package com.example.inventoryUI;
import com.example.inventory.R;
import com.example.inventoryBE.Customer;
import com.example.inventoryDB.DBCustomer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayOneCustomer extends Activity {
	
	TextView customerDetails;
	String s;
	int ID;
	Customer customer;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_one_customer);
		customerDetails = (TextView)findViewById(R.id.customer_details);
		Button delete = (Button)findViewById(R.id.button1);
		Button order = (Button)findViewById(R.id.button2);
		Button orderall = (Button)findViewById(R.id.button3);
		s = getIntent().getStringExtra("customer");
		customer = (Customer) getIntent().getSerializableExtra("order");
		ID = customer.GetCustomerID();
		customerDetails.setText(s);
		
		delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showOKDialog("confirmation" , "Delete Customer Table ?"  , DisplayOneCustomer.this);
			}
		});
		
		order.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(DisplayOneCustomer.this,MakeOrder.class);
            	Bundle b = new Bundle();
            	b.putSerializable("order",customer );
            	i.putExtras(b);
            	DisplayOneCustomer.this.startActivity(i);
            	finish();
				
			}
		});
		
		orderall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(DisplayOneCustomer.this,DisplayOrder.class);
            	Bundle b = new Bundle();
            	b.putSerializable("order",customer );
            	i.putExtras(b);
            	DisplayOneCustomer.this.startActivity(i);
            	finish();
			}
		});
		
	}
	
	private void showOKDialog(String title , String msg , Context con)
	{
		final AlertDialog alertDialog = new AlertDialog.Builder(con).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(msg);
		alertDialog.setButton("Yes", new
				DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
							DBCustomer DB = new DBCustomer(DisplayOneCustomer.this);
							DB.DeleteCustomer(ID);
							//Start Product Main Activity
						
							alertDialog.dismiss();
						
					}
		});
		
		alertDialog.setButton2("No", new
				DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						alertDialog.dismiss();
		}});
	
		alertDialog.show();
	}
	
	protected Context getActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onBackPressed()
	{
		finish();
		//Start CustomerMain Activity
		Intent myIntent=new Intent(DisplayOneCustomer.this, DisplayAllCustomer.class);
		DisplayOneCustomer.this.startActivity(myIntent);
	}

}
