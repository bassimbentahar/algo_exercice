package com.bassim.algo_exercice.fibo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Exercice1{
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        var f1 = new FibonacciTask(5);
        var res = pool.invoke(f1);

        System.out.println(res);
    }

public static class FibonacciTask extends RecursiveTask<Integer> {

    private int n;
    public FibonacciTask(int n) {this.n=n;}

    @Override
    protected Integer compute() {
        if(n<=1) return 1;
        FibonacciTask left = new FibonacciTask(n-1);
        FibonacciTask right = new FibonacciTask(n-2);
        right.fork();

        return left.compute() + right.join();
    }
}
}
