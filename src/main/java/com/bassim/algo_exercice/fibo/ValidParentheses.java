package com.bassim.algo_exercice.fibo;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {

    static {

    }

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(){[]}"));
        System.out.println(isValid("({[])"));
        System.out.println(isValid("(("));
    }

    public static boolean isValid(String s) {
            if(s.length()<=1) return false;
            ArrayDeque<Character> stack = new ArrayDeque<>();
            int i = 0;
            while (i < s.length()) {
                if (s.charAt(i) == '(') stack.push(')');
                else if (s.charAt(i) == '{') stack.push('}');
                else if (s.charAt(i) == '[') stack.push(']');
                else {
                    if (stack.isEmpty() || s.charAt(i)!= stack.pop()) return false;
                }
            i++;
            }
            return stack.isEmpty();
        }

    public static boolean isValid2(String s) {
        char[] stack = new char[s.length()];
        int top = -1; // pointeur de pile

        for (char c : s.toCharArray()) {
            if (c == '(') stack[++top] = ')';
            else if (c == '{') stack[++top] = '}';
            else if (c == '[') stack[++top] = ']';
            else {
                if (top < 0 || stack[top--] != c) return false;
            }
        }

        return top == -1;
    }
}
