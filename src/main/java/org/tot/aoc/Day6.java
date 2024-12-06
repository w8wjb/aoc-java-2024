package org.tot.aoc;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.StringGrid;
import org.tot.aoc.grid.Vector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 {


    public static final List<Vector> legalMoves = List.of(
            Vector.N, Vector.E, Vector.S, Vector.W
    );

    private Vector turnRight(Vector from) {
        int next = (legalMoves.indexOf(from) + 1) % legalMoves.size();
        return legalMoves.get(next);
    }

    public int solvePuzzle1(List<String> input) {

        var grid = new StringGrid(input);
        var visited = new HashSet<Point>();

        Point guardPosition = null;
        Vector guardDirection = Vector.N;

        for (Point p : grid) {
            if (grid.get(p) == '^') {
                guardPosition = p;
                break;
            }
        }
        assert guardPosition != null;

        while (grid.isWithinBounds(guardPosition)) {
            visited.add(guardPosition);

            var nextPosition = guardPosition.add(guardDirection);
            while (grid.get(nextPosition) == '#') {
                guardDirection = turnRight(guardDirection);
                nextPosition = guardPosition.add(guardDirection);
            }

            guardPosition = nextPosition;
        }


        return visited.size();
    }

    public int solvePuzzle2(List<String> input) {

        var grid = HashGrid.fromList(input);

        GuardPose startPose = findStart(grid);

        Set<Point> visitedPoints = walkRoute(grid, startPose);

        int count = 0;

        for (Point potentialObstruction : visitedPoints) {
            var alternateGrid = new HashGrid<>(grid);
            alternateGrid.put(potentialObstruction, '#');

            try {
                walkRoute(alternateGrid, startPose);
            } catch (RouteLoopException e) {
                count++;
            }
        }


        return count;
    }

    private GuardPose findStart(HashGrid<Character> grid) {
        for (Point p : grid) {
            if (grid.get(p) == '^') {
                return new GuardPose(p, Vector.N);
            }
        }
        throw new IllegalStateException("Staring point not found");
    }

    private Set<Point> walkRoute(HashGrid<Character> grid, GuardPose start) {

        var visitedPoses = new HashSet<GuardPose>();
        var visitedPoints = new HashSet<Point>();

        GuardPose guardPose = start;

        while (grid.isWithinBounds(guardPose.position)) {
            if (!visitedPoses.add(guardPose)) {
                throw new RouteLoopException("We've been here before");
            }
            visitedPoints.add(guardPose.position);

            var nextPose = guardPose;
            for (int turn = 0; turn <= 4; turn++) {
                nextPose = guardPose.turnRight(turn).peekStep();
                if (grid.get(nextPose.position) != '#') {
                    break;
                }
                if (turn == 4) {
                    throw new IllegalStateException("Turned more than 3 times");
                }
            }
            guardPose = nextPose;
        }
        return visitedPoints;
    }

    static class GuardPose {

        Point position;
        Vector facing;

        public GuardPose(Point position, Vector facing) {
            this.position = position;
            this.facing = facing;
        }

        public GuardPose peekStep() {
            return new GuardPose(this.position.add(this.facing), this.facing);
        }

        private GuardPose turnRight(int count) {
            int next = (legalMoves.indexOf(this.facing) + count) % legalMoves.size();
            return new GuardPose(this.position, legalMoves.get(next));
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder()
                    .append(position)
                    .append(facing)
                    .hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj instanceof GuardPose) {
                GuardPose other = (GuardPose) obj;
                return this.position.equals(other.position)
                        && this.facing.equals(other.facing);
            }
            return super.equals(obj);
        }

        @Override
        public String toString() {
            var buffer = new StringBuilder(position.toString());
            buffer.append(" ");
            if (this.facing.equals(Vector.S)) {
                buffer.append("V");
            } else if (this.facing.equals(Vector.E)) {
                buffer.append(">");
            } else if (this.facing.equals(Vector.W)) {
                buffer.append("<");
            } else {
                buffer.append("^");
            }
            return buffer.toString();
        }
    }

    private static class RouteLoopException extends RuntimeException {

        public RouteLoopException(String message) {
            super(message);
        }
    }

}
