package com.bassim.algo_exercice.optional;

import org.apache.logging.log4j.util.Strings;

import java.util.Optional;

public class Optional2 {

    public static void main(String[] args) {
        Optional<Integer> op1 = Optional.empty();
        Optional<Integer> op2 = Optional.of(10);

        Integer result = op1.orElse(-1); // retourne -1
        Integer result2 = op2.orElse(10);// retourne 10

        Integer result3 = op1.orElseGet(()-> -1); // -1

        Integer result4 = op2.orElseThrow();

        // map x-> y
        //flatmap()   x -> Optional of y       si on a une methode par exemple qui retourn un Optional
        // un optional dans un optional
        Optional<Integer> i = op2.flatMap(x -> twice(x));

    }

    private static Optional<Integer> twice(Integer x) {
        return Optional.of(x*2);
    }
}
