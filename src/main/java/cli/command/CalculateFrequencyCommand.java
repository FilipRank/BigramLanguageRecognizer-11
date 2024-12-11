package cli.command;

import util.Ngrams;

import javax.swing.*;
import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class CalculateFrequencyCommand implements Command {
    @Override
    public void execute() throws NoSuchCommandException {
        System.out.println("Choose an option:\n" +
                "1 - I wish to give the input text directly\n" +
                "2 - I wish to to use text from a file\n");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine().trim()) {
            case "1":
                System.out.println("Insert input text:");
                fromText(scanner.nextLine());
                break;
            case "2":
                fromFile();
                break;
            default:
                throw new NoSuchCommandException("That was not a valid option.");
        }
    }

    private static void fromText(String text) {
        Map<String, Double> frequencies =
                Ngrams.sortDataAscending(Ngrams.extractBigramFrequency(text));
        frequencies.forEach((key, value) -> System.out.println(key + " | " + value));
    }

    private static void fromFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        Map<String, Double> frequencies =
                Ngrams.sortDataAscending(Ngrams.extractBigramFrequency(file));
        frequencies.forEach((key, value) -> System.out.println(key + " | " + value));
    }
}
