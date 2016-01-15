package com.example.inventoryUI;

import java.util.ArrayList;
import java.util.LinkedList;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.example.inventoryBE.Product;
import com.example.inventoryDB.DBProduct;

public class DisplayAllProdcut  extends ListActivity  {
	
	DBProduct DB ;
	ArrayList <String> names = new ArrayList<String>();
	LinkedList<Product> products = new LinkedList<Product>();
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final Context cont = this;
	    DB = new DBProduct(cont); 
	    
		products = DB.GetAllProducts();
		for( int i = 0 ; i < products.size() ; i++){
			names.add(products.get(i).toString());
		}

		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names));
		
		
	}
	@Override
	public void onBackPressed()
	{
		finish();
		//Start CustomerMain Activity
		Intent myIntent=new Intent(DisplayAllProdcut.this, ProductUI.class);
		DisplayAllProdcut.this.startActivity(myIntent);
	}
	
}