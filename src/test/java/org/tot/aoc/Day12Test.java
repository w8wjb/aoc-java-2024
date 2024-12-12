package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day12Test {

    @Test
    public void testSample1_1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/sample1_1.txt");

        Day12 day = new Day12();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(140, result);
    }

    @Test
    public void testSample1_2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/sample1_2.txt");

        Day12 day = new Day12();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(772, result);
    }

    @Test
    public void testSample1_3() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/sample1_3.txt");

        Day12 day = new Day12();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(1930, result);
    }

    @Test
    public void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/input1.txt");

        Day12 day = new Day12();

        int result = day.solvePuzzle1(lines);

        Assert.assertEquals(1375476, result);
    }

    @Test
    public void testSample2_1() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/sample2_1.txt");

        Day12 day = new Day12();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(80, result);
    }

    @Test
    public void testSample2_2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/sample2_2.txt");

        Day12 day = new Day12();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(436, result);
    }

    @Test
    public void testSample2_3() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/sample2_3.txt");

        Day12 day = new Day12();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(236, result);
    }

    @Test
    public void testSample2_4() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/sample2_4.txt");

        Day12 day = new Day12();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(368, result);
    }

    @Test
    public void testSample2_5() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/sample2_5.txt");

        Day12 day = new Day12();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(1206, result);
    }

    @Test
    public void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/input1.txt");

        Day12 day = new Day12();

        int result = day.solvePuzzle2(lines);

        Assert.assertEquals(821372, result);
    }

}
