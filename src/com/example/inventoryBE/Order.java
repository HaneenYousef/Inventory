package com.example.inventoryBE;

public class Order {

	public int OrderId;
	public String OrderDate;
	public String OrderDueDate;
	public int CustomerID;
	public String VoidInd;
	public String CustomerName;
	
		
	public Order( String OrderDate, String OrderDueDate, int CustomerId)
	{
 
		this.OrderDate = OrderDate;
		this.OrderDueDate = OrderDueDate; 
	    this.CustomerID=CustomerId;
	}
	
	
	
	@Override
	public String toString()
	{
		return ("\n" + "Order ID: " + OrderId + "\n" + "Order  Date: " + OrderDate + "\n" + "Order Due Date: " + OrderDueDate + "\n" + "Customer's ID: " + CustomerID + "\n");
	}
	
	
	@Override
	public boolean equals (Object P)
	{
		if (P instanceof Order) 
		{
			if (( ((Order)P).OrderDate.equals(OrderDate)) && ((Order)P).OrderDueDate.equals(OrderDueDate))
				return true; 
			else 
				return false; 
		}
		else 
			return false; 
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public String getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}

	public String getOrderDueDate() {
		return OrderDueDate;
	}

	public void setOrderDueDate(String orderDueDate) {
		OrderDueDate = orderDueDate;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public String getVoidInd() {
		return VoidInd;
	}

	public void setVoidInd(String voidInd) {
		VoidInd = voidInd;
	}
	
	

}
