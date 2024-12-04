package org.tot.aoc.grid;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Wrapper class to make a list of Strings appear as a grid
 */
public class StringGrid implements Iterable<Point> {


    protected final List<String> rows;
    public final long minX = 0;
    public final long minY = 0;
    public final long maxX;
    public final long maxY;

    public StringGrid(List<String> rows) {
        this.rows = rows;
        this.maxY = rows.size() - 1;
        this.maxX = rows.get(0).length() - 1;
    }

    /**
     * This is 'safe' coordinate access.
     * If the target point lies outside the bounds of the 2D array, it will return the 'empty' character, '.'
     *
     * @param p target point
     * @return character at the grid point
     */
    public char get(Point p) {
        // Bounds checking
        if (p.x < minX || p.y < minY || p.x > maxX || p.y > maxY) {
            return '.';
        }
        return rows.get((int) p.y).charAt((int) p.x);
    }

    public String row(int index) {
        if (index < minY || index > maxY) {
            return null;
        }
        return rows.get(index);
    }

    public boolean isWithinBounds(Point p) {
        return p.x >= minX
                && p.x <= maxX
                && p.y >= minY
                && p.y <= maxY;
    }

    @Override
    public Iterator<Point> iterator() {
        return new StringGridIterator();
    }

    @Override
    public void forEach(Consumer<? super Point> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Point> spliterator() {
        return Iterable.super.spliterator();
    }

    private class StringGridIterator implements Iterator<Point> {

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
