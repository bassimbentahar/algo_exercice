package com.bassim.algo_exercice.medianMaintenance;

import reactor.core.publisher.Flux;

import java.util.*;

public class MedianMaintenance {
    // Liste d'entrée contenant les nombres du fichier
    private final List<Integer> list;

    // max_heap contient les plus petits éléments (la moitié inférieure), max en haut
    private final Queue<Integer> max_heap = new PriorityQueue<>(Collections.reverseOrder());

    // min_heap contient les plus grands éléments (la moitié supérieure), min en haut
    private final Queue<Integer> min_heap = new PriorityQueue<>();

    // Constructeur qui reçoit la liste à traiter
    public MedianMaintenance(List<Integer> list) {
        this.list = list;
    }

    public static void main(String[] args) {
        // Lecture de la liste depuis le fichier Median.txt
        List<Integer> list = TestDataFactory.toListLongs("Median.txt");

        // Création d'une instance de la classe
        MedianMaintenance mm = new MedianMaintenance(list);

        long sum = 0;

        // Traitement de chaque nombre dans la liste
        for (int value : list) {
            // Calcul de la médiane après ajout du nouveau nombre
            sum += mm.addAndGetMedian(value);
        }

        Flux.fromIterable(list)
                .map(mm::addAndGetMedian)
                .reduce(0L, Long::sum)
                .map(s -> s % 10000)
                .doOnNext(result -> System.out.println("Résultat final (mod 10000): " + result))
                .block(); // Bloque l'exécution pour attendre la fin du flux

        // Affichage du résultat modulo 10000
        System.out.println(sum % 10000);  // Résultat attendu pour le fichier : 1213
    }

    /**
     * Ajoute une valeur à la structure de données et retourne la médiane courante.
     */
    public int addAndGetMedian(int value) {
        // Si max_heap est vide ou la valeur est inférieure au max de max_heap,
        // on ajoute dans max_heap (demi inférieure)
        if (max_heap.isEmpty() || value <= max_heap.peek()) {
            max_heap.offer(value);
        } else {
            // Sinon on ajoute dans min_heap (demi supérieure)
            min_heap.offer(value);
        }

        // Rééquilibrage des deux heaps : max_heap peut avoir au plus 1 élément de plus
        if (max_heap.size() > min_heap.size() + 1) {
            min_heap.offer(max_heap.poll());
        } else if (min_heap.size() > max_heap.size()) {
            max_heap.offer(min_heap.poll());
        }

        // La médiane est toujours le max de max_heap (car on favorise max_heap)
        return max_heap.peek();
    }
}
