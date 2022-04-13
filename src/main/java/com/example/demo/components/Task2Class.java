package com.example.demo.components;

import java.io.*;
import java.util.Random;
import java.util.stream.IntStream;


public class Task2Class {

    static int rowCount = 100000;
    static int colCount = 100000;

    public static void task2() throws IOException {
        csvInit();
        csvSort();
    }

    private static void csvInit() throws FileNotFoundException {
        var r = new Random();
        var fileWriter = new PrintWriter("data.csv");

        for (int i = 0; i < rowCount; i++) {
            var sb = new StringBuilder();
            for (int j = 0; j < colCount; j++) {
                sb.append(r.nextInt(100001));
                sb.append(";");
            }
            sb.append("\r\n");
            fileWriter.write(sb.toString());
        }
        fileWriter.flush();
        fileWriter.close();
    }

    private static void csvSort() throws IOException {
        int [] intArray = new int[colCount];
        int [] sortedIntArray;
        var fileReader = new BufferedReader(new FileReader("data.csv"));
        var fileWriter = new PrintWriter("data2.csv");
        int lineCounter = 0;
        while (true) {
            String line = fileReader.readLine();
            if (line == null)
                break;

            String [] values = line.split(";");

            for (int i = 0; i < colCount; i++) {
                intArray[i] = Integer.parseInt(values[i]);
            }

            if(++lineCounter % 2 == 0) {
                sortedIntArray = IntStream.of(intArray).boxed().sorted((a, b) -> Integer.compare(b, a))
                        .mapToInt(Integer::intValue).toArray();
            }
            else {
                sortedIntArray = IntStream.of(intArray).boxed().sorted((a, b) -> Integer.compare(a, b))
                        .mapToInt(Integer::intValue).toArray();
            }

            var sb = new StringBuilder();
            for (int value : sortedIntArray) {
                sb.append(value);
                sb.append(";");
            }
            sb.append("\r\n");
            fileWriter.write(sb.toString());
        }
        fileWriter.flush();
        fileWriter.close();
    }
}
