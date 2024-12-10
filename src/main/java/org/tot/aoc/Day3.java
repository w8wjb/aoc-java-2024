package org.tot.aoc;

import java.util.List;
import java.util.regex.Pattern;

public class Day3 {

    public int solvePuzzle1(List<String> input) {

        // This should find "mul(<any number>, <any number>)"
        // This has a bunch of crazy escaping. In the regex pattern, the backslashes are necessary, However, Java treats
        // a backslash as a special character, so we use "\\" to 'escape' the backslash, so a "\\" tells java to just treat
        // it as a single slash. That way the slashes get sent to the regex engine. Some programming languages have a
        // "raw" String that ignores special character in Strings, but Java doesn't, yet.
        //
        // However, you will notice both a "\(" and a "(". In regex syntax, the \( means "match a literal left parentheses"
        // whereas "(" actually starts a matching group.
        //
        // I'm using these groups later to pull out just the numbers
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        int total = 0;

        // Don't make the same mistake I did: I didn't realize there were multiple lines in the input
        for (String line : input) {
            // Apply the regex to the line
            var matcher = pattern.matcher(line);
            // find() finds the next match in the long String. It will return false when it can't find any more
            while (matcher.find()) {
                // Here are those groups I mentioned above. Group 0 is the whole match.
                // Group 1 & 2 are the parentheses around each of the numbers
                int left = Integer.parseInt(matcher.group(1));
                int right = Integer.parseInt(matcher.group(2));
                total += (left * right);
            }
        }

        return total;
    }

    public int solvePuzzle2(List<String> input) {

        // This pattern extends the same one above that finds "mul(#,#)", but it uses an option group
        // where (A|B|C) will match eiter A, B or C. In this case it's "do()", "don't()", or "mul(#,#)"
        Pattern pattern = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\))");

        int total = 0;

        // For part 2, this adds a toggle that we can use to keep track of whether mul(#,#) commands are ignored or not.
        // "At the beginning of the program, mul instructions are enabled."
        boolean enabled = true;

        for (String line : input) {
            var matcher = pattern.matcher(line);
            while (matcher.find()) {

                // As before, group 0 is the entire match. Because of the option group, we can switch on the cases.
                switch (matcher.group()) {

                    case "do()":
                        enabled = true;
                        break;

                    case "don't()":
                        enabled = false;
                        break;

                    default:
                        // Only do the math if it's enabled
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
