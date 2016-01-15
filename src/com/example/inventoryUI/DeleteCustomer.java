package com.example.inventoryUI;

import java.util.ArrayList;
import java.util.LinkedList;

import com.example.inventory.MainActivity;
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

public class DeleteCustomer extends ListActivity {
	
		DBCustomer DB ;
		ArrayList <String> names = new ArrayList<String>();
		LinkedList<Customer> customers = new LinkedList<Customer>();
		ArrayAdapter<String> adapter ;

		public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.delete_customer);
			final Context cont = this;
		    DB = new DBCustomer(cont); 
		    
			customers = DB.GetAllCustomers();
			for( int i = 0 ; i < customers.size() ; i++){
				names.add(customers.get(i).GetName());
			}
		     adapter = new ArrayAdapter<String>(this,
		            android.R.layout.simple_list_item_multiple_choice, names);
		        setListAdapter(adapter);
		        
		    	
		    	Button delete = (Button)findViewById(R.id.button2);
				
							
				delete.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ListView thelistview = (ListView) findViewById(android.R.id.list);
			            ArrayList<String> items = new ArrayList<String>();

			            SparseBooleanArray checkedItems = thelistview.getCheckedItemPositions();
			            if(checkedItems.size() == 0){
			            	Toast.makeText(getBaseContext(), "No selected item", Toast.LENGTH_SHORT).show();
			            }
			            else {
			            	showOKDialog("confirmation" , "Delete "+checkedItems.size() +"record are you sure ?"  , cont);
			            }

					}
				});
		   }

		  @Override
		  public void onListItemClick(ListView l, View v, int position, long id) {
		        // Do something with the data
		  }
		  private void showOKDialog(String title , String msg , Context con)
			{
				final AlertDialog alertDialog = new AlertDialog.Builder(con).create();
				alertDialog.setTitle(title);
				alertDialog.setMessage(msg);
				alertDialog.setButton("Yes", new
						DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								
								ListView thelistview = (ListView) findViewById(android.R.id.list);
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
				Intent myIntent=new Intent(DeleteCustomer.this, CustomerUI.class);
				DeleteCustomer.this.startActivity(myIntent);
			}
		
}
