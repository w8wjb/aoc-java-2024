package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day10Test {

    @Test
    void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day10/sample1.txt");

        var day = new Day10();

        long result = day.solvePuzzle1(lines);

        Assertions.assertEquals(36, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day10/input1.txt");

        var day = new Day10();

        long result = day.solvePuzzle1(lines);

        Assertions.assertEquals(794, result);
    }

    @Test
    void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day10/sample1.txt");

        var day = new Day10();

        long result = day.solvePuzzle2(lines);

        Assertions.assertEquals(81, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day10/input1.txt");

        var day = new Day10();

        long result = day.solvePuzzle2(lines);

        Assertions.assertEquals(1706, result);
    }

}
