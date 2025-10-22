package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean schalter = true;

        Scanner scanner = new Scanner(System.in);

        while (schalter) {
            auswahlfeld();
            int auswahl = scanner.nextInt();

            if (auswahl == 1) {
                klicktest();
            } else if (auswahl == 2) {
                reaktionszeit();
            } else if (auswahl == 3) {
                schalter = false;
                cookyclicker();
            } else  if (auswahl == 4) {
                System.out.println("closing program...");
            } else {
                System.out.println("Ungültige Eingabe!");
            }
        }
    }
    public static void auswahlfeld() {
        System.out.println("\nWas willst du spielen: ");
        System.out.println("1. Spacebar per second:" );
        System.out.println("2. Reaction time Test:" );
        System.out.println("3. Cooky clicker");
        System.out.println("4. Exit: ");
    }
    public static void klicktest() {

    }
    public static void reaktionszeit() {

    }
    public static void cookyclicker() {

    }
}