package org.qa.shop;

public class CartItem {
    private final int productId;
    private final String name;
    private final double price;
    private int amount;

    CartItem(int _productId, String _name,  double _price) {
        productId = _productId;
        name = _name;
        price = _price;
        amount = 1;
    }

    public int getProductId() {
        return  productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void increaseAmount() {
        amount++;
    }

    public void decreaseAmount() {
        if (amount > 0) {
            amount--;
        }
    }

    @Override
    public String toString() {
        return "Added "+name+". (Amount: "+amount+").";
    }
}
