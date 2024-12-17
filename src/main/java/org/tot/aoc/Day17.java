package org.tot.aoc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class Day17 {


    public String solvePuzzle1(List<String> input) {


        var computer = new ThreeBitComputer(input);
        return computer.call();
    }

    public long solvePuzzle2(List<String> input) {


        var initialState = new ThreeBitComputer(input);

        String target = Arrays.stream(initialState.program)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));

        initialState.A = 117440L;
        String r1 = initialState.call();

        for (long i = 0; i < 10000000; i++) {
            var clone = new ThreeBitComputer(initialState);
            clone.A = i;

            String result = clone.call();
            if (target.equals(result)) {
                return i;
            }
        }

        return 0;
    }

    static class ThreeBitComputer implements Callable<String> {

        long A = 0;
        long B = 0;
        long C = 0;

        int[] program = new int[0];

        // Instruction pointer
        int i = 0;

        StringBuilder output = new StringBuilder();

        ThreeBitComputer(List<String> input) {
            for (String line : input) {
                String[] parts = line.split(":\\s+");

                switch (parts[0]) {
                    case "Register A":
                        A = Integer.parseInt(parts[1]);
                        break;
                    case "Register B":
                        B = Integer.parseInt(parts[1]);
                        break;
                    case "Register C":
                        C = Integer.parseInt(parts[1]);
                        break;
                    case "Program":
                        program = Arrays.stream(parts[1].split(","))
                                .mapToInt(Integer::parseInt)
                                .toArray();
                    default:
                        break;
                }
            }

        }

        ThreeBitComputer(ThreeBitComputer copy) {
            this.A = copy.A;
            this.B = copy.B;
            this.C = copy.C;
            this.program = copy.program;
            this.i = 0;
            this.output = new StringBuilder();
        }


        @Override
        public String call() {


            for (i = 0; i < program.length - 1; i += 2) {

                int opcode = program[i];
                int operand = program[i + 1];

                switch (opcode) {
                    case 0:
                        adv(combo(operand));
                        break;
                    case 1:
                        bxl(operand);
                        break;
                    case 2:
                        bst(combo(operand));
                        break;
                    case 3:
                        jnz(operand);
                        break;
                    case 4:
                        bxc(operand);
                        break;
                    case 5:
                        out(combo(operand));
                        break;
                    case 6:
                        bdv(combo(operand));
                        break;
                    case 7:
                        cdv(combo(operand));
                        break;
                    default:
                        throw new IllegalStateException("Invalid opcode " + opcode);
                }

            }


            return output.toString();
        }

        void adv(long operand) {
            A = (int) ((double) A / Math.pow(2, operand));
        }

        void bxl(long operand) {
            B = B ^ operand;
        }

        void bst(long operand) {
            B = operand % 8;
        }

        void jnz(long operand) {
            if (A == 0) {
                return;
            }
            i = (int) operand - 2;
        }

        void bxc(long operand) {
            B = B ^ C;
        }

        void out(long operand) {
            long value = operand % 8;
            if (output.length() > 0) {
                output.append(',');
            }
            output.append(String.valueOf(value));
        }

        void bdv(long operand) {
            B = (long) ((double) A / Math.pow(2, operand));
        }

        void cdv(long operand) {
            C = (long) ((double) A / Math.pow(2, operand));
        }

        long combo(long operand) {
            switch ((int) operand) {
                case 4:
                    return A;
                case 5:
                    return B;
                case 6:
                    return C;
                case 7:
                    throw new IllegalStateException("Combo operand 7 is reserved and will not appear in valid programs");
                default:
                    return operand;
            }
        }

    }


}
