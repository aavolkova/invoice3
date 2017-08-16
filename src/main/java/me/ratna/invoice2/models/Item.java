package me.ratna.invoice2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Item {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idItem;

    private final double TAX_RATE=0.06;

    private String description;
    private double price;
    private int quantity;
    private  double taxPrice;
    private double totalPrice;

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public long getIdItem() {
        return idItem;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice() {
        this.taxPrice = TAX_RATE*price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = this.price + this.taxPrice;
    }

}
