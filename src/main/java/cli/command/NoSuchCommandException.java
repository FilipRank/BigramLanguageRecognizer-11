package cli.command;

public class NoSuchCommandException extends Exception {
    public NoSuchCommandException(String errorMessage) {
        super(errorMessage);
    }
}