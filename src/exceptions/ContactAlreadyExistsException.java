package exceptions;

/**
 * @author
 *
 */
public class ContactAlreadyExistsException extends Exception {

    private static final String CONTACT_ALREADY_EXISTS = "Contact already exists.";
    /**
     *
     */
    private static final long serialVersionUID = -2297027481179500567L;

    public ContactAlreadyExistsException() {
        super(CONTACT_ALREADY_EXISTS);
    }

}
