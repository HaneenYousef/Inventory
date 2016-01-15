package com.example.inventoryUI;

import java.util.ArrayList;
import java.util.LinkedList;

import com.example.inventory.R;
import com.example.inventoryBE.Customer;
import com.example.inventoryDB.DBCustomer;

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


public class SearchResult  extends Activity  {
	
	/** Called when the activity is first created. */
	DBCustomer DB ;
	ArrayAdapter<String> adapter ;
	ArrayList <String> names = new ArrayList<String>();
	LinkedList<Customer> customers = new LinkedList<Customer>();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_result_search);
		
		int size = getIntent().getIntExtra("size", 0);
		
		ListView list = (ListView)findViewById(R.id.listView1);
		Button delete = (Button)findViewById(R.id.button1);
		Button order = (Button)findViewById(R.id.button2);
		Button orderall = (Button)findViewById(R.id.button3);
		DB = new DBCustomer(this);
		
		for( int i = 0 ; i < size ; i++){
			customers.add( (Customer) getIntent().getSerializableExtra("result"+i));
			names.add(customers.get(i).GetName()+" address :"+customers.get(i).GetAddress());
		}
		
			orderall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SearchResult.this,DisplayOrder.class);
            	Bundle b = new Bundle();
            	b.putSerializable("order",customers.get(0) );
            	i.putExtras(b);
            	SearchResult.this.startActivity(i);
            	finish();
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
	            	showOKDialog("confirmation" , "Delete "+checkedItems.size() +"record are you sure ?"  , SearchResult.this);
	            }
			}
		});
		
		order.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ListView thelistview = (ListView) findViewById(R.id.listView1);
	             SparseBooleanArray checkedItems = thelistview.getCheckedItemPositions();
	            if(checkedItems.size() == 0){
	            	Toast.makeText(getBaseContext(), "No selected item", Toast.LENGTH_SHORT).show();
	            }
	            else if(checkedItems.size() > 1){
	            	Toast.makeText(getBaseContext(), "Select one customer", Toast.LENGTH_SHORT).show();
	   	       }
	            else {
	            	Intent i = new Intent(SearchResult.this,MakeOrder.class);
	            	Bundle b = new Bundle();
	            	b.putSerializable("order", customers.get(checkedItems.keyAt(0)));
	            	i.putExtras(b);
	            	SearchResult.this.startActivity(i);
	            	finish();
	            }
	            
			}
		});
		 adapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, names);
			 
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
		                    	DB.DeleteCustomer(customers.get(checkedItems.keyAt(i)).GetCustomerID());
		                        
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
		Intent myIntent=new Intent(SearchResult.this, CustomerSearch.class);
		SearchResult.this.startActivity(myIntent);
	}
}