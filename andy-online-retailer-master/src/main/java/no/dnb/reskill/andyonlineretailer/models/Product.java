package no.dnb.reskill.andyonlineretailer.models;

import lombok.Data;

@Data
public class Product {

    private long id;
    private String description;
    private double price;
    private long inStock;

    public Product(){}

    public Product (String description, double price, long inStock){
        this(-1, description, price, inStock);

    }

    public Product(long id, String description, double price, long inStock) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
    }

    public void adjustPriceByPersent(double percent){
        price *= 1 + percent / 100;
    }
}
