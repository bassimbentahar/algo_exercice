package com.bassim.algo_exercice.graph.topologySort;

import java.util.*;

public class TopologySort {

    /*
        S
       / \
      A   B
       \ / \
        C   D
         \
          E


     */
    private final Map<String, List<String>> adjList = new HashMap<>();
    private final Set<String> visited = new HashSet<>();
    private final Map<String, Integer> finishingTimes = new HashMap<>();
    private int currentLabel;

    public void recursifDfs(String s) {
        visited.add(s);
        List<String> strings = adjList.getOrDefault(s, new ArrayList<>());
        for (String u : strings) {
            if (!visited.contains(u)) {
                recursifDfs(u);
            }
        }
        finishingTimes.put(s, currentLabel);
        currentLabel--;
    }

    public void topologySort() {
        // Récupérer tous les sommets (clé et voisins)
        Set<String> allNodes = new HashSet<>();
        adjList.forEach((k, v) -> {
            allNodes.add(k);
            allNodes.addAll(v);
        });

        currentLabel = allNodes.size();

        for (String node : allNodes) {
            if (!visited.contains(node)) {
                recursifDfs(node);
            }
        }

        //  Affichage du tri topologique
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(finishingTimes.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue()); // ordre décroissant

        System.out.println("Ordre topologique :");
        for (Map.Entry<String, Integer> entry : sorted) {
            System.out.println(entry.getKey() + " (F=" + entry.getValue() + ")");
        }
    }

    public void addEdge(String u, String v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(v); // graphe orienté
    }

    public static void main(String[] args) {
        TopologySort graph = new TopologySort();

        graph.addEdge("S", "A");
        graph.addEdge("S", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");

        graph.topologySort();
    }
}
