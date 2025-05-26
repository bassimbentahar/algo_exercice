package com.bassim.algo_exercice.stream;
import java.util.List;
import java.util.function.Predicate;

public class CustomStreamPipeline {

    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Alex", "Charlie");

        // On définit notre prédicat comme dans un filter
        Predicate<String> filterPredicate = name -> name.startsWith("A");

        // On crée notre Sink terminal
        Sink<String> terminalSink = new Sink<>() {
            @Override
            public void accept(String item) {
                System.out.println(">> " + item);
            }
        };

        // On crée le Sink intermédiaire (filter)
        Sink<String> filterSink = new FilterSink<>(filterPredicate, terminalSink);

        // On simule le pipeline : push des éléments à la main
        for (String name : names) {
            filterSink.accept(name); // push dans le pipeline
        }
    }

    // Interface Sink = comme un consommateur (Consumer), mais chaînable
    interface Sink<T> {
        void accept(T item);
    }

    // Classe FilterSink = étape intermédiaire qui applique un filtre
    static class FilterSink<T> implements Sink<T> {
        private final Predicate<T> predicate;
        private final Sink<T> downstream;

        public FilterSink(Predicate<T> predicate, Sink<T> downstream) {
            this.predicate = predicate;
            this.downstream = downstream;
        }

        @Override
        public void accept(T item) {
            if (predicate.test(item)) {
                downstream.accept(item); // passe au prochain maillon si OK
            }
        }
    }
}
