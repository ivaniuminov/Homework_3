package com.iuminov.classes;

public class BracketValidationTask {
    public static void main(String[] args) {
        String input = "()[({(())})]";
        String input2 = "([)]";
        testString(input);
        testString(input2);
    }

    static String testString(String input) {
        char[] array = input.toCharArray();
        char[] cacheArray = new char[input.length()];
        int cachePosition = 0;
        boolean couldBeCorrect = true;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == '{' || array[i] == '[' || array[i] == '(') {
                cacheArray[cachePosition] = array[i];
                cachePosition++;
            } else if (cachePosition == 0 || (array[i] - cacheArray[cachePosition - 1] > 2) || (array[i] - cacheArray[cachePosition - 1] < 1)) {
                couldBeCorrect = false;
                break;
            } else {
                cachePosition--;
                cacheArray[cachePosition] = '\u0000';
            }
        }

        return couldBeCorrect && cachePosition == 0 ? "is correct" : "isn't correct";
    }
}
