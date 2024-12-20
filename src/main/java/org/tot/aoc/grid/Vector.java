package org.tot.aoc.grid;

public class Vector {


    public static final Vector NW = new Vector(-1, -1);
    public static final Vector N = new Vector(0, -1);
    public static final Vector NE = new Vector(1, -1);
    public static final Vector W = new Vector(-1, 0);
    public static final Vector E = new Vector(1, 0);
    public static final Vector SW = new Vector(-1, 1);
    public static final Vector S = new Vector(0, 1);
    public static final Vector SE = new Vector(1, 1);

    // https://en.wikipedia.org/wiki/Cardinal_direction
    public static final Vector[] CARDINAL = {N, E, S, W};
    public static final Vector[] ORDINAL = {N, NE, E, SE, S, SW, W, NW};

    public final long x;
    public final long y;

    public Vector(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector) {
            Vector that = (Vector) obj;
            return this.x == that.x && this.y == that.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) x;
        hash = 31 * hash + (int) y;
        return hash;
    }

    public Vector inverted() {
        return new Vector(-this.x, -this.y);
    }

    public Vector times(long scalar) {
        return new Vector(this.x * scalar, this.y * scalar);
    }


    @Override
    public String toString() {
        return String.format("%d,%d", x, y);
    }

    public char asArrow() {
        if (x > 0) {
            if (y > 0) {
                return '↘';
            } else if (y < 0) {
                return '↗';
            } else {
                return '>';
            }
        } else if (x < 0) {
            if (y > 0) {
                return '↙';
            } else if (y < 0) {
                return '↖';
            } else {
                return '<';
            }
        } else {
            if (y > 0) {
                return 'v';
            } else if (y < 0) {
                return '^';
            } else {
                return '0';
            }
        }
    }

}
