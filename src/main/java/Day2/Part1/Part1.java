package Day2.Part1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Part1 {

    public static void main(String[] args) {

        String fileName = "day2.txt";

        int maxRed = 12, maxGreen = 13, maxBlue = 14;

        int sumOfValidGameIds = 0;

        try (InputStream inputStream = Part1.class.getClassLoader().getResourceAsStream(fileName)) {
            assert inputStream != null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(": ");
                    int gameId = Integer.parseInt(parts[0].replace("Game ", ""));
                    String[] revealedSets = parts[1].split("; ");

                    boolean isValid = true;

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

                        if (redCount > maxRed || greenCount > maxGreen || blueCount > maxBlue) {
                            isValid = false;
                            break;
                        }
                    }

                    if (isValid) {
                        sumOfValidGameIds += gameId;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("The sum of valid game IDs is: " + sumOfValidGameIds);
    }
}
