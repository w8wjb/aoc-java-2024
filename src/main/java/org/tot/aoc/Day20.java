package org.tot.aoc;

import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.StringGrid;
import org.tot.aoc.grid.Vector;

import java.util.*;
import java.util.stream.Collectors;


public class Day20 {

    StringGrid maze;
    Point mazeStart = new Point(0, 0);
    Point mazeEnd;

    final int picosecondSavings;
    final int maxCheatLength;

    public Day20(int picosecondSavings, int maxCheatLength) {
        this.picosecondSavings = picosecondSavings;
        this.maxCheatLength = maxCheatLength;
    }

    private void parseInput(List<String> input) {

        maze = new StringGrid(input, '#');

        ListIterator<String> rows = input.listIterator();
        while (rows.hasNext()) {
            int y = rows.nextIndex();
            String line = rows.next();

            int x = line.indexOf('S');
            if (x > -1) {
                mazeStart = new Point(x, y);
            }

            x = line.indexOf('E');
            if (x > -1) {
                mazeEnd = new Point(x, y);
            }
        }
        assert mazeStart != null;
        assert mazeEnd != null;
    }

    public int solvePuzzle1(List<String> input) {

        parseInput(input);

        List<Vector> kernel = generateKernel(maxCheatLength);
        List<Step> path = findShortestPath(kernel);

        Map<Point, Step> stepLookup = path.stream()
                .collect(Collectors.toMap(
                        step -> step.point,
                        step -> step
                ));


        int[] cheatCounts = new int[path.size()];
        for (Step step : path) {

            for (Point cheatPoint : step.cheats) {
                Step cheatTo = stepLookup.get(cheatPoint);

                int distance = (int) step.point.chessboardStepDistance(cheatPoint);
                int savings = cheatTo.actualCost - step.actualCost - distance;

                if (savings >= picosecondSavings) {
                    cheatCounts[savings]++;
                }

            }
        }

        return Arrays.stream(cheatCounts).sum();
    }


    private List<Vector> generateKernel(int maxDistance) {

        List<Vector> kernel = new ArrayList<>();

        Point center = new Point(0, 0);
        for (int x = -maxDistance; x <= maxDistance; x++) {
            for (int y = -maxDistance; y <= maxDistance; y++) {
                Vector v = new Vector(x, y);
                long distance = center.chessboardStepDistance(v);
                if (distance > 0 && distance <= maxDistance) {
                    kernel.add(v);
                }
            }
        }
        return kernel;
    }


    List<Step> findShortestPath(List<Vector> kernel) {

        Set<Point> visited = new HashSet<>();

        Queue<Step> queue = new LinkedList<>();
        queue.add(new Step(mazeStart));

        Step step;
        while ((step = queue.poll()) != null) {
            if (visited.contains(step.point)) {
                continue;
            }
            visited.add(step.point);

            if (step.point.equals(mazeEnd)) {
                return step.path;
            }

            for (var dir : Vector.CARDINAL) {
                Point neighborPoint = step.point.add(dir);
                char neighborType = maze.get(neighborPoint);

                if (neighborType != '#') {
                    Step neighbor = new Step(step, dir);
                    queue.add(neighbor);
                }
            }


            for (Vector v : kernel) {
                Point testPoint = step.point.add(v);
                char testContents = maze.get(testPoint);
                if (testContents == '.' || testContents == 'E') {
                    step.cheats.add(testPoint);
                }
            }


        }

        return Collections.emptyList();
    }

    private static class Step {

        Point point;
        int actualCost;
        List<Step> path = new ArrayList<>();
        List<Point> cheats = new ArrayList<>();

        public Step(Point point) {
            this.point = point;
            this.actualCost = 0;
            this.path.add(this);
        }

        public Step(Step from, Vector direction) {
            this.point = from.point.add(direction);

            this.path.addAll(from.path);
            this.path.add(this);
            this.actualCost = this.path.size() - 1; // Don't include the start
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
