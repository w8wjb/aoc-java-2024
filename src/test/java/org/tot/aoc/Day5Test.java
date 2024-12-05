package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day5Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day5/sample1.txt");

        var day = new Day5();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(143, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day5/input1.txt");

        var day = new Day5();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(4281, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day5/sample1.txt");

        var day = new Day5();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(123, result);
    }

    @Test
    public void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day5/input1.txt");

        var day = new Day5();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(5466, result);
    }

}
