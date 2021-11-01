package cbook;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import exceptions.ContactAlreadyExistsException;
import exceptions.ContactDoesNotExistException;

public class ContactBookInList implements ContactBook {
    /**
     * A collection of contacts.
     */
    private final List<Contact> contacts;

    /**
     * Default constructor
     */
    public ContactBookInList() {
        contacts = new LinkedList<>();
    }

    @Override
    public boolean has_Contact(String name) {
        return this.getContact(name) != null;
    }

    @Override
    public int getNumberOfContacts() {
        return contacts.size();
    }

    @Override
    public void addContact(String name, int phone, String email) throws ContactAlreadyExistsException {
        if (has_Contact(name))
            throw new ContactAlreadyExistsException();
        else
            contacts.add(new ContactClass(name, phone, email));
    }

    @Override
    public void deleteContact(String name) throws ContactDoesNotExistException {
        if (has_Contact(name)) {
            Contact c = this.getContact(name);
            contacts.remove(c);
        } else {
            throw new ContactDoesNotExistException();
        }
    }

    @Override
    public int getPhone(String name) throws ContactDoesNotExistException {
        Contact c = this.getContact(name);
        if (c != null)
            return c.getPhone();
        else
            throw new ContactDoesNotExistException();
    }

    @Override
    public String getEmail(String name) throws ContactDoesNotExistException {
        Contact c = this.getContact(name);
        if (c != null)
            return c.getEmail();
        else
            throw new ContactDoesNotExistException();
    }

    @Override
    public void setPhone(String name, int phone) throws ContactDoesNotExistException {
        Contact c = this.getContact(name);
        if (c != null)
            c.setPhone(phone);
        else
            throw new ContactDoesNotExistException();
    }

    @Override
    public void setEmail(String name, String email) throws ContactDoesNotExistException {
        Contact c = this.getContact(name);
        if (c != null)
            c.setEmail(email);
        else
            throw new ContactDoesNotExistException();
    }

    @Override
    public Iterator<Contact> listContacts() {
        return contacts.iterator();
    }

    /**
     * @param name The contact name to lookup in the list
     * @return the contact with the given name if it exists,
     * otherwise null
     */
    private Contact getContact(String name) {
        for (Contact c: contacts)
            if (c.getName().equals(name))
                return c;
        return null;
    }
}
