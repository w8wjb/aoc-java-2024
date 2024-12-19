package org.tot.aoc;

import java.util.*;

public class Day19 {

    Set<String> allTowels = new TreeSet<>();
    List<String> desiredDesigns = new ArrayList<>();

    Map<String, Long> patternCounts = new HashMap<>();

    void parseInput(List<String> input) {

        for (String line : input) {
            String[] items = line.split(",\\s+");

            if (items.length > 1) {
                allTowels.addAll(Arrays.asList(items));
            } else if (items.length == 1) {
                String item = items[0];
                if (!item.isBlank()) {
                    desiredDesigns.add(item);
                }
            }
        }

    }

    public long solvePuzzle1(List<String> input) {

        parseInput(input);
        discardUnnecessaryTowels();

        long possible = 0;
        for (String design : desiredDesigns) {
            possible += findTowels(design, allTowels, true);
        }

        return possible;
    }

    void discardUnnecessaryTowels() {

        Set<String> towelsTemp = new TreeSet<>(allTowels);

        Iterator<String> itor = towelsTemp.iterator();
        while (itor.hasNext()) {
            String towel = itor.next();

            Set<String> withoutMe = new TreeSet<>(allTowels);
            withoutMe.remove(towel);
            if (findTowels(towel, withoutMe, true) > 0) {
                itor.remove();
            }

        }
        allTowels = towelsTemp;
        patternCounts.clear();
    }

    long findTowels(String design, Set<String> availableTowels, boolean first) {

        Long memoCount = patternCounts.get(design);
        if (memoCount != null) {
            return memoCount;
        }

        long combinationCount = 0;

        for (String towel : availableTowels) {
            if (design.startsWith(towel)) {
                String subdesign = design.substring(towel.length());
                if (subdesign.isEmpty()) {
                    combinationCount += 1;
                } else {
                    long count = findTowels(subdesign, availableTowels, first);
                    combinationCount += count;
                }

                if (first && combinationCount > 0) {
                    return combinationCount;
                }
            }
        }

        patternCounts.put(design, combinationCount);

        return combinationCount;
    }


    public long solvePuzzle2(List<String> input) {

        parseInput(input);

        long possible = 0;
        for (String design : desiredDesigns) {
            possible += findTowels(design, allTowels, false);
        }


        return possible;
    }

}
