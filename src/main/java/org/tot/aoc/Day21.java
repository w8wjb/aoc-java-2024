package org.tot.aoc;

import org.apache.commons.lang3.StringUtils;
import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.StringGrid;
import org.tot.aoc.grid.Vector;

import java.security.Key;
import java.util.*;
import java.util.regex.Pattern;

public class Day21 {

    final List<Keypad> keypads = new ArrayList<>();

    Day21(int numKeypads) {

        for (int i = 0; i < numKeypads; i++) {
            keypads.add(new Keypad(i, i != 0));
        }
    }

    public int solvePuzzle1(List<String> input) {


        for (var line : input) {


            List<String> sequences = findSequences(0, line);


//            StringBuilder fullSequence = new StringBuilder();
//
//            for (char c : line.toCharArray()) {
//                fullSequence.append(findSequence(0, 'A', c));
//            }
//
//            System.out.println(fullSequence.toString());

        }


        return 0;
    }


    List<String> findSequences(int level, String source) {

        var keypad = keypads.get(level);

        List<String> sequences = new ArrayList<>();

        char last = 'A';
        for (int i = 0; i < source.length(); i++) {
            char next = source.charAt(i);

            List<String> paths = keypad.findPaths(last, next);

            if (level >= keypads.size() - 1) {
                sequences.addAll(paths);
                continue;
            }

            for (String subpath : paths) {
                List<String> exploded = findSequences(level + 1, subpath);
                sequences.addAll(exploded);
            }

            System.out.printf("%s -> %s %s%n", last, next, paths);

        }

//        for (char a = '0'; a <= '9'; a++) {
//            for (char b = '0'; b <= '9'; b++) {
//                List<String> paths = keypad.findPaths(a, b);
//                for (String path : paths) {
//                    System.out.printf("%s %s %s%n", a, b, path);
//                }
//            }
//
//        }
//        System.exit(0);

        return Collections.emptyList();
    }

    public int solvePuzzle2(List<String> input) {


        return 0;
    }

    private static class Keypad {

        final boolean directional;
        final int index;
        final StringGrid grid;
        final Point blank;
        final Map<Character, Point> char2point = new HashMap<>();
        final Map<String, List<String>> pair2path = new TreeMap<>();


        Keypad(int index, boolean directional) {
            this.directional = directional;
            this.index = index;
            if (directional) {
                grid = new StringGrid(List.of(".^A", "<v>"));
                blank = new Point(0, 0);
            } else {
                grid = new StringGrid(List.of("789", "456", "123", ".0A"));
                blank = new Point(0, 3);
            }
            for (Point p : grid) {
                char2point.put(grid.get(p), p);
            }

            char[] allKeys = String.join("", grid.rows).toCharArray();

            for (char fromChar : allKeys) {
                if (fromChar == '.') {
                    continue;
                }
                for (char toChar : allKeys) {
                    if (toChar == '.' || fromChar == toChar) {
                        continue;
                    }
                    List<String> paths = new ArrayList<>();
                    Point start = char2point.get(fromChar);
                    Point end = char2point.get(toChar);

                    Vector delta = end.subtract(start);

                    List<Vector> dirs = new ArrayList<>();
                    if (delta.x > 0) {
                        dirs.add(Vector.E);
                    } else if (delta.x < 0) {
                        dirs.add(Vector.W);
                    }

                    if (delta.y > 0) {
                        dirs.add(Vector.S);
                    } else if (delta.y < 0) {
                        dirs.add(Vector.N);
                    }

                    collectPaths(start, dirs, end, "", paths);
                    pair2path.put(String.valueOf(fromChar) + toChar, paths);
                }
            }


//            for (var entry : pair2path.entrySet()) {
//                String pair = entry.getKey();
//                for (String path : entry.getValue()) {
//                    System.out.printf("%s : %s%n", pair, path);
//                }
//            }
//            System.out.println();


        }

        void collectPaths(Point start, List<Vector> dirs, Point end, String path, List<String> paths) {

            for (var dir : dirs) {
                Point check = start.add(dir);
                if (check.equals(start)) {
                    continue;
                }

                if (check.equals(end)) {
                    String finalPath = path + dir.asArrow();
                    paths.add(finalPath);
                    continue;
                }

                char contents = grid.get(check);
                if (contents == '.') {
                    continue;
                }

                collectPaths(check, dirs, end, path + dir.asArrow(), paths);
            }
        }


//        public String findSequence(char destination) {
//
//            String translate;
//            if (next == null) {
//                translate = String.valueOf(destination);
//            } else {
//                translate = next.findSequence(destination);
//            }
//
//            StringBuilder sequence = new StringBuilder();
//            for (char c : translate.toCharArray()) {
//                sequence.append(findPath(c));
//                sequence.append('A');
//            }
//
//            System.out.printf("%s: ➤ %s: %s %n", name, destination, sequence);
//
//            return sequence.toString();
//        }

