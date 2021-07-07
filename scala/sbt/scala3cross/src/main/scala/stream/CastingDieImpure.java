package stream;

import java.util.Random;

public class CastingDieImpure {
    public static class NoFailures {
        static int castTheDieImpure() {
            System.out.println("The die is cast");
            Random rand = new Random();
            return rand.nextInt(6) + 1;
        }
    }
}
