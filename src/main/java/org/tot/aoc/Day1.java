package org.tot.aoc;

import java.util.*;

public class Day1 {

    public int solvePuzzle1(List<String> input) {

        // Create 2 lists to hold the numbers, left column and right column
        List<Integer> leftCol = new ArrayList<>();
        List<Integer> rightCol = new ArrayList<>();

        // Parse each line
        for (String line : input) {
            // Rather than splitting strings like many do, Java has a Scanner class that already understands whitespace
            // and helper functions to convert from strings to ints
            var scan = new Scanner(line);
            // Grab the next int and add it to the list
            leftCol.add(scan.nextInt());
            rightCol.add(scan.nextInt());
        }

        // This is the main trick to this puzzle; if you sort both lists from smallest to largest, the pairs will
        // line up like they're supposed to.
        Collections.sort(leftCol);
        Collections.sort(rightCol);


        // Keep a running total
        int total = 0;

        // Using the index of just one column here
        for (int i = 0; i < leftCol.size(); i++) {

            // Grab the item at the same index in both lists
            int left = leftCol.get(i);
            int right = rightCol.get(i);

            // Find the difference between the two columns
            // They could be in reverse order, where right is larger than left, resulting in a negative number, so we
            // fix that by taking the absolute value.
            int diff = Math.abs(left - right);
            total += diff;
        }


        return total;
    }

    public int solvePuzzle2(List<String> input) {

        // This time, I'm only making a list for the left column.
        List<Integer> leftCol = new ArrayList<>();

        // This is a map where the key is a unique number in the right column and the value is the number of times
        // it occurs.
        Map<Integer, Integer> counts = new HashMap<>();

        // Parse each line
        for (String line : input) {
            var scan = new Scanner(line);

            // Add the first number to the left column
            leftCol.add(scan.nextInt());

            int right = scan.nextInt();

            // This is using a feature of Map where, if you lookup a key (first arg) and it's not there, it will
            // return the default (second arg). This is to avoid getting a null and having to handle it
            int count = counts.getOrDefault(right, 0);
            // Now, add 1 to the count and update the map
            counts.put(right, count + 1);
        }

        // Keep a running total
        int total = 0;
        for (int left : leftCol) {
            // For each number in the left column, find the count from the right
            int count = counts.getOrDefault(left, 0);
            total += (left * count);
        }

        return total;
    }

}
