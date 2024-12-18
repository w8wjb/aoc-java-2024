package org.tot.aoc;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class Day17 {

    private ThreeBitComputer computer = null;
    private int[] targetSequence = null;

    public String solvePuzzle1(List<String> input) {
        computer = new ThreeBitComputer(input);
        return computer.call();
    }

    public long solvePuzzle2(List<String> input) {
        computer = new ThreeBitComputer(input);

        // Store the sequence we need to match
        targetSequence = computer.program;

        // Once that's done, though, we're going to reprogram the computer and drop the last 2 opcodes
        // These are the operations that make it output and loop. We don't need the output,
        // and it needs to only run through the opcode sequence once.
        computer.program = new int[targetSequence.length - 4];
        System.arraycopy(targetSequence, 0, computer.program, 0, computer.program.length);

        return calcA(0, targetSequence.length - 1);
    }

    long calcA(long a, int segment) {

        if (segment < 0) {
            return a;
        }

        // This is the number we want to get out of the computer
        long targetOutput = targetSequence[segment];

        for (int i = 0; i < 8; i++) {

            long input = (a << 3) + i;
            long output = computer.calc(input);

            if (targetOutput == output) {
                // We have a possible input candidate, lets keep moving left
                input = calcA(input, segment - 1);
                if (input != -1) {
                    return input;
                }
            }
        }

        return -1;
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
                        break;
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

        void reset() {
            A = 0;
            B = 0;
            C = 0;
            i = 0;
            output = new StringBuilder();
        }

        @Override
        public String call() {
            runProgram();
            return output.toString();
        }

        private void runProgram() {
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
        }

        long calc(long a) {
            this.A = a;
            this.B = 0;
            this.C = 0;
            runProgram();
            return B % 8;
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
