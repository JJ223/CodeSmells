package cBook;

public interface Contact {
    /**
     * Returns the contact name
     * @return contact name
     */
    String getName();

    /**
     * Returns the phone number
     * @return returns the phone number
     */
    int getPhone();

    /**
     * Returns the email address
     * @return email address
     */
    String getEmail();

    /**
     * Changes the phone number of this contact
     * @param phone the phone number
     */
    void setPhone(int phone);

    /**
     * Updates the contact email
     * @param email new email address
     */
    void setEmail(String email);
}