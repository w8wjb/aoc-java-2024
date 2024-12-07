package org.tot.aoc;

import java.util.*;
import java.util.function.BiFunction;

public class Day7 {


    private List<Equation> parseInput(List<String> input, Operator[] operators) {

        List<Equation> equations = new ArrayList<>();
        for (String line : input) {
            var scan = new Scanner(line).useDelimiter("\\D+");
            long testValue = scan.nextLong();
            List<Long> values = new ArrayList<>();
            while (scan.hasNext()) {
                values.add(scan.nextLong());
            }
            equations.add(new Equation(testValue, values, operators));
        }
        return equations;
    }

    public long solvePuzzle1(List<String> input) {


        Operator[] operators = new Operator[]{
                Long::sum,
                (l, r) -> l * r,
        };

        var equations = parseInput(input, operators);

        long total = 0;
        for (var equation : equations) {

            if (equation.evaluate()) {
                total += equation.testValue;
            }

        }

        return total;
    }

    public long solvePuzzle2(List<String> input) {

        Operator[] operators = new Operator[]{
                Long::sum,
                (l, r) -> l * r,
                (l, r) -> {
                    long numDigits = (int) Math.log10(r) + 1;
                    return l * ((long) Math.pow(10, numDigits)) + r;
                }
        };

        var equations = parseInput(input, operators);

        long total = 0;
        for (var equation : equations) {

            if (equation.evaluate()) {
                total += equation.testValue;
            }

        }

        return total;
    }

    interface Operator extends BiFunction<Long, Long, Long> {
    }

    private static class Equation {

        long testValue;
        List<Long> values;
        Operator[] operators;
        int numValues ;

        public Equation(long testValue, List<Long> values, Operator[] operators) {
            this.testValue = testValue;
            this.values = values;
            this.operators = operators;
            numValues = values.size();
        }

        public boolean evaluate() {
            long left = values.get(0);
            var remaining = values.listIterator(1);

            return evaluate(left, remaining);

        }

        public boolean evaluate(long left, ListIterator<Long> remaining) {

            if (remaining.hasNext()) {
                long right = remaining.next();
                for (var op : operators) {
                    long result = op.apply(left, right);
                    if (evaluate(result, values.listIterator(remaining.nextIndex()))) {
                        return true;
                    }
                }
                return false;

            } else {
                return left == testValue;
            }

        }

    }

}
