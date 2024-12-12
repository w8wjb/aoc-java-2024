package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day7Test {

    @Test
    void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day7/sample1.txt");

        var day = new Day7();

        long result = day.solvePuzzle1(lines);

        Assertions.assertEquals(3749, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day7/input1.txt");

        var day = new Day7();

        long result = day.solvePuzzle1(lines);

        Assertions.assertEquals(932137732557L, result);
    }

    @Test
    void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day7/sample1.txt");

        var day = new Day7();

        long result = day.solvePuzzle2(lines);

        Assertions.assertEquals(11387, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day7/input1.txt");

        var day = new Day7();

        long result = day.solvePuzzle2(lines);

        Assertions.assertEquals(661823605105500L, result);
    }

}
