import java.util.Stack;

public class BracketValidationTask {
    public static void main(String[] args) {
        String input = "()[({(())})]";
        String input2 = "([)]";
        System.out.println(testString(input));
        System.out.println(testString(input2));
    }

    static String testString(String input) {
        char[] array = input.toCharArray();
        Stack<Character> stack = new Stack<>();
        boolean couldBeCorrect = true;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == '{' || array[i] == '[' || array[i] == '(') {
            	stack.push(array[i]);
            } else if (stack.isEmpty() || (array[i] - stack.peek() > 2) || (array[i] - stack.peek() < 1)) {
                couldBeCorrect = false;
                break;
            } else {
                stack.pop();
            }
        }

        return couldBeCorrect && stack.empty() ? "is correct" : "isn't correct";
    }
}
