package com.example.inventoryUI;

import java.util.ArrayList;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventoryBE.Product;
import com.example.inventoryDB.DBProduct;

public class EditProduct  extends Activity  {
	
	/** Called when the activity is first created. */
	DBProduct DB ;
	EditText name;
	 EditText price;
	 EditText quatity;
	 EditText unit ;
	 Product p ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_update);
		
		DB = new DBProduct(this);
		Button update = (Button)findViewById(R.id.button1);
		name = (EditText)findViewById(R.id.editText1);
		price = (EditText)findViewById(R.id.editText2);
	    quatity = (EditText)findViewById(R.id.editText3);
		unit = (EditText)findViewById(R.id.editText4);
		p = (Product) getIntent().getSerializableExtra("product");
		name.setText(p.getName());
		price.setText(""+p.getStandardPrice());
		quatity.setText(""+p.getQuantity());
		unit.setText(""+p.getUnit());
		update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(name.getText().toString().isEmpty() || price.getText().toString().isEmpty() 
						|| quatity.getText().toString().isEmpty() || unit.getText().toString().isEmpty()){
					Toast.makeText(EditProduct.this, "enter all data", Toast.LENGTH_SHORT).show();
				}
				else{
				Product product = new Product(name.getText().toString(),Double.parseDouble(price.getText().toString()), 
						Integer.parseInt(quatity.getText().toString()), Integer.parseInt(unit.getText().toString()));
						
						DB.UpdateProduct(product, p.getProdcutID());
						Toast.makeText(EditProduct.this, "the data Updated successfully ", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
	}
	public void onBackPressed()
	{
		
		//Start CustomerMain Activity
		Intent myIntent=new Intent(EditProduct.this, ProductSearch.class);
		EditProduct.this.startActivity(myIntent);
		finish();
	}
}