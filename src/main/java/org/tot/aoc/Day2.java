package org.tot.aoc;

import java.util.*;

public class Day2 {


    private boolean isSafe(int last, int next, int direction) {
        int distance = Math.abs(last - next);

        if (distance < 1 || distance > 3) {
            return false;
        }

        int currDirection = (next > last) ? 1 : -1;
        return currDirection == direction;
    }


    public int solvePuzzle1(List<String> input) {

        int safeCount = 0;

        for (String line : input) {
            Scanner scan = new Scanner(line);

            int direction = 0;
            int unsafeLevels = 0;
            int lastLevel = scan.nextInt();

            while (scan.hasNext()) {
                int level = scan.nextInt();

                int currDirection = (level > lastLevel) ? 1 : -1;
                if (direction == 0) {
                    direction = currDirection;
                }

                if (!isSafe(lastLevel, level, direction)) {
                    unsafeLevels++;
                }
                lastLevel = level;
            }
            if (unsafeLevels == 0) {
                safeCount++;
            }
        }


        return safeCount;
    }

    private boolean isSafe(int[] levels) {
        int direction = 0;
        int unsafeLevels = 0;
        for (int i = 1; i < levels.length; i++) {
            int level = levels[i];
            int lastLevel = levels[i-1];

            int currDirection = (level > lastLevel) ? 1 : -1;
            if (direction == 0) {
                direction = currDirection;
            }

            if (!isSafe(lastLevel, level, direction)) {
                unsafeLevels++;
            }
        }
        return unsafeLevels == 0;
    }

    public int solvePuzzle2(List<String> input) {

        int safeCount = 0;

        for (String line : input) {

            int[] levels = Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (isSafe(levels)) {
                safeCount++;
            } else {
                for (int i = 0; i < levels.length; i++) {
                    int[] omitList = new int[levels.length - 1];
                    System.arraycopy(levels, 0, omitList, 0, i);
                    System.arraycopy(levels, i+1, omitList, i, omitList.length - i);

                    if (isSafe(omitList)) {
                        safeCount++;
                        break;
                    }
                }

            }

//
//            int direction = 0;
//            int unsafeLevels = 0;
//            boolean skipUsed = false;
//            int lastLevel = levels[0];
//
//            for (int i = 1; i < levels.length; i++) {
//                int level = levels[i];
//
//                int currDirection = (level > lastLevel) ? 1 : -1;
//                if (direction == 0) {
//                    direction = currDirection;
//                }
//
//                if (!isSafe(lastLevel, level, direction)) {
//                    if (!skipUsed) {
//                        if (i > 1 && isSafe(levels[i - 2], level, direction)) {
//                            // Would it work if I skipped the one prior?
////                            System.out.print("Skipped index ");
////                            System.out.println(i-1);
//                            lastLevel = levels[i - 2];
//                            skipUsed = true;
//                            continue;
//                        } else if (i < (levels.length - 1) && isSafe(levels[i - 1], levels[i + 1], direction)) {
//                            // What if I skip the current one, would that fix it?
//                            lastLevel = levels[i - 1];
//                            skipUsed = true;
////                            System.out.print("Skipped index ");
////                            System.out.println(i);
//                            continue;
//                        } else if (i == levels.length - 1) {
//                            skipUsed = true;
//                            System.out.println(line);
//                            // Edge case: bad level is the last one in the list
//                            continue;
//                        }
//                    }
//                    unsafeLevels++;
//                }
//                lastLevel = level;
//            }
//            if (unsafeLevels == 0) {
//                System.out.print("Safe");
//                if (skipUsed) {
//                    System.out.print(" * :");
//                } else {
//                    System.out.print("   :");
//                }
//                safeCount++;
//            } else {
//                System.out.print("Unsafe :");
//            }
//            System.out.println(line);
        }


        return safeCount;
    }

}
