package com.bassim.algo_exercice.withoutString;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class WithoutString {

    public static void main(String[] args) {
        System.out.println(withoutString("Hello there", "e"));
    }

    public static String withoutString(String base, String remove) {
        StringBuilder result = new StringBuilder(base);
        List<Integer> indexes = new ArrayList();

        for (int i = base.length()-1; i >= 0 + remove.length(); i--) {
            if (base.startsWith(remove, i)) {
                indexes.add(i);
            }
        }
        indexes.forEach(System.out::print);
        indexes
                .forEach(
                    i -> result.delete(i, i + remove.length())
                );

        return result.toString();

    }

}
