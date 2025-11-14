package com.bassim.algo_exercice.fibo.singleton.thread;

import org.springframework.context.event.SimpleApplicationEventMulticaster;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);

        Set<String> keys1 = map.keySet();
        Set<String> keys2 = map.keySet();

        System.out.println(keys1 == keys2); // true, même objet
        keys1.remove("A");

        System.out.println("-------------------");

        //WeakHashMap
        //LinkedHashMap;
        //SimpleApplicationEventMulticaster

        //new BigDecimal("1.0").equals(new BigDecimal("1.00"));
        //Comparator.comparingInt().thenComparingInt()

        Order o = new Order();
        o.getItems().add("Croissant"); // UnsupportedOperationException ❌


        System.out.println(map.containsKey("A")); // false, modification visible sur la Map
        Callable<Logger> task = () -> {
            Logger logger = Logger.getInstance();
            Logger logger2 = Logger.getInstance();
            logger.log("Bonjour depuis ce thread !");
            System.out.println("A l'interieur du même thread, même instance ? " + (logger == logger2));
            return logger;
        };

        Future<Logger> future1 = executor.submit(task);
        Future<Logger> future2 = executor.submit(task);
        Future<Logger> future3 = executor.submit(task);

        // Récupérer les Logger depuis chaque thread
        Logger logger1 = future1.get();
        Logger logger2 = future2.get();
        Logger logger3 = future3.get();

        System.out.println("Logger1 == Logger2 ? " + (logger1 == logger2));
        System.out.println("Logger1 == Logger3 ? " + (logger1 == logger3));

        executor.shutdown();
    }
    public static class Order {
        public final List<String> items = new ArrayList<>();

        public List<String> getItems() {
            return List.copyOf(items); // Java 10+
        }
    }
}
