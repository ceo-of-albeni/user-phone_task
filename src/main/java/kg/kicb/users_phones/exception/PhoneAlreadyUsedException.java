package kg.kicb.users_phones.exception;

public class PhoneAlreadyUsedException extends RuntimeException {
    public PhoneAlreadyUsedException(String message) {
        super(message);
    }
}
