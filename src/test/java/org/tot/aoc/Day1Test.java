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

        Assert.assertEquals(24000, result);
    }

    @Test
    public void testSolution1() {

    }

    @Test
    public void testSample2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day1/sample1.txt");

        Day1 day = new Day1();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(45000, result);
    }

    @Test
    public void testSolution2() {

    }

}
