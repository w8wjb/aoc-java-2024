package org.tot.aoc.grid;

/**
 * I made my own Point class, because I didn't really like the built-in options
 */
public class Point extends Vector implements Comparable<Point> {

    public Point(long x, long y) {
        super(x, y);
    }


    public Point add(Vector other) {
        return add(other.x, other.y);
    }

    public Point add(int dx, int dy) {
        return new Point(this.x + dx, this.y + dy);
    }
    public Point add(long dx, long dy) {
        return new Point(this.x + dx, this.y + dy);
    }


    public Point subtract(Vector other) {
        return subtract(other.x, other.y);
    }

    public Point subtract(int dx, int dy) {
        return new Point(this.x - dx, this.y - dy);
    }

    public Point subtract(long dx, long dy) {
        return new Point(this.x - dx, this.y - dy);
    }

    @Override
    public int compareTo(Point that) {
        if (this.x == that.x) {
            return (int)(this.y - that.y);
        }
        return (int)(this.x - that.x);
    }

    public long chessboardStepDistance(Point to) {
        long dx = Math.abs(to.x - this.x);
        long dy = Math.abs(to.y - this.y);
        return  dx + dy;
    }

}
