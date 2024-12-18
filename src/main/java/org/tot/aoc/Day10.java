package org.tot.aoc;

import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Vector;
import org.tot.aoc.grid.Point;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day10 {

    HashGrid<Integer> grid;


    public long solvePuzzle1(List<String> input) {

        grid = HashGrid.fromList(input, c -> c - '0', -1);

        Set<Point> trailheads = new HashSet<>();
        for (var p : grid) {
            if (grid.get(p) == 0) {
                trailheads.add(p);
            }
        }

        int total = 0;
        for (Point trailhead : trailheads) {
            int score = walkTrailBFS(trailhead);
            total += score;
        }


        return total;
    }

    int walkTrailBFS(Point trailhead) {

        Queue<Point> queue = new LinkedList<>();
        queue.add(trailhead);

        Set<Point> visited = new HashSet<>();

        int score = 0;
        Point p;
        while ((p = queue.poll()) != null) {

            int height = grid.get(p);

            if (visited.contains(p)) {
                continue;
            }

            visited.add(p);

            if (height == 9) {
                score++;
                continue;
            }

            int uphill = height + 1;

            for (var dir : Vector.CARDINAL) {
                Point adjacent = p.add(dir);
                if (grid.get(adjacent) == uphill) {
                    queue.add(adjacent);
                }
            }

        }

        return score;
    }

    public long solvePuzzle2(List<String> input) {

        grid = HashGrid.fromList(input, c -> c - '0', -1);

        Set<Point> trailheads = new HashSet<>();
        for (var p : grid) {
            if (grid.get(p) == 0) {
                trailheads.add(p);
            }
        }

        int total = 0;
        for (Point trailhead : trailheads) {
            AtomicInteger score = new AtomicInteger();
            walkTrailDFS(trailhead, score);
            total += score.get();
        }


        return total;
    }

    void walkTrailDFS(Point from, AtomicInteger score) {

        int height = grid.get(from);

        if (height == 9) {
            score.incrementAndGet();
            return;
        }

        int uphill = height + 1;

        for (Vector dir : Vector.CARDINAL) {
            Point adjacent = from.add(dir);
            if (grid.get(adjacent) == uphill) {
                walkTrailDFS(adjacent, score);
            }
        }

    }


}
