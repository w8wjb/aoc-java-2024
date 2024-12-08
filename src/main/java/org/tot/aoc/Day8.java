package org.tot.aoc;

import org.tot.aoc.grid.HashGrid;
import org.tot.aoc.grid.Point;
import org.tot.aoc.grid.Vector;

import java.util.*;

public class Day8 {

    public long solvePuzzle1(List<String> input) {

        var antennaGrid = HashGrid.fromList(input);
        var antinodes = new HashGrid<>(antennaGrid);
        antinodes.clear();

        Map<Character, List<Point>> antennasByFreq = new HashMap<>();

        for (Map.Entry<Point, Character> entry : antennaGrid.entrySet()) {
            var location = entry.getKey();
            var frequency = entry.getValue();

            List<Point> locations = antennasByFreq.computeIfAbsent(frequency, freq -> new ArrayList<>());
            locations.add(location);
        }

        for (Map.Entry<Character, List<Point>> entry : antennasByFreq.entrySet()) {
            // var frequency = entry.getKey();
            var points = entry.getValue();

            points.sort(Comparator.comparing(Point::getX));

            for (int i = 0; i < points.size(); i++) {
                for (int j = i + 1; j < points.size(); j++) {
                    var antennaA = points.get(i);
                    var antennaB = points.get(j);

                    Vector delta = antennaB.subtract(antennaA);
                    Point antinode1 = antennaA.subtract(delta);
                    if (antennaGrid.isWithinBounds(antinode1)) {
                        antinodes.put(antinode1, '#');
                    }

                    Point antinode2 = antennaB.add(delta);
                    if (antennaGrid.isWithinBounds(antinode2)) {
                        antinodes.put(antinode2, '#');
                    }

                }
            }


        }

        return antinodes.size();
    }

    public long solvePuzzle2(List<String> input) {

        var antennaGrid = HashGrid.fromList(input);
        var antinodes = new HashGrid<>(antennaGrid);
        antinodes.clear();

        Map<Character, List<Point>> antennasByFreq = new HashMap<>();

        for (Map.Entry<Point, Character> entry : antennaGrid.entrySet()) {
            var location = entry.getKey();
            var frequency = entry.getValue();

            List<Point> locations = antennasByFreq.computeIfAbsent(frequency, freq -> new ArrayList<>());
            locations.add(location);
        }

        for (Map.Entry<Character, List<Point>> entry : antennasByFreq.entrySet()) {
            var frequency = entry.getKey();
            var points = entry.getValue();

            points.sort(Comparator.comparing(Point::getX));

            for (int i = 0; i < points.size(); i++) {
                for (int j = i + 1; j < points.size(); j++) {
                    var antennaA = points.get(i);
                    var antennaB = points.get(j);
                    antinodes.put(antennaA, frequency);
                    antinodes.put(antennaB, frequency);

                    Vector delta = antennaB.subtract(antennaA);
                    Point antinode1 = antennaA.subtract(delta);
                    while (antennaGrid.isWithinBounds(antinode1)) {
                        antinodes.put(antinode1, '#');
                        antinode1 = antinode1.subtract(delta);
                    }

                    Point antinode2 = antennaB.add(delta);
                    while (antennaGrid.isWithinBounds(antinode2)) {
                        antinodes.put(antinode2, '#');
                        antinode2 = antinode2.add(delta);
                    }

                }
            }


        }

        return antinodes.size();
    }

}
