package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        /*
         * [[Apple], [Apple], [Apple, beer, crow, foot], [Apple, beer, crow, foot], [Apple, happy],
         * [Apple, shot], [Apple, shot], [Apple, shot, crow], [Hungry], [Hungry, happy], [Hungry,
         * pattern], [Hungry, pattern], [Hungry, pattern]]
         * 
         */
        String[][] arrayOfArrays = {
                {"Apple"},
                {"Apple", "happy"},
                {"Apple", "beer", "crow", "foot"},
                {"Apple", "shot"},
                {"Hungry", "happy"},
                {"Hungry", "pattern"},
                {"Apple", "shot"},
                {"Apple", "shot", "crow"},
                {"Apple", "beer", "crow", "foot"},
                {"Hungry", "pattern"},
                {"Hungry", "pattern"},
                {"Apple"},
                {"Hungry"}};
        Arrays.sort(arrayOfArrays, (a, b) -> {
            String joinedA = String.join("", a);
            String joinedB = String.join("", b);
            return joinedA.compareTo(joinedB);
        });
        System.out.println(Arrays.deepToString(arrayOfArrays));
    }

}
