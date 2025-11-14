package com.bassim.algo_exercice.graph.dijkstra.utils;

import com.bassim.algo_exercice.graph.dijkstra.DijkstraExercice;
import com.bassim.algo_exercice.graph.dijkstra.DijkstraExercice.*;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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



    public static Map<Integer, List<Edge>> buildGraph(String filename){
        List<String> lines = getLines(filename);

        assert lines != null;
        return parseLines(lines);
    }

    private static Map<Integer, List<Edge>> parseLines(List<String> lines) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (String line : lines) {
            String[] strings = line.split(Pattern.quote("\t"));
            int key = Integer.parseInt(strings[0]);
            List<DijkstraExercice.Edge> edges = new ArrayList<>();
            for (int i = 1; i < strings.length; i++) {
                String[] part = strings[i].split(Pattern.quote(","));
                int target = Integer.parseInt(part[0]);
                int weight = Integer.parseInt(part[1]);
                edges.add(new Edge(target, weight));
            }
            graph.put(key, edges);
        }
        return graph;
    }

    /**
     * Return the lines
     */
    public static List<String> getLines(String filename) {
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

                    // Collect the results into a List of String objects.
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
