package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean schalter = true;
        Scanner scanner = new Scanner(System.in);

        while (schalter) {
            auswahlfeld();
            int auswahl = scanner.nextInt();
            scanner.nextLine(); // Eingabezeile leeren

            if (auswahl == 1) {
                klicktest();
            } else if (auswahl == 2) {
                reaktionszeit();
            } else if (auswahl == 3) {
                cookyclicker();
            } else if (auswahl == 4) {
                schalter = false;
                System.out.println("closing program...");
            } else {
                System.out.println("Ungültige Eingabe!");
            }
        }
    }

    public static void auswahlfeld() {
        System.out.println("\nWas willst du spielen: ");
        System.out.println("1. Spacebar per second:");
        System.out.println("2. Reaction time Test:");
        System.out.println("3. Cooky clicker");
        System.out.println("4. Exit:");
    }

    public static void klicktest() {}

    public static void reaktionszeit() {}

    public static void cookyclicker() {
        Scanner scanner = new Scanner(System.in);
        boolean schalter = true;
        double cookycount = 0;
        double cookymultiplyer = 1;

        while (schalter) {
            System.out.println("\n--- Cooky Clicker ---");
            System.out.println("Drücke [Leertaste] um Cookys zu bekommen");
            System.out.println("Multiplyer: " + cookymultiplyer);
            System.out.println("Cookys: " + cookycount);
            System.out.println("Tippe [1] für Shop oder [Exit] um zurückzugehen");

            String input = scanner.nextLine();

            switch (input) {
                case " " -> cookycount += 1 * cookymultiplyer;

                case "1" -> {
                    boolean shopOffen = true;
                    while (shopOffen) {
                        cookyshop();
                        System.out.print("Wähle: ");
                        int choice;
                        while (!scanner.hasNextInt()) {
                            System.out.println("Bitte gib eine Zahl ein!");
                            scanner.nextLine();
                        }
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1 -> {
                                if (cookycount >= 10) {
                                    cookymultiplyer += 1;
                                    cookycount -= 10;
                                    System.out.println("Du hast einen normalen Cooky gekauft!");
                                } else {
                                    System.out.println("Nicht genug Cookys!");
                                }
                            }
                            case 2 -> {
                                if (cookycount >= 50) {
                                    cookymultiplyer += 2;
                                    cookycount -= 50;
                                    System.out.println("Du hast einen goldenen Cooky gekauft!");
                                } else {
                                    System.out.println("Nicht genug Cookys!");
                                }
                            }
                            case 3 -> {
                                if (cookycount >= 100) {
                                    cookymultiplyer += 3;
                                    cookycount -= 100;
                                    System.out.println("Du hast einen Regenbogen-Cooky gekauft!");
                                } else {
                                    System.out.println("Nicht genug Cookys!");
                                }
                            }
                            case 4 -> {
                                System.out.println("Zurück zum Spiel...");
                                shopOffen = false;
                            }
                            default -> System.out.println("Ungültige Eingabe!");
                        }
                    }
                }

                case "Exit" -> schalter = false;
                default -> System.out.println("Ungültige Eingabe!");
            }
        }
    }

    public static void cookyshop() {
        System.out.println("\n--- Cooky Shop ---");
        System.out.println("1. Normaler Cooky: +1 Multiplier (10 Cookys)");
        System.out.println("2. Goldener Cooky: +2 Multiplier (50 Cookys)");
        System.out.println("3. Regenbogen Cooky: +3 Multiplier (100 Cookys)");
        System.out.println("4. Zurück zum Spiel");
    }
}
