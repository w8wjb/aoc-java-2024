package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day4Test {

    @Test
    void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day4/sample1.txt");

        var day = new Day4();

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(18, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day4/input1.txt");

        var day = new Day4();

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(2514, result);
    }

    @Test
    void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day4/sample1.txt");

        var day = new Day4();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(9, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day4/input1.txt");

        var day = new Day4();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(1888, result);
    }

}
