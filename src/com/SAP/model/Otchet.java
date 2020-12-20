package com.SAP.model;

import java.io.Serializable;

public class Otchet implements Serializable {
    private double totalPayment;
    private double totalPrihodi;
    private double totalMoneySpent;

    public double getTotalPayment(){
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public double getTotalPrihodi() {
        return totalPrihodi;
    }

    public void setTotalPrihodi(double totalPrihodi) {
        this.totalPrihodi = totalPrihodi;
    }

    public double getTotalMoneySpent() {
        return totalMoneySpent;
    }

    public void setTotalMoneySpent(double totalMoneySpent) {
       this.totalMoneySpent = totalMoneySpent;
    }
}
