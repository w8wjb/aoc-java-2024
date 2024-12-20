package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day20Test {

    @Test
    void testSample1_2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/sample1.txt");

        var day = new Day20();

        int result = day.solvePuzzle1(lines, 2);

        Assertions.assertEquals(44, result);
    }

    @Test
    void testSample1_64() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/sample1.txt");

        var day = new Day20();

        int result = day.solvePuzzle1(lines, 64);

        Assertions.assertEquals(1, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/input1.txt");

        var day = new Day20();

        int result = day.solvePuzzle1(lines, 100);

        Assertions.assertEquals(1378, result);
    }

    @Test
    void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/sample2.txt");

        var day = new Day20();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(48, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day20/input1.txt");

        var day = new Day20();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(102467299, result);
    }

}
