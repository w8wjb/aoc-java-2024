package org.tot.aoc;

import org.apache.commons.lang3.Range;

import java.nio.CharBuffer;
import java.util.*;
import java.util.function.Predicate;

public class Day9 {

    List<FileBlock> filesystem = new LinkedList<>();
    SortedMap<Integer, Integer> freeSpaceMarkers = new TreeMap<>();

    public long solvePuzzle1(String input) {

        int[] diskMap = CharBuffer.wrap(input)
                .chars()
                .map(c -> c - 48)
                .toArray();

        int totalMem = Arrays.stream(diskMap).sum();

        Long[] filesystem = new Long[totalMem];

        int fileId = 0;
        int writeIndex = 0;
        for (int i = 0; i < diskMap.length; i++) {
            int length = diskMap[i];
            if ((i % 2) == 0) {
                Long fileContents = (long) fileId;
                Arrays.fill(filesystem, writeIndex, writeIndex + length, fileContents);
                fileId++;
            }
            writeIndex += length;
        }


        int from = filesystem.length - 1;
        for (int to = 0; to < filesystem.length && to < from; to++) {
            while (filesystem[to] == null) {
                filesystem[to] = filesystem[from];
                filesystem[from] = null;
                from--;
            }
        }

        long checksum = 0;
        for (int i = 0; i < from + 1; i++) {
            checksum += filesystem[i] * i;
        }


        return checksum;
    }

    public long solvePuzzle2(String input) {

        int[] diskMap = CharBuffer.wrap(input)
                .chars()
                .map(c -> c - 48)
                .toArray();

        long maxFileId = 0;
        for (int i = 0; i < diskMap.length; i += 2) {
            maxFileId = i / 2;
            int size = diskMap[i];
            int free = (i + 1) < diskMap.length ? diskMap[i + 1] : 0;
            filesystem.add(new FileBlock(maxFileId, size, free));
        }

        int seekRangeStart = 0;
        int seekRangeEnd = filesystem.size() - 1;


        System.out.println("Defragging...");
//        printFilesystem();

        nextFieldId:
        for (long fileId = maxFileId; fileId > 0; fileId--) {

            if (fileId % 100 == 0) {
                System.out.println(fileId);
            }

            int fromIndex = indexOfReverse(filesystem, seekRangeEnd, seekRangeStart, fileId);
            if (fromIndex < 0) {
                throw new IllegalStateException("FileBlock not found " + fileId);
            }
            if (seekRangeEnd != fromIndex) {
                seekRangeEnd = fromIndex;
            }

            FileBlock moving = filesystem.get(fromIndex);

            int freeSpaceIdx = indexOfFreeSpace(moving.size, fromIndex);
            if (freeSpaceIdx < 0) {
//                System.out.println("Not moving, no space " + fileId);
//                printFilesystem();
                continue;
            }

            relocate(moving, fromIndex, freeSpaceIdx);
//            printFilesystem();

//            FileBlock gap = filesystem.get(fromIndex - 1);
//
//            for (int i = seekRangeStart; i < seekRangeEnd; i++) {
//                FileBlock preceding = filesystem.get(i);
//                if (preceding.freeSpaceAfter >= moving.size) {
//                    gap.freeSpaceAfter += moving.size + moving.freeSpaceAfter;
//                    moving.freeSpaceAfter = preceding.freeSpaceAfter - moving.size;
//                    preceding.freeSpaceAfter = 0;
//
//                    filesystem.remove(fromIndex);
//                    filesystem.add(i + 1, moving);
//                    System.out.printf("Moved %d to %d%n", fileId, i + 1);
//                    continue nextFieldId;
//                }
//
//            }
//            System.out.println("Not moving, no space " + fileId);


        }

        long checksum = 0;
        int idx = 0;
        for (var f : filesystem) {
//            System.out.printf("%d %d %n", f.fileId, idx);
            int end = idx + f.size;
            while (idx < end) {
                checksum += (idx * f.fileId);
                idx++;
            }
            idx += f.freeSpaceAfter;
        }

        return checksum;
    }

    int indexOfReverse(List<FileBlock> list, int start, int end, long findFileId) {
        for (int j = start; j >= end; j--) {
            if (list.get(j).fileId == findFileId) {
                return j;
            }
        }
        return -1;
    }

    int indexOfFreeSpace(int size, int notBeyond) {

        var itor = freeSpaceMarkers.tailMap(size).entrySet().iterator();
        while (itor.hasNext()) {
            var entry = itor.next();
            if (entry.getKey() >= size) {
                int index = entry.getValue();
                if (index < notBeyond) {
                    itor.remove();
                    return index;
                }
            }
        }


        int start = freeSpaceMarkers.values()
                .stream()
                .sorted()
                .findFirst()
                .orElse(0);
//        System.out.printf("Cache miss for %d, starting at %d %n", size, start);

        for (int i = start; i < notBeyond; i++) {
            FileBlock b = filesystem.get(i);
            int freeSpace = b.freeSpaceAfter;
            if (freeSpace < size) {
                Integer existingIndex = freeSpaceMarkers.getOrDefault(freeSpace, Integer.MAX_VALUE);
                if (i < existingIndex) {
                    freeSpaceMarkers.put(freeSpace, i);
                }

            } else {
                return i;
            }
        }
        return -1;
    }

    void printFilesystem() {
        for (var f : filesystem) {
            for (int i = 0; i < f.size; i++) {
                System.out.print(f.fileId % 10);
            }
            for (int i = 0; i < f.freeSpaceAfter; i++) {
                System.out.print(".");
            }
        }
        System.out.println();
    }

    void relocate(FileBlock moveFile, int from, int to) {
        FileBlock fromNeighbor = filesystem.get(from - 1);
        FileBlock toNeighbor = filesystem.get(to);

        fromNeighbor.freeSpaceAfter += moveFile.size + moveFile.freeSpaceAfter;
        moveFile.freeSpaceAfter = toNeighbor.freeSpaceAfter - moveFile.size;
        toNeighbor.freeSpaceAfter = 0;

        filesystem.remove(from);
        filesystem.add(to + 1, moveFile);
    }

    static class FileBlock {
        long fileId;
        int size;
        int freeSpaceAfter;

        public FileBlock(long fileId, int size, int freeSpaceAfter) {
            this.fileId = fileId;
            this.size = size;
            this.freeSpaceAfter = freeSpaceAfter;
        }
    }

}
