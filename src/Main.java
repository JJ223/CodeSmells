import java.util.InputMismatchException;
import java.util.Scanner;

import cbook.Contact;
import cbook.ContactBook;
import cbook.ContactBookInList;
import exceptions.ContactAlreadyExistsException;
import exceptions.ContactDoesNotExistException;

/**
 * Main program for the application ContactBook.
 */
public class Main {
    private static final String NOT_A_VALID_PHONE_NUMBER = "Not a valid phone number.";

    // Constants for defining the user feedback
    private static final String CONTACT_EXISTS = "Contact already exists.";
    private static final String NAME_NOT_EXIST = "Contact does not exist.";
    private static final String CONTACT_ADDED = "Contact added.";
    private static final String CONTACT_REMOVED = "Contact removed.";
    private static final String CONTACT_UPDATED = "Contact updated.";
    private static final String BOOK_EMPTY = "Contact book empty.";
    private static final String ERROR = "Unknown command.";
    private static final String EXIT = "Goodbye!";

    // Enumeration for defining Commands
    private enum Command {
        ADD_CONTACT("AC"), REMOVE_CONTACT("RC"), GET_PHONE("GP"), GET_EMAIL("GE"),
        SET_PHONE("SP"), SET_EMAIL("SE"), LIST_CONTACTS("LC"),
        QUIT("Q"), UNKNOWN_COMMAND("");

        private final String commandInputName;

        Command(String commandInputName) {
            this.commandInputName = commandInputName;
        }

        public String getCommandInputName() {
            return commandInputName;
        }
    }

    /**
     * Main program. Runs the command interpreter.
     * @param args - arguments for executing the program. Not used in this program.
     */
    public static void main(String[] args) {
        executeCommands();
    }

    /**
     * Command interpreter.
     */
    private static void executeCommands() {
        Scanner in = new Scanner(System.in);
        ContactBook cBook = new ContactBookInList();
        Command comm = readCommand(in);
        while (!comm.equals(Command.QUIT)){
            switch (comm) {
                case ADD_CONTACT:
                    addContact(in,cBook);
                    break;
                case REMOVE_CONTACT:
                    deleteContact(in,cBook);
                    break;
                case GET_PHONE:
                    getPhone(in,cBook);
                    break;
                case GET_EMAIL:
                    getEmail(in,cBook);
                    break;
                case SET_PHONE:
                    setPhone(in,cBook);
                    break;
                case SET_EMAIL:
                    setEmail(in,cBook);
                    break;
                case LIST_CONTACTS:
                    listAllContacts(cBook);
                    break;
                default:
                    System.out.println(ERROR);
            }
            System.out.println();
            comm = readCommand(in);
        }
        System.out.println(EXIT);
        System.out.println();
        in.close();
    }

    /**
     * Reads a new command to execute.
     * @param in input from where data is read.
     */
    private static Command readCommand(Scanner in) {
        String input = in.nextLine().toUpperCase().trim();
        for (Command c :Command.values())
            if (c.getCommandInputName().equals(input))
                return c;
        return Command.UNKNOWN_COMMAND;
    }

    /**
     * Adds a new contact, if it dose not exist. If it already exists, this
     * does nothing
     * @param in input from which data is read
     * @param cBook ContactBook in which we want to add a new contact
     */
    private static void addContact(Scanner in, ContactBook cBook) {
        try {
            String name = in.nextLine();
            int phone = in.nextInt();
            in.nextLine();
            String email = in.nextLine();
            cBook.addContact(name, phone, email);
            System.out.println(CONTACT_ADDED);
        } catch (InputMismatchException e) {
            System.out.println(NOT_A_VALID_PHONE_NUMBER);
            in.nextLine();
            in.nextLine();
        } catch (ContactAlreadyExistsException e) {
            System.out.println(CONTACT_EXISTS);
        }
    }

    /**
     * Removes a contact, if it exists. Otherwise, does nothing.
     * @param in input from which data will be read.
     * @param cBook ContactBook in which one wants to remove the contact.
     */
    private static void deleteContact(Scanner in, ContactBook cBook) {
        String name = in.nextLine();
        try {
            cBook.deleteContact(name);
            System.out.println(CONTACT_REMOVED);
        } catch (ContactDoesNotExistException e) {
            System.out.println(NAME_NOT_EXIST);
        }
    }

    /**
     * Gets the phone number of a contact, if it exists. Otherwise, it does nothing.
     * @param in input from which data will be read.
     * @param cBook ContactBook from which we want to fetch the phone number.
     */
    private static void getPhone(Scanner in, ContactBook cBook) {
        String name = in.nextLine();
        try {
            System.out.println(cBook.getPhone(name));
        } catch (ContactDoesNotExistException e) {
            System.out.println(NAME_NOT_EXIST);
        }
    }

    /**
     * Gets the email address of a contact, if it exists. Otherwise, it does nothing.
     * @param in input from which data will be read.
     * @param cBook ContactBook from which we want to fetch the email address.
     */
    private static void getEmail(Scanner in, ContactBook cBook) {
        String name = in.nextLine();
        try {
            System.out.println(cBook.getEmail(name));
        } catch (ContactDoesNotExistException e) {
            System.out.println(NAME_NOT_EXIST);
        }
    }

    /**
     * Updates the phone number of a contact, if it exists. Otherwise, does nothing.
     * @param in input from which data will be read.
     * @param cBook ContactBook where we want to update the phone number.
     */
    private static void setPhone(Scanner in, ContactBook cBook) {
        try {
            String name = in.nextLine();
            int phone;
            try {
                phone = in.nextInt();
                in.nextLine();
                cBook.setPhone(name, phone);
                System.out.println(CONTACT_UPDATED);
            } catch (InputMismatchException e) {
                in.nextLine();
                System.out.println(NOT_A_VALID_PHONE_NUMBER);
            } catch (ContactDoesNotExistException e) {
                System.out.println(NAME_NOT_EXIST);
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates the contact email, if it exists. Otherwise, does nothing.
     * @param in input from which data will be read.
     * @param cBook ContactBook in which we want to update the email address.
     */
    private static void setEmail(Scanner in, ContactBook cBook) {
        String name = in.nextLine();
        String email = in.nextLine();
        try {
            cBook.setEmail(name,email);
            System.out.println(CONTACT_UPDATED);
        } catch (ContactDoesNotExistException e) {
            System.out.println(NAME_NOT_EXIST);
        }
    }

    /**
     * Lists all contacts.
     * @param cBook ContactBook from which we want to list all contacts.
     */
    private static void listAllContacts(ContactBook cBook) {
        if (cBook.getNumberOfContacts() != 0) {
            java.util.Iterator<Contact> it = cBook.listContacts();
            while(it.hasNext()) {
                Contact c = it.next();
                System.out.println(c.getName() + "; " + c.getEmail() + "; " + c.getPhone());
            }
        }
        else System.out.println(BOOK_EMPTY);
    }
}
