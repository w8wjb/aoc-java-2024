package org.tot.aoc.grid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class VectorTest {

    @Test
    void testAsArrow() {

        assertEquals("^", Vector.N.asArrow());
        assertEquals("↗", Vector.NE.asArrow());
        assertEquals(">", Vector.E.asArrow());
        assertEquals("↘", Vector.SE.asArrow());
        assertEquals("v", Vector.S.asArrow());
        assertEquals("↙", Vector.SW.asArrow());
        assertEquals("<", Vector.W.asArrow());
        assertEquals("↖", Vector.NW.asArrow());
        assertEquals("0", new Vector(0, 0).asArrow());

    }

}
