package org.tot.aoc;

import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.StringGrid;
import org.tot.aoc.grid.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Day4 {

    public int solvePuzzle1(List<String> input) {

        int total = 0;

        StringGrid grid = new StringGrid(input);

        char[] XMAS = "XMAS".toCharArray();

        for (Point start : grid) {

            nextDirection:
            for (Vector direction : Vector.ADJACENT_MOVES) {
                Point p = start;

                for (char c : XMAS) {
                    char check = grid.get(p);
                    if (c == check) {
                        p = p.add(direction);
                    } else {
                        continue nextDirection;
                    }
                }
                total++;
            }
        }


        return total;
    }

    public int solvePuzzle2(List<String> input) {

        int total = 0;

        StringGrid grid = new StringGrid(input);

        char[] XMAS = "XMAS".toCharArray();

        Vector[] corners = new Vector[] {
                Vector.NE,
                Vector.SE,
                Vector.SW,
                Vector.NW
        };

        for (Point center : grid) {

            // 'A' always has to be at the center. No sense checking if it's not
            if (grid.get(center) != 'A') {
                continue;
            }

            // Find the Ms
            List<Vector> starts = new ArrayList<>();
            for (Vector dir : corners) {
                Point corner = center.add(dir);
                if (grid.get(corner) == 'M') {
                    starts.add(dir);
                }
            }

            // Has to only be 2 Ms, otherwise it's not an X-MAS
            if (starts.size() != 2) {
                continue;
            }

            int diagCount = 0;
            // For each starting 'M', check diagonally for an 'S'
            // This should exclude the case where the 'M's are opposite of each other
            for (Vector start : starts) {
                Point opposite = center.add(start.inverted());
                char check = grid.get(opposite);
                if (check == 'S') {
                    diagCount++;
                }
            }

            if (diagCount == 2) {
                total++;
            }
        }


        return total;
    }

}
