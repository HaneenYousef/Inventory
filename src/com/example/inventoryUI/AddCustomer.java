package com.example.inventoryUI;

import com.example.inventoryBE.Customer;
import com.example.inventoryDB.DBCustomer;
import com.example.inventory.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddCustomer extends Activity {
	
	EditText Address;
	EditText Name; 
	EditText PhoneNumber; 
	Button Add; 
	Button Back;
	DBCustomer DBC;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcustomer);
	    final Context cont = this;
	    DBC = new DBCustomer(cont); 
		
		Address = (EditText)findViewById(R.id.customer_address);
	    Name = (EditText)findViewById(R.id.Customer_name);
	    PhoneNumber = (EditText)findViewById(R.id.Customerphone);
	    Add = (Button)findViewById(R.id.add_customer);
	    Back = (Button)findViewById(R.id.cancel_add_customer);
	
	    
	    Add.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(Name.getText().toString().isEmpty()  || PhoneNumber.getText().toString().isEmpty() 
						|| Address.getText().toString().isEmpty() ){
					Toast.makeText(AddCustomer.this, "enter all data", Toast.LENGTH_SHORT).show();
				}
				else
				showOKDialog("confirmation" , "ADD to Customer Table ?"  , cont);
				
			}

		
		});
        
        Back.setOnClickListener(new View.OnClickListener() {		
			public void onClick(View v) {
				Intent intent = new Intent(AddCustomer.this , CustomerUI.class) ;
				AddCustomer.this.startActivity(intent);
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
						if(Name.getText().toString().isEmpty()  || PhoneNumber.getText().toString().isEmpty() 
								|| Address.getText().toString().isEmpty() ){
							Toast.makeText(AddCustomer.this, "enter all data", Toast.LENGTH_SHORT).show();
						}
						else {
							Customer CustomerOBJ = new Customer(Name.getText().toString(), PhoneNumber.getText().toString(), Address.getText().toString());
							DBC.insertCustomer(CustomerOBJ);
							Toast.makeText(getApplicationContext(), "Customer ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show(); 
							//hide the activity
							
							//Start Product Main Activity
							Name.setText("");
							Address.setText("");
							PhoneNumber.setText("");
							alertDialog.dismiss();
						}
					}
		});
		
		alertDialog.setButton2("No", new
				DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						alertDialog.dismiss();
		}});
	
		alertDialog.show();
	}
	
	@Override
	public void onBackPressed()
	{
		finish();
		//Start CustomerMain Activity
		Intent myIntent=new Intent(AddCustomer.this, CustomerUI.class);
		AddCustomer.this.startActivity(myIntent);
	}
	

}
