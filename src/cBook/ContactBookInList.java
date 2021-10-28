package cBook;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import exceptions.ContactAlreadyExistsException;
import exceptions.ContactDoesNotExistException;

public class ContactBookInList implements ContactBook {
    /**
     * A collection of contacts.
     */
    List<Contact> contacts;

    /**
     * Default constructor
     */
    public ContactBookInList() {
        contacts = new LinkedList<Contact>();
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
        // A possible alternative would be:
        // Contact c = this.getContact(name);
        // contacts.remove(c);
        // but this requires two searches in the list.
        // The following solution requires an equals(Object o) method
        // implemented in the ContactClass and a constructor that receives the name
        // as its single argument.
        if (has_Contact(name))
            contacts.remove(new ContactClass(name));
        else
            throw new ContactDoesNotExistException();
    }

    @Override
    public int getPhone(String name) throws ContactDoesNotExistException {
        if (has_Contact(name))
            return this.getContact(name).getPhone();
        else
            throw new ContactDoesNotExistException();
    }

    @Override
    public String getEmail(String name) throws ContactDoesNotExistException {
        if (has_Contact(name))
            return this.getContact(name).getEmail();
        else
            throw new ContactDoesNotExistException();
    }

    @Override
    public void setPhone(String name, int phone) throws ContactDoesNotExistException {
        if (has_Contact(name))
            this.getContact(name).setPhone(phone);
        else
            throw new ContactDoesNotExistException();
    }

    @Override
    public void setEmail(String name, String email) throws ContactDoesNotExistException {
        if (has_Contact(name))
            this.getContact(name).setEmail(email);
        else
            throw new ContactDoesNotExistException();
    }

    @Override
    public Iterator<Contact> listContacts() {
        return contacts.iterator();
    }

    /**
     * @param name The contact name to lookup in the list
     * @return the <code>name</code> of the contact, if it exists,
     * otherwise <code>null</code>
     */
    private Contact getContact(String name) {
        for (Contact c: contacts)
            if (c.getName().equals(name))
                return c;
        return null;
    }
}
