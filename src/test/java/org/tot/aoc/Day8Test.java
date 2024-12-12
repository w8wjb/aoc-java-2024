package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day8Test {

    @Test
    void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day8/sample1.txt");

        var day = new Day8();

        long result = day.solvePuzzle1(lines);

        Assertions.assertEquals(14, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day8/input1.txt");

        var day = new Day8();

        long result = day.solvePuzzle1(lines);

        Assertions.assertEquals(379, result);
    }

    @Test
    void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day8/sample1.txt");

        var day = new Day8();

        long result = day.solvePuzzle2(lines);

        Assertions.assertEquals(34, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day8/input1.txt");

        var day = new Day8();

        long result = day.solvePuzzle2(lines);

        Assertions.assertEquals(1339, result);
    }

}
