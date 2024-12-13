package org.tot.aoc;

import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.StringGrid;
import org.tot.aoc.grid.Vector;

import java.util.*;
import java.util.regex.Pattern;

public class Day13 {


    List<ClawMachine> parseInput(List<String> input, long conversionAdjustment) {

        input.add(""); // Add extra line to process last group
        List<ClawMachine> machines = new ArrayList<>();

        ClawMachine machine = new ClawMachine();

        var pattern = Pattern.compile("(Button .|Prize):\\s++X.(\\d+),\\s+Y.(\\d+)");

        for (String line : input) {

            var matcher = pattern.matcher(line);

            if (matcher.find()) {

                switch (matcher.group(1)) {
                    case "Button A":
                        machine.a = new Vector(
                                Long.parseLong(matcher.group(2)),
                                Long.parseLong(matcher.group(3))
                        );
                        break;
                    case "Button B":
                        machine.b = new Vector(
                                Long.parseLong(matcher.group(2)),
                                Long.parseLong(matcher.group(3))
                        );
                        break;

                    default:
                        machine.prize = new Point(
                                Long.parseLong(matcher.group(2)) + conversionAdjustment,
                                Long.parseLong(matcher.group(3)) + conversionAdjustment
                        );
                        break;
                }

            } else {
                machines.add(machine);
                machine = new ClawMachine();
            }


        }

        return machines;
    }

    public long solvePuzzle(List<String> input, long conversionAdjustment) {

        List<ClawMachine> machines = parseInput(input, conversionAdjustment);

        long tokens = 0;

        for (var machine : machines) {

            double ax = machine.a.x;
            double ay = machine.a.y;
            double bx = machine.b.x;
            double by = machine.b.y;
            double px = machine.prize.x;
            double py = machine.prize.y;

            // For both the X and Y coordinates, they can be represented as a line of the linear equation:

            //      (p.x - (a.x * x)
            //      ----------------
            // y =        b.x
            //
            //      (p.y - (a.y * x)
            //      ----------------
            // y =        b.y


            // What these lines represent is the ratio between tha A button and the B button for the x and y coordinate.
            // Or in other words, there are potentially *many* ways that you can combine the A & B presses on only one axis

            // However, to find the solution that satisfies both axes, we need to find the place where the lines cross
            // To find that point, set the two equations equal to each other


            //  (p.x - (a.x * A)     (p.y - (a.y * A)
            //  ----------------  =  ----------------
            //       b.x                   b.y

            // When solved for A, this becomes this:
            double A = ((by * px) - (bx * py)) / ((by * ax) - (bx * ay));

            // If the result has a fraction then there is no combination that will win a prize
            if (A != Math.floor(A)) {
                continue;
            }

            // With A in hand, plug it into one of the linear equations above.
            double B = (px - (ax * A)) / bx;

            tokens += ((long) A * 3);
            tokens += (long) B;

        }



        return tokens;
    }


    static class ClawMachine {
        Vector a;
        Vector b;
        Point prize;
    }

}
