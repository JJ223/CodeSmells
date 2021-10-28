package cBook;

/**
 * By removing the public visibility, this class is no longer visible outside the package
 */
class ContactClass implements Contact {
    /**
     * Contact name.
     */
    private final String name;

    /**
     * Contact phone number.
     */
    private int phone;

    /**
     * Contact the email address.
     */
    private String email;

    /**
     * Default constructor
     */
    public ContactClass(String name, int phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Constructor with just the name. Leaves the phone number at 0, and the email address to null.
     * @param name : name of contact
     */
    public ContactClass(String name) {
        this(name, 0, null);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPhone() {
        return phone;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (!(obj instanceof Contact)) return false;

        Contact other = (Contact) obj;

        if (name == null) {
            if (other.getName() != null) return false;
            return true;
        }
        return name.equals(other.getName());
    }

}
