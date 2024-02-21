package org.qa.shop;

public class Product {
    private final String name;
    private final String description;
    private int amount;
    private final double pricePerItem;

    public Product(String _name, String _description, int _amount, double _pricePerItem) {
        name = _name;
        description = _description;
        amount = _amount;
        pricePerItem = _pricePerItem;
    }

    public String getName() {
        return name;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public int getAmount() {
        return amount;
    }

    public void increaseAmount(int n) {
        amount += n;
    }

    public void decreaseAmount(int n) {
        if (n <= amount) {
            amount -= n;
            return;
        }
        System.out.println("Input number is more than current amount");
    }

    @Override
    public String toString() {
        return "Name: "+name+" ("+description+"). Price: "+pricePerItem+". (Available items: "+amount+").";
    }
}