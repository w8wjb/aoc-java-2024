package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day18Test {

    @Test
    void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day18/sample1.txt");

        var day = new Day18();

        int result = day.solvePuzzle1(lines, 6, 6, 12);

        Assertions.assertEquals(22, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day18/input1.txt");

        var day = new Day18();

        int result = day.solvePuzzle1(lines, 70, 70, 1024);

        Assertions.assertEquals(248, result);
    }

    @Test
    void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day18/sample1.txt");

        var day = new Day18();

        String result = day.solvePuzzle2(lines, 6, 6, 12);

        Assertions.assertEquals("6,1", result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day18/input1.txt");

        var day = new Day18();

        String result = day.solvePuzzle2(lines, 70, 70, 1024);

        Assertions.assertEquals("32,55", result);
    }

}
