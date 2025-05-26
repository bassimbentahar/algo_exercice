package com.bassim.algo_exercice.happyG;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

public class CountYZ {
    public static void main(String[] args) {
        new CountYZ().countYZStream("fez day day fyyyz");
    }

    public static int countYZ(String str) {
        int count = 0;
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            boolean endOfWord = i == str.length() - 1 || !Character.isLetter(str.charAt(i+1));

            if ((c == 'z' || c == 'y') && endOfWord) {
                count++;
            }
        }
    return count;
    }

    public void countYZStream(String str) {
        str = str.toLowerCase();

        List<Result> list = StreamSupport
                // Use a WordMatchSpliterator to add the indices of all
                // places in the input where word matches.
                .stream(new RecherchePhraseQuiFiniParYZ(str), false)
                .toList();

        list.forEach(System.out::println);
    }

    private class RecherchePhraseQuiFiniParYZ extends Spliterators.AbstractSpliterator<Result> {
        /**
         * The input string.
         */
        private CharSequence mInput;

        /**
         * The phrase matcher.
         */
        private final Matcher mWordMatcher;

        /**
         * The phrase matcher.
         */
        private Matcher mPhraseMatcher;

        private RecherchePhraseQuiFiniParYZ(String input) {
            super(Long.MAX_VALUE, ORDERED | NONNULL);
            String regexWord = "\\b\\w*[yzYZ]\\b";

            mWordMatcher = Pattern
                    // Compile the regex, which will ignore case.
                    .compile(regexWord,
                            Pattern.CASE_INSENSITIVE)

                    // Create a Matcher for the regex on the input.
                    .matcher(input);
        }

        @Override
        public boolean tryAdvance(Consumer<? super Result> action) {
            // If there's no match then we're done with the iteration.
            if (!mWordMatcher.find())
                return false;
            else {
                // Create a new Result object indicating where the index
                // starts.
                action.accept(new Result(mWordMatcher.start()));
                return true;
            }
        }

        @Override
        public Spliterator<Result> trySplit() {
            return null;
        }

        /**
         * Estimate the current size of the remaining input.
         */
        @Override
        public long estimateSize() {
            return mInput.length();
        }

        /**
         * The characteristics for this spliterator.
         */
        @Override
        public int characteristics() {
            return ORDERED + NONNULL + IMMUTABLE;
        }


    }
    private class Result{
        int index;

        public Result(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "index=" + index +
                    '}';
        }
    }
}
