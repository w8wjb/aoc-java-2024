package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day2Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day2/sample1.txt");

        var day = new Day2();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(2, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day2/input1.txt");

        var day = new Day2();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(516, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day2/sample1.txt");

        var day = new Day2();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(4, result);
    }

    @Test
    public void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day2/input1.txt");

        var day = new Day2();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(561, result);
    }

}
