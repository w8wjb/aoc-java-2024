package org.tot.aoc;

import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class Day18 {

    HashGrid<Character> maze = new HashGrid<>('.');
    Point mazeStart = new Point(0, 0);
    Point mazeEnd;

    List<Point> parseInput(List<String> input) {
        return input.stream().map(line -> {
                    String[] pair = line.split(",");
                    return new Point(
                            Long.parseLong(pair[0]),
                            Long.parseLong(pair[1])
                    );
                })
                .collect(Collectors.toList());
    }

    public int solvePuzzle1(List<String> input, long maxX, long maxY, int fallen) {

        List<Point> fallingBytes = parseInput(input);

        Set<Point> corrupt = new HashSet<>(fallingBytes.subList(0, fallen));

        maze.maxX = maxX;
        maze.maxY = maxY;
        mazeEnd = new Point(maxX, maxY);


        Set<Point> visited = new HashSet<>();

        PriorityQueue<Step> queue = new PriorityQueue<>();
        queue.add(new Step(mazeStart));

        Step step;
        while ((step = queue.poll()) != null) {
            if (visited.contains(step.point)) {
                continue;
            }
            visited.add(step.point);

            if (step.point.equals(mazeEnd)) {
                return step.path.size() - 1;
            }

            List<Step> neighbors = findNeighbors(step, corrupt);
            for (var neighbor : neighbors) {
                if (visited.contains(neighbor.point)) {
                    continue;
                }
                queue.add(neighbor);
            }

        }

        return -1;
    }


    public String solvePuzzle2(List<String> input, long maxX, long maxY, int fallen) {

        List<Point> fallingBytes = parseInput(input);

        for (int i = fallen; i < fallingBytes.size(); i++) {
            int steps = solvePuzzle1(input, maxX, maxY, i);
            if (steps == -1) {
                return fallingBytes.get(i - 1).toString();
            }
        }


        return "";
    }


    private void printPath(Step end, Set<Point> corrupt) {
        System.out.println();
        var mazeCopy = new HashGrid<>(maze);

        for (var p : corrupt) {
            mazeCopy.put(p, '#');
        }

        for (var step : end.path) {
            mazeCopy.put(step.point, 'O');
        }
        mazeCopy.print();
        System.out.println();
    }

    List<Step> findNeighbors(Step adjacentTo, Set<Point> corrupt) {

        List<Step> neighbors = new ArrayList<>();

        for (var dir : Vector.CARDINAL) {
            Point neighborPoint = adjacentTo.point.add(dir);
            if (!maze.isWithinBounds(neighborPoint)) {
                continue;
            }

            if (corrupt.contains(neighborPoint)) {
                continue;
            }

            Step neighbor = new Step(adjacentTo, dir, mazeEnd);
            neighbors.add(neighbor);
        }

        return neighbors;
    }


    static class Step implements Comparable<Step> {

        Point point;
        int actualCost;
        long estimatedCost;
        List<Step> path = new ArrayList<>();

        public Step(Point point) {
            this.point = point;
            this.actualCost = 0;
            this.estimatedCost = 0;
            this.path.add(this);
        }

        public Step(Step from, Vector direction, Point mazeEnd) {
            this.point = from.point.add(direction);

            this.path.addAll(from.path);
            this.path.add(this);
            this.actualCost = this.path.size();
            this.estimatedCost = this.actualCost
                    + this.point.chessboardStepDistance(mazeEnd);

        }

        @Override
        public int compareTo(Step that) {
            return (int) (this.estimatedCost - that.estimatedCost);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Step)) return false;
            Step step = (Step) o;
            return Objects.equals(point, step.point);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(point);
        }
    }

}