        public List<String> findPaths(char from, char to) {
            String joined = new String(new char[]{from, to});
            return pair2path.getOrDefault(joined, Collections.emptyList());
//
//            Point fromPoint = char2point.get(from);
//            Point toPoint = char2point.get(to);
//
//            List<String> paths = new ArrayList<>();
//
//            Vector change = toPoint.subtract(fromPoint);
//
//            char horiz = change.x < 0 ? '<' : '>';
//            char vert = change.y < 0 ? '^' : 'v';
//
//            int distX = (int) Math.abs(change.x);
//            int distY = (int) Math.abs(change.y);
//
//            if (distX == 0 && distY == 0) {
//                return Collections.emptyList();
//            }
//
//            int chessDist = (int) fromPoint.chessboardStepDistance(toPoint);
//
//            if (directional && fromPoint.y == 0) {
//            } else if (fromPoint.y == 3) {
//            }
//
//            for (int x = 0; x < distX; x++) {
//                String path = StringUtils.repeat(horiz, x)
//                        + StringUtils.repeat(vert, distY)
//                        + StringUtils.repeat(horiz, distX - x);
//                paths.add(path);
//            }
//
//            for (int y = 0; y < distY; y++) {
//                String path = StringUtils.repeat(vert, y)
//                        + StringUtils.repeat(horiz, distX)
//                        + StringUtils.repeat(vert, distY - y);
//                paths.add(path);
//            }

            /*
            1 -> 9

            chessboard 4

            >>^^
            >^>^
            >^^>
            ^>>^
            ^>^>


             */

//            for (int x = 1; x <= distX; x++) {
//                for (int y = 1; y <= distY; y++) {
//                    // When moving left, always move vertically first, to avoid the blank space
//                    String path;
//                    if (change.x < 0) {
//                        path = StringUtils.repeat(vert, y) + StringUtils.repeat(horiz, x);
//                    } else {
//                        path = StringUtils.repeat(horiz, x) + StringUtils.repeat(vert, y);
//                    }
//                    paths.add(path);
//                }
//            }
//            return paths;
        }

//        public String findPath(char from, char to) {
//
//            Point fromPoint = char2point.get(from);
//            Point toPoint = char2point.get(to);
//
//            Vector change = toPoint.subtract(fromPoint);
//
//            StringBuilder sequence = new StringBuilder();
//            if (change.x > 0) {
//                // When moving right, always move horizontally first, to avoid the blank space
//                if (change.y > 0) { // SE
//                    sequence.append(StringUtils.repeat('>', (int) change.x));
//                    sequence.append(StringUtils.repeat('v', (int) change.y));
//
//                } else if (change.y < 0) { // NE
//                    sequence.append(StringUtils.repeat('>', (int) change.x));
//                    sequence.append(StringUtils.repeat('^', (int) -change.y));
//
//                } else { // E
//                    sequence.append(StringUtils.repeat('>', (int) change.x));
//                }
//            } else if (change.x < 0) {
//                // When moving left, always move vertically first, to avoid the blank space
//                if (change.y > 0) { // SW
//                    sequence.append(StringUtils.repeat('v', (int) change.y));
//                    sequence.append(StringUtils.repeat('<', (int) -change.x));
//
//                } else if (change.y < 0) { // NW
//                    sequence.append(StringUtils.repeat('^', (int) -change.y));
//                    sequence.append(StringUtils.repeat('<', (int) -change.x));
//
//                } else { // W
//                    sequence.append(StringUtils.repeat('<', (int) -change.x));
//                }
//            } else {
//                if (change.y > 0) { // S
//                    sequence.append(StringUtils.repeat('v', (int) change.y));
//                } else if (change.y < 0) { // N
//                    sequence.append(StringUtils.repeat('^', (int) -change.y));
//                } else {
//                    //throw new IllegalStateException("No movement necessary");
//                }
//            }
//
//
//            return sequence.toString();
//        }

    }

}
