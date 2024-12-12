package org.tot.aoc.grid;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringGridTest {

    @Test
    void testIterator() {

        StringGrid grid = new StringGrid(List.of(
                "ABCDEF",
                "GHIJKL",
                "MNOPQR",
                "STUVWY"
        ));


        StringBuilder collector = new StringBuilder();
        for (Point p : grid) {
            collector.append(grid.get(p));
        }
        Assertions.assertEquals("ABCDEFGHIJKLMNOPQRSTUVWY", collector.toString());

    }


}
