package com.fstates.library;

import java.util.Random;

public class GameRNG
{
    private static long seed = 42;
    static Random random = new Random();

    // Gera aleatoriamente 0 ou 1
    public static int zeroOrOne()
    {
        return random.nextInt(2);
    }

    // Gera aleatoriamente 0 até n
    public int random(int n)
    {
        Random random = new Random(seed);
        return random.nextInt(n);
    }
}
