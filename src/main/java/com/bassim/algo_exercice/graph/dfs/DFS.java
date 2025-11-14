package com.bassim.algo_exercice.graph.dfs;

import java.util.*;

public class DFS {


  /*    S
       / \
      A   B
       \ / \
        C   D
         \
         E
   */

    private final Map<String, List<String>> adjList = new HashMap<>();
    private final Set<String> visited = new HashSet<>();

    public void addEdge(String u, String v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(v);
        adjList.get(v).add(u); // enlever si graphe orienté
    }

    public void recursifDfs(String s) {
        List<String> strings = adjList.get(s);
        for (String u : strings) {
            if (!visited.contains(u)) {
                visited.add(u);
                recursifDfs(u);
            }
        }
    }

    public void dfs(String start) {
        Stack<String> stack = new Stack<>();
        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            System.out.println(current);

            for (String neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }
    }



    public static void main(String[] args) {
        DFS graph = new DFS();

        graph.addEdge("S", "A");
        graph.addEdge("S", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");

        //System.out.println("S");
        //graph.visited.add("S");
        graph.dfs("S");
        System.out.println(graph.visited);
    }
}
