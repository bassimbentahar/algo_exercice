package com.bassim.algo_exercice.graph.bfs;

import java.util.*;

public class GraphBFSPath {
    private final Map<String, List<String>> adjList = new HashMap<>();

  /*    S
       / \
      A   B
       \ / \
        C   D
         \
         E
   */

    public void addEdge(String u, String v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(v);
        adjList.get(v).add(u); // enlever si graphe orienté
    }

    public List<Node> bfsPath(Node start, Node goal) {
        Map<String, Node> parent = new HashMap<>();
        Map<String, Node> nodeByName = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        visited.add(start.getName());
        queue.add(start);
        parent.put(start.getName(), null); // le départ n’a pas de parent

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.getName().equals(goal.getName())) {
                break; // chemin trouvé
            }

            for (String neighbor : adjList.get(current.getName())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    Node neighborNode = new Node(neighbor, current.getDistance() + 1);
                    queue.add(neighborNode);
                    parent.put(neighbor, current);
                    nodeByName.put(neighbor, neighborNode);

                    //A découvre C → C a comme parent A.
                    //ensuite, B voit aussi C, mais C est déjà visité, donc on ignore cette nouvelle découverte.
                }
            }
        }

        // Reconstruire le chemin de goal à start
        List<Node> path = new LinkedList<>();
        Node step = nodeByName.get(goal.getName());
        while (step != null) {
            path.add(0, step); // ajouter au début
            step = parent.get(step.getName());
        }

        // Vérifier si un chemin existe
        if (!path.get(0).equals(start)) {
            return Collections.emptyList(); // pas de chemin trouvé
        }

        return path;
    }

    public static void main(String[] args) {
        GraphBFSPath graph = new GraphBFSPath();

        graph.addEdge("S", "A");
        graph.addEdge("S", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");

        List<Node> path = graph.bfsPath(new Node("S",0), new Node("E", 0));
        System.out.println("Chemin le plus court de S à E : " + path);
    }

}

