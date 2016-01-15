package com.example.inventoryUI;

import com.example.inventory.MainActivity;
import com.example.inventoryDB.DBAdapter;
import com.example.inventoryDB.DBProduct;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ProductUI extends ListActivity {
	String[] Labels = { "Display All Prodcut", "Add a new product",
			"Update a prodcut", "Search for a prodcuct" };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, Labels));

	}

	public void onListItemClick(ListView parent, View v, int position, long id) {
		Intent intent;
		switch (position) {
		case 0:
			intent = new Intent(ProductUI.this, DisplayAllProdcut.class);
			ProductUI.this.startActivity(intent);

			break;
		case 1:
			intent = new Intent(ProductUI.this, AddProduct.class);
			ProductUI.this.startActivity(intent);

			break;
		case 2:
			intent = new Intent(ProductUI.this, EditProductWithID.class);
			ProductUI.this.startActivity(intent);
			break;
		case 3:
			intent = new Intent(ProductUI.this, ProductSearch.class);
			ProductUI.this.startActivity(intent);
			break;
		}

	}

	@Override
	public void onBackPressed() {
		finish();
		// Start CustomerMain Activity
		Intent myIntent = new Intent(ProductUI.this, MainActivity.class);
		ProductUI.this.startActivity(myIntent);
	}

}
