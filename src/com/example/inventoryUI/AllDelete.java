package com.example.inventoryUI;

import java.util.ArrayList;
import java.util.LinkedList;
import com.example.inventoryBE.Customer;
import com.example.inventoryDB.DBCustomer;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AllDelete extends ListActivity  {
	
	DBCustomer DB ;
	ArrayList <String> names = new ArrayList<String>();
	LinkedList<Customer> customers = new LinkedList<Customer>();
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final Context cont = this;
	    DB = new DBCustomer(cont); 
	    
		customers = DB.GetAllCustomers();
		for( int i = 0 ; i < customers.size() ; i++){
			names.add(customers.get(i).GetName());
		}
		
		ListView lstView = getListView();
		//lstView.setChoiceMode(0); //CHOICE_MODE_NONE
		//lstView.setChoiceMode(1); //CHOICE_MODE_SINGLE
		lstView.setChoiceMode(2); //CHOICE_MODE_MULTIPLE
		lstView.setTextFilterEnabled(true);
		
		//names.add("haneen");
		// setContentView(R.layout.main);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names));
		
		
	}
	
	public void onListItemClick(
			ListView parent, View v, int position, long id)
			{
				
			
				//---toggle the check displayed next to the item---
				parent.setItemChecked(position, parent.isItemChecked(position));
				//showDialog(position);
				//Open Activity
				/*Intent myIntent = new Intent(AllDelete.this, DisplayOneCustomer.class).
						putExtra("customer", customers.get(position).toString());
				AllDelete.this.startActivity(myIntent);
				finish();*/
			
			}

/*Override

	protected Dialog onCreateDialog(int id) {
		
			return new AlertDialog.Builder(this)
			.setIcon(R.drawable.ic_launcher)
			.setTitle(customers.get(id).toString())
			.setPositiveButton("OK", new
					DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
						int whichButton)
				{
					Toast.makeText(getBaseContext(),
							"OK clicked!", Toast.LENGTH_SHORT).show();
				}
			}).create();
		
		
		
	}
*/
	

}
