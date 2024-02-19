package org.qa.shop;
import java.util.ArrayList;

public class CustomerProfile {
    private final String customerName;
    private double totalPay;
    private boolean isDiscountUsed;
    private final ArrayList<CartItem> personalCart;
    CustomerProfile(String _customerName) {
        customerName = _customerName;
        isDiscountUsed = false;
        totalPay = 0;
        personalCart = new ArrayList<>();
    }

    public boolean isDiscountUsed() {
        return isDiscountUsed;
    }

    public void setDiscountStatus(boolean isAvailable) {
        if (isDiscountUsed != isAvailable) {
            isDiscountUsed = !isDiscountUsed;
        }
    }

    public void addToTotalPay(double amount) {
        if (amount != 0.00) {
            totalPay += amount;
        }
    }

    public void resetTotalPayToZero() {
        totalPay = 0;
    }

    private int findIndexOfItemById(int id) {
        int index = 0;
        for (CartItem item : personalCart) {
            if (item.getProductId() == id) {
                return index;
            }
                index++;
        }
        return -1;
    }

    public void addToCart(int productId, String productName, double productPrice) {
        int cartIndex = findIndexOfItemById(productId);
        if (cartIndex != -1) {
            CartItem cartItem = personalCart.get(cartIndex);
            cartItem.increaseAmount();
        }
        else {
            personalCart.add(new CartItem(productId, productName, productPrice));
        }
        totalPay += productPrice;
        System.out.println("Item "+productName+" was added to Cart.");
        System.out.println("Total price for pay: "+totalPay);
    }

    public int deleteFromCart(int indexOfItem) {
        indexOfItem--;
        if (indexOfItem > -1 && indexOfItem < personalCart.size()) {
            CartItem cartItem = personalCart.get(indexOfItem);
            cartItem.decreaseAmount();
            totalPay -= cartItem.getPrice() * cartItem.getAmount();
            System.out.println("Item "+cartItem.getName()+" was deleted");
            return cartItem.getAmount() == 0 ? personalCart.remove(indexOfItem).getProductId() : cartItem.getProductId();
        }
        System.out.println("Item is not present on the Cart");
        return -1;
    }

    public CartItem[] clearCartAndGetRemovedItems() {
        if (!personalCart.isEmpty()) {
            int i = 0;
            CartItem[] removedItems = new CartItem[personalCart.size()];
            for (CartItem item : personalCart) {
                removedItems[i] = item;
                totalPay -= item.getPrice() * item.getAmount();
                i++;
            }
            personalCart.clear();
            return removedItems;
        }
        System.out.println("Nothing was removed. List is empty");
        return null;
    }

    public void printCartList() {
        if (!personalCart.isEmpty()) {
            int index = 1;
            System.out.println("***** List of added Cart items: *****");
            for (CartItem item : personalCart) {
                System.out.print("#" + index + " - ");
                System.out.println(item);
                index++;
            }
            System.out.println("**********************************");
            System.out.println("Total pay: " + totalPay);
            return;
        }
        System.out.println("Personal cart is empty.");
    }

    @Override
    public String toString() {
        return customerName;
    }
}
