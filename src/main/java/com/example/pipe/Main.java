package com.example.pipe;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String [] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
                PipedInputStream writeIn = new PipedInputStream();
                PipedOutputStream readOut = new PipedOutputStream();

                writeIn.connect(readOut);

                ReadThread rt  = new ReadThread(System.in, readOut);
                ReadThread wt = new ReadThread(writeIn, System.out);

                executorService.submit(rt);
                executorService.submit(wt);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
            System.out.println("Running Completed...");
        }
    }
}