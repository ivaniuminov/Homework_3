import java.util.Scanner;

public class UnixPath_Task {
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
        cacheArray = new String[inputArray.length];

        for (int i = 0; i < inputArray.length; i++) {
            cache.append(inputArray[i]);

            if (inputArray[i] == '/') {
                String cacheString = cache.toString();
                if (cacheString.equals("//") || cacheString.equals("/./")) {
                    cache.setLength(1);
                } else if (cacheString.equals("/../")) {
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
        String result = prepareResult();
        return result;
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

    private static String prepareResult() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < cachePosition; i++) {
            result.append(cacheArray[i]);
        }

        return result.toString();
    }
}
