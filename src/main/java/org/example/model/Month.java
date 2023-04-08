package org.example.model;

import java.util.Arrays;

public enum Month {
    JAN(new int[31]),
    FEB(new int[28]),
    MAR(new int[31]),
    APR(new int[31]),
    MAY(new int[31]),
    JUNE(new int[31]),
    JULY(new int[31]),
    AUG(new int[31]),
    SEP(new int[31]),
    OCT(new int[31]),
    NOV(new int[31]),
    DEC(new int[31]);



    private int[] day;

    Month(int... day) {

        this.day = day;
    }

    public int[] getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "Month{" +
                "day=" + Arrays.toString(day) +
                '}';
    }
}
