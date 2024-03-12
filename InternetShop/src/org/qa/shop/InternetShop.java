package org.qa.shop;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InternetShop {
    private final ArrayList<Product> products;
    private final Map<String, CustomerProfile> customers;
    private final Map<String, ArrayList<CartItem[]>> purchaseStory;
    private final Map<String, DiscountType> discounts;

    public InternetShop() {
        products = new ArrayList<>();
        customers = new HashMap<>();
        purchaseStory = new HashMap<>();
        discounts = new HashMap<>();
    }

    public void uploadProducts(Product[] productsArr) {
        if (productsArr.length > 0) {
            products.addAll(Arrays.asList(productsArr));
        }
    }

    public void uploadListOfDiscounts(HashMap<String, DiscountType> _discounts) {
        if (!_discounts.isEmpty()) {
            for (String key : _discounts.keySet()) {
                if (!discounts.containsKey(key)) {
                    discounts.put(key, _discounts.get(key));
                }
            }
        }
    }

    private String generateCustomerKey(String name) {
        return name+"@user";
    }

    public String getRandomDiscountCode() {
        String[] codes = discounts.keySet().toArray(new String[0]);
        return codes[(int) (Math.random() * codes.length)];
    }

    public boolean isDiscountCodeCorrect(String code) {
        return  discounts.containsKey(code);
    }
    public DiscountType getDiscount(String code) {
        if (discounts.containsKey(code)) {
            return discounts.get(code);
        }
        return DiscountType.ZERO;
    }

    public boolean isCustomerExist(String name) {
        return customers.containsKey(generateCustomerKey(name));
    }

    public void registerNewCustomer(String name) {
        String key = generateCustomerKey(name);
        if (!customers.containsKey(key)) {
            customers.put(key, new CustomerProfile(name));
            purchaseStory.put(key, new ArrayList<>());
        }
    }

    public CustomerProfile getCustomer(String name) {
        String key = generateCustomerKey(name);
        if (customers.containsKey(key)) {
            return customers.get(key);
        }
        return null;
    }

    public void printListOfProducts() {
        if (!products.isEmpty()) {
            int index = 1;
            System.out.println("***** Products: *****");
            for (Product product : products) {
                System.out.print("#"+index+" - ");
                System.out.println(product);
                index++;
            }
            System.out.println("***** ****** *****");
        }
    }

    public Product getProductById(int productId) {
        --productId;
        if (productId > -1 && productId < products.size()) {
            return products.get(productId);
        }
        return null;
    }

    public void purchaseOrder(String userName, CartItem[] listFromCart) {
        String key = generateCustomerKey(userName);
        if (purchaseStory.containsKey(key) && listFromCart.length > 0) {
            purchaseStory.get(key).add(listFromCart);
        }
    }

    public void printPurchaseHistory(String userName) {
        String key = generateCustomerKey(userName);
        if (purchaseStory.containsKey(key) && !purchaseStory.get(key).isEmpty()) {
            int numberItem = 0;
            System.out.println("**** List of purchase history: *****");
            for (CartItem[] list : purchaseStory.get(key)) {
                numberItem++;
                System.out.println("#"+numberItem);
                    for (CartItem item : list) {
                        System.out.print("* - ");
                        System.out.println(item);
                    }
                System.out.println("****************");
            }
            System.out.println("**** End of List *****");
            return;
        }
        System.out.println("Record was not found");
    }

    @Override
    public String toString() {
        return "IShop";
    }
}
