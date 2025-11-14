package com.bassim.algo_exercice.kargerMinCut.utils;

import java.io.BufferedReader;
import java.io.StringReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public static Map<Integer, List<Integer>> getInput(String filename) {
        try {
            URI uri = ClassLoader.getSystemResource(filename).toURI();
            String content = new String(Files.readAllBytes(Paths.get(uri)));
            BufferedReader reader = new BufferedReader(new StringReader(content));
            Map<Integer, List<Integer>> map = new HashMap<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\t");
                Integer node = Integer.parseInt(parts[0]);
                List<Integer> connections = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    if (!parts[i].trim().isEmpty()) {
                        connections.add(Integer.parseInt(parts[i].trim()));
                    }
                }
                map.put(node, connections);
            }
            return map;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
