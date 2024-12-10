package org.tot.aoc;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.StringGrid;
import org.tot.aoc.grid.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class Day5 {

    // This is a Set that will contain the pairs of numbers that define the ordering rules.
    // It's here, outside the other methods because it's needed by the inner class
    Set<Pair<Integer, Integer>> orderingRules = new HashSet<>();


    private List<List<Integer>> parseInput(List<String> input) {

        orderingRules.clear();
        boolean rulesSection = true;

        List<List<Integer>> updates = new ArrayList<>();

        for (String line : input) {

            // When we encounter the blank line, we have to change the way the input is parsed.
            if (line.isBlank()) {
                rulesSection = false;
                continue;
            }

            if (rulesSection) {
                // Each line in the rules section is two numbers separated by a pipe |
                // First, split the line into 2 parts
                String[] parts = line.split("\\|");
                // Using the Commons-Lang Pair class here
                Pair<Integer, Integer> pair = new ImmutablePair<>(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
                orderingRules.add(pair);

            } else {
                // For the pages list, split the line by the commas, convert each item to and int, and then collect to a list
                List<Integer> pages = Arrays.stream(line.split(","))
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());
                updates.add(pages);
            }

        }
        return updates;
    }


    /**
     * This is the magic for this puzzle. In Java, you can provide a custom Comparator to be used when sorting a list.
     * This class uses the provide rules to determine the ordering of the pages.
     */
    private class PageComparator implements Comparator<Integer> {

        /**
         * Taking advantage of the fact that Comparators are objects on their own and can have side effects.
         * Defaults to true, because we assume, at the beginning, that the pages are in order until we find otherwise.
         */
        public boolean orderedCorrectly = true;

        /**
         *
         * @param left the first object to be compared.
         * @param right the second object to be compared.
         * @return L &lt; R = -1, L &gt; R = 1, and 0 for no change
         */
        @Override
        public int compare(Integer left, Integer right) {

            // Try both combinations
            var lr = new ImmutablePair<>(left, right);
            var rl = new ImmutablePair<>(right, left);

            if (orderingRules.contains(lr)) {
                // If we find a match in the rules, but backwards, we can tell that this pair is out of order.
                orderedCorrectly = false;
                return -1;
            } else if (orderingRules.contains(rl)) {
                return 1;
            }

            return 0;
        }
    }


    public int solvePuzzle1(List<String> input) {

        int total = 0;

        var updates = parseInput(input);

        for (List<Integer> update : updates) {

            // Creating a new comparator per trip through the loop because we want orderedCorrectly to reset to true
            var comparator = new PageComparator();

            // This is where most of the work is done
            update.sort(comparator);


            if (comparator.orderedCorrectly) {
                // Find the number in the middle of the update list
                int middleIndex = update.size() / 2;
                total += update.get(middleIndex);
            }
        }


        return total;
    }

    public int solvePuzzle2(List<String> input) {

        int total = 0;

        var updates = parseInput(input);

        for (List<Integer> update : updates) {
            var comparator = new PageComparator();
            update.sort(comparator);


            // The difference between part 1 and part 2 is literally just the not (!) sign below
            if (!comparator.orderedCorrectly) {
                int middleIndex = update.size() / 2;
                total += update.get(middleIndex);
            }
        }


        return total;
    }

}
