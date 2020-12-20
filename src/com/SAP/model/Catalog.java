package com.SAP.model;

import java.io.Serializable;

public class Catalog implements Serializable {
    private final String id;
    private final String prodID;
    private final double cena;
    private final String name;
    private final String email;
    private final int date;
    private final String prodname;
    private final int quantity;

    public Catalog(String id, String prodID, double cena, String name, String email,int date,String prodname,int quantity) {
        this.id = id;
        this.prodID = prodID;
        this.prodname=prodname;
        this.cena = cena;
        this.name = name;
        this.email = email;
        this.quantity = quantity;
        this.date = date;

    }

    public int getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id='" + id + '\'' +
                ", prodID='" + prodID + '\'' +
                ", prodname='" + prodname + '\'' +
                ", quantity=" + quantity +
                ", cena=" + cena +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
