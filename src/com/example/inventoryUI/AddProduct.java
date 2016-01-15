package com.example.inventoryUI;

import com.example.inventory.R;
import com.example.inventoryBE.Customer;
import com.example.inventoryBE.Product;
import com.example.inventoryDB.DBCustomer;
import com.example.inventoryDB.DBProduct;

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

public class AddProduct extends Activity {
	DBProduct DB ;
	EditText name;
	EditText standardPrice;
	 EditText quantity;
	 EditText unit;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_order);
		Button add = (Button)findViewById(R.id.add);

		name = (EditText)findViewById(R.id.editText4);
		standardPrice = (EditText)findViewById(R.id.editText1);
		quantity = (EditText)findViewById(R.id.editText2);
		unit = (EditText)findViewById(R.id.editText3);
		DB = new DBProduct(this); 
		add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(name.getText().toString().isEmpty() || standardPrice.getText().toString().isEmpty() 
						|| quantity.getText().toString().isEmpty() || unit.getText().toString().isEmpty()){
					Toast.makeText(AddProduct.this, "enter all data", Toast.LENGTH_SHORT).show();
				}
				else
				showOKDialog("confirmation" , "ADD to Product Table ?"  , AddProduct.this);
				
	
				
				
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
							Product product = new Product(name.getText().toString(),Double.parseDouble(standardPrice.getText().toString()), 
							Integer.parseInt(quantity.getText().toString()), Integer.parseInt(unit.getText().toString()));
							DB.insertProduct(product);
							Toast.makeText(getApplicationContext(), "product ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show(); 
							//hide the activity
							
							//Start Product Main Activity
							name.setText("");
							quantity.setText("");
							unit.setText("");
							standardPrice.setText("");
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
	@Override
	public void onBackPressed()
	{
		finish();
		//Start CustomerMain Activity
		Intent myIntent=new Intent(AddProduct.this, ProductUI.class);
		AddProduct.this.startActivity(myIntent);
	}

}
