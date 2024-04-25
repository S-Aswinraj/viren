package Utility;

import java.util.concurrent.ThreadLocalRandom;

public class Generate_randomNum {
    public static int getRandomValue(int Min, int Max)
    {

        // Get and return the random integer
        // within Min and Max
        return ThreadLocalRandom
                .current()
                .nextInt(Min, Max + 1);
    }




}
