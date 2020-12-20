package com.SAP.model;

import java.io.Serializable;

public class Products implements Serializable {
    private final String id;
    private final String name;
    private int quantity;
    private double price;


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public int getQuantity() {
        return quantity;
    }


    public double getPrice() {
        return price;
    }


    public Products(String id, String name, int quantity, double price,Otchet otchet) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        otchet.setTotalMoneySpent(otchet.getTotalMoneySpent()+(price*0.7)*quantity);
    }

    @Override
    public String toString() {
        return "Products{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
    public void sell(int i){
        if(quantity<i)
            System.out.println("not enough");
        else
            quantity-=i;
    }

}
