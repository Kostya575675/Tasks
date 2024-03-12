package org.qa.helpers;

import org.qa.main_classes.PhoneBook;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Helper {
    private static final Map<ContactField, String> correctInputData = new HashMap<>();

    private static String getValidTextByContactField(ContactField contactField) {
        Scanner scanner = new Scanner(System.in);
        String inputText;
        System.out.println("Input " + contactField);
        while (true) {
            inputText = scanner.nextLine().trim();
            if (inputText.matches(contactField.getRegexExpression())) return inputText;
            System.out.println("! " + contactField.getErrorMessage() + " Please try again.");
        }
    }

    public static void addContact(PhoneBook phoneBook) {
        String inputText;
        for (ContactField contactField : ContactField.values()) {
            inputText = getValidTextByContactField(contactField);
            correctInputData.put(contactField, inputText);
        }
        phoneBook.addContact(correctInputData.get(ContactField.FIRST_NAME), correctInputData.get(ContactField.LAST_NAME), Long.parseLong(correctInputData.get(ContactField.PHONE_NUMBER)), correctInputData.get(ContactField.ADDRESS));
    }

    public static void editContact(PhoneBook phoneBook) {
        String inputText = getValidTextByContactField(ContactField.PHONE_NUMBER);
        if (phoneBook.isContactExist(Long.parseLong(inputText))) {
            correctInputData.put(ContactField.PHONE_NUMBER, inputText);
            for (ContactField contactField : Arrays.asList(ContactField.FIRST_NAME, ContactField.LAST_NAME, ContactField.ADDRESS)) {
                inputText = getValidTextByContactField(contactField);
                correctInputData.put(contactField, inputText);
            }
            phoneBook.editContact(correctInputData.get(ContactField.FIRST_NAME), correctInputData.get(ContactField.LAST_NAME), Long.parseLong(correctInputData.get(ContactField.PHONE_NUMBER)), correctInputData.get(ContactField.ADDRESS));
        } else {
            System.out.println("Contact was not found");
        }
    }

    public static void deleteContact(PhoneBook phoneBook) {
        String inputText = getValidTextByContactField(ContactField.PHONE_NUMBER);
        phoneBook.deleteContact(Long.parseLong(inputText));
    }

    public static void printContactInfo(PhoneBook phoneBook) {
        String inputText = getValidTextByContactField(ContactField.PHONE_NUMBER);
        phoneBook.printContact(Long.parseLong(inputText));
    }
}
