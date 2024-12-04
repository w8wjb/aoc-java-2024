package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day4Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day4/sample1.txt");

        var day = new Day4();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(18, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day4/input1.txt");

        var day = new Day4();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(178538786, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day4/sample1.txt");

        var day = new Day4();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(9, result);
    }

    @Test
    public void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day4/input1.txt");

        var day = new Day4();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(1888, result);
    }

}
