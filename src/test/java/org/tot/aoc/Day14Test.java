package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day14Test {

    @Test
    void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day14/sample1.txt");

        var day = new Day14();

        int result = day.solvePuzzle1(lines, 11, 7, 100);

        Assertions.assertEquals(12, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day14/input1.txt");

        var day = new Day14();

        int result = day.solvePuzzle1(lines, 101, 103, 100);

        Assertions.assertEquals(225521010, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day14/input1.txt");

        var day = new Day14();

        int result = day.solvePuzzle2(lines, 101, 103, 10000);

        Assertions.assertEquals(7774, result);
    }

}
