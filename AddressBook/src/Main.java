import org.qa.contacts.ContactField;
import org.qa.contacts.PhoneBook;
import java.util.*;

public class Main {

    private static String getValidTextByContactField(ContactField contactField) {
        Scanner scanner = new Scanner(System.in);
        String inputText = null;
        System.out.println("Input "+contactField);
        while(true) {
            inputText = scanner.nextLine().trim();
            if (inputText.matches(contactField.getRegexExpression())) return inputText;
            System.out.println("! "+contactField.getErrorMessage()+ " Please try again.");
        }
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner sc = new Scanner(System.in);
        boolean isPlay = true;
        String inputTxt = null;
        Map<ContactField, String> correctInputData = new HashMap<>();

        System.out.println("Welcome to org.qa.contacts.Contact book");
        while (isPlay) {
            System.out.println("Please choose desired command by inputting one of the following letters:");
            System.out.println("C - create, E - edit, D - delete, I - print one contact, P - print whole list of contacts, F - close program");
            inputTxt = sc.nextLine().trim();
            switch(inputTxt.toUpperCase()) {
                case "C":
                    for (ContactField contactField : ContactField.values()) {
                        inputTxt = getValidTextByContactField(contactField);
                        correctInputData.put(contactField, inputTxt);
                    }
                    phoneBook
                            .addContact(
                                    correctInputData.get(ContactField.FIRST_NAME),
                                    correctInputData.get(ContactField.LAST_NAME),
                                    Long.parseLong(correctInputData.get(ContactField.PHONE_NUMBER)),
                                    correctInputData.get(ContactField.ADDRESS)
                            );
                    break;
                case "E":
                    inputTxt = getValidTextByContactField(ContactField.PHONE_NUMBER);
                    if (phoneBook.isContactExist(Long.parseLong(inputTxt))) {
                        correctInputData.put(ContactField.PHONE_NUMBER, inputTxt);
                        for (ContactField contactField : Arrays.asList(ContactField.FIRST_NAME, ContactField.LAST_NAME, ContactField.ADDRESS)) {
                            inputTxt = getValidTextByContactField(contactField);
                            correctInputData.put(contactField, inputTxt);
                        }
                        phoneBook
                                .editContact(
                                        correctInputData.get(ContactField.FIRST_NAME),
                                        correctInputData.get(ContactField.LAST_NAME),
                                        Long.parseLong(correctInputData.get(ContactField.PHONE_NUMBER)),
                                        correctInputData.get(ContactField.ADDRESS)
                                );
                    }
                    else {
                        System.out.println("org.qa.contacts.Contact was not found");
                    }
                    break;
                case "D":
                    inputTxt = getValidTextByContactField(ContactField.PHONE_NUMBER);
                    phoneBook.deleteContact(Long.parseLong(inputTxt));
                    break;
                case "I":
                    inputTxt = getValidTextByContactField(ContactField.PHONE_NUMBER);
                    phoneBook.printContact(Long.parseLong(inputTxt));
                    break;
                case "P":
                    phoneBook.printAllContacts();
                    break;
                case "F":
                    isPlay = false;
                    break;
                default:
                    System.out.println("! Wrong command was input. Please try again");
            }
        }
        System.out.println("App is closed");
    }
}