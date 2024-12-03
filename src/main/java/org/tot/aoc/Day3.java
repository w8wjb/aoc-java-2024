package org.tot.aoc;

import java.util.List;
import java.util.regex.Pattern;

public class Day3 {

    public int solvePuzzle1(List<String> input) {

        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        int total = 0;

        for (String line : input) {
            var matcher = pattern.matcher(line);
            while (matcher.find()) {
                int left = Integer.parseInt(matcher.group(1));
                int right = Integer.parseInt(matcher.group(2));
                total += (left * right);
            }
        }

        return total;
    }

    public int solvePuzzle2(List<String> input) {

        Pattern pattern = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\))");

        int total = 0;
        boolean enabled = true;

        for (String line : input) {
            var matcher = pattern.matcher(line);
            while (matcher.find()) {

                switch (matcher.group()) {

                    case "do()":
                        enabled = true;
                        break;

                    case "don't()":
                        enabled = false;
                        break;

                    default:
                        if (enabled) {
                            int left = Integer.parseInt(matcher.group(2));
                            int right = Integer.parseInt(matcher.group(3));
                            total += (left * right);
                        }
                }
            }
        }

        return total;
    }

}
