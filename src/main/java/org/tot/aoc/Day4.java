package org.tot.aoc;

import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.StringGrid;
import org.tot.aoc.grid.Vector;

import java.util.ArrayList;
import java.util.List;

public class Day4 {

    public int solvePuzzle1(List<String> input) {

        int total = 0;

        // This is using a StringGrid class I made to handle a lot of the AoC puzzles.
        // The primary feature is that it handles bounds checking, meaning if you ask for a point in the grid that is
        // out of bounds, you'll get a "." instead of a null or an IndexOutOfBoundsException.
        StringGrid grid = new StringGrid(input);

        // Get the individual characters in XMAS as an array
        char[] XMAS = "XMAS".toCharArray();

        // Loop through all the points in the grid
        for (Point start : grid) {

            // Check all the directions around this point, N, NE, E, SE, S, SW, W, NW
            for (Vector direction : Vector.ORDINAL) {

                // Create a 'p' variable to hold our current place. It starts at the center.
                Point p = start;

                // Count how many correct letters we encounter
                int correct = 0;
                // Loop through each letter in XMAS
                for (char c : XMAS) {
                    // Get the letter at the current grid point
                    char check = grid.get(p);

                    // If the letter isn't what it's supposed to be, there's no need to keep going; just bail out
                    if (c != check) {
                        break;
                    }

                    correct++;
                    // This line takes the current point, moves it further in current direction, and then
                    // makes that the new point.
                    //
                    // So, if the direction is SW or (x: -1, y: 1) and the point was (5, 6),
                    // the new point would be (5 - 1, 6 + 1) = (4, 7)
                    p = p.add(direction);
                }

                // Only count if we found all the letters
                if (correct == XMAS.length) {
                    total++;
                }
            }
        }


        return total;
    }

    public int solvePuzzle2(List<String> input) {

        int total = 0;

        StringGrid grid = new StringGrid(input);

        // Instead of all the directionsm, we're only using the diagonal ones
        Vector[] corners = new Vector[]{
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
                // Check all the corners by adding the directions, one by one, to the center point.
                Point corner = center.add(dir);
                if (grid.get(corner) == 'M') {
                    // If we found an 'M', add it to the list to check.
                    // Note that I'm adding the _direction_ to the list, not the actual point.
                    // Keeping it relative to the center point makes it easier to find the opposite later
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
                // Inverting the direction effectively mirrors it across the center. So (1,1) SE becomes (-1,-1) NW
                Point opposite = center.add(start.inverted());
                // Now check the opposite corner
                char check = grid.get(opposite);
                if (check == 'S') {
                    diagCount++;
                }
            }

            // Only count places that have both diagonals
            if (diagCount == 2) {
                total++;
            }
        }


        return total;
    }

}
