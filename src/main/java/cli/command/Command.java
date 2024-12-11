package cli.command;

public interface Command {
    public void execute() throws NoSuchCommandException;
}
