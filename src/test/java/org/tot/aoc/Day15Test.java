package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day15Test {

    @Test
    void testSample1_1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day15/sample1.txt");

        var day = new Day15();

        long result = day.solvePuzzle1(lines);

        Assertions.assertEquals(2028, result);
    }

    @Test
    void testSample1_2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day15/sample2.txt");

        var day = new Day15();

        long result = day.solvePuzzle1(lines);

        Assertions.assertEquals(10092, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day15/input1.txt");

        var day = new Day15();

        long result = day.solvePuzzle1(lines);

        Assertions.assertEquals(1457740, result);
    }

    @Test
    void testSample2_2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day15/sample2.txt");

        var day = new Day15();

        long result = day.solvePuzzle2(lines);

        Assertions.assertEquals(9021, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day15/input1.txt");

        var day = new Day15();

        long result = day.solvePuzzle2(lines);

        Assertions.assertEquals(1467145, result);
    }

}
