package com.bassim.algo_exercice.QuickSort.simple.utils;

public enum PivotChoice {
    FIRST_ELEMENT("first_element"),
    LAST_ELEMENT("last_element"),
    RANDOM("random"),
    MEDIAN_OF_THREE("median_of_three");

    private final String value;

    PivotChoice(String value) {
        this.value = value;
    }
}
