package com.bassim.algo_exercice.graph.dijkstra;

import com.bassim.algo_exercice.graph.dijkstra.utils.TestDataFactory;

import java.util.*;
import java.util.stream.Stream;

public class DijkstraExercice {

    Map<Integer, List<Edge>> adjList = new HashMap<>();
    private final Set<Integer> visited = new HashSet<>();
    private final Map<Integer, Integer> minDistances = new HashMap<>();
    private final PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.distanceFromS));

    public void addEdge(Integer u, Integer v, int distance) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(new Edge(v, distance));
    }

    public void execute(Integer s) {
        initializeDistances(s);
        queue.add(new Node(s, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (visited.contains(current.name)) continue;
            visited.add(current.name);
            // Parcourt tous les voisins (edges) du sommet courant dans la liste d'adjacence.
            // Si le sommet n'a pas de voisins, retourne une liste vide (évite une NullPointerException).
            for (Edge e : adjList.getOrDefault(current.name, Collections.emptyList())) {

                // Calcule la nouvelle distance entre la source et le voisin e.target
                // via le sommet courant. C’est-à-dire : distance minimale pour atteindre
                // 'current' + le poids de l’arête entre 'current' et 'e.target'.
                int newDist = minDistances.get(current.name) + e.weight;

                // Si cette nouvelle distance est plus courte que celle actuellement connue
                // pour atteindre 'e.target', on met à jour la distance minimale.
                if (newDist < minDistances.get(e.target)) {

                    // Mise à jour de la distance minimale pour 'e.target'.
                    minDistances.put(e.target, newDist);

                    // Ajoute ce voisin dans la file de priorité (min-heap),
                    // avec sa nouvelle distance, pour traitement ultérieur.
                    queue.add(new Node(e.target, newDist));
                }
            }

        }

        System.out.println("Distances depuis " + s + ":");
        minDistances
                .forEach((k, v) -> System.out.println(k + " : " + (v == Integer.MAX_VALUE ? "∞" : v)));
    }

    private void initializeDistances(Integer s) {
        Stream.concat(
                        adjList.keySet().stream(),
                        adjList.values().stream().flatMap(List::stream).map(e -> e.target)
                )
                .distinct()
                .forEach(u -> minDistances.put(u, 1000000));

        minDistances.put(s, 0);
    }

    public static void main(String[] args) {
        DijkstraExercice graph = new DijkstraExercice();

        graph.adjList = TestDataFactory.buildGraph("dijkstraData.txt");

        graph.execute(1);

        List<Integer> targets = List.of(7, 37, 59, 82, 99, 115, 133, 165, 188, 197);
        System.out.println("\nDistances spécifiques depuis 1 :");
        targets.forEach(k -> {
            int d = graph.minDistances.getOrDefault(k, Integer.MAX_VALUE);
            System.out.println(k + " : " + (d == 1000000 ? "∞" : d));
        });

    }

    private record Node(Integer name, int distanceFromS) {}

    public record Edge(int target, int weight) {}

}
