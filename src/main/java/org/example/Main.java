package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean schalter = true;
        Scanner scanner = new Scanner(System.in);

        while (schalter) {
            auswahlfeld();
            int auswahl = scanner.nextInt();
            scanner.nextLine();

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

        double price1 = 10;
        double price2 = 50;
        double price3 = 100;

        double priceIncrease1 = 1.2;
        double priceIncrease2 = 1.3;
        double priceIncrease3 = 1.4;

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
                        System.out.println("\n--- Cooky Shop ---");
                        System.out.printf("1. Normaler Cooky: +1 Multiplier (%.0f Cookys)\n", price1);
                        System.out.printf("2. Goldener Cooky: +2 Multiplier (%.0f Cookys)\n", price2);
                        System.out.printf("3. Regenbogen Cooky: +3 Multiplier (%.0f Cookys)\n", price3);
                        System.out.println("4. Zurück zum Spiel");
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
                                if (cookycount >= price1) {
                                    cookymultiplyer += 1;
                                    cookycount -= price1;
                                    price1 *= priceIncrease1;
                                    System.out.println("Du hast einen normalen Cooky gekauft!");
                                } else {
                                    System.out.println("Nicht genug Cookys!");
                                }
                            }
                            case 2 -> {
                                if (cookycount >= price2) {
                                    cookymultiplyer += 2;
                                    cookycount -= price2;
                                    price2 *= priceIncrease2;
                                    System.out.println("Du hast einen goldenen Cooky gekauft!");
                                } else {
                                    System.out.println("Nicht genug Cookys!");
                                }
                            }
                            case 3 -> {
                                if (cookycount >= price3) {
                                    cookymultiplyer += 3;
                                    cookycount -= price3;
                                    price3 *= priceIncrease3;
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
}
