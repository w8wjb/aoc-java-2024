package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day9Test {

    @Test
    void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day9/sample1.txt");

        var day = new Day9();

        long result = day.solvePuzzle1(lines.get(0));

        Assertions.assertEquals(1928, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day9/input1.txt");

        var day = new Day9();

        long result = day.solvePuzzle1(lines.get(0));

        Assertions.assertEquals(6200294120911L, result);
    }

    @Test
    void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day9/sample1.txt");

        var day = new Day9();

        long result = day.solvePuzzle2(lines.get(0));

        Assertions.assertEquals(2858, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day9/input1.txt");

        var day = new Day9();

        long result = day.solvePuzzle2(lines.get(0));

        Assertions.assertEquals(6227018762750L, result);
    }

}
