package org.tot.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.tot.helper.ResourceHelper;

import java.util.List;

public class Day11Test {

    @Test
    public void testSample1() {

        var day = new Day11();

        List<Long> input = List.of(125L, 17L);

        long result = day.solvePuzzle2(input, 6);
        Assert.assertEquals(22, result);

        result = day.solvePuzzle2(input, 25);
        Assert.assertEquals(55312, result);
    }

    @Test
    public void testSolution1() {

        var day = new Day11();

        List<Long> input = List.of(64554L, 35L, 906L, 6L, 6960985L, 5755L, 975820L, 0L);

        long result = day.solvePuzzle2(input, 25);

        Assert.assertEquals(175006, result);
    }

    @Test
    public void testSolution2() {

        var day = new Day11();

        List<Long> input = List.of(64554L, 35L, 906L, 6L, 6960985L, 5755L, 975820L, 0L);

        long result = day.solvePuzzle2(input, 75);

        Assert.assertEquals(207961583799296L, result);
    }

}
