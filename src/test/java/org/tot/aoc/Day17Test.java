package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

class Day17Test {

    @Test
    void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day17/sample1.txt");

        var day = new Day17();

        String result = day.solvePuzzle1(lines);

        Assertions.assertEquals("4,6,3,5,6,3,5,2,1,0", result);
    }

    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day17/input1.txt");

        var day = new Day17();

        String result = day.solvePuzzle1(lines);

        Assertions.assertEquals("6,4,6,0,4,5,7,2,7", result);
    }


    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day17/input1.txt");

        var day = new Day17();

        long result = day.solvePuzzle2(lines);

        Assertions.assertEquals(164541160582845L, result);
    }

}
