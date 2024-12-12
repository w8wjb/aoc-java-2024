package org.tot.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tot.helper.ResourceHelper;

import java.util.List;
import java.util.stream.Stream;

class Day12Test {


    private static Stream<Arguments> puzzle1Samples() {
        return Stream.of(
                Arguments.of("Day12/sample1_1.txt", 140),
                Arguments.of("Day12/sample1_2.txt", 772),
                Arguments.of("Day12/sample1_3.txt", 1930)
        );
    }

    private static Stream<Arguments> puzzle2Samples() {
        return Stream.of(
                Arguments.of("Day12/sample2_1.txt", 80),
                Arguments.of("Day12/sample2_2.txt", 436),
                Arguments.of("Day12/sample2_3.txt", 236),
                Arguments.of("Day12/sample2_4.txt", 368),
                Arguments.of("Day12/sample2_5.txt", 1206)
        );
    }

    @ParameterizedTest
    @MethodSource("puzzle1Samples")
    void testSample1(String fileName, int expected) {

        List<String> lines = ResourceHelper.loadLinesFromFile(fileName);

        Day12 day = new Day12();

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(expected, result);
    }


    @Test
    void testSolution1() {
        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/input1.txt");

        Day12 day = new Day12();

        int result = day.solvePuzzle1(lines);

        Assertions.assertEquals(1375476, result);
    }

    @ParameterizedTest
    @MethodSource("puzzle2Samples")
    void testSample2(String fileName, int expected) {

        List<String> lines = ResourceHelper.loadLinesFromFile(fileName);

        Day12 day = new Day12();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testSolution2() {

        List<String> lines = ResourceHelper.loadLinesFromFile("Day12/input1.txt");

        Day12 day = new Day12();

        int result = day.solvePuzzle2(lines);

        Assertions.assertEquals(821372, result);
    }

}
