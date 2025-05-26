package com.bassim.algo_exercice.Reactive.Flux.ex1;
import com.bassim.algo_exercice.Reactive.Flux.ex1.utils.AsyncTaskBarrier;

/**
 * This example shows how to apply Project Reactor features
 * synchronously and asynchronously to perform basic Flux operations,
 * including just(), fromIterable(), fromArray(), from(), doOnNext(),
 * map(), mapNotNull(), mergeWith(), repeat(), and subscribe().
 */
@SuppressWarnings("ConstantConditions")
public class ex1 {
    /**
     * Main entry point into the test program.
     */
    public static void main (String[] argv) {
        // Test BigFraction multiplication using a synchronous Flux
        // stream.
        AsyncTaskBarrier.register(FluxEx::testFractionMultiplicationSync1);

        // Another test of BigFraction multiplication using a
        // synchronous Flux stream.
        AsyncTaskBarrier.register(FluxEx::testFractionMultiplicationSync2);

        // Yet another test of BigFraction multiplication using a
        // synchronous Flux stream.
        AsyncTaskBarrier.register(FluxEx::testFractionMultiplicationSync3);

        // Test erroneous BigFraction division using a synchronous
        // Flux stream.
        AsyncTaskBarrier.register(FluxEx::testFractionDivisonErrorSync);

        // A test of the Flux.mapNotNull() operator using a synchronous
        // Flux stream.
        AsyncTaskBarrier.register(FluxEx::testMapNotNull);

        long testCount = AsyncTaskBarrier
                // Run all the tests.
                .runTasks()

                // Block until all the tests are done to allow future
                // computations to complete running.
                .block();

        // Print the results.
        System.out.println("Completed " + testCount + " tests");
    }
}
