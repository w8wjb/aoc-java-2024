package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day20Test {

    @Test
    void testSample1_2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/sample1.txt");

        var day = new Day20(2, 2);

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(44, result);
    }

    @Test
    void testSample1_64() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/sample1.txt");

        var day = new Day20(64, 2);

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(1, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/input1.txt");

        var day = new Day20(100, 2);

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(1378, result);
    }

    @Test
    void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/sample1.txt");

        var day = new Day20(50, 20);

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(285, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/input1.txt");

        var day = new Day20(100, 20);

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(975379, result);
    }

}
