import java.util.Arrays;
import java.util.Scanner;

public class UnixPathTask {
    private static int cachePosition = 0;
    private static String[] cacheArray;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String result = execute(input);
        System.out.println(result);
    }

    private static String execute(String input) {
        char[] inputArray = (input + "/").toCharArray();
        StringBuilder cache = new StringBuilder();
        cacheArray = new String[inputArray.length / 2 + 1];

        for (int i = 0; i < inputArray.length; i++) {
            cache.append(inputArray[i]);

            if (inputArray[i] == '/') {
                if (cache.toString().equals("//") || cache.toString().equals("/./")) {
                    cache.setLength(1);
                } else if (cache.toString().equals("/../")) {
                    removeTopFromCache();
                    cache.setLength(1);
                } else {
                    appendCache(cache);
                    cache.setLength(0);
                    cache.append('/');
                }
            }
        }

        if (cache.length() != 1 || (cache.length() == 1 && cachePosition == 0)) {
            cacheArray[cachePosition] = cache.toString();
            cachePosition++;
        }

        return Arrays.stream(cacheArray)
                .filter(str -> str != null)
                .reduce("", String::concat);
    }

    private static void removeTopFromCache() {
        cacheArray[cachePosition] = null;
        if (cachePosition > 0) {
            cachePosition--;
        }
    }

    private static void appendCache(StringBuilder cache) {
        if (cache.length() == 1) {
            return;
        }
        cacheArray[cachePosition] = cache.deleteCharAt(cache.length() - 1).toString();
        cachePosition++;
    }
}
