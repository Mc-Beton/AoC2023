package Day1.part1;

import java.io.*;

public class Part1 {

    public static void main(String[] args) {

        String fileName = "day2.txt";

        try (InputStream inputStream = Part1.class.getClassLoader().getResourceAsStream(fileName)) {
            assert inputStream != null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

                int sum = br.lines()
                        .mapToInt(str -> {

                            String digits = str.replaceAll("[^0-9]", "");

                            if (digits.length() >= 2) {
                                return Integer.parseInt("" + digits.charAt(0) + digits.charAt(digits.length() - 1));
                            } else if (digits.length() == 1) {
                                int digit = Integer.parseInt(digits);
                                return digit * 10 + digit;
                            } else {
                                return 0;
                            }
                        })
                        .sum();

                System.out.println("The sum is: " + sum);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
