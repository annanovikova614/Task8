package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int[][] arr = readFile();
        printArr(arr);
        processAnArray(arr);
        printResultInFile(arr);
    }

    private static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] readFile () throws FileNotFoundException {
        File input = new File("com/company/input.txt");
        Scanner scanner = new Scanner(input);
        int[][] arr = new int[scanner.nextInt()][scanner.nextInt()];
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {
                arr[row][col] = scanner.nextInt();
            }
        }
        return arr;
    }

    public static void printResultInFile(int [][]arr) throws IOException {
        File output = new File("com/company/output.txt");
        FileWriter fileWriter = new FileWriter(output);
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {
                fileWriter.append(arr[row][col]+" ");
            }
            fileWriter.append("\n");
        }
        fileWriter.close();
    }

    public static int[] countColSum(int[][] arr) {
        int[] sums = new int[arr[0].length];
        for (int col = 0; col < arr[0].length; col++) {
            for (int row = 0; row < arr.length; row++) {
                sums[col] += arr[row][col];
            }
        }
        return sums;
    }

    public static int countPositiveValues(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0){
                count++;
            }
        }
        return count;
    }

    public static int[][] processAnArray(int[][] arr) {
        int[] sums = countColSum(arr);
        int positiveCols = countPositiveValues(sums);
        int[][] positiveArr = new int[arr.length][positiveCols];
        int[][] negativeArr = new int[arr.length][arr[0].length - positiveCols];

        int positiveIndex = 0;

        for (int col = 0; col < arr[0].length; col++) {
            if (sums[col]<0) continue;
            for (int row = 0; row < arr.length; row++) {
                positiveArr[row][positiveIndex] = arr[row][col];
            }
            positiveIndex++;
        }
        int negativeIndex = 0;
        for (int col = 0; col < arr[0].length; col++) {
            if (sums[col]>=0) continue;
            for (int row = 0; row < arr.length; row++) {
                negativeArr[row][negativeIndex] = arr[row][col];
            }
            negativeIndex++;
        }
        //merge positive
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < positiveArr[0].length; col++) {
                arr[row][col] = positiveArr[row][col];
            }
        }
        //merge negative
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < negativeArr[0].length; col++) {
                arr[row][col + positiveCols] = negativeArr[row][col];
            }
        }
        return arr;
    }
}