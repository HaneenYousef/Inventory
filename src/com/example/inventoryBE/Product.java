package com.example.inventoryBE;

import java.io.Serializable;

public class Product implements Serializable {
	private double StandardPrice;
	private int Quantity;
	private int Unit;
	private int ProdcutID;
	private String Name;
	
	public int getProdcutID() {
		return ProdcutID;
	}
	public void setProdcutID(int prodcutID) {
		ProdcutID = prodcutID;
	}
	public Product(String Name ,double StandardPrice, int Quantity , int Unit){
		this.Name = Name;
		this.Quantity = Quantity;
		this.Unit = Unit;
		this.StandardPrice = StandardPrice;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public double getStandardPrice() {
		return StandardPrice;
	}
	public void setStandardPrice(double standardPrice) {
		StandardPrice = standardPrice;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public int getUnit() {
		return Unit;
	}
	public void setUnit(int unit) {
		Unit = unit;
	}
	
	@Override
	public String toString()
	{
		return ("\n" +"Product Name : "+this.Name+ "\nstandard Price : " + this.StandardPrice + "\n" + "Quantity : " + this.Quantity + "\n" + "Unit : " + this.Unit + "\n");
	}
	@Override
	public boolean equals (Object P)
	{
		if (P instanceof Product) 
		{
			if (((Product)P).getUnit()== Unit && ((Product)P).getStandardPrice()== StandardPrice && ((Product)P).getQuantity()== Quantity )
				return true; 
			else 
				return false; 
		}
		else 
			return false; 
	}
}
