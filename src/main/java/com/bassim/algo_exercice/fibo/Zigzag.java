package com.bassim.algo_exercice.fibo;

import java.util.ArrayList;
import java.util.List;

public class Zigzag {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int nbRows = 3;
        System.out.println(STR."result \{zigZag2(s, nbRows)}");
    }

    private static String zigZag(String s, int nbRows) {
        if (nbRows <= 1) return s;
        List<List<Character>> list = new ArrayList<>();
        for (int e = 0; e < nbRows; e++) {
            list.add(new ArrayList());
        }

        int i = 0;
        int j = 0;
        boolean upDown = true;

        while (i < s.length()) {
            list.get(j).add(s.charAt(i));

            j = incOrDec(j, upDown);
            if (j == nbRows - 1 || j == 0) {
                upDown = !upDown;

            }
            i++;
        }
        StringBuilder sb = new StringBuilder();
        list.forEach(e -> {
            e.forEach(sb::append);
        });
        return sb.toString();
    }

    private static String zigZag2(String s, int numRows) {
        if (numRows <= 1) return s;
        StringBuilder[] list = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) list[i] = (new StringBuilder());


        int i = 0;
        int j = 0;
        boolean upDown = true;

        while (i < s.length()) {
            list[j].append(s.charAt(i));

            j = incOrDec(j, upDown);
            if (j == numRows - 1 || j == 0) {
                upDown = !upDown;

            }
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for(StringBuilder row : list){
            sb.append(row);
        }

        return sb.toString();
    }

    private static int incOrDec(int n, boolean upDown) {
        return upDown ? ++n : --n;
    }
}
