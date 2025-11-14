package com.bassim.algo_exercice.medianMaintenance;


import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;


/**
 * This utility class contains methods for obtaining test data.
 */
public class TestDataFactory {
    /**
     * A utility class should always define a private constructor.
     */
    private TestDataFactory() {
    }



    /**
     * Return the lines
     */
    public static List<Integer> toListLongs(String filename) {
        try {
            // Convert the filename into a pathname.
            URI uri = ClassLoader.getSystemResource(filename).toURI();

            // Open the file and get all the bytes.
            String bytes = new String(Files.readAllBytes(Paths.get(uri)));

            return Pattern
                    // Compile splitter into a regular expression (regex).
                    .compile("\n")

                    // Use the regex to split the file into a stream of
                    // strings.
                    .splitAsStream(bytes)

                    // Filter out any empty strings.
                    .filter(((Predicate<String>) String::isEmpty).negate())
                    .map(Integer::parseInt)

                    // Collect the results into a List of String objects.
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
