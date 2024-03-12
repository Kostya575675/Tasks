package org.qa.contacts;

import java.util.HashMap;
import java.util.Map;


public class PhoneBook {
    private final Map<String, Contact> contacts = new HashMap<>();

    private String getFormattedKey(long data) {
        return "#" + data;
    }

    public boolean isContactExist(long data) {
        return contacts.containsKey(getFormattedKey(data));
    }

    public void addContact(String firstName, String lastName, long phoneNumber, String address) {
        String key = getFormattedKey(phoneNumber);
        if (contacts.containsKey(key)) {
            System.out.println("Contact already exists");
            return;
        }
        contacts.put(key, new Contact(firstName, lastName, phoneNumber, address));
        System.out.println("Contact was added");
    }

    public void editContact(String firstName, String lastName, long phoneNumber, String address) {
        String key = getFormattedKey(phoneNumber);
        if (contacts.containsKey(key)) {
            Contact contact = contacts.get(key);
            contact.setFirstName(firstName.isEmpty() ? contact.getFirstName() : firstName);
            contact.setLastName(lastName.isEmpty() ? contact.getLastName() : lastName);
            contact.setAddress(address.isEmpty() ? contact.getAddress() : address);
            System.out.println("Contact was edited");
            return;
        }
        System.out.println("Contact does not exist");
    }

    public void deleteContact(long phoneNumber) {
        String key = getFormattedKey(phoneNumber);
        if (contacts.containsKey(key)) {
            contacts.remove(key);
            System.out.println("Contact was deleted");
            return;
        }
        System.out.println("Contact does not exist");
    }

    public void printContact(long phoneNumber) {
        String key = getFormattedKey(phoneNumber);
        String text = contacts.containsKey(key) ? contacts.get(key).toString() : "Contact does not exist";
        System.out.println(text);
    }

    public void printAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Contact book is empty");
            return;
        }
        System.out.println("*** Contacts: ***");
        for (Contact contact : contacts.values()) {
            System.out.print("* ");
            System.out.println(contact);
        }
        System.out.println("*** End of Contact book ***");
    }
}
