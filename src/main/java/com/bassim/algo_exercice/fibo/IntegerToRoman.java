package com.bassim.algo_exercice.fibo;

import java.util.TreeMap;

public class IntegerToRoman {

    static TreeMap<Integer, String> romans = new TreeMap<>();

    static {
        // Ajouter toutes les valeurs classiques et soustractives
        romans.put(1, "I");
        romans.put(4, "IV");
        romans.put(5, "V");
        romans.put(9, "IX");
        romans.put(10, "X");
        romans.put(40, "XL");
        romans.put(50, "L");
        romans.put(90, "XC");
        romans.put(100, "C");
        romans.put(400, "CD");
        romans.put(500, "D");
        romans.put(900, "CM");
        romans.put(1000, "M");
    }


    public static void main(String[] args) {
        System.out.println(intToRoman(49));
    }

    public static String intToRoman(int num) {
        if (num > 3999 || num < 1) return "N'existe pas";

        int nbDigits = 1;
        int num2 = num;
        while (num2 > 10) {
            nbDigits *= 10;
            num2 /= 10;
        }
        System.out.println(nbDigits);

        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int firstDigits = num / nbDigits;
            if (firstDigits != 4 && firstDigits != 9) {
                Integer closInfOrEq = romans.floorKey(num);
                sb.append(romans.get(closInfOrEq));
                num -= closInfOrEq;
            } else {
                sb.append(romans.get(firstDigits * nbDigits));
                num = num - firstDigits * nbDigits;
            }
            if (num < nbDigits) {
                nbDigits = nbDigits / 10;
            }

        }
        return sb.toString();
    }

    public static String intToRoman2(int num) {
        if (num > 3999 || num < 1) return "N'existe pas";

        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int value = romans.floorKey(num);
            sb.append(romans.get(value));
            num -= value;
        }
        return sb.toString();
    }

    public static String intToRoman3(int num) {
        if (num < 1 || num > 3999) return "N'existe pas";

        int[] values =    {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols ={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
        }

        return sb.toString();
    }
}
