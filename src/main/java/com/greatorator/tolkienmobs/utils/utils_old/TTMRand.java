package com.greatorator.tolkienmobs.utils.utils_old;

public class TTMRand {

    public static int getRandom(int max){
        return (int) (Math.random()*max);
    }

    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
}