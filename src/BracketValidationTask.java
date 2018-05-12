public class BracketValidationTask {
    public static void main(String[] args) {
        String input = "()[({(())})]";
        String input2 = "([)]";
        testString(input);
        testString(input2);
    }

    private static void testString(String input) {
        char[] array = input.toCharArray();
        char[] cacheArray = new char[input.length()];
        int cachePosition = 0;
        boolean couldBeCorrect = true;
        int i = 0;

        while (i < array.length && couldBeCorrect) {
            switch (array[i]) {
                case '{':
                    cacheArray[cachePosition] = '}';
                    cachePosition++;
                    break;
                case '[':
                    cacheArray[cachePosition] = ']';
                    cachePosition++;
                    break;
                case '(':
                    cacheArray[cachePosition] = ')';
                    cachePosition++;
                    break;
                case '}':
                case ']':
                case ')':
                    if (cacheArray[cachePosition - 1] < 0 || cacheArray[cachePosition - 1] != array[i]) {
                        couldBeCorrect = false;
                    } else {
                        cachePosition--;
                    }
                    break;
            }
            i++;
        }
        System.out.println(couldBeCorrect ? "is correct" : "isn't correct");
    }
}
