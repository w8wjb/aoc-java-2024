package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day7Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day7/sample1.txt");

        var day = new Day7();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(3749, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day7/input1.txt");

        var day = new Day7();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(932137732557L, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day7/sample1.txt");

        var day = new Day7();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(11387, result);
    }

    @Test
    public void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day7/input1.txt");

        var day = new Day7();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(661823605105500L, result);
    }

}
