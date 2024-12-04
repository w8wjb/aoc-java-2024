package org.tot.aoc.grid;

import org.junit.Test;

import java.util.List;
import org.junit.Assert;

public class StringGridTest {

    @Test
    public void testIterator() {

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
        Assert.assertEquals("ABCDEFGHIJKLMNOPQRSTUVWY", collector.toString());

    }


}
