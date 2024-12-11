package util;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataGenerator {
    public static void main(String[] args) throws IOException {
        File dataFile1 = new File("src/main/resources/data/texts/spanish_text.txt");
        Map<String, Double> data = Ngrams.extractBigramProbability(dataFile1);
        List<Double> top10probabilities = Ngrams.getTopXValues(10, data);

        File targetFile1 = new File("src/main/resources/data/spanishtop10.csv");
        targetFile1.delete();
        Files.createFile(targetFile1.toPath());

        FileWriter writer = new FileWriter(targetFile1);
        top10probabilities.stream()
                        .forEach((value) -> {
                            try {
                                writer.write(value + ",");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
        writer.close();

        // This is for reading the data from a file
        // BufferedReader reader = new BufferedReader(new FileReader(targetFile1));
        // List<Double> probabilities = Arrays.stream(reader.readLine().split(","))
        //                 .map(Double::parseDouble)
        //                 .toList();
        // System.out.println(probabilities);
        // reader.close();
    }
}
