import org.qa.shop.*;
import java.util.HashMap;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static String getInputDataAfterCheck(InputType inputType) {
        Scanner scanner = new Scanner(System.in);
        String inputTxt = null;
        System.out.println("Input "+inputType);
        while (true) {
            inputTxt = scanner.nextLine();
            if (inputTxt.matches(inputType.getRegex())) return inputTxt;
            System.out.println(inputType.getErrorMessage());
        }
    }

    public static void main(String[] args) {
        InternetShop iShop = new InternetShop();
        Scanner scanner = new Scanner(System.in);
        String inputText = null;
        CustomerProfile customerProfile = null;
        boolean isStayOnSite = true;

        //Data pre setup
        iShop.uploadProducts(new Product[]{
            new Product("Play station", "Game console by Sony", 10, 500.00),
            new Product("IPhone 15 Pro Max", "Smartphone by Apple", 25, 1500.00),
            new Product("HP Probook 5400", "Office laptop by HP", 8, 499.00)
        });
        iShop.registerNewCustomer("test01");
        iShop.uploadListOfDiscounts(new HashMap<>(){{
            put("245av", DiscountType.PREMIUM);
            put("a881C", DiscountType.ADVANCED);
            put("01Ygg", DiscountType.BASIC);
        }});

        System.out.println("Welcome to "+iShop);

        while (isStayOnSite) {
            System.out.println();
            System.out.println("Please input one of the following commands:");
            System.out.println("C - create new shop user, T - login as TEST user, L - login as created user, E - exit from the shop.");
            inputText = scanner.nextLine().trim();
            switch (inputText.toUpperCase()) {
                case "C":
                    while (true) {
                        inputText = getInputDataAfterCheck(InputType.USER_NAME);
                        if (!iShop.isCustomerExist(inputText)) {
                            iShop.registerNewCustomer(inputText);
                            break;
                        }
                        System.out.println("User with name "+inputText+" already exists");
                    }
                    System.out.println("User was created successfully!");
                    break;
                case "T":
                    customerProfile = iShop.getCustomer("test01");
                    System.out.println("Login as TEST user...");
                    break;
                case "L":
                    System.out.println("Input your user name: ");
                    inputText = scanner.nextLine();
                    customerProfile = iShop.getCustomer(inputText);
                    if (customerProfile == null) {
                        System.out.println("Sorry user with name "+inputText+" does not exist. Please try again later.");
                    }
                    break;
                case "E":
                    isStayOnSite = false;
                    break;
                default: System.out.println("Wrong command. Please try again");
            }

            if (customerProfile != null) {
                CartItem[] cartItems = null;
                Product selectProduct = null;
                boolean isLogin = true;

                System.out.println("Welcome to shop, "+customerProfile);
                if (!customerProfile.isDiscountUsed()) {
                    System.out.println("You personal discount is "+iShop.getRandomDiscountCode());
                }
                while (isLogin) {
                    System.out.println();
                    System.out.println("Please input one of the following command:");
                    System.out.println("P - view products list, V - view cart's content, H - view history of purchase");
                    System.out.println("A - add product to cart, R- Remove product from cart, C - clear cart");
                    System.out.println("B - buy order, D - apply discount, E - log out");
                    inputText = scanner.nextLine().trim();
                    switch (inputText.toUpperCase()) {
                        case "P":
                            iShop.printListOfProducts();
                            break;
                        case "A":
                            System.out.println("Input index (#) of product to add it to Cart list");
                            inputText = getInputDataAfterCheck(InputType.ITEM_ID);
                            selectProduct = iShop.getProductById(Integer.parseInt(inputText));
                            if (selectProduct != null && selectProduct.getAmount() > 0) {
                                customerProfile.addToCart(
                                        Integer.parseInt(inputText),
                                        selectProduct.getName(),
                                        selectProduct.getPricePerItem()
                                );
                                selectProduct.decreaseAmount(1);
                            } else {
                                System.out.println("Sorry. Product is unable for adding.");
                            }
                            break;
                        case "R":
                            System.out.println("Input index (#) of item from Cart list to remove");
                            inputText = getInputDataAfterCheck(InputType.ITEM_ID);
                            int productId = customerProfile.deleteFromCart(Integer.parseInt(inputText));
                            if (productId > -1) {
                                iShop.getProductById(productId).increaseAmount(1);
                                System.out.println("One item with #"+inputText+" was deleted successfully from Cart");
                            }
                            else {
                                System.out.println("Cart item with #"+inputText+" does not exist.");
                            }
                            break;
                        case "C":
                            cartItems = customerProfile.clearCartAndGetRemovedItems();
                            if (cartItems != null) {
                                for (CartItem item : cartItems) {
                                    iShop.getProductById(item.getProductId()).increaseAmount(item.getAmount());
                                }
                            }
                            System.out.println("Cart was cleared successfully!");
                            break;
                        case "V":
                            customerProfile.printCartList();
                            break;
                        case "B":
                            cartItems = customerProfile.clearCartAndGetRemovedItems();
                            if (cartItems != null) {
                                iShop.purchaseOrder(customerProfile.toString(), cartItems);
                                customerProfile.resetTotalPayToZero();
                                System.out.println("Congrats! Your order was done!");
                            }
                            else {
                                System.out.println("Please add product to your Cart and buy then");
                            }
                            break;
                        case "H":
                            iShop.printPurchaseHistory(customerProfile.toString());
                            break;
                        case "D":
                            if (!customerProfile.isDiscountUsed()) {
                                inputText = getInputDataAfterCheck(InputType.DISCOUNT);
                                DiscountType discountType = iShop.getDiscount(inputText);
                                System.out.println(discountType.getAmount());
                                customerProfile.addToTotalPay(discountType.getAmount());
                                customerProfile.setDiscountStatus(discountType.isApplicable());
                                System.out.println(discountType.getMessage());
                            }
                            else {
                                System.out.println("Sorry. Your personal discount has been used.");
                            }
                            break;
                        case "E":
                            isLogin = false;
                            break;
                        default:
                            System.out.println("Wrong command. Please try again.");
                    }
                }
                System.out.println(customerProfile+", You log out successfully.");
                customerProfile = null;
            }
        }
        System.out.println("Good bye");
    }
}