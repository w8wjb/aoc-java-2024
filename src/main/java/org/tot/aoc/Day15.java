package org.tot.aoc;

import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.Vector;

import java.util.*;

public class Day15 {


    HashGrid<Character> warehouse;
    Point start;
    List<Vector> movementSequence = new ArrayList<>();

    void parseInput(List<String> input) {

        List<String> warehouseLines = new ArrayList<>();

        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            if (line.startsWith("#")) {
                warehouseLines.add(line);

                if (start == null) {
                    int startX = line.indexOf('@');
                    if (startX > 0) {
                        start = new Point(startX, y);
                    }
                }
            } else {
                for (char c : line.toCharArray()) {
                    switch (c) {
                        case '^':
                            movementSequence.add(Vector.N);
                            break;
                        case '>':
                            movementSequence.add(Vector.E);
                            break;
                        case 'v':
                            movementSequence.add(Vector.S);
                            break;
                        case '<':
                            movementSequence.add(Vector.W);
                            break;
                    }
                }
            }
        }

        warehouse = HashGrid.fromList(warehouseLines);

    }


    public long solvePuzzle1(List<String> input) {

        parseInput(input);


        Point robot = start;
        Point check;
        List<Point> stack = new ArrayList<>();
        boolean canMove = false;

        for (Vector move : movementSequence) {

            for (int i = 0; i < warehouse.maxX; i++) {
                check = robot.add(move.times(i));
                char contents = warehouse.get(check);

                if (contents == '#') {
                    canMove = false;
                    break;
                } else if (contents == '.') {
                    canMove = true;
                    break;
                }
                stack.add(check);
            }

            if (canMove) {
                for (int i = stack.size() - 1; i >= 0; i--) {
                    Point oldLocation = stack.get(i);
                    Point newLocation = oldLocation.add(move);
                    char object = warehouse.remove(oldLocation);
                    if (object == '@') {
                        robot = newLocation;
                    }
                    warehouse.put(newLocation, object);
                }
            }
            stack.clear();

        }

        long total = 0;

        for (var entry : warehouse.entrySet()) {
            if (entry.getValue() == 'O') {
                Point loc = entry.getKey();
                long gps = (loc.y * 100) + loc.x;
                total += gps;
            }
        }

        return total;
    }

    void expandWarehouse() {

        HashGrid<Character> wideWarehouse = new HashGrid<>('.');

        for (var entry : warehouse.entrySet()) {
            Point oldLoc = entry.getKey();
            char item = entry.getValue();

            Point newLoc1 = new Point(oldLoc.x * 2, oldLoc.y);
            Point newLoc2 = new Point(newLoc1.x + 1, oldLoc.y);
            if (item == '@') {
                start = newLoc1;
                wideWarehouse.put(newLoc1, item);
                wideWarehouse.put(newLoc2, '.');
            } else if (item == 'O') {
                wideWarehouse.put(newLoc1, '[');
                wideWarehouse.put(newLoc2, ']');
            } else {
                wideWarehouse.put(newLoc1, item);
                wideWarehouse.put(newLoc2, item);
            }

        }

        warehouse = wideWarehouse;
    }

    public long solvePuzzle2(List<String> input) {

        parseInput(input);
        expandWarehouse();

        Point robot = start;
        NavigableSet<Point> stack = new TreeSet<>();
        boolean canMove;

        for (Vector move : movementSequence) {

            canMove = collectStack(robot, move, stack);

            if (canMove) {

                Iterator<Point> itor;
                if (move.x > 0 || move.y > 0) {
                    itor = stack.descendingIterator();
                } else {
                    itor = stack.iterator();
                }
                while (itor.hasNext()) {
                    Point oldLocation = itor.next();
                    Point newLocation = oldLocation.add(move);
                    char object = warehouse.remove(oldLocation);
                    if (object == '@') {
                        robot = newLocation;
                    }
                    warehouse.put(newLocation, object);
                }

            }
            stack.clear();

//            System.out.println(move.asArrow());
//            warehouse.print();

        }

        long total = 0;

        for (var entry : warehouse.entrySet()) {
            if (entry.getValue() == '[') {
                Point loc = entry.getKey();
                long gps = (loc.y * 100) + loc.x;
                total += gps;
            }
        }

        return total;
    }

    boolean collectStack(Point start, Vector direction, Set<Point> stack) {

        char startType = warehouse.get(start);

        List<Vector> toCheck = new ArrayList<>();
        toCheck.add(direction);

        boolean verticalMove = direction.y != 0;
        if (verticalMove) {
            if (startType == ']') {
                toCheck.add(Vector.W);
            } else if (startType == '[') {
                toCheck.add(Vector.E);
            }
        }

        boolean alreadyVisited = !stack.add(start);
        if (alreadyVisited) {
            return true;
        }

        for (Vector adj : toCheck) {
            Point next = start.add(adj);
            char contents = warehouse.get(next);

            switch (contents) {
                case '#':
                    return false;
                case '.':
                    continue;
                default:
                    if (!collectStack(next, direction, stack)) {
                        return false;
                    }
            }
        }

        return true;
    }


}
