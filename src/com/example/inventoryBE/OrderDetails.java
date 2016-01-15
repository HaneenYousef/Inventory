package com.example.inventoryBE;

public class OrderDetails {

	public int OrderDetailID;
	public int OrderID;
	public int ProductID;
	public double FinalPrice;
	public int Quantity;
	public String VoidInd;
	
	
public OrderDetails( int OrderID, int ProductID ,Double FinalPrice,int Quantity)
{

	this.OrderID = OrderID;
	this.ProductID= ProductID; 
this.FinalPrice=FinalPrice;
this.Quantity =Quantity;
}



@Override
public String toString()
{
	return ("\n" + "Order ID: " + OrderID + "\n" + "Product ID: " + ProductID + "\n" + "Final Price: " + FinalPrice + "\n" + "Quantity: " + Quantity + "\n");
}



	
	
	public int getOrderDetailID() {
		return OrderDetailID;
	}
	public void setOrderDetailID(int orderDetailID) {
		OrderDetailID = orderDetailID;
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public double getFinalPrice() {
		return FinalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		FinalPrice = finalPrice;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public String getVoidInd() {
		return VoidInd;
	}
	public void setVoidInd(String voidInd) {
		VoidInd = voidInd;
	}
}
