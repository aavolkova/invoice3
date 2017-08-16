package me.ratna.invoice2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long idT;
    private long itemId;
    private int aQuantity;
    private String description;
    private double price;
    private  double taxPrice;
    private double totalPrice;


    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }



    public long getIdT() {
        return idT;
    }
    public int getAQuantity() {
        return aQuantity;
    }
    public void setAQuantity(int aQuantity) {
        this.aQuantity = aQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(double taxPrice) {
        this.taxPrice = taxPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = aQuantity*(taxPrice+price);
    }
}
