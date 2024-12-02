package org.tot.aoc;

import java.util.*;

public class Day1 {

    public int solvePuzzle1(List<String> input) {

        List<Integer> leftCol = new ArrayList<>();
        List<Integer> rightCol = new ArrayList<>();

        for (String line : input) {
            var scan = new Scanner(line);
            leftCol.add(scan.nextInt());
            rightCol.add(scan.nextInt());
        }

        Collections.sort(leftCol);
        Collections.sort(rightCol);


        int total = 0;

        for (int i = 0; i < leftCol.size(); i++) {
            int left = leftCol.get(i);
            int right = rightCol.get(i);

            int diff = Math.max(left, right) - Math.min(left, right);
            total += diff;
        }


        return total;
    }

    public int solvePuzzle2(List<String> input) {

        List<Integer> leftCol = new ArrayList<>();
        Map<Integer, Integer> counts = new HashMap<>();

        for (String line : input) {
            var scan = new Scanner(line);
            leftCol.add(scan.nextInt());

            int right = scan.nextInt();
            int count = counts.getOrDefault(right, 0);
            counts.put(right, count + 1);
        }

        int total = 0;
        for (int left : leftCol) {
            int count = counts.getOrDefault(left, 0);
            total += (left * count);
        }

        return total;
    }

}
