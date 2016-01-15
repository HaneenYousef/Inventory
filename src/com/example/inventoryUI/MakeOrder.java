package com.example.inventoryUI;

import java.util.LinkedList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventoryBE.Customer;
import com.example.inventoryBE.Order;
import com.example.inventoryBE.OrderDetails;
import com.example.inventoryBE.Product;
import com.example.inventoryDB.DBOrder;
import com.example.inventoryDB.DBOrderDetails;
import com.example.inventoryDB.DBProduct;

public class MakeOrder extends Activity  {
	Customer c;
	DBProduct DBP;
	DBOrder DBO;
	DBOrderDetails DBD;
	EditText product;
	EditText quantity;
	EditText price;
	DatePicker date;
	DatePicker dueDate;
	 @Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.make_order);
			DBP = new DBProduct(this);
			DBO = new DBOrder(this);
			DBD = new DBOrderDetails(this);
			c = (Customer) getIntent().getSerializableExtra("order");
			Button addOrder = (Button)findViewById(R.id.button1);
			product = (EditText)findViewById(R.id.editText1);
			quantity = (EditText)findViewById(R.id.editText2);
			price = (EditText)findViewById(R.id.editText3);
			date = (DatePicker)findViewById(R.id.datePicker1);
			dueDate = (DatePicker)findViewById(R.id.datePicker1);
			final String date1 = date.getDayOfMonth()+"/"+date.getMonth()+"/"+date.getYear();
			final String date2 = dueDate.getDayOfMonth()+"/"+dueDate.getMonth()+"/"+dueDate.getYear();
			
			addOrder.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					LinkedList<Product> pro = DBP.Search(product.getText().toString());
					if(product.getText().toString().isEmpty() ||quantity.getText().toString().isEmpty() ||price.getText().toString().isEmpty()){
						Toast.makeText(MakeOrder.this, "fill all data", Toast.LENGTH_SHORT).show();
					}
					else if (pro.size() == 0 ){
						Toast.makeText(MakeOrder.this, "Product Name does not exist", Toast.LENGTH_SHORT).show();
					}
					// check if there is enough quantity
					else if (pro.get(0).getQuantity() < Integer.parseInt(quantity.getText().toString())){
						Toast.makeText(MakeOrder.this, "The exist quantity is not enough", Toast.LENGTH_SHORT).show();
						
					}
					else{
						Order order = new Order(date1,date2,c.GetCustomerID());
						LinkedList<Product> products = DBP.Search(product.getText().toString());
						DBO.insertOrder(order);
						int orderID = DBO.getOderID();
						int Quantity = pro.get(0).getQuantity() - Integer.parseInt(quantity.getText().toString());
						Product newPro = new Product(pro.get(0).getName(),pro.get(0).getStandardPrice(),Quantity , pro.get(0).getUnit());
						// udpate quantity in product table
						DBP.UpdateProduct(newPro, pro.get(0).getProdcutID());
						//insertOder into dataBase
						
						//add order to database
						OrderDetails orderDetails = new OrderDetails(orderID , products.get(0).getProdcutID() , Double.parseDouble(price.getText().toString()) ,Integer.parseInt(quantity.getText().toString()));
						DBD.insertOrderDetails(orderDetails);
						Toast.makeText(MakeOrder.this, "ADD Successfully", Toast.LENGTH_SHORT).show();
						
					}
				
				}
			});
	
	 }
}