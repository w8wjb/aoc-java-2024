package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day1Test {

    @Test
    void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day1/sample1.txt");

        Day1 day = new Day1();

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(11, result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day1/input1.txt");

        Day1 day = new Day1();

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(2375403, result);
    }

    @Test
    void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day1/sample1.txt");

        Day1 day = new Day1();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(31, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day1/input1.txt");

        Day1 day = new Day1();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(23082277, result);
    }

}
