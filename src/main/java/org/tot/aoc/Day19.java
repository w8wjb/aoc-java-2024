package org.tot.aoc;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day19 {

    Set<String> allTowels = new TreeSet<>();
    List<String> desiredDesigns = new ArrayList<>();

    Map<String, Integer> patternCounts = new HashMap<>();

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

    public int solvePuzzle1(List<String> input) {

        parseInput(input);
        discardUnnecessaryTowels();

        AtomicInteger possible = new AtomicInteger(0);
        for (String design : desiredDesigns) {
            findTowels(design, allTowels, possible, false);
        }


        return possible.get();
    }

    void discardUnnecessaryTowels() {

        Set<String> towelsTemp = new TreeSet<>(allTowels);

        Iterator<String> itor = towelsTemp.iterator();
        while (itor.hasNext()) {
            String towel = itor.next();

            Set<String> withoutMe = new TreeSet<>(allTowels);
            withoutMe.remove(towel);
            AtomicInteger possible = new AtomicInteger(0);
            if (findTowels(towel, withoutMe, possible, false)) {
                itor.remove();
            }

        }
        allTowels = towelsTemp;
    }

    boolean findTowels(String design, Set<String> availableTowels, AtomicInteger possible, boolean all) {


        Integer memoCount = null;

        for (String towel : availableTowels) {
            if (design.startsWith(towel)) {
                String subdesign = design.substring(towel.length());
                if (subdesign.isEmpty()) {
                    possible.incrementAndGet();
                    if (all) {
                        continue;
                    }
                    return true;
                }

                memoCount = patternCounts.get(subdesign);
                if (memoCount != null) {
                    possible.getAndAdd(memoCount);
                    if (all) {
                        continue;
                    }
                    return true;
                }

                boolean success = findTowels(subdesign, availableTowels, possible, all);
                if (success) {
                    int count = patternCounts.getOrDefault(subdesign, 0);
                    patternCounts.put(subdesign, count + 1);
                    if (all) {
                        continue;
                    }
                    return true;
                }

            }
        }

        return false;
    }


    public int solvePuzzle2(List<String> input) {

        parseInput(input);

        AtomicInteger possible = new AtomicInteger(0);
        for (String design : desiredDesigns) {
            findTowels(design, allTowels, possible, true);
        }


        return possible.get();
    }

}
