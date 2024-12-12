package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day11Test {

    @Test
    void testSample1() {

        var day = new Day11();

        List<Long> input = List.of(125L, 17L);

        long result = day.solvePuzzle2(input, 6);
        Assertions.assertEquals(22, result);

        result = day.solvePuzzle2(input, 25);
        Assertions.assertEquals(55312, result);
    }

    @Test
    void testSolution1() {

        var day = new Day11();

        List<Long> input = List.of(64554L, 35L, 906L, 6L, 6960985L, 5755L, 975820L, 0L);

        long result = day.solvePuzzle2(input, 25);

        Assertions.assertEquals(175006, result);
    }

    @Test
    void testSolution2() {

        var day = new Day11();

        List<Long> input = List.of(64554L, 35L, 906L, 6L, 6960985L, 5755L, 975820L, 0L);

        long result = day.solvePuzzle2(input, 75);

        Assertions.assertEquals(207961583799296L, result);
    }

}
