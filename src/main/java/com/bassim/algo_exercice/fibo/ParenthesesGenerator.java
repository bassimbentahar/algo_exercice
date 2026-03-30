package com.bassim.algo_exercice.fibo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParenthesesGenerator {




    public static void main(String[] args) {
        int n = 3;
        List<String> combinations = generateParentheses(n);
        System.out.println(combinations); // ["((()))","(()())","(())()","()(())","()()()"]
    }

    public static List<String> generateParentheses(int n) {
        List<String> result = new ArrayList<>();
        dfs(result, "", 0, 0, n);
        return result;
    }

    private static void dfs(List<String> result, String current, int nbLeftP, int nbRightP, int n) {
        if (current.length() == n * 2) result.add(current);

        if (nbLeftP < n) dfs(result, current + "(", nbLeftP + 1, nbRightP, n);
        //On ne peut pas ajouter plus de ( que n.
        if (nbRightP < nbLeftP) dfs(result, current + ")", nbLeftP, nbRightP + 1, n);
        //On ne peut fermer une parenthèse ) que s’il y a déjà une ( ouverte.
        //(Autrement dit, il ne peut jamais y avoir plus de ) que de ( à un moment donné.)
    }
}

