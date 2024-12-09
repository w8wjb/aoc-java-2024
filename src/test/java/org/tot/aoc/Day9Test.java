package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day9Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day9/sample1.txt");

        var day = new Day9();

        long result = day.solvePuzzle1(lines.get(0));

        Assert.assertEquals(1928, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day9/input1.txt");

        var day = new Day9();

        long result = day.solvePuzzle1(lines.get(0));

        Assert.assertEquals(6200294120911L, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day9/sample1.txt");

        var day = new Day9();

        long result = day.solvePuzzle2(lines.get(0));

        Assert.assertEquals(2858, result);
    }

    @Test
    public void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day9/input1.txt");

        var day = new Day9();

        long result = day.solvePuzzle2(lines.get(0));

        Assert.assertEquals(1339, result);
    }

}
