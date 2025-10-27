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
        System.out.println("1. Enter pro second:");
        System.out.println("2. Reaction Zeit Test:");
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
        double price3 = 150;
        double price4 = 500;
        double price5 = 2000;

        double priceIncrease1 = 1.1;
        double priceIncrease2 = 1.15;
        double priceIncrease3 = 1.2;
        double priceIncrease4 = 1.25;
        double priceIncrease5 = 1.3;

        while (schalter) {
            System.out.println("\n=== Cooky Clicker ===");
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
                        System.out.printf("3. Regenbogen Cooky: +5 Multiplier (%.0f Cookys)\n", price3);
                        System.out.printf("4. Mega Cooky: +10 Multiplier (%.0f Cookys)\n", price4);
                        System.out.printf("5. Legendärer Cooky: +25 Multiplier (%.0f Cookys)\n", price5);
                        System.out.println("6. Zurück zum Spiel");
                        System.out.printf("Aktueller Multiplier: %.1f | Cookys: %.1f\n", cookymultiplyer, cookycount);
                        System.out.print("Wähle: ");

                        int choice;
                        while (!scanner.hasNextInt()) {
                            System.out.println("Bitte gib eine Zahl ein!");
                            scanner.nextLine();
                        }
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        int shopcount;
                        double totalCost;
                        switch (choice) {
                            case 1 -> {
                                System.out.print("Wie viele normale Cookys willst du kaufen? (oder max): ");
                                String s = scanner.nextLine();
                                if (s.equalsIgnoreCase("max")) {
                                    shopcount = (int) Math.floor(Math.log(1 + (cookycount * (priceIncrease1 - 1) / price1)) / Math.log(priceIncrease1));
                                } else shopcount = Integer.parseInt(s);
                                totalCost = price1 * ((Math.pow(priceIncrease1, shopcount) - 1) / (priceIncrease1 - 1));
                                if (cookycount >= totalCost) {
                                    cookycount -= totalCost;
                                    cookymultiplyer += shopcount;
                                    price1 *= Math.pow(priceIncrease1, shopcount);
                                    System.out.printf("Du hast %d normale Cookys gekauft! (-%.0f Cookys)\n", shopcount, totalCost);
                                } else System.out.println("Nicht genügend Cookys!");
                            }
                            case 2 -> {
                                System.out.print("Wie viele goldene Cookys willst du kaufen? (oder max): ");
                                String s = scanner.nextLine();
                                if (s.equalsIgnoreCase("max")) {
                                    shopcount = (int) Math.floor(Math.log(1 + (cookycount * (priceIncrease2 - 1) / price2)) / Math.log(priceIncrease2));
                                } else shopcount = Integer.parseInt(s);
                                totalCost = price2 * ((Math.pow(priceIncrease2, shopcount) - 1) / (priceIncrease2 - 1));
                                if (cookycount >= totalCost) {
                                    cookycount -= totalCost;
                                    cookymultiplyer += 2 * shopcount;
                                    price2 *= Math.pow(priceIncrease2, shopcount);
                                    System.out.printf("Du hast %d goldene Cookys gekauft! (-%.0f Cookys)\n", shopcount, totalCost);
                                } else System.out.println("Nicht genügend Cookys!");
                            }
                            case 3 -> {
                                System.out.print("Wie viele Regenbogen-Cookys willst du kaufen? (oder max): ");
                                String s = scanner.nextLine();
                                if (s.equalsIgnoreCase("max")) {
                                    shopcount = (int) Math.floor(Math.log(1 + (cookycount * (priceIncrease3 - 1) / price3)) / Math.log(priceIncrease3));
                                } else shopcount = Integer.parseInt(s);
                                totalCost = price3 * ((Math.pow(priceIncrease3, shopcount) - 1) / (priceIncrease3 - 1));
                                if (cookycount >= totalCost) {
                                    cookycount -= totalCost;
                                    cookymultiplyer += 5 * shopcount;
                                    price3 *= Math.pow(priceIncrease3, shopcount);
                                    System.out.printf("Du hast %d Regenbogen-Cookys gekauft! (-%.0f Cookys)\n", shopcount, totalCost);
                                } else System.out.println("Nicht genügend Cookys!");
                            }
                            case 4 -> {
                                System.out.print("Wie viele Mega-Cookys willst du kaufen? (oder max): ");
                                String s = scanner.nextLine();
                                if (s.equalsIgnoreCase("max")) {
                                    shopcount = (int) Math.floor(Math.log(1 + (cookycount * (priceIncrease4 - 1) / price4)) / Math.log(priceIncrease4));
                                } else shopcount = Integer.parseInt(s);
                                totalCost = price4 * ((Math.pow(priceIncrease4, shopcount) - 1) / (priceIncrease4 - 1));
                                if (cookycount >= totalCost) {
                                    cookycount -= totalCost;
                                    cookymultiplyer += 10 * shopcount;
                                    price4 *= Math.pow(priceIncrease4, shopcount);
                                    System.out.printf("Du hast %d Mega-Cookys gekauft! (-%.0f Cookys)\n", shopcount, totalCost);
                                } else System.out.println("Nicht genügend Cookys!");
                            }
                            case 5 -> {
                                System.out.print("Wie viele legendäre Cookys willst du kaufen? (oder max): ");
                                String s = scanner.nextLine();
                                if (s.equalsIgnoreCase("max")) {
                                    shopcount = (int) Math.floor(Math.log(1 + (cookycount * (priceIncrease5 - 1) / price5)) / Math.log(priceIncrease5));
                                } else shopcount = Integer.parseInt(s);
                                totalCost = price5 * ((Math.pow(priceIncrease5, shopcount) - 1) / (priceIncrease5 - 1));
                                if (cookycount >= totalCost) {
                                    cookycount -= totalCost;
                                    cookymultiplyer += 25 * shopcount;
                                    price5 *= Math.pow(priceIncrease5, shopcount);
                                    System.out.printf("Du hast %d legendäre Cookys gekauft! (-%.0f Cookys)\n", shopcount, totalCost);
                                } else System.out.println("Nicht genügend Cookys!");
                            }
                            case 6 -> shopOffen = false;
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