package org.tot.aoc;

import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Vector;
import org.tot.aoc.grid.Point;

import java.util.*;
import java.util.regex.Pattern;

public class Day14 {


    List<Robot> parseInput(List<String> input) {

        var pattern = Pattern.compile("p=(-?\\d+),(-?\\d+)\\s+v=(-?\\d+),(-?\\d+)");

        List<Robot> robots = new ArrayList<>();
        for (String line : input) {

            var matcher = pattern.matcher(line);
            if (matcher.find()) {
                Robot robot = new Robot(
                        Long.parseLong(matcher.group(1)),
                        Long.parseLong(matcher.group(2)),
                        Long.parseLong(matcher.group(3)),
                        Long.parseLong(matcher.group(4))
                );
                robots.add(robot);
            }

        }
        return robots;

    }

    public int solvePuzzle1(List<String> input, long gridWidth, long gridHeight, long seconds) {

        List<Robot> robots = parseInput(input);

        int[] quadrants = new int[4];

        Point middle = new Point(gridWidth / 2, gridHeight / 2);

        HashGrid<Character> grid = new HashGrid<>('.');
        grid.maxX = gridWidth - 1;
        grid.maxY = gridHeight - 1;

        for (Robot robot : robots) {
            long newX = wrap(robot.start.x + (robot.velocity.x * seconds), gridWidth);
            long newY = wrap(robot.start.y + (robot.velocity.y * seconds), gridHeight);

            grid.put(new Point(newX, newY), '#');

            if (newX < middle.x) {
                if (newY < middle.y) {

                    quadrants[0]++;
                } else if (newY > middle.y) {
                    quadrants[1]++;
                }
            } else if (newX > middle.x) {
                if (newY < middle.y) {
                    quadrants[2]++;
                } else if (newY > middle.y) {
                    quadrants[3]++;
                }
            }


        }

        int safety = 1;
        for (int count : quadrants) {
            safety *= count;
        }
        return safety;
    }

    public long wrap(long value, long max) {
        return ((value % max) + max) % max;
    }

    public int solvePuzzle2(List<String> input, long gridWidth, long gridHeight, long seconds) {


        for (int t = 0; t < seconds; t++) {

            List<Robot> robots = parseInput(input);

            HashGrid<Character> grid = new HashGrid<>('.');
            grid.maxX = gridWidth - 1;
            grid.maxY = gridHeight - 1;

            for (Robot robot : robots) {
                long newX = wrap(robot.start.x + (robot.velocity.x * t), gridWidth);
                long newY = wrap(robot.start.y + (robot.velocity.y * t), gridHeight);

                grid.put(new Point(newX, newY), '#');

            }

            int largestBlob = findLargestBlob(grid);
            if (largestBlob > (robots.size() / 4)) {
                grid.print();
                return t;
            }

        }

        return 0;
    }

    int findLargestBlob(HashGrid<Character> grid) {

        int largetsBlob = 0;
        Set<Point> visited = new HashSet<>();

        for (Point start : grid) {

            if (visited.contains(start)) {
                continue;
            }

            Queue<Point> queue = new LinkedList<>();
            queue.add(start);

            int blobSize = 0;
            Point p;
            while ((p = queue.poll()) != null) {

                if (visited.contains(p)) {
                    continue;
                }
                visited.add(p);
                for (var dir : Vector.ORDINAL) {
                    Point adjacent = p.add(dir);
                    if (grid.get(adjacent) == '#') {
                        queue.add(adjacent);
                    }
                }

                blobSize++;
            }

            largetsBlob = Math.max(largetsBlob, blobSize);
        }


        return largetsBlob;
    }

    static class Robot {
        Point start;
        Vector velocity;

        Robot(long px, long py, long vx, long vy) {
            this.start = new Point(px, py);
            this.velocity = new Vector(vx, vy);
        }

    }

}
