package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day13Test {

    @Test
    void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day13/sample1.txt");

        var day = new Day13();

        long result = day.solvePuzzle(lines, 0);

        Assertions.assertEquals(480, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day13/input1.txt");

        var day = new Day13();

        long result = day.solvePuzzle(lines, 0);

        Assertions.assertEquals(29438, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day13/input1.txt");

        var day = new Day13();

        long result = day.solvePuzzle(lines, 10000000000000L);

        Assertions.assertEquals(104958599303720L, result);
    }

}
