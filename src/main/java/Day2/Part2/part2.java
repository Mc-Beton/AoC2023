package Day2.Part2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class part2 {

    public static void main(String[] args) {

        String fileName = "day2.txt";

        int sumOfPowers = 0;

        try (InputStream inputStream = part2.class.getClassLoader().getResourceAsStream(fileName)) {
            assert inputStream != null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(": ");
                    int gameId = Integer.parseInt(parts[0].replace("Game ", ""));
                    String[] revealedSets = parts[1].split("; ");

                    int minRed = 0, minGreen = 0, minBlue = 0;

                    for (String set : revealedSets) {
                        int redCount = 0, greenCount = 0, blueCount = 0;

                        String[] cubes = set.split(", ");
                        for (String cube : cubes) {
                            String[] cubeParts = cube.split(" ");
                            int count = Integer.parseInt(cubeParts[0]);
                            String color = cubeParts[1];

                            switch (color) {
                                case "red":
                                    redCount += count;
                                    break;
                                case "green":
                                    greenCount += count;
                                    break;
                                case "blue":
                                    blueCount += count;
                                    break;
                            }
                        }

                        minRed = Math.max(minRed, redCount);
                        minGreen = Math.max(minGreen, greenCount);
                        minBlue = Math.max(minBlue, blueCount);
                    }

                    int power = minRed * minGreen * minBlue;

                    sumOfPowers += power;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("The sum of powers is: " + sumOfPowers);
    }
}
