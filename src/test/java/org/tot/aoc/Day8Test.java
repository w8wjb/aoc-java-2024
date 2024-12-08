package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day8Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day8/sample1.txt");

        var day = new Day8();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(14, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day8/input1.txt");

        var day = new Day8();

        long result = day.solvePuzzle1(lines);

        Assert.assertEquals(379, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day8/sample1.txt");

        var day = new Day8();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(34, result);
    }

    @Test
    public void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day8/input1.txt");

        var day = new Day8();

        long result = day.solvePuzzle2(lines);

        Assert.assertEquals(1339, result);
    }

}
