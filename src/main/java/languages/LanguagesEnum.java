package languages;

import util.Ngrams;
import util.Vectors;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum LanguagesEnum {
    ENGLISH,
    FRENCH,
    GERMAN,
    SPANISH;

    public Path getFilePath() {
        String fileName = String.format("%stop10.csv", this.name().toLowerCase());
        Path path = Paths.get("src/main/resources/data/" + fileName);
        return path;
    }

    public String getFormattedName() {
        return (this.name().substring(0, 1) + this.name().substring(1).toLowerCase());
    }

    public static Map<String, List<Double>> createValueMap() {
        Map<String, List<Double>> result = new HashMap<>();

        for (LanguagesEnum language : values()) {
            result.put(language.name(), Vectors.CSVToVector(

                    language.getFilePath().toFile()

            ));
        }

        return result;
    }
}
