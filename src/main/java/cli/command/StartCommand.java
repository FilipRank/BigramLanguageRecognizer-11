package cli.command;

import util.Ngrams;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StartCommand implements Command {
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
        Map<String, Double> data = Ngrams.extractBigramProbability(text);
        List<Double> top10values = Ngrams.getTopXValues(10, data);
        String result = Ngrams.getClosestMatchInDistanceMap(
                Ngrams.getDistanceMapFrom(top10values)
        );

        System.out.println("The closest match is:\n");
        System.out.printf("%s\n",
                result.substring(0, 1) + result.substring(1).toLowerCase());
    }

     private static void fromFile() {
         JFileChooser chooser = new JFileChooser();
         chooser.showOpenDialog(null);
         File file = chooser.getSelectedFile();

         Map<String, Double> data = Ngrams.extractBigramProbability(file);
         List<Double> top10values = Ngrams.getTopXValues(10, data);
         String result = Ngrams.getClosestMatchInDistanceMap(
                 Ngrams.getDistanceMapFrom(top10values)
         );

         System.out.println("The closest match is:\n");
         System.out.printf("%s\n",
                 result.substring(0, 1) + result.substring(1).toLowerCase());
     }
}
