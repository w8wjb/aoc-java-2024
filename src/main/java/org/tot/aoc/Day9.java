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

        final int bucketCount = 10;

        List<File> files = new ArrayList<>();

        // Set up 9 buckets to hold the indexes to chunks of free space
        List<SortedSet<Integer>> freeSpaceBuckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            freeSpaceBuckets.add(new TreeSet<>());
        }

        // Convert the characters into an array of integers
        int[] diskMap = CharBuffer.wrap(input)
                .chars()
                .map(c -> c - 48)
                .toArray();

        // Loop through the "compressed" sizes and create
        // either File objects add the space to a bucket
        int fileIndex = 0;
        for (int i = 0; i < diskMap.length; i++) {
            int size = diskMap[i];
            if ((i % 2) == 0) {
                var file = new File(i / 2, size, fileIndex);
                files.add(file);
            } else if (size > 0) {
                freeSpaceBuckets.get(size).add(fileIndex);
            }
            fileIndex += size;
        }

        // Reverse the list of files in order to process from highest file ID to lowest
        Collections.reverse(files);

        // Move files one by one
        for (File file : files) {

            // This is important. This sets the maximum index of where space can be found.
            int spaceIndex = file.index;

            int selectedSize = bucketCount;
            SortedSet<Integer> selectedBucket = null;

            // Look through all the buckets with sizes that can accomodate the file
            for (int size = file.size; size < bucketCount; size++) {
                var bucket = freeSpaceBuckets.get(size);
                if (bucket.isEmpty()) {
                    // Skip empty buckets
                    continue;
                }
                var first = bucket.first();
                // Choose the bucket that has the index closest to the beginning
                // this won't always be an exact fit.
                if (first < spaceIndex) {
                    selectedBucket = bucket;
                    selectedSize = size;
                    spaceIndex = first;
                }
            }

            if (selectedBucket == null) {
                // Can't move this file; no space
                continue;
            }

            // Update the location of the file to be the start of the free space
            file.index = spaceIndex;
            // Remove the space index from the bucket
            selectedBucket.remove(file.index);

            // Add the leftover space to a different bucket
            int leftoverSpace = selectedSize - file.size;
            if (leftoverSpace > 0) {
                freeSpaceBuckets.get(leftoverSpace).add(spaceIndex + file.size);
            }
        }

        // Now that the files have been "moved", i.e. their indexes changed,
        // sort them in order by index
        files.sort(Comparator.comparing(File::getIndex));

        // Compute the checksum
        long checksum = 0;
        for (File file : files) {
            for (int i = file.index; i < file.index + file.size; i++) {
                checksum += (i * file.id);
            }
        }

        return checksum;
    }

    private void printFilesystem(List<File> files) {

        int prevIdx = 0;
        for (File file : files) {
            int spaceCount = file.index - prevIdx;
            for (int i = 0; i < spaceCount; i++) {
                System.out.print('.');
            }

            prevIdx = file.index;
            for (int i = file.index; i < file.index + file.size; i++) {
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

        @Override
        public String toString() {
            return String.format("%d @ %d %db", id, index, size);
        }
    }

}
