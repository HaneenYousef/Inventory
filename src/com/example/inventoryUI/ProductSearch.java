package com.example.inventoryUI;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventoryBE.Product;
import com.example.inventoryDB.DBCustomer;
import com.example.inventoryDB.DBProduct;

public class ProductSearch extends Activity  {
	DBProduct DB;
	LinkedList<Product> Products = new LinkedList<Product>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_search);
		DB = new DBProduct(this);
		Button btn = (Button)findViewById(R.id.button1);
		final EditText txt = (EditText)findViewById(R.id.editText1);
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if( txt.getText().toString().isEmpty()){
					Toast.makeText(ProductSearch.this, "fill all value", Toast.LENGTH_SHORT).show();
				}
				else {
				Intent intent = new Intent(ProductSearch.this , SearchResultProduct.class);
				//int selectedId = myGr.getCheckedRadioButtonId();
				
					Products = DB.Search( txt.getText().toString());
					if (Products.size() == 0){
						Toast.makeText(ProductSearch.this, "No Result Founded", Toast.LENGTH_SHORT).show();
					}
					else {
					for( int i = 0 ; i < Products.size() ; i++){
							
						intent.putExtra("result"+i, Products.get(i));
						
					
						
					}
					intent.putExtra("size", Products.size());
					ProductSearch.this.startActivity(intent);
				}
				}
			}
				
			
		});
		
		
	}
	public void onBackPressed()
	{
		finish();
		//Start CustomerMain Activity
		Intent myIntent=new Intent(ProductSearch.this, ProductUI.class);
		ProductSearch.this.startActivity(myIntent);
	}
}
