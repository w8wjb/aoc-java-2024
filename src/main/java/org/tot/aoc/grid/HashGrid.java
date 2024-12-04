package org.tot.aoc.grid;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HashGrid<V> extends HashMap<Point, V> {

    public long minX = 0;
    public long minY = 0;
    public long maxX = 0;
    public long maxY = 0;

    public HashGrid() {

    }

    public HashGrid(Map<Point, V> points) {
        this.putAll(points);
    }

    public V put(Point p, V v) {
        minX = Math.min(p.x, minX);
        maxX = Math.max(p.x, maxX);
        minY = Math.min(p.y, minY);
        maxY = Math.max(p.y, maxY);
        return super.put(p, v);
    }

    public V get(Point p) {
        return super.get(p);
    }


    public void print() {
        for (long y = minY; y <= maxY; y++) {
            for (long x = minX; x <= maxX; x++) {
                Point p = new Point(x, y);
                V value = get(p);
                if (value == null) {
                    System.out.print(".");
                } else {
                    System.out.print(value);
                }
            }
            System.out.println();
        }

    }

}
