package org.example;

import java.util.Scanner;

public abstract class MiniGame {
    protected String name;

    public MiniGame(String name) {
        this.name = name;
    }

    public abstract void start(Scanner scanner);

    protected void printTitle() {
        System.out.println("\n=== " + name + " ===");
    }
}