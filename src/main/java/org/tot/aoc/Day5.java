package org.tot.aoc;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.StringGrid;
import org.tot.aoc.grid.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class Day5 {

    Set<Pair<Integer, Integer>> orderingRules = new HashSet<>();


    private List<List<Integer>> parseInput(List<String> input) {

        orderingRules.clear();
        boolean rulesSection = true;

        List<List<Integer>> updates = new ArrayList<>();

        for (String line : input) {

            if (line.isBlank()) {
                rulesSection = false;
                continue;
            }

            if (rulesSection) {
                String[] parts = line.split("\\|");
                Pair<Integer, Integer> pair = new ImmutablePair<>(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
                orderingRules.add(pair);
            } else {
                List<Integer> pages = Arrays.stream(line.split(","))
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());
                updates.add(pages);
            }

        }
        return updates;
    }

    private class PageComparator implements Comparator<Integer> {

        public boolean orderedCorrectly = true;

        @Override
        public int compare(Integer left, Integer right) {

            var lr = new ImmutablePair<>(left, right);
            var rl = new ImmutablePair<>(right, left);
            if (orderingRules.contains(lr)) {
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
            var comparator = new PageComparator();
            update.sort(comparator);


            if (comparator.orderedCorrectly) {
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


            if (!comparator.orderedCorrectly) {
                int middleIndex = update.size() / 2;
                total += update.get(middleIndex);
            }
        }


        return total;
    }

}
