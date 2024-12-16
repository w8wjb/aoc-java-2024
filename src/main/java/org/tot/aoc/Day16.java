package org.tot.aoc;

import org.apache.commons.lang3.ArrayUtils;
import org.tot.aoc.grid.Vector;
import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Point;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 {

    HashGrid<Character> maze = null;
    Point mazeStart = null;
    Point mazeEnd = null;


    static Vector[] movements = new Vector[]{
            Vector.N,
            Vector.E,
            Vector.S,
            Vector.W
    };

    public int wrap(int value, int max) {
        return ((value % max) + max) % max;
    }

    Vector rotate(Vector source, int quarterTurns) {
        int offset = ArrayUtils.indexOf(movements, source);
        return movements[wrap(offset + quarterTurns, movements.length)];
    }

    private void parseInput(List<String> input) {
        maze = HashGrid.fromList(input);

        for (var entry : maze.entrySet()) {
            if (entry.getValue() == 'S') {
                mazeStart = entry.getKey();
            } else if (entry.getValue() == 'E') {
                mazeEnd = entry.getKey();
            }
        }

        assert mazeStart != null;
        assert mazeEnd != null;
    }

    public int solvePuzzle1(List<String> input) {

        parseInput(input);

        Step lastStep = findLeastCostPath();
        return lastStep.actualCost;
    }

    Step findLeastCostPath() {

        Set<Point> visited = new HashSet<>();

        PriorityQueue<Step> queue = new PriorityQueue<>();
        queue.add(new Step(mazeStart, Vector.E));

        Step step;
        while ((step = queue.poll()) != null) {

            char tileType = maze.get(step.point);

            if (visited.contains(step.point)) {
                continue;
            }
            visited.add(step.point);

            if (tileType == 'E') {
                return step;
            }

            List<Step> neighbors = findNeighbors(step, tileType);
            if (neighbors.isEmpty()) {
                continue;
            }

            for (var neighbor : neighbors) {
                if (visited.contains(neighbor.point)) {
                    continue;
                }

                queue.add(neighbor);
            }


        }

        return null;
    }

    List<Step> findNeighbors(Step adjacentTo, char tileType) {


        int rotateEnd = tileType == 'S' ? 2 : 1;

        List<Step> neighbors = new ArrayList<>();

        for (int i = -1; i <= rotateEnd; i++) {
            Vector heading = (i == 0) ? adjacentTo.heading : rotate(adjacentTo.heading, i);

            char neighborType = maze.get(adjacentTo.point.add(heading));

            if (neighborType != '#') {
                Step neighbor = new Step(adjacentTo, heading, mazeEnd);
                neighbors.add(neighbor);
            }

        }

//        System.out.print(adjacentTo.heading.asArrow());
//        System.out.print(": ");
//        for (var neighbor: neighbors) {
//            System.out.print(neighbor.heading.asArrow());
//        }
//        System.out.println();

        return neighbors;
    }


    public int solvePuzzle2(List<String> input) {

        parseInput(input);

        return findLeastCostTiles();
    }

    int findLeastCostTiles() {


        Map<Step, Integer> visited = new HashMap<>();
        Set<Point> bestPathTiles = new HashSet<>();
        int leastTileCost = Integer.MAX_VALUE;
//        Set<Point> visited = new HashSet<>();

        PriorityQueue<Step> queue = new PriorityQueue<>();
        queue.add(new Step(mazeStart, Vector.E));

        Step step;
        while ((step = queue.poll()) != null) {

            System.out.println();
            printPath(step);

            char tileType = maze.get(step.point);

            if (step.estimatedCost > leastTileCost) {
                continue;
            }

            int prevCost = visited.getOrDefault(step, Integer.MAX_VALUE);
            if (step.actualCost >= prevCost) {
                if (step.actualCost == leastTileCost) {
                    bestPathTiles.addAll(step.getPathPoints());
                }
                continue;
            }
            visited.put(step, step.actualCost);

            if (tileType == 'E') {
                leastTileCost = step.actualCost;
                bestPathTiles.addAll(step.getPathPoints());
            }

            List<Step> neighbors = findNeighbors(step, tileType);
            queue.addAll(neighbors);

//            for (var neighbor : neighbors) {
//                if (visited.contains(neighbor.point)) {
//                    continue;
//                }
//
//                queue.add(neighbor);
//            }


        }

        return bestPathTiles.size();
    }

    private void printPath(Step end) {
        var mazeCopy = new HashGrid<>(maze);
        for (var step: end.path) {
            mazeCopy.put(step.point, step.heading.asArrow());
        }
        mazeCopy.print();
    }

    private static class Step implements Comparable<Step> {
        Point point;
        Vector heading;
        int actualCost;
        long estimatedCost;
        List<Step> path = new ArrayList<>();

        public Step(Point point, Vector heading) {
            this.point = point;
            this.heading = heading;
            this.actualCost = 0;
            this.estimatedCost = 0;
            this.path.add(this);
        }

        public Step(Step from, Vector heading, Point mazeEnd) {
            this.point = from.point.add(heading);
            this.heading = heading;

            if (from.heading == heading) {
                this.actualCost = from.actualCost + 1;
            } else {
                this.actualCost = from.actualCost + 1001;
            }

            this.estimatedCost = this.actualCost
                    + (rotationDistance(this.point, mazeEnd) * 1000)
                    + this.point.chessboardStepDistance(mazeEnd);

            this.path.addAll(from.path);
            this.path.add(this);
        }

        List<Point> getPathPoints() {
            return this.path
                    .stream()
                    .map(s -> s.point)
                    .collect(Collectors.toList());
        }

        long rotationDistance(Vector from, Vector to) {
            int fromOffset = ArrayUtils.indexOf(movements, from);
            int toOffset = ArrayUtils.indexOf(movements, to);
            return Math.abs(toOffset - fromOffset);
        }


        @Override
        public int compareTo(Step that) {
            return (int) (this.estimatedCost - that.estimatedCost);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Step)) return false;
            Step that = (Step) o;
            return this.point.equals(that.point)
                    && this.heading.equals(that.heading);
        }

        @Override
        public int hashCode() {
            return Objects.hash(point, heading);
        }

        @Override
        public String toString() {
            return String.format("%s %s", point, heading.asArrow());
        }
    }
}
