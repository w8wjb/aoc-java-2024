package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day3Test {

    @Test
    void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day3/sample1.txt");

        var day = new Day3();

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(161, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day3/input1.txt");

        var day = new Day3();

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(178538786, result);
    }

    @Test
    void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day3/sample2.txt");

        var day = new Day3();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(48, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day3/input1.txt");

        var day = new Day3();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(102467299, result);
    }

}
