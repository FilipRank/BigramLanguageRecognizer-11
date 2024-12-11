package util;

import languages.LanguagesEnum;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Ngrams {

    public static String getClosestMatchInDistanceMap(Map<String, Double> distanceMap) {
        return distanceMap.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static Map<String, Double> getDistanceMapFrom(List<Double> v) {
        Map<String, List<Double>> valueMap = LanguagesEnum.createValueMap();

        return valueMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> Vectors.magnitude(Vectors.subtract(entry.getValue(), v))
                ));
    }

    public static  Map<String, Double> extractBigramProbability(File file) {
        Map<String, Double> bigramFrequency = extractBigramFrequency(file);
        return calculategNgramProbability(bigramFrequency);
    }

    public static Map<String, Double> extractBigramProbability(String text) {
        Map<String, Double> bigramFrequency = extractBigramFrequency(text);
        return calculategNgramProbability(bigramFrequency);
    }

    public static Map<String, Double> extractBigramFrequency(File file) {
        Map<String, Double> bigramFrequency = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null) {
                text = text.toLowerCase().replaceAll("[^\\p{L}]", "");

                for (int i = 0; i < text.length() - 1; i++) {
                    String currentBigram = text.substring(i, i + 2);

                    bigramFrequency.put(
                            currentBigram,
                            bigramFrequency.getOrDefault(currentBigram, 0.0) + 1
                    );
                }
            }
            return bigramFrequency;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Double> extractBigramFrequency(String text) {
        Map<String, Double> bigramFrequency = new HashMap<>();

        text = text.toLowerCase().replaceAll("[^\\p{L}]", "");

        for (int i = 0; i < text.length() - 1; i++) {
            String currentBigram = text.substring(i, i + 2);

            bigramFrequency.put(
                    currentBigram,
                    bigramFrequency.getOrDefault(currentBigram, 0.0) + 1
            );
        }
        return bigramFrequency;
    }

    public static List<Double> getTopXValues
            (int topX, Map<String, Double> bigrams) {
        List<Double> values = bigrams.values().stream()
                .sorted((e1, e2) -> e2.compareTo(e1))
                .collect(ArrayList::new, List::add, List::addAll);
        return values.subList(0, topX);
    }

    private static Map<String, Double> calculategNgramProbability
            (Map<String, Double> ngramFrequency) {
        Double frequencySum = getFrequencySum(ngramFrequency);

        return ngramFrequency.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() / frequencySum
                ));
    }

    private static Double getFrequencySum(Map<String, Double> hashMap) {
        return hashMap.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public static Map<String, Double> sortDataAscending(
            Map<String, Double> data) {

        return data.entrySet().stream().sorted((val1, val2) -> val1.getValue().compareTo(val2.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (val1, val2) -> val1,
                        LinkedHashMap::new
                ));
    }
}
