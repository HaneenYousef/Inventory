package com.example.inventoryUI;

import java.util.ArrayList;
import java.util.LinkedList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventoryBE.Product;
import com.example.inventoryDB.DBCustomer;
import com.example.inventoryDB.DBProduct;

public class SearchResultProduct  extends Activity  {
	
	/** Called when the activity is first created. */
	DBProduct DB ;
	ArrayList <String> names = new ArrayList<String>();
	LinkedList<Product> Products = new LinkedList<Product>();
	ArrayAdapter<String> adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_search_result);
		int size = getIntent().getIntExtra("size", 0);
		DB = new DBProduct(this);
		for( int i = 0 ; i < size ; i++){
			Products.add( (Product) getIntent().getSerializableExtra("result"+i));
			names.add(Products.get(i).getName()+" Price: "+Products.get(i).getStandardPrice()+" Quantiy: "+Products.get(i).getQuantity());
		}
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, names);
		ListView list = (ListView)findViewById(R.id.listView1);
		Button delete = (Button)findViewById(R.id.button1);
		Button update = (Button)findViewById(R.id.button2);
		
		update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				ListView thelistview = (ListView) findViewById(R.id.listView1);
	            SparseBooleanArray checkedItems = thelistview.getCheckedItemPositions();
				if(checkedItems.size() == 0){
	            	Toast.makeText(getBaseContext(), "No selected item", Toast.LENGTH_SHORT).show();
	            }
	            else if (checkedItems.size() >1) {
	            	Toast.makeText(getBaseContext(), "Select one item", Toast.LENGTH_SHORT).show();
	  	          
	            }
	            else{
	            	Intent intent = new Intent(SearchResultProduct.this,EditProduct.class);
	            	Bundle b = new Bundle();
	            	b.putSerializable("product",Products.get(checkedItems.keyAt(0)));
	            	intent.putExtras(b);
	            	SearchResultProduct.this.startActivity(intent);
	            	
	            }
			}
		});
		
		delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ListView thelistview = (ListView) findViewById(R.id.listView1);
	            ArrayList<String> items = new ArrayList<String>();

	            SparseBooleanArray checkedItems = thelistview.getCheckedItemPositions();
	            if(checkedItems.size() == 0){
	            	Toast.makeText(getBaseContext(), "No selected item", Toast.LENGTH_SHORT).show();
	            }
	            else {
	            	showOKDialog("confirmation" , "Delete "+checkedItems.size() +"record are you sure ?"  , SearchResultProduct.this);
	            }
			}
		});
		
		
		list.setAdapter(adapter);
	}
	
	private void showOKDialog(String title , String msg , Context con)
	{
		final AlertDialog alertDialog = new AlertDialog.Builder(con).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(msg);
		alertDialog.setButton("Yes", new
				DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
						ListView thelistview = (ListView) findViewById(R.id.listView1);
			            ArrayList<String> items = new ArrayList<String>();
			            SparseBooleanArray checkedItems = thelistview.getCheckedItemPositions();
			            
			            for (int i=0; i<checkedItems.size(); i++) {
		                    if (checkedItems.valueAt(i)) {
		                    	names.remove(checkedItems.keyAt(i));
		                    	DB.DeleteProduct(Products.get(checkedItems.keyAt(i)).getProdcutID());
		                        
		                    }
		                }
		         
			            adapter.notifyDataSetChanged();
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
	
	public void onBackPressed()
	{
		finish();
		//Start CustomerMain Activity
		Intent myIntent=new Intent(SearchResultProduct.this, ProductSearch.class);
		SearchResultProduct.this.startActivity(myIntent);
	}
}
