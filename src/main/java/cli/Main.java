package cli;

import cli.command.CommandFactory;
import cli.command.NoSuchCommandException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
            CommandFactory.createCommand("help").execute();
        } catch (NoSuchCommandException e) {
            throw new RuntimeException(e);
        }

        // Main loop
        while (true) {
            System.out.println("\nEnter a command:");
            String nextCommand = scanner.nextLine()
                    .toLowerCase()
                    .strip();
            try {
                CommandFactory.createCommand(nextCommand).execute();
            }
            catch (NoSuchCommandException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
