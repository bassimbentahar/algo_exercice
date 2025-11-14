package com.bassim.algo_exercice.kargerMinCut;

import com.bassim.algo_exercice.kargerMinCut.utils.TestDataFactory;

import java.util.*;

public class KargerMinCut {

    private static final String sINPUT_FILE = "kargerMinCut.txt";
    private static final int NUM_ITERATIONS = 50;

    public static void main(String[] args) {
        Map<Integer, List<Integer>> originalGraph = TestDataFactory.getInput(sINPUT_FILE);
        int minCut = Integer.MAX_VALUE;

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            Map<Integer, List<Integer>> graph = deepCopyGraph(originalGraph);
            int cut = runKargerMinCut(graph);
            if (cut < minCut) {
                minCut = cut;
            }
        }

        System.out.println("Min Cut trouvé après " + NUM_ITERATIONS + " itérations : " + minCut);
    }

    private static int runKargerMinCut(Map<Integer, List<Integer>> graph) {
        System.out.println("Thread " + Thread.currentThread().getName() + " started contraction");

        long start = System.currentTimeMillis();
        int contractions = 0;

        Random random = new Random();

        while (graph.size() > 2) {
            contractions++;
            List<Integer> nodes = new ArrayList<>(graph.keySet());
            int u, v;
            List<Integer> uAdj;

            do {
                u = nodes.get(random.nextInt(nodes.size()));
                uAdj = graph.get(u);
            } while (uAdj == null || uAdj.isEmpty());

            // choisir un voisin
            do {
                v = uAdj.get(random.nextInt(uAdj.size()));
            } while (!graph.containsKey(v)); // s'assurer que v existe encore

            List<Integer> vAdj = null;
            // a. Ajouter tous les voisins de v à la liste d’adjacence de u
            List<Integer> validNeighbors = uAdj.stream()
                    .filter(graph::containsKey)
                    .toList();

            if (validNeighbors.isEmpty()) {
                throw new IllegalStateException("No valid neighbors found for node " + u);
            }

            v = validNeighbors.get(random.nextInt(validNeighbors.size()));
            vAdj = graph.get(v);
            System.out.println("comparaison contenu de u et v");
            System.out.println(graph.get(v));
            System.out.println(graph.get(u));

            Set<Integer> uniques = new HashSet<>(uAdj);
            uniques.addAll(vAdj);

            List<Integer> fusion = new ArrayList<>(uniques);

            // Remplace complètement la liste existante dans graph[u]
            graph.put(u, fusion);
            System.out.println("apres fusion");
            System.out.println(graph.get(u));

            // b. Supprimer les boucles (u -> u)
            final int finalU = u;
            graph.get(u).removeIf(e -> e == finalU);

            System.out.println(u);
            System.out.println("---------");
            System.out.println(graph.get(u));
            // c. Pour chaque voisin w de v :
            for (Integer w : graph.get(v)) {
                List<Integer> list = graph.get(w);
                if (list == null) continue; // sécurité supplémentaire
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) == v) {
                        list.set(i, u); // remplacer v par u
                    }
                }
            }
            // d. Supprimer v
            graph.remove(v);

            if (contractions % 10 == 0) {
                System.out.println("Thread " + Thread.currentThread().getName() +
                        " contraction #" + contractions + " | nodes left: " + graph.size());
            }
        }

        System.out.println("Thread " + Thread.currentThread().getName() + " finished in " +
                (System.currentTimeMillis() - start) + "ms | MinCut = " +
                graph.values().iterator().next().size());
        // Retourner la taille de l'une des deux listes d’adjacence
        return graph.values().iterator().next().size();
    }

    private static Map<Integer, List<Integer>> deepCopyGraph(Map<Integer, List<Integer>> original) {
        Map<Integer, List<Integer>> copy = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : original.entrySet()) {
            copy.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return copy;
    }
}
