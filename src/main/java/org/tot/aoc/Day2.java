package org.tot.aoc;

import java.util.*;

public class Day2 {


    /**
     * Applies the rules to see if a pair of levels are safe:
     * <ul>
     * <li>The levels are either all increasing or all decreasing.</li>
     * <li>Any two adjacent levels differ by at least one and at most three.</li>
     * </ul>
     *
     * @param last      first level in a pair
     * @param next      second level in a pair
     * @param direction the overall direction the sequence is going. 1 means increasing, -1 means decreasing
     * @return true if the levels are safe
     */
    private boolean isSafe(int last, int next, int direction) {
        // Find the absolute distance between the two levels.
        int distance = Math.abs(last - next);

        // "Any two adjacent levels differ by at least one and at most three."
        if (distance < 1 || distance > 3) {
            return false;
        }

        // Calculate if the next one is increasing or decreasing
        int currDirection = (next > last) ? 1 : -1;
        // The current direction should match the overall direction
        return currDirection == direction;
    }


    public int solvePuzzle1(List<String> input) {

        int safeCount = 0;

        for (String line : input) {

            // Using a Scanner again, here, instead of splitting the String. Seems less messy to me.
            Scanner scan = new Scanner(line);

            // Keep track of the overall direction.
            // It starts at 0 because we don't know until the first pair what the direction will be
            int direction = 0;

            // Count how many unsafe levels we found
            int unsafeLevels = 0;

            // Grab the first level
            int lastLevel = scan.nextInt();

            while (scan.hasNext()) {
                // We're going to be comparing each level to the previous level in the list
                int level = scan.nextInt();

                // If the direction hasn't been determined yet, we need to set it
                if (direction == 0) {
                    direction = (level > lastLevel) ? 1 : -1;
                    ;
                }

                // Check to see if this pair of levels is safe
                if (!isSafe(lastLevel, level, direction)) {
                    // If not, increment the count
                    unsafeLevels++;
                }

                // The current level becomes the last level
                lastLevel = level;
            }

            // If no unsafe levels were found, add 1 to the safe count.
            if (unsafeLevels == 0) {
                safeCount++;
            }
        }


        return safeCount;
    }

    /**
     * Applies the safe/unsafe rules to an array of levels.
     *
     * <p>This is the same logic from part 1, just put in a method of its own so it can be called over and over</p>
     *
     * @param levels array of levels
     * @return true if the levels are safe
     */
    private boolean isSafe(int[] levels) {

        // Overall direction, defaults to zero because we don't know what it is yet
        int direction = 0;
        // Count of unsafe levels.
        int unsafeLevels = 0;

        // Loop over the levels, using a "for-i" style loop because I want the index number.
        // Notice that 'i' starts at 1. This is because I'll be accessing the previous item with "i - 1"
        // and it would crash on the first element, where i = 0.
        for (int i = 1; i < levels.length; i++) {

            // Unlike above, where the levels come from a Scanner, we can access the levels using indexes
            int level = levels[i];
            // Get the preceding item in the list
            int lastLevel = levels[i - 1];

            // If the direction hasn't been determined yet, we need to set it
            if (direction == 0) {
                direction = (level > lastLevel) ? 1 : -1;;
            }

            // Check to see if this pair of levels is safe
            if (!isSafe(lastLevel, level, direction)) {
                unsafeLevels++;
            }
        }

        // Return true if there are no unsafe levels
        return unsafeLevels == 0;
    }

    public int solvePuzzle2(List<String> input) {

        int safeCount = 0;

        for (String line : input) {

            // This is using Java's streams feature. This basically:
            // 1. Splits the string by whitespace using a regular expression, "\s+". This results in an array: String[]
            // 2. For each item in the String[], convert it to an int using `Integer.parseInt()`
            // 3. Convert this stream back to an array of int[]
            int[] levels = Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            // I'm using arrays instead of Lists here for efficiency.
            // I wanted to use some of Java's array copying functions
            // You could probably use a List just as well

            // Check the safety of the initial set of levels. If it's safe already, we can just count it and move on
            if (isSafe(levels)) {
                safeCount++;

            } else {
                // If we got here, it means that there is something in the list that makes it unsafe.
                // The approach from here is to go item by item in the array, take it out of the list, and check it
                for (int i = 0; i < levels.length; i++) {

                    // Make a new array that is 1 size smaller.
                    int[] omitList = new int[levels.length - 1];

                    // Copy items form the beginning of the source list to the target list, up to the item we want to omit
                    System.arraycopy(levels, 0, omitList, 0, i);
                    // Copy items from the end of the source list to the target list, after item we want to omit
                    System.arraycopy(levels, i + 1, omitList, i, omitList.length - i);

                    // Now, with this new, smaller list (minus the item) check it for safety.
                    if (isSafe(omitList)) {
                        safeCount++;
                        // This break is important because, once we've found an item that would make the list safe,
                        // we don't need to keep looking. The break stops the inner for loop, but not the outer line loop
                        break;
                    }
                }

            }

        }


        return safeCount;
    }

}
