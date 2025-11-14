package com.bassim.algo_exercice.graph.bfs;

import java.util.*;


public class BFSandUndirectedConnectivity {
    private final Map<Integer, List<Integer>> adjList = new HashMap<>();
    private final Set<Integer> visited = new HashSet<>();

    public void addEdge(int u, int v) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    public void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : adjList.getOrDefault(current, Collections.emptyList())) {
                if (visited.add(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
    }

    public int countConnectedComponents(int maxNode) {
        int count = 0;
        for (int i = 1; i <= maxNode; i++) {
            if (!visited.contains(i)) {
                bfs(i);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        BFSandUndirectedConnectivity graph = new BFSandUndirectedConnectivity();

        graph.addEdge(1, 3);
        graph.addEdge(1, 5);
        graph.addEdge(3, 5);
        graph.addEdge(5, 7);
        graph.addEdge(5, 9);
        graph.addEdge(2, 4);
        graph.addEdge(6, 8);
        graph.addEdge(6, 10);
        graph.addEdge(10, 8);

        int numberOfBlocks = graph.countConnectedComponents(10);
        System.out.println(numberOfBlocks);  // Affiche 3
    }
}
