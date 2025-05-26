package com.bassim.algo_exercice.happyG;

public class HappyG {
    public static void main(String[] args) {

    }

    public static boolean gHappy(String str) {
        for(int i = 0; i < str.length(); i++){

            if (str.charAt(i) == 'g'){
                boolean hasLeftG = i > 0 && str.charAt(i - 1) == 'g';
                boolean hasRightG = i < str.length()-1 && str.charAt(i + 1) == 'g';
                if (!hasLeftG || !hasRightG){
                    return false;
                }
            }
        }
        return true;
    }
}
