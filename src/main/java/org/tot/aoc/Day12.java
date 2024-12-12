package org.tot.aoc;

import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.StringGrid;
import org.tot.aoc.grid.Vector;

import java.util.*;

public class Day12 {

    static final Vector[] sides = new Vector[] {
            Vector.N,
            Vector.E,
            Vector.S,
            Vector.W
    };

    public int solvePuzzle1(List<String> input) {


        var grid = new StringGrid(input);

        Set<Point> visited = new HashSet<>();
        Queue<Point> queue = new LinkedList<>();

        int total = 0;

        for (Point start : grid) {


            if (visited.contains(start)) {
                continue;
            }

            char plantType = grid.get(start);
            int area = 0;
            int perimeter = 0;

            queue.add(start);

            Point p;
            while ((p = queue.poll()) != null) {
                if (visited.contains(p)) {
                    continue;
                }
                visited.add(p);
                area++;

                for (Vector dir : sides) {
                    Point adjacent = p.add(dir);

                    if (grid.get(adjacent) == plantType) {
                        queue.add(adjacent);
                    } else {
                        perimeter++;
                    }
                }

            }

            total += (area * perimeter);
        }

        return total;
    }


    public int solvePuzzle2(List<String> input) {

        List<Region> regions = new ArrayList<>();

        var grid = new StringGrid(input);

        Set<Point> visited = new HashSet<>();
        Queue<Point> queue = new LinkedList<>();

        for (Point start : grid) {

            if (visited.contains(start)) {
                continue;
            }

            char plantType = grid.get(start);
//            int area = 0;
//            int perimeter = 0;

            Region region = new Region(plantType, start);
            regions.add(region);

            queue.add(start);

            Point p;
            while ((p = queue.poll()) != null) {
                if (visited.contains(p)) {
                    continue;
                }
                visited.add(p);

                Set<Vector> borders = new HashSet<>();
                for (Vector dir : sides) {
                    Point adjacent = p.add(dir);

                    if (grid.get(adjacent) == plantType) {
                        queue.add(adjacent);
                    } else {
                        borders.add(dir);
                    }
                }

                region.addPlant(p, borders);
            }

        }

        int total = 0;
        for (Region region : regions) {
            total += region.getPrice();
        }

        return total;
    }

    static class Region {

        private static final short topMask = 1;
        private static final short bottomMask = 1<<1;
        private static final short leftMask = 1<<2;
        private static final short rightMask = 1<<3;


        final char plantType;
        final Point startPlant;
        final HashGrid<Integer> regionGrid = new HashGrid<>();

        public Region(char plantType, Point startPlant) {
            this.plantType = plantType;
            this.startPlant = startPlant;
            regionGrid.empty = 0;
        }

        void addPlant(Point p, Set<Vector> borders) {

            int border = 0;
            if (borders.contains(Vector.N)) {
                border |= topMask;
            }
            if (borders.contains(Vector.E)) {
                border |= rightMask;
            }
            if (borders.contains(Vector.S)) {
                border |= bottomMask;
            }
            if (borders.contains(Vector.W)) {
                border |= leftMask;
            }
            regionGrid.put(p, border);
        }

        int getPrice() {
            return getArea() * getSideCount();
        }

        int getArea() {
            return regionGrid.size();
        }

        int getSideCount() {

            int sides = 0;

            for (long y = regionGrid.minY; y <= regionGrid.maxY; y++) {

                int topCount = 0;
                int bottomCount = 0;
                for (long x = regionGrid.minX; x <= regionGrid.maxX + 1; x++) {
                    Point p = new Point(x, y);

                    int borders = regionGrid.get(p);

                    if ((borders & topMask) == topMask) {
                        topCount++;
                    } else {
                        if (topCount > 0) {
                            sides++;
                        }
                        topCount = 0;
                    }

                    if ((borders & bottomMask) == bottomMask) {
                        bottomCount++;
                    } else {
                        if (bottomCount > 0) {
                            sides++;
                        }
                        bottomCount = 0;
                    }
                }
            }

            for (long x = regionGrid.minX; x <= regionGrid.maxX; x++) {

                int leftCount = 0;
                int rightCount = 0;
                for (long y = regionGrid.minY; y <= regionGrid.maxY + 1; y++) {
                    Point p = new Point(x, y);

                    int borders = regionGrid.get(p);

                    if ((borders & leftMask) == leftMask) {
                        leftCount++;
                    } else {
                        if (leftCount > 0) {
                            sides++;
                        }
                        leftCount = 0;
                    }

                    if ((borders & rightMask) == rightMask) {
                        rightCount++;
                    } else {
                        if (rightCount > 0) {
                            sides++;
                        }
                        rightCount = 0;
                    }
                }
            }


            return sides;

        }


    }

}
