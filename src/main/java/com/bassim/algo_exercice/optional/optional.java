package com.bassim.algo_exercice.optional;

import java.util.Optional;

public class optional {
    public static void main(String[] args) {
        Optional<Integer> op1 = Optional.empty();
        Optional<Integer> op2 = Optional.of(10);

        Optional<Integer> op3 = Optional.of(null);// ne pas faire car retourne une exception(plante l'objectif de l'utilisation d'optional)
                            // il faut surtout pas donner une valeur qui peut être NULL à la méthode factor  Optional.of()

        Optional<Integer> op4 = Optional.ofNullable(null); // on peut car elle vérifie si c'est null(elle retourn un optinal empty)

        boolean v = op1.isPresent();// false
        boolean v2 = op2.isPresent();// true

        op2.get();// si null il throw NoSuchElementException ==> il faut etre sur qu'il y'a quelque chose
        // en général on le fait avec un if isPresent
        if(op1.isPresent()){
            Integer i = op2.get();
        }


    }
}
