import java.util.*;



public class Main {

    private static String getValidTextByContactField(Scanner scanner, ContactField contactField) {
        String inputText = null;
        System.out.println("Input "+contactField);
        while(true) {
            inputText = scanner.nextLine().trim();
            if (inputText.matches(contactField.getRegexExpression())) break;
            System.out.println("! "+contactField.getErrorMessage()+ " Please try again.");
        }
        return inputText;
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner sc = new Scanner(System.in);
        boolean isPlay = true;
        String inputStr = null;
        Map<ContactField, String> correctInputData = new HashMap<>();

        System.out.println("Welcome to Phonebook");
        while (isPlay) {
            System.out.println("Please choose desired command by inputting one of the following letters:");
            System.out.println("C - create, E - edit, D - delete, I - print one contact, P - print whole list of contacts, F - close program");
            inputStr = sc.nextLine().trim();
            switch(inputStr.toUpperCase()) {
                case "C":
                    for (ContactField contactField : ContactField.values()) {
                        inputStr = getValidTextByContactField(sc, contactField);
                        correctInputData.put(contactField, inputStr);
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
                    inputStr = getValidTextByContactField(sc, ContactField.PHONE_NUMBER);
                    if (phoneBook.isContactExist(Long.parseLong(inputStr))) {
                        correctInputData.put(ContactField.PHONE_NUMBER, inputStr);
                        for (ContactField contactField : Arrays.asList(ContactField.FIRST_NAME, ContactField.LAST_NAME, ContactField.ADDRESS)) {
                            inputStr = getValidTextByContactField(sc, contactField);
                            correctInputData.put(contactField, inputStr);
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
                        System.out.println("Contact was not found");
                    }
                    break;
                case "D":
                    inputStr = getValidTextByContactField(sc, ContactField.PHONE_NUMBER);
                    phoneBook.deleteContact(Long.parseLong(inputStr));
                    break;
                case "I":
                    inputStr = getValidTextByContactField(sc, ContactField.PHONE_NUMBER);
                    phoneBook.printContact(Long.parseLong(inputStr));
                    break;
                case "P":
                    phoneBook.printAllContacts();
                    break;
                case "F": isPlay = false; break;
                default: System.out.println("! Wrong command was input. Please try again"); break;
            }
        }
        System.out.println("App is closed");
    }
}