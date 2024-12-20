package org.tot.aoc;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.Vector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Day6 {

    public int solvePuzzle1(List<String> input) {

        var grid = HashGrid.fromList(input);
        GuardPose startPose = findStart(grid);
        var visited = walkRoute(grid, startPose);

        return visited.size();
    }

    public int solvePuzzle2(List<String> input) {

        var grid = HashGrid.fromList(input);

        GuardPose startPose = findStart(grid);

        Set<Point> visitedPoints = walkRoute(grid, startPose);

        AtomicInteger count = new AtomicInteger();

        visitedPoints.parallelStream().forEach(potentialObstruction -> {
            var alternateGrid = new HashGrid<>(grid);
            alternateGrid.put(potentialObstruction, '#');

            try {
                walkRoute(alternateGrid, startPose);
            } catch (RouteLoopException e) {
                count.incrementAndGet();
            }
        });

        return count.get();
    }

    private GuardPose findStart(HashGrid<Character> grid) {
        for (Point p : grid) {
            if (grid.get(p) == '^') {
                return new GuardPose(p, Vector.N);
            }
        }
        throw new IllegalStateException("Starting point not found");
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

        private static final List<Vector> legalMoves = List.of(
                Vector.N, Vector.E, Vector.S, Vector.W
        );

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
