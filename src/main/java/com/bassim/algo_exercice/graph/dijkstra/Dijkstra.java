package com.bassim.algo_exercice.graph.dijkstra;

import java.util.*;
import java.util.stream.Stream;

public class Dijkstra {

    Map<String, List<Edge>> adjList = new HashMap<>();
    private final Set<String> visited = new HashSet<>();
    private final Map<String, Integer> minDistances = new HashMap<>();
    private final PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.distanceFromS));

    public void addEdge(String u, String v, int distance) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(new Edge(v, distance));
    }

    public void execute(String s) {
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

    private void initializeDistances(String s) {
        Stream.concat(
                        adjList.keySet().stream(),
                        adjList.values().stream().flatMap(List::stream).map(e -> e.target)
                )
                .distinct()
                .forEach(u -> minDistances.put(u, Integer.MAX_VALUE));

        minDistances.put(s, 0);
    }

    public static void main(String[] args) {
        Dijkstra graph = new Dijkstra();

        graph.addEdge("S", "V", 1);
        graph.addEdge("S", "T", -2);   // Distance directe S → T
        graph.addEdge("V", "T", -5);   // Distance indirecte S → V → T
        graph.addEdge("T", "U", 3);    // Pour tester la propagation depuis T
        graph.addEdge("U", "X", 3);    // Pour tester la propagation depuis T

        graph.execute("S");
    }

    private record Node(String name, int distanceFromS) {}

    public static class Edge {
        String target;
        int weight;

        public Edge(String target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }
}
