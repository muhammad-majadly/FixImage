package images;

/**
 * Exception thrown when initializing the image with an invalid color.
 */
public class InvalidColorException extends Exception {

    public InvalidColorException(String message) {
        super(message);
    }
}
