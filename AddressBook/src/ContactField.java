public enum ContactField {
    FIRST_NAME("First name", "[a-zA-Z_]+", "Only letters are required."),
    LAST_NAME("Last name", "[a-zA-Z_]+", "Only letters are required."),
    PHONE_NUMBER("Phone number", "[0-9]+", "Only digits are required."),
    ADDRESS("Address", "[A-Za-z0-9_.,# ?(\\/)]+", "Only letters, numbers and symbols like (_ . , # /) are allowed.");

    private final String name;
    private final String regexExpression;
    private  final  String errorMessage;

    private ContactField(String _name, String _regexExpression, String _errorMessage) {
        name = _name;
        regexExpression = _regexExpression;
        errorMessage = _errorMessage;
    }

    public String getRegexExpression() {
        return regexExpression;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String toString() {
        return name;
    }
}
