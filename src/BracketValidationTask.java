import com.sun.javaws.exceptions.InvalidArgumentException;

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
        while (couldBeCorrect && i < array.length) {
            switch (array[i]) {
                case '{':
                case '[':
                case '(':
                    cacheArray[cachePosition] = getReverseBracket(array[i]);
                    cachePosition++;
                    break;
                case '}':
                case ']':
                case ')':
                    if (cachePosition - 1 < 0 || cacheArray[cachePosition - 1] != array[i]) {
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

    private static char getReverseBracket(char ch) {
        char result;
        switch (ch) {
            case '{':
                result = '}';
                break;
            case '[':
                result = ']';
                break;
            case '(':
                result = ')';
                break;
            default:
                throw new IllegalArgumentException("Unsupported character");
        }
        return result;
    }
}
