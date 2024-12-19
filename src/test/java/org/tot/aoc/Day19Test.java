package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day19Test {

    @Test
    void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day19/sample1.txt");

        var day = new Day19();

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(6, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day19/input1.txt");

        var day = new Day19();

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(360, result);
    }

    @Test
    void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day19/sample1.txt");

        var day = new Day19();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(16, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day19/input1.txt");

        var day = new Day19();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(561, result);
    }

}
