package com.example.webpos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {
    private Product product;
    private int quantity;

    public int getQuantity() {
        return this.quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Item() {
        this.product = null;
        this.quantity = 0;
    }
}
