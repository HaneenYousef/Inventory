package com.example.inventoryUI;

import java.util.ArrayList;
import java.util.LinkedList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventoryBE.Customer;
import com.example.inventoryBE.Order;
import com.example.inventoryDB.DBOrder;

public class DisplayOrder  extends Activity {
			
			DBOrder DBO ;
			ListView list ;
			ArrayList <String> names = new ArrayList<String>();
			LinkedList<Order> orders = new LinkedList<Order>();
			ArrayAdapter<String> adapter ;
			@Override
			public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.customer_order);
				
				DBO = new DBOrder(this);
				Customer customer = (Customer) getIntent().getSerializableExtra("order");
				orders = DBO.getOderByCustomer(customer.GetCustomerID());
				for( int i = 0 ; i < orders.size() ; i++){
					names.add("Id : "+orders.get(i).getOrderId()+" due date"+orders.get(i).getOrderDueDate());
				}
			     adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice, names);
			     list = (ListView)this.findViewById(R.id.listView1);
			     list.setAdapter(adapter);
			        
			    	
			    Button delete = (Button)findViewById(R.id.button1);
			    delete.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						 SparseBooleanArray checkedItems = list.getCheckedItemPositions();
				            if(checkedItems.size() == 0){
				            	Toast.makeText(getBaseContext(), "No selected item", Toast.LENGTH_SHORT).show();
				            }
				            else {
				            	showOKDialog("confirmation" , "Delete "+checkedItems.size() +"record are you sure ?"  , DisplayOrder.this);
				            }
					}
				});
				
				
			}
			
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
									
									
						            SparseBooleanArray checkedItems = list.getCheckedItemPositions();
						            
						            for (int i=0; i<checkedItems.size(); i++) {
					                    if (checkedItems.valueAt(i)) {
					                    	names.remove(checkedItems.keyAt(i));
					                    	DBO.DeleteOrder(orders.get(checkedItems.keyAt(i)).getOrderId());
					                        
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

}
