package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day1Test {

    @Test
    public void testSample1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day1/sample1.txt");

        Day1 day = new Day1();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(11, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day1/input1.txt");

        Day1 day = new Day1();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(2375403, result);
    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day1/sample1.txt");

        Day1 day = new Day1();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(31, result);
    }

    @Test
    public void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day1/input1.txt");

        Day1 day = new Day1();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(23082277, result);
    }

}
