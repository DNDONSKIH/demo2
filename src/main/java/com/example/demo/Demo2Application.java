package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {


        Random r = new Random();
        int [] intArray = new int[100000];

        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = r.nextInt(100001);
        }




        SpringApplication.run(Demo2Application.class, args);
    }

}
