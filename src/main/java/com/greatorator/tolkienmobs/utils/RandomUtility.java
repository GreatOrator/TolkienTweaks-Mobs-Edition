package com.greatorator.tolkienmobs.utils;

import java.util.Random;

public class RandomUtility {

    public static int getRandom(int max){
        return (int) (Math.random()*max);
    }

    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }

    public static int nextInt(Random random, int minimum, int maximum) {
        return minimum >= maximum ? minimum : random.nextInt(maximum - minimum + 1) + minimum;
    }

    public static float degToRad(float degrees)
    {
        return degrees * (float)Math.PI / 180 ;
    }
}