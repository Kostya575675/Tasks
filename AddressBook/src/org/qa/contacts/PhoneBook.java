package org.qa.contacts;
import java.util.HashMap;
import java.util.Map;


public class PhoneBook {
    private final Map<String, Contact> listOfContacts;

    public PhoneBook() {
        listOfContacts = new HashMap<>();
    }

    private String getFormattedKey(long data) {
        return "#"+data;
    }

    public boolean isContactExist(long data) {
        return listOfContacts.containsKey(getFormattedKey(data));
    }

    public void addContact(String firstName, String lastName, long phoneNumber, String address) {
        String key = getFormattedKey(phoneNumber);
        if (listOfContacts.containsKey(key)) {
            System.out.println("org.qa.contacts.Contact already exists");
            return;
        }
        listOfContacts.put(key, new Contact(firstName, lastName, phoneNumber, address));
        System.out.println("org.qa.contacts.Contact was added");
    }

    public void editContact(String firstName, String lastName, long phoneNumber, String address) {
        String key = getFormattedKey(phoneNumber);
        if (listOfContacts.containsKey(key)) {
            Contact contact = listOfContacts.get(key);
            contact.setFirstName(firstName.isEmpty() ? contact.getFirstName() : firstName);
            contact.setLastName(lastName.isEmpty() ? contact.getLastName() : lastName);
            contact.setAddress(address.isEmpty() ? contact.getAddress() : address);
            System.out.println("org.qa.contacts.Contact was edited");
            return;
        }
        System.out.println("org.qa.contacts.Contact does not exist");
    }

    public void deleteContact(long phoneNumber) {
        String key = getFormattedKey(phoneNumber);
        if (listOfContacts.containsKey(key)) {
            listOfContacts.remove(key);
            System.out.println("org.qa.contacts.Contact was deleted");
            return;
        }
        System.out.println("org.qa.contacts.Contact does not exist");
    }

    public void printContact(long phoneNumber) {
        String key = getFormattedKey(phoneNumber);
        String text = listOfContacts.containsKey(key) ? listOfContacts.get(key).toString() : "org.qa.contacts.Contact does not exist";
        System.out.println(text);
    }

    public void printAllContacts() {
        if (listOfContacts.isEmpty()) {
            System.out.println("Phone book is empty");
            return;
        }
        System.out.println("*** Contacts: ***");
        for (Contact contact : listOfContacts.values()) {
            System.out.print("* ");
            System.out.println(contact);
        }
        System.out.println("*** End of Phonebook ***");
    }
}
