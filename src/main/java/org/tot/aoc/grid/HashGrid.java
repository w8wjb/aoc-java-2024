package org.tot.aoc.grid;

import java.util.*;

public class HashGrid<V> extends HashMap<Point, V>  implements Iterable<Point> {

    public long minX = 0;
    public long minY = 0;
    public long maxX = 0;
    public long maxY = 0;
    public V empty = null;

    public HashGrid() {

    }

    public HashGrid(HashGrid<V> copy) {
        super(copy);
        this.minX = copy.minX;
        this.minY = copy.minY;
        this.maxX = copy.maxX;
        this.maxY = copy.maxY;
        this.empty = copy.empty;
    }

    public static HashGrid<Character> fromList(List<String> rows) {
        return fromList(rows, '.');
    }

    public static HashGrid<Character> fromList(List<String> rows, char empty) {

        Map<Point, Character> points = new HashMap<>();

        for (int y = 0; y < rows.size(); y++) {
            char[] row = rows.get(y).toCharArray();
            for (int x = 0; x < row.length; x++) {
                if (row[x] == empty) {
                    continue;
                }
                points.put(new Point(x,y), row[x]);
            }
        }
        HashGrid<Character> grid = new HashGrid<>(points);
        grid.maxY = rows.size() - 1;
        grid.maxX = rows.get(0).length() - 1;
        grid.empty = empty;
        return grid;
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
        return Objects.requireNonNullElse(super.get(p), empty);
    }

    public boolean isWithinBounds(Point p) {
        return p.x >= minX
                && p.x <= maxX
                && p.y >= minY
                && p.y <= maxY;
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

    @Override
    public Iterator<Point> iterator() {
        return new HashGridIterator();
    }

    private class HashGridIterator implements Iterator<Point> {

        Point curr = new Point(-1,0);

        private Point peekNext() {
            Point next = curr.add(1, 0);
            if (next.x > maxX) {
                next = new Point(minX, curr.y + 1);
            }
            return next;
        }

        @Override
        public boolean hasNext() {
            return isWithinBounds(peekNext());
        }

        @Override
        public Point next() {
            this.curr = peekNext();
            return curr;
        }
    }
}
