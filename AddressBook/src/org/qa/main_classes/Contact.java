package org.qa.main_classes;

public class Contact {
    private String firstName;
    private String lastName;
    private final long phoneNumber;
    private String address;

    public Contact(String _firstName, String _lastName, long _phoneNumber, String _address) {
        firstName = _firstName;
        lastName = _lastName;
        phoneNumber = _phoneNumber;
        address = _address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setFirstName(String _firstName) {
        firstName = _firstName;
    }

    public void setLastName(String _lastName) {
        lastName = _lastName;
    }

    public void setAddress(String _address) {
        address = _address;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + ". Surname: " + lastName + ". Phone number: " + phoneNumber + ". Address: " + address + ".";
    }
}
