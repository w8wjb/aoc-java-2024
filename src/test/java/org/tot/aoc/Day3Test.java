package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day3Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day3/sample1.txt");

        var day = new Day3();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(161, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day3/input1.txt");

        var day = new Day3();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(178538786, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day3/sample2.txt");

        var day = new Day3();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(48, result);
    }

    @Test
    public void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day3/input1.txt");

        var day = new Day3();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(102467299, result);
    }

}
