import org.qa.contacts.PhoneBook;
import org.qa.helpers.Helper;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Contact book");
        while (true) {
            System.out.println("Please choose desired command by inputting one of the following letters:");
            System.out.println("C - create, E - edit, D - delete, I - print one contact, P - print whole list of contacts, F - close program");
            String inputText = sc.nextLine().trim();
            switch (inputText.toUpperCase()) {
                case "C":
                    Helper.addContact(phoneBook);
                    break;
                case "E":
                    Helper.editContact(phoneBook);
                    break;
                case "D":
                    Helper.deleteContact(phoneBook);
                    break;
                case "I":
                    Helper.printContactInfo(phoneBook);
                    break;
                case "P":
                    phoneBook.printAllContacts();
                    break;
                case "F":
                    System.out.println("App is closed");
                    return;
                default:
                    System.out.println("! Wrong command was input. Please try again");
            }
        }
    }
}