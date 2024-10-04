package Day1.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Part2 {

    public static void main(String[] args) {
        Map<String, Integer> numberWordMap = new HashMap<>();
        numberWordMap.put("eight", 8);
        numberWordMap.put("seven", 7);
        numberWordMap.put("three", 3);
        numberWordMap.put("four", 4);
        numberWordMap.put("five", 5);
        numberWordMap.put("nine", 9);
        numberWordMap.put("zero", 0);
        numberWordMap.put("one", 1);
        numberWordMap.put("two", 2);
        numberWordMap.put("six", 6);

        String fileName = "day1.txt";

        try (InputStream inputStream = Part2.class.getClassLoader().getResourceAsStream(fileName)) {
            assert inputStream != null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

                int sum = br.lines()
                        .mapToInt(str -> {
                            StringBuilder digits = new StringBuilder();

                            int i = 0;
                            while (i < str.length()) {
                                boolean matched = false;

                                for (String word : numberWordMap.keySet()) {
                                    if (i + word.length() <= str.length() && str.startsWith(word, i)) {
                                        digits.append(numberWordMap.get(word));
                                        i ++;
                                        matched = true;
                                        break;
                                    }
                                }

                                if (!matched) {
                                    if (Character.isDigit(str.charAt(i))) {
                                        digits.append(str.charAt(i));
                                    }
                                    i++;
                                }
                            }

                            String extractedDigits = digits.toString();

                            if (extractedDigits.length() >= 2) {
                                return Integer.parseInt("" + extractedDigits.charAt(0) + extractedDigits.charAt(extractedDigits.length() - 1));
                            } else if (extractedDigits.length() == 1) {
                                int digit = Integer.parseInt(extractedDigits);
                                return digit * 10 + digit;
                            } else {
                                return 0;
                            }
                        })
                        .sum();

                System.out.println("The sum is: " + sum);
            } catch (IOException e) {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
