package com.example.inventoryUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventoryBE.Product;
import com.example.inventoryDB.DBProduct;

public class EditProductWithID  extends Activity  {
	
	/** Called when the activity is first created. */
	DBProduct DB ;
	EditText name;
	 EditText price;
	 EditText quatity;
	 EditText id;
	 EditText unit ;


	 @Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.pr_id_update);
			
			DB = new DBProduct(this);
			Button update = (Button)findViewById(R.id.button1);
			Button search = (Button)findViewById(R.id.button2);
			id = (EditText)findViewById(R.id.editText1);
			name = (EditText)findViewById(R.id.editText2);
			price = (EditText)findViewById(R.id.editText3);
		    quatity = (EditText)findViewById(R.id.editText4);
			unit = (EditText)findViewById(R.id.editText5);

			search.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(EditProductWithID.this,ProductSearch.class);
					EditProductWithID.this.startActivity(i);
					finish();
				}
			});
			update.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(name.getText().toString().isEmpty() || price.getText().toString().isEmpty() 
							|| quatity.getText().toString().isEmpty() || unit.getText().toString().isEmpty()
							|| id.getText().toString().isEmpty()){
						
						Toast.makeText(EditProductWithID.this, "enter all data", Toast.LENGTH_SHORT).show();
					}
					
					else{
						
						if(DB.SearchByID( Integer.parseInt(id.getText().toString())) != null){
							Product product = new Product(name.getText().toString(),Double.parseDouble(price.getText().toString()), 
							Integer.parseInt(quatity.getText().toString()), Integer.parseInt(unit.getText().toString()));
							DB.UpdateProduct(product, Integer.parseInt(id.getText().toString()));
							Toast.makeText(EditProductWithID.this, "data Updated", Toast.LENGTH_SHORT).show();
							name.setText("");
							id.setText("");
							quatity.setText("");
							price.setText("");
							unit.setText("");
							
						}
						else {
							Toast.makeText(EditProductWithID.this, "Entered Id not founded you can search for the product", Toast.LENGTH_SHORT).show();
						}
						
					}
				}
			});
			
		}
		public void onBackPressed()
		{
			
			//Start CustomerMain Activity
			Intent myIntent=new Intent(EditProductWithID.this, ProductUI.class);
			EditProductWithID.this.startActivity(myIntent);
			finish();
		}
}