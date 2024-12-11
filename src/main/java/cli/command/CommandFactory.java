package cli.command;

public class CommandFactory {

    public static Command createCommand(String commandName) throws NoSuchCommandException {
        switch (commandName) {
            case "start":
                return new StartCommand();
            case "prob":
                return new CalculateProbabilityCommand();
            case "freq":
                return new CalculateFrequencyCommand();
            case "help":
                return new HelpCommand();
            case "ls-languages":
                return new LsLanguagesCommand();
            case "exit":
                return new ExitCommand();
        }
        throw new NoSuchCommandException("This is not a valid command.");
    }

}
