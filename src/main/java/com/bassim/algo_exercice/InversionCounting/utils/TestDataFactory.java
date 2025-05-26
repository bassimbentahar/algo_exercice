package com.bassim.algo_exercice.InversionCounting.utils;

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
     * Return the input data in the given @a filename as an array of
     * Strings.
     */
    public static List<String> getInput(String filename,
                                        String splitter) {
        try {
            // Convert the filename into a pathname.
            URI uri = ClassLoader.getSystemResource(filename).toURI();

            // Open the file and get all the bytes.
            String bytes = new String(Files.readAllBytes(Paths.get(uri)));

            return Pattern
                    // Compile splitter into a regular expression (regex).
                    .compile(splitter)

                    // Use the regex to split the file into a stream of
                    // strings.
                    .splitAsStream(bytes)

                    // Filter out any empty strings.
                    .filter(((Predicate<String>) String::isEmpty).negate())

                    // Collect the results into a List of String objects.
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return The word list in the {@code filename} as a list of
     * non-empty strings.
     */
    public static List<String> getWordList(String filename) {
        // Use the try-with-resources idiom to open the file and
        // read all the lines from it.
        try (var lines = Files
                // Read all lines from filename.
                .lines(Paths.get(ClassLoader.getSystemResource
                        (filename).toURI()))) {
            return lines

                    // Filter out any empty strings.
                    .filter(((Predicate<String>) String::isEmpty).negate())

                    // Collect the results into a List of String objects.
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
