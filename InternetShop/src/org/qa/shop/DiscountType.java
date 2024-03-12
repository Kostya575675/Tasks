package org.qa.shop;

public enum DiscountType {
    PREMIUM("Premium", -200.00, true, "You get Premium discount -200."),
    ADVANCED("Advanced", -100.00, true, "You get Advanced discount -100."),
    BASIC("Basic", -50.00, true,"You get Basic discount -50."),
    ZERO("No discount", 0.00, false, "Sorry, You got no discount");

    private final String name;
    private  final  double amount;
    private final boolean isApplicable;
    private final String message;
    private DiscountType(String _name, double _amount, boolean _isApplicable , String _message) {
        name = _name;
        amount = _amount;
        isApplicable = _isApplicable;
        message = _message;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isApplicable() {
        return isApplicable;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return name;
    }
}
