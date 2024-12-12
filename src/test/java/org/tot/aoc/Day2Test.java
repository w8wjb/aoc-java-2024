package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day2Test {

    @Test
    void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day2/sample1.txt");

        var day = new Day2();

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(2, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day2/input1.txt");

        var day = new Day2();

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(516, result);
    }

    @Test
    void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day2/sample1.txt");

        var day = new Day2();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(4, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day2/input1.txt");

        var day = new Day2();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(561, result);
    }

}
