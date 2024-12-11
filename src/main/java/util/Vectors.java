package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Vectors {

    public static double magnitude(ArrayList<Double> v) {
        double sumOfSquares = v.stream()
                .mapToDouble((e) -> Math.pow(e, 2))
                .sum();
        return Math.sqrt(sumOfSquares);
    }

    public static ArrayList<Double> subtract(List<Double> v1, List<Double> v2) {
        ArrayList<Double> result = new ArrayList<>();

        for (int i = 0; i < v1.size(); i++) {
            result.add(i,
                    v1.get(i) - v2.get(i)
            );
        }

        return result;
    }

    public static List<Double> CSVToVector(File file) {
        try (Scanner scanner = new Scanner(file)){
            String valueString = scanner.nextLine();

            List<Double> values = new ArrayList<>();

            values = Arrays.stream(valueString.split(","))
                    .map((str) -> Double.parseDouble(str))
                    .collect(Collectors.toList());
            return values;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Double> mapValuesToVectorSorted(Map<String, Double> map) {
        return map.values().stream()
                .sorted((e1, e2) -> e2.compareTo(e1))
                .collect(Collectors.toList());
    }
}
