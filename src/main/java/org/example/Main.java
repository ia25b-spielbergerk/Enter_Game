package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        MiniGame[] games = {
                new Klicktest(),
                new Reaktionszeit(),
                new CookyClicker()
        };

        while (running) {
            auswahlfeld();
            int auswahl;
            while (!scanner.hasNextInt()) {
                System.out.println("Bitte Zahl eingeben!");
                scanner.nextLine();
            }
            auswahl = scanner.nextInt();
            scanner.nextLine();

            if (auswahl >= 1 && auswahl <= games.length) {
                games[auswahl - 1].start(scanner);
            } else if (auswahl == 4) {
                running = false;
                System.out.println("closing program...");
            } else {
                System.out.println("Ungültige Eingabe!");
            }
        }
        scanner.close();
    }

    private static void auswahlfeld() {
        System.out.println("\nWas willst du spielen: ");
        System.out.println("1. Enter pro second:");
        System.out.println("2. Reaction Zeit Test:");
        System.out.println("3. Cooky clicker");
        System.out.println("4. Exit:");
    }
}