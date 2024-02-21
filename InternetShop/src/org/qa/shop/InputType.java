package org.qa.shop;

public enum InputType {
    USER_NAME("User name", "[A-Za-z0-9]+", "User name can contain letters and numbers"),
    ITEM_ID("Id", "[0-9]+", "Id can contain only numbers"),
    DISCOUNT("Discount","[A-Za-z0-9_.,# ?(\\/)]+","Discount can contain only letters, numbers and special characters like _.,# ?");

    private final  String name;
    private final  String  regex;
    private  final String errorMessage;
    private InputType(String _name, String  _regex, String _errorMessage) {
        name = _name;
        regex = _regex;
        errorMessage = _errorMessage;
    }

    public String getRegex() {
        return regex;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return name;
    }
}
