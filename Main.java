package com.company;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // for testing
        String[] array1 = new String[]{"Apple"};
        String[] array2 = new String[]{"Apple", "happy"};
        String[] array3 = new String[]{"Apple", "beer", "crow", "foot"};
        String[] array4 = new String[]{"Apple", "shot"};
        String[] array5 = new String[]{"Hungry", "happy"};
        String[] array6 = new String[]{"Hungry", "pattern"};
        String[] array7 = new String[]{"Apple", "shot"};
        String[] array8 = new String[]{"Apple", "shot", "crow"};
        String[] array9 = new String[]{"Apple", "beer", "crow", "foot"};
        String[] array10 = new String[]{"Hungry", "pattern"};
        String[] array11 = new String[]{"Hungry", "pattern"};
        String[] array12 = new String[]{"Apple"};
        String[] array13 = new String[]{"Hungry"};

        /*
       [[Apple], [Apple], [Apple, beer, crow, foot], [Apple, beer, crow, foot], [Apple, happy], [Apple, shot],
        [Apple, shot], [Apple, shot, crow], [Hungry], [Hungry, happy],
       [Hungry, pattern], [Hungry, pattern], [Hungry, pattern]]

         */



        String[][] myArrays = {array2, array1, array4, array5, array6, array7,array8,array9, array10, array11, array12, array3, array13};
        String[][] firstClone = myArrays.clone();
        String[][] secondClone = myArrays.clone();


        System.out.println("Array pre-sort:\t\t" + Arrays.deepToString(myArrays));
        sortArrays(myArrays);
        System.out.println("Array post-sort:\t" + Arrays.deepToString(myArrays) + "\r\n\r\n");

        System.out.println("-----dave's arrays-----\r\n\r\nComparing on array content only (alphabetic)");
        System.out.println("pre-sort:\t\t" + Arrays.deepToString(firstClone));
        Arrays.sort(firstClone, Main::compareArraysOnContentOnly);
        System.out.println("post-sort:\t" + Arrays.deepToString(firstClone) + "\r\n\r\n");
        // check out the java.util.Comparator interface. Abstractions for the win! :)
        // lets you focus on the meat of the problem (the *what*) without needing to worry about juggling the array elements around (the *how*)
        // also, descriptive commit messages are your friend! :)

        System.out.println("Comparing on array length first and then content");
        System.out.println("pre-sort:\t\t" + Arrays.deepToString(secondClone));
        // or with the stream API... same idea, different tool
        // Note that our original array 'secondClone' didn't change
        List<String[]> list = Arrays.stream(secondClone)
                .sorted(Main::compareArraysOnLengthAndContent)
                .collect(Collectors.toList());
        System.out.println("post-sort:\t" + Arrays.deepToString(list.toArray()));
        System.out.println("unmodified source array:\t" + Arrays.deepToString(secondClone));
    }

    public static int compareArraysOnContentOnly(String[] a1, String[] a2) {
        String left = String.join("", a1);
        String right = String.join("", a2);
        return left.compareTo(right);
    }

    public static int compareArraysOnLengthAndContent(String[] a1, String[] a2) {
        if (a1.length != a2.length) {
            return a1.length - a2.length;
        }
        String left = String.join("", a1);
        String right = String.join("", a2);
        return left.compareTo(right);
    }

    public static String [] returnMinLengthArray(String [] firstArray, String [] secondArray) {
        if (firstArray.length > secondArray.length) {
            return secondArray;
        } else {
            return firstArray;
        }
    }



    public static String [] compareTwoArrays(String [] firstArray, String [] secondArray) {


        String[] arrayReturn = firstArray;
        if(Arrays.equals(firstArray, secondArray)) {
            return arrayReturn;
        } else {
            int minLength = findMinLength(firstArray, secondArray);
            for(int i = 0; i < minLength; i++) {

               if(secondArray[i].compareToIgnoreCase(firstArray[i] ) > 0) {
                    arrayReturn = firstArray;

                    break;
                } else if (secondArray[i].compareToIgnoreCase(firstArray[i] ) < 0) {
                   arrayReturn = secondArray;

                   break;
                } else if(i+ 1 == minLength && (firstArray.length > minLength || secondArray.length > minLength)) {
                   arrayReturn = returnMinLengthArray(firstArray, secondArray);

               }

            }
        }
        return arrayReturn;
    }

    public static void sortArrays(String [][] myArrays) {
        String[] temp;
        for (int i = 0; i < myArrays.length; i++) {
            for (int j = i + 1; j < myArrays.length; j++) {
                temp = compareTwoArrays(myArrays[j], myArrays[i]);
                if(findArrayMatch(temp, myArrays[j])) {
                    myArrays[j] = myArrays[i];
                    myArrays[i] = temp;
                }
            }
        }
    }

    public static boolean findArrayMatch(String[] temp, String[] firstArray) {
        return Arrays.equals(temp, firstArray);

    }

    public static int findMinLength(String [] firstArray, String [] secondArray) {
        return Math.min(firstArray.length, secondArray.length);
    }


}
