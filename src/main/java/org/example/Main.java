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

    public static void klicktest() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Klicktest ===");
        System.out.println("Drücke so oft du kannst die [Enter]-Taste!");
        System.out.println("Sobald du Enter drückst, startet der 10-Sekunden-Test.");

        scanner.nextLine();

        long startZeit = System.currentTimeMillis();
        int klicks = 0;

        while (System.currentTimeMillis() - startZeit < 10000) {
            scanner.nextLine();
            klicks++;
        }

        System.out.println("Zeit ist vorbei!");
        System.out.println("Du hast " + klicks + " Klicks in 10 Sekunden geschafft!");
    }

    public static void reaktionszeit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Reaktionszeit-Test ===");
        System.out.println("Drücke Enter sobald die Ampel GRÜN ist!");
        System.out.println("Enter drücken um zu starten...");
        scanner.nextLine();

        System.out.println("\n\n\n\n\n\033[31m[O]\033[0m  <- Rot\n[ ]  <- Gelb\n[ ]  <- Grün");
        long start = System.currentTimeMillis();
        long warteZeit = (long) (Math.random() * 2000) + 1000;
        while (System.currentTimeMillis() - start < warteZeit) {

        }

        System.out.println("\n\n\n\n\n[ ]  <- Rot\n\033[33m[O]\033[0m  <- Gelb\n[ ]  <- Grün");
        long gelbStart = System.currentTimeMillis();
        long gelbZeit = 1000;
        while (System.currentTimeMillis() - gelbStart < gelbZeit) {

        }

        System.out.println("\n\n\n\n\n[ ]  <- Rot\n[ ]  <- Gelb\n\033[32m[O]\033[0m  <- Grün");
        System.out.println("Grün! Jetzt drücken!");
        long gruenZeit = System.currentTimeMillis();
        scanner.nextLine();
        long reaktionsZeit = System.currentTimeMillis() - gruenZeit;

        System.out.println("Deine Reaktionszeit: " + reaktionsZeit + " ms");
    }

    public static void cookyclicker() {
        Scanner scanner = new Scanner(System.in);
        boolean schalter = true;

        double cookycount = 0;
        double cookymultiplyer = 1;

        double price1 = 10;
        double price2 = 50;
        double price3 = 100;

        double priceIncrease1 = 1.1;
        double priceIncrease2 = 1.2;
        double priceIncrease3 = 1.3;

        while (schalter) {
            System.out.println("\n--- Cooky Clicker ---");
            System.out.println("Drücke [Enter] um Cookys zu bekommen");
            System.out.printf("Multiplier: %.1f\n", cookymultiplyer);
            System.out.printf("Cookys: %.1f\n", cookycount);
            System.out.println("Tippe [1] für Shop oder [Exit] um zurückzugehen");

            String input = scanner.nextLine();

            switch (input) {
                case "" -> cookycount += cookymultiplyer;

                case "1" -> {
                    boolean shopOffen = true;
                    while (shopOffen) {
                        System.out.println("\n--- Cooky Shop ---");
                        System.out.printf("1. Normaler Cooky: +1 Multiplier (%.0f Cookys)\n", price1);
                        System.out.printf("2. Goldener Cooky: +2 Multiplier (%.0f Cookys)\n", price2);
                        System.out.printf("3. Regenbogen Cooky: +3 Multiplier (%.0f Cookys)\n", price3);
                        System.out.println("4. Zurück zum Spiel");
                        System.out.printf("Aktueller Multiplier: %.1f | Cookys: %.1f\n", cookymultiplyer, cookycount);
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
                                System.out.print("Wie viele normale Cookys willst du kaufen? ");
                                int shopcount = scanner.nextInt();
                                scanner.nextLine();

                                double totalCost = price1 * ((Math.pow(priceIncrease1, shopcount) - 1) / (priceIncrease1 - 1));
                                if (cookycount >= totalCost) {
                                    cookycount -= totalCost;
                                    cookymultiplyer += shopcount;
                                    price1 *= Math.pow(priceIncrease1, shopcount);
                                    System.out.printf("Du hast %d normale Cookys gekauft! (-%.0f Cookys)\n", shopcount, totalCost);
                                } else {
                                    System.out.println("Nicht genügend Cookys für diesen Kauf!");
                                }
                            }
                            case 2 -> {
                                System.out.print("Wie viele goldene Cookys willst du kaufen? ");
                                int shopcount = scanner.nextInt();
                                scanner.nextLine();

                                double totalCost = price2 * ((Math.pow(priceIncrease2, shopcount) - 1) / (priceIncrease2 - 1));
                                if (cookycount >= totalCost) {
                                    cookycount -= totalCost;
                                    cookymultiplyer += 2 * shopcount;
                                    price2 *= Math.pow(priceIncrease2, shopcount);
                                    System.out.printf("Du hast %d goldene Cookys gekauft! (-%.0f Cookys)\n", shopcount, totalCost);
                                } else {
                                    System.out.println("Nicht genügend Cookys für diesen Kauf!");
                                }
                            }
                            case 3 -> {
                                System.out.print("Wie viele Regenbogen-Cookys willst du kaufen? ");
                                int shopcount = scanner.nextInt();
                                scanner.nextLine();

                                double totalCost = price3 * ((Math.pow(priceIncrease3, shopcount) - 1) / (priceIncrease3 - 1));
                                if (cookycount >= totalCost) {
                                    cookycount -= totalCost;
                                    cookymultiplyer += 3 * shopcount;
                                    price3 *= Math.pow(priceIncrease3, shopcount);
                                    System.out.printf("Du hast %d Regenbogen-Cookys gekauft! (-%.0f Cookys)\n", shopcount, totalCost);
                                } else {
                                    System.out.println("Nicht genügend Cookys für diesen Kauf!");
                                }
                            }
                            case 4 -> {
                                System.out.println("Zurück zum Spiel...");
                                shopOffen = false;
                            }
                            case 5 -> {
                                cookymultiplyer = cookymultiplyer + Double.POSITIVE_INFINITY;
                                cookycount = cookycount + Double.POSITIVE_INFINITY;
                                System.out.println("debug only!");
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
