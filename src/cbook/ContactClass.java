package cbook;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (!(obj instanceof Contact)) return false;

        Contact other = (Contact) obj;

        if (name == null) return other.getName() == null;
   
        return name.equals(other.getName());
    }


}
