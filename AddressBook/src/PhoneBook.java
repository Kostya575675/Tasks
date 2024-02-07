import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private final Map<String, Contact> listOfContacts;

    PhoneBook() {
        listOfContacts = new HashMap<>();
    }

    private String getFormattedKey(long data) {
        return "#"+data;
    }

    boolean isContactExist(long data) {
        return listOfContacts.containsKey(getFormattedKey(data));
    }

    void addContact(String firstName, String lastName, long phoneNumber, String address) {
        String key = getFormattedKey(phoneNumber);
        if (listOfContacts.containsKey(key)) {
            System.out.println("Contact is already exist");
            return;
        }
        listOfContacts.put(key, new Contact(firstName, lastName, phoneNumber, address));
        System.out.println("Contact was added");
    }

    void editContact(String firstName, String lastName, long phoneNumber, String address) {
        String key = getFormattedKey(phoneNumber);
        if (listOfContacts.containsKey(key)) {
            Contact contact = listOfContacts.get(key);
            contact.setFirstName(firstName.isEmpty() ? contact.getFirstName() : firstName);
            contact.setLastName(lastName.isEmpty() ? contact.getLastName() : lastName);
            contact.setAddress(address.isEmpty() ? contact.getAddress() : address);
            System.out.println("Contact was edited");
            return;
        }
        System.out.println("Contact does not exist");
    }

    void deleteContact(long phoneNumber) {
        String key = getFormattedKey(phoneNumber);
        if (listOfContacts.containsKey(key)) {
            listOfContacts.remove(key);
            System.out.println("Contact was deleted");
            return;
        }
        System.out.println("Contact doest not exist");
    }

    void printContact(long phoneNumber) {
        String key = getFormattedKey(phoneNumber);
        String text = listOfContacts.containsKey(key) ? listOfContacts.get(key).toString() : "Contact doest not exist";
        System.out.println(text);
    }

    void printAllContacts() {
        if (listOfContacts.isEmpty()) {
            System.out.println("Phone book is empty");
            return;
        }
        System.out.println("*** Phone contacts: ***");
        for (Contact contact : listOfContacts.values()) {
            System.out.print("* ");
            System.out.println(contact);
        }
        System.out.println("*** End of contacts ***");
    }
}
