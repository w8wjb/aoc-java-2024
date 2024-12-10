package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day10Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day10/sample1.txt");

        var day = new Day10();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(36, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day10/input1.txt");

        var day = new Day10();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(794, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day10/sample1.txt");

        var day = new Day10();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(81, result);
    }

    @Test
    public void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day10/input1.txt");

        var day = new Day10();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(1706, result);
    }

}
