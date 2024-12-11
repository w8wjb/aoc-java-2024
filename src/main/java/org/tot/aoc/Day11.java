package org.tot.aoc;

import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.Vector;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day11 {


    /**
     * This was my solution to part 1, but part 2 solves both.
     */
    @Deprecated()
    public long solvePuzzle1(List<Long> input, int blinks) {

        List<Long> stones = new ArrayList<>(input);

        for (int i=0; i < blinks; i++) {

            List<Long> newStones = new ArrayList<>();

            for (long stone : stones) {

                int numDigits = (int) (Math.log10(stone) + 1);

                if (stone == 0) {
                    newStones.add(1L);

                } else if ((numDigits % 2) == 0) {
                    long halfDigits = numDigits / 2;
                    long threshold = ((long) Math.pow(10, halfDigits));

                    long left = stone / threshold;
                    long right = stone % threshold;

                    newStones.add(left);
                    newStones.add(right);

                } else {
                    newStones.add(stone * 2024);
                }

            }

            stones = newStones;


        }


        return stones.size();
    }


    public long solvePuzzle2(List<Long> input, int blinks) {

        Map<Long, Long> stoneCounts = new HashMap<>();

        for (Long stone : input) {
            stoneCounts.put(stone, 1L);
        }

        for (int i=0; i < blinks; i++) {

            Map<Long, Long> newCounts = new HashMap<>();

            for (Map.Entry<Long, Long> entry : stoneCounts.entrySet()) {

                long stone = entry.getKey();
                long count = entry.getValue();

                int numDigits = (int) (Math.log10(stone) + 1);

                if (stone == 0) {
                    long newStone = stone + 1;
                    add(newCounts, newStone, count);

                } else if ((numDigits % 2) == 0) {
                    long halfDigits = numDigits / 2;
                    long threshold = ((long) Math.pow(10, halfDigits));

                    long left = stone / threshold;
                    long right = stone % threshold;

                    add(newCounts, left, count);
                    add(newCounts, right, count);

                } else {
                    long newStone = stone * 2024;
                    add(newCounts, newStone, count);
                }

            }

            stoneCounts = newCounts;

        }


        long sum = 0;
        for (long count : stoneCounts.values()) {
            sum += count;
        }

        return sum;
    }

    private void add(Map<Long, Long> to, long stone, long count) {
        long existing = to.getOrDefault(stone, 0L);
        to.put(stone, existing + count);
    }


}
