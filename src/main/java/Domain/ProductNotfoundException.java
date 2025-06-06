package Domain;

public class ProductNotfoundException extends RuntimeException {
    public ProductNotfoundException(String message) {
        super(message);
    }
}
