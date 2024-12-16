package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day16Test {

    @Test
    void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day16/sample1.txt");

        var day = new Day16();

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(7036, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day16/input1.txt");

        var day = new Day16();

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(104516, result);
    }

    @Test
    void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day16/sample1.txt");

        var day = new Day16();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(123, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day16/input1.txt");

        var day = new Day16();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(5466, result);
    }

}
