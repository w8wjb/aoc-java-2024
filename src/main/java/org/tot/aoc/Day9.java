package org.tot.aoc;

import java.nio.CharBuffer;
import java.util.*;

public class Day9 {


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


        List<File> files = new ArrayList<>();
        List<SortedSet<Integer>> freeSpaceBuckets = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            freeSpaceBuckets.add(new TreeSet<>());
        }

        int[] diskMap = CharBuffer.wrap(input)
                .chars()
                .map(c -> c - 48)
                .toArray();

        int fileIndex = 0;
        for (int i = 0; i < diskMap.length; i++) {
            int size = diskMap[i];
            if ((i % 2) == 0) {
                var block = new File(i / 2, size, fileIndex);
                files.add(block);
            } else if (size > 0) {
                freeSpaceBuckets.get(size).add(fileIndex);
            }
            fileIndex += size;
        }

        System.out.println("Defragging...");

        Collections.reverse(files);

        for (File file : files) {
            int sizeNeeded = file.size;

            nextSize:
            for (int size = sizeNeeded; size < freeSpaceBuckets.size(); size++) {
                var bucket = freeSpaceBuckets.get(size);
                if (!bucket.isEmpty()) {
                    int spaceIndex = bucket.first();
                    while (spaceIndex > file.index) {
                        bucket.remove(spaceIndex);
                        if (bucket.isEmpty()) {
                            continue nextSize;
                        }
                        spaceIndex = bucket.first();
                    }

                    file.index = spaceIndex;
                    bucket.remove(file.index);

                    int leftoverSpace = size - sizeNeeded;
                    if (leftoverSpace > 0) {
                        spaceIndex += sizeNeeded;
                        freeSpaceBuckets.get(leftoverSpace).add(spaceIndex);
                    }
                    break;
                }
            }

//            printFilesystem(files);
        }

        files.sort(Comparator.comparing(File::getIndex));
        printFilesystem(files);
        
        long checksum = 0;
        for (File file : files) {
            for (int i=file.index; i< file.index + file.size; i++) {
                checksum += (i * file.id);
            }
        }

        return checksum;
    }

    private void printFilesystem(List<File> files) {

        int prevIdx = 0;
        for (File file : files) {
            int spaceCount = file.index - prevIdx;
            for (int i= 0; i < spaceCount; i++) {
                System.out.print('.');
            }

            prevIdx = file.index;
            for (int i=file.index; i< file.index + file.size; i++) {
                System.out.print(file.id);
                prevIdx++;
            }
        }

        System.out.println();
    }

    static class File {
        long id;
        int size;
        int index;

        public File(long id, int size, int index) {
            this.id = id;
            this.size = size;
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

}
