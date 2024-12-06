package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day6Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day6/sample1.txt");

        var day = new Day6();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(41, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day6/input1.txt");

        var day = new Day6();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(4903, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day6/sample1.txt");

        var day = new Day6();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(6, result);
    }

    @Test
    public void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day6/input1.txt");

        var day = new Day6();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(1911, result);
    }

}
