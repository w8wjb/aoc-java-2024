package org.tot.aoc;

import org.apache.commons.lang3.ArrayUtils;
import org.tot.aoc.grid.Vector;
import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Point;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 {

    HashGrid<Character> grid = null;

    Vector[] movements = new Vector[]{
            Vector.N,
            Vector.E,
            Vector.S,
            Vector.W
    };


    public int solvePuzzle1(List<String> input) {

        grid = HashGrid.fromList(input);

        Point start = null;
        for (var entry : grid.entrySet()) {
            if (entry.getValue() == 'S') {
                start = entry.getKey();
            }
        }
        assert start != null;

        List<Step> path = findPath(start);

        for (var step : path) {
            grid.put(step.point, step.heading.asArrow());
        }
        grid.print();


        return path.get(path.size() - 1).accumCost;
    }

    List<Step> findPath(Point start) {

        Set<Point> visited = new HashSet<>();

        PriorityQueue<Step> queue = new PriorityQueue<>();
        queue.add(new Step(start, Vector.E));


        Step step;
        while ((step = queue.poll()) != null) {

            if (visited.contains(step.point)) {
                continue;
            }
            visited.add(step.point);

            if (grid.get(step.point) == 'E') {
                return step.path;
            }

            List<Step> neighbors = findNeighbors(step);
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

        return Collections.emptyList();
    }

    List<Step> findNeighbors(Step adjacentTo) {

        int offset = ArrayUtils.indexOf(movements, adjacentTo.heading);
        Vector cwHeading = movements[wrap(offset + 1, movements.length)];
        Vector ccwHeading = movements[wrap(offset - 1, movements.length)];

        List<Vector> headings = List.of(adjacentTo.heading, cwHeading, ccwHeading);

        return headings
                .stream()
                .filter(h -> grid.get(adjacentTo.point.add(h)) != '#')
                .map(h -> new Step(adjacentTo, h))
                .collect(Collectors.toList());

    }

    public int wrap(int value, int max) {
        return ((value % max) + max) % max;
    }

    private static class Step implements Comparable<Step> {
        Point point;
        Vector heading;
        int accumCost;
        List<Step> path;

        public Step(Point point, Vector heading) {
            this.point = point;
            this.heading = heading;
            this.accumCost = 0;
            this.path = new ArrayList<>();
            this.path.add(this);
        }

        public Step(Step from, Vector heading) {
            this.point = from.point.add(heading);
            this.heading = heading;

            if (from.heading == heading) {
                this.accumCost = from.accumCost + 1;
            } else {
                this.accumCost = from.accumCost + 1001;
            }

            this.path = new ArrayList<>(from.path);
            this.path.add(this);

        }

        @Override
        public int compareTo(Step that) {
            return this.accumCost - that.accumCost;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Step)) return false;
            Step step = (Step) o;
            return Objects.equals(point, step.point);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(point);
        }

    }

    public int solvePuzzle2(List<String> input) {

        int total = 0;


        return total;
    }

}
