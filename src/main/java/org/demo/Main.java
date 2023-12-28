package org.demo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Ticktacktoe ticktacktoe = new Ticktacktoe();
        boolean play = true;
        while (play){
            play = ticktacktoe.playGameCommandLine();
        }
        System.out.println("Exiting ticktacktoe");
    }
}