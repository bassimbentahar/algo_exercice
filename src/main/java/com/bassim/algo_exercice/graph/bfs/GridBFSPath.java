package com.bassim.algo_exercice.graph.bfs;

import java.util.*;

public class GridBFSPath {
    private final Map<String, List<String>> adjList = new HashMap<>();
    private final int rows;
    private final int cols;
    private final int[][] grid;

    public GridBFSPath(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        buildGraph();
    }

    // Convertir la grille en graphe
    private void buildGraph() {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // bas, haut, droite, gauche
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (grid[x][y] == 0) {
                    String key = key(x, y);
                    adjList.putIfAbsent(key, new ArrayList<>());
                    for (int[] d : dirs) {
                        int nx = x + d[0];
                        int ny = y + d[1];
                        if (inBounds(nx, ny) && grid[nx][ny] == 0) {
                            adjList.get(key).add(key(nx, ny));
                        }
                    }
                }
            }
        }
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }

    private String key(int x, int y) {
        return x + "," + y;
    }

    // BFS pour trouver le plus court chemin
    public List<String> bfsPath(int[] start, int[] goal) {
        String startKey = key(start[0], start[1]);
        String goalKey = key(goal[0], goal[1]);

        Queue<String> queue =new LinkedList<>();
        Set<String> visited = new HashSet<>();


        queue.add(startKey);
        visited.add(startKey);
        while (!queue.isEmpty()){
            String current = queue.peek();
            if(current.equals(goalKey)) break;

            List<String> neighbors = adjList.getOrDefault(current, Collections.emptyList());
            for (String neighbor: neighbors){
                if (!visited.contains(neighbor)){
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0}
        };

        GridBFSPath graph = new GridBFSPath(grid);
        List<String> path = graph.bfsPath(new int[]{0, 0}, new int[]{4, 4});

        System.out.println("Chemin le plus court : " + path);
        System.out.println("Longueur du chemin : " + (path.size() - 1));
    }
}
