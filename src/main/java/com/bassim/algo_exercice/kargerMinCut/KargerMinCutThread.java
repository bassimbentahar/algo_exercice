package com.bassim.algo_exercice.kargerMinCut;

import com.bassim.algo_exercice.kargerMinCut.utils.TestDataFactory;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class KargerMinCutThread {

    private static final String sINPUT_FILE = "kargerMinCut.txt";
    private static final int NUM_ITERATIONS = 100;

    public static void main(String[] args) {
        Map<Integer, List<Integer>> originalGraph = TestDataFactory.getInput(sINPUT_FILE);

        ExecutorService exec = Executors.newFixedThreadPool(50);
        List<Callable<Integer>> jobs = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Map<Integer, List<Integer>> copy = deepCopyGraph(originalGraph);
            jobs.add(() -> runKargerMinCut(copy));   // méthode qui fait le boulot
        }

        List<Future<Integer>> futures = null;
        try {
            futures = exec.invokeAll(jobs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int minCut = futures.stream()
                .mapToInt(f -> {
                    try { return f.get(); }
                    catch (Exception e) { throw new RuntimeException(e); }
                })
                .min()
                .orElse(Integer.MAX_VALUE);

        System.out.println("Min-cut trouvé : " + minCut);
        exec.shutdown();

    }

    private static int runKargerMinCut(Map<Integer, List<Integer>> graph) {
        Random random = new Random();

        while (graph.size() > 2) {
            List<Integer> nodes = new ArrayList<>(graph.keySet());
            int u, v;
            List<Integer> uAdj;

            do {
                u = nodes.get(random.nextInt(nodes.size()));
                uAdj = graph.get(u);
            } while (uAdj == null || uAdj.isEmpty());

            v = uAdj.get(random.nextInt(uAdj.size()));// voisin choisi

            // a. Ajouter tous les voisins de v à la liste d’adjacence de u
            List<Integer> vAdj = graph.get(v);
            if (vAdj == null) continue; // ou throw exception/log si anormal
            graph.get(u).addAll(vAdj);

            // b. Supprimer les boucles (u -> u)
            final int finalU = u;
            graph.get(u).removeIf(e -> e == finalU);

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
        }

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
