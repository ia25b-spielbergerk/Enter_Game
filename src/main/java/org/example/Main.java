package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static class Building {
        String name;
        double price;
        double priceIncrease;
        int count;
        double baseCps;
        Building(String name, double price, double priceIncrease, double baseCps) {
            this.name = name;
            this.price = price;
            this.priceIncrease = priceIncrease;
            this.baseCps = baseCps;
            this.count = 0;
        }
        double buyCost(int n) {
            if (n <= 0) return 0;
            return price * ((Math.pow(priceIncrease, n) - 1) / (priceIncrease - 1));
        }
        int maxAffordable(double money) {
            if (money < price) return 0;
            int low = 0;
            int high = 1;
            while (buyCost(high) <= money) high *= 2;
            while (low + 1 < high) {
                int mid = (low + high) / 2;
                if (buyCost(mid) <= money) low = mid; else high = mid;
            }
            return low;
        }
        void applyBuys(int n) {
            if (n <= 0) return;
            price *= Math.pow(priceIncrease, n);
            count += n;
        }
        double totalCps(double cursorMultiplier, double upgradeCursorMultiplier) {
            double mult = 1.0;
            if (name.equalsIgnoreCase("Cursor")) mult = cursorMultiplier * upgradeCursorMultiplier;
            return count * baseCps * mult;
        }
    }

    static class Upgrade {
        int id;
        String name;
        double price;
        int reqCursors;
        boolean purchased;
        Upgrade(int id, String name, double price, int reqCursors) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.reqCursors = reqCursors;
            this.purchased = false;
        }
    }

    public static void main(String[] args) {
        boolean schalter = true;
        Scanner scanner = new Scanner(System.in);

        while (schalter) {
            auswahlfeld();
            int auswahl;
            while (!scanner.hasNextInt()) {
                System.out.println("Bitte Zahl eingeben!");
                scanner.nextLine();
            }
            auswahl = scanner.nextInt();
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

        Building cursor = new Building("Cursor", 100, 1.15, 0.1);
        Building grandma = new Building("Grandma", 1000, 1.15, 1.0);

        List<Upgrade> upgrades = new ArrayList<>();
        upgrades.add(new Upgrade(0, "Reinforced index finger", 100, 1));
        upgrades.add(new Upgrade(1, "Carpal tunnel prevention cream", 500, 1));
        upgrades.add(new Upgrade(2, "Berrylium cursor", 500, 1));
        upgrades.add(new Upgrade(3, "Ambidextrous", 10000, 10));
        upgrades.add(new Upgrade(4, "Thousand fingers (Chalcedhoney)", 100000, 25));
        upgrades.add(new Upgrade(5, "Million fingers (Buttergold)", 10_000_000, 50));
        upgrades.add(new Upgrade(6, "Billion fingers (Sugarmuck)", 100_000_000, 100));
        upgrades.add(new Upgrade(7, "Trillion fingers (Jetmint)", 1_000_000_000L, 150));
        upgrades.add(new Upgrade(8, "Quadrillion fingers (Cherrysilver)", 10_000_000_000L, 200));
        upgrades.add(new Upgrade(9, "Quintillion fingers (Hazelrald)", 10_000_000_000_000L, 250));
        upgrades.add(new Upgrade(10, "Sextillion fingers (Mooncandy)", 10_000_000_000_000_000L, 300));
        upgrades.add(new Upgrade(11, "Septillion fingers (Astrofudge)", 1e20, 350));
        upgrades.add(new Upgrade(12, "Octillion fingers (Alabascream)", 1e22, 400));
        upgrades.add(new Upgrade(13, "Nonillion fingers (Iridyum)", 1e25, 450));
        upgrades.add(new Upgrade(14, "Decillion fingers (Glucosmium)", 1e28, 500));
        upgrades.add(new Upgrade(15, "Undecillion fingers (Glimmeringue)", 1e31, 550));

        double upgradeCursorMultiplier = 1.0;
        boolean thousandFingersBought = false;

        double storedPassive = 0.0;
        long lastTime = System.nanoTime();

        while (schalter) {
            long now = System.nanoTime();
            double elapsedSeconds = (now - lastTime) / 1_000_000_000.0;
            lastTime = now;

            double totalCps = cursor.totalCps(cookymultiplyer, upgradeCursorMultiplier) + grandma.totalCps(cookymultiplyer, 1.0);
            if (thousandFingersBought) {
                double nonCursorOwned = grandma.count;
                double extraPerNonCursor = 0.1;
                totalCps += cursor.count * extraPerNonCursor * nonCursorOwned;
            }
            storedPassive += totalCps * elapsedSeconds;

            System.out.println("\n=== Cooky Clicker ===");
            System.out.printf("Cookys: %.1f\n", cookycount);
            System.out.printf("Aktueller Multiplier: %.1f | CPS (laufend, nicht eingesammelt): %.3f\n", cookymultiplyer, totalCps);
            System.out.println("Drücke [Enter] um Cookys aktiv zu bekommen");
            System.out.println("1 = Shop (Items), 2 = Gesammelte Cookys einsammeln, 3 = Buildings & Upgrades, Exit = Zurück");
            String input = scanner.nextLine();

            switch (input) {
                case "" -> cookycount += cookymultiplyer;
                case "1" -> {
                    boolean shopOffen = true;
                    double priceA = 10;
                    double priceB = 50;
                    double priceC = 150;
                    double priceD = 500;
                    double priceE = 2000;
                    double incA = 1.1;
                    double incB = 1.15;
                    double incC = 1.2;
                    double incD = 1.25;
                    double incE = 1.3;
                    while (shopOffen) {
                        System.out.println("\n--- Shop ---");
                        System.out.printf("1 Normaler Cooky: +1 Multiplier (%.0f)\n", priceA);
                        System.out.printf("2 Goldener Cooky: +2 Multiplier (%.0f)\n", priceB);
                        System.out.printf("3 Regenbogen Cooky: +5 Multiplier (%.0f)\n", priceC);
                        System.out.printf("4 Mega Cooky: +10 Multiplier (%.0f)\n", priceD);
                        System.out.printf("5 Legendärer Cooky: +25 Multiplier (%.0f)\n", priceE);
                        System.out.println("6 Zurück");
                        System.out.printf("Aktueller Multiplier: %.1f | Cookys: %.1f\n", cookymultiplyer, cookycount);
                        System.out.print("Wähle: ");
                        int choice;
                        while (!scanner.hasNextInt()) {
                            System.out.println("Bitte Zahl eingeben!");
                            scanner.nextLine();
                        }
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) {
                            case 1 -> {
                                System.out.print("Wie viele normale Cookys? (oder max): ");
                                String s = scanner.nextLine();
                                int n;
                                if (s.equalsIgnoreCase("max")) {
                                    n = (int) Math.floor(Math.log(1 + (cookycount * (incA - 1) / priceA)) / Math.log(incA));
                                } else {
                                    try { n = Integer.parseInt(s); } catch (Exception e) { n = 0; }
                                }
                                double cost = priceA * ((Math.pow(incA, n) - 1) / (incA - 1));
                                if (n > 0 && cookycount >= cost) {
                                    cookycount -= cost;
                                    cookymultiplyer += n;
                                    priceA *= Math.pow(incA, n);
                                    System.out.printf("Gekauft: %d normale Cookys (-%.0f)\n", n, cost);
                                } else System.out.println("Nicht genügend Cookys oder ungültig");
                            }
                            case 2 -> {
                                System.out.print("Wie viele goldene Cookys? (oder max): ");
                                String s = scanner.nextLine();
                                int n;
                                if (s.equalsIgnoreCase("max")) {
                                    n = (int) Math.floor(Math.log(1 + (cookycount * (incB - 1) / priceB)) / Math.log(incB));
                                } else {
                                    try { n = Integer.parseInt(s); } catch (Exception e) { n = 0; }
                                }
                                double cost = priceB * ((Math.pow(incB, n) - 1) / (incB - 1));
                                if (n > 0 && cookycount >= cost) {
                                    cookycount -= cost;
                                    cookymultiplyer += 2 * n;
                                    priceB *= Math.pow(incB, n);
                                    System.out.printf("Gekauft: %d goldene Cookys (-%.0f)\n", n, cost);
                                } else System.out.println("Nicht genügend Cookys oder ungültig");
                            }
                            case 3 -> {
                                System.out.print("Wie viele Regenbogen-Cookys? (oder max): ");
                                String s = scanner.nextLine();
                                int n;
                                if (s.equalsIgnoreCase("max")) {
                                    n = (int) Math.floor(Math.log(1 + (cookycount * (incC - 1) / priceC)) / Math.log(incC));
                                } else {
                                    try { n = Integer.parseInt(s); } catch (Exception e) { n = 0; }
                                }
                                double cost = priceC * ((Math.pow(incC, n) - 1) / (incC - 1));
                                if (n > 0 && cookycount >= cost) {
                                    cookycount -= cost;
                                    cookymultiplyer += 5 * n;
                                    priceC *= Math.pow(incC, n);
                                    System.out.printf("Gekauft: %d Regenbogen-Cookys (-%.0f)\n", n, cost);
                                } else System.out.println("Nicht genügend Cookys oder ungültig");
                            }
                            case 4 -> {
                                System.out.print("Wie viele Mega-Cookys? (oder max): ");
                                String s = scanner.nextLine();
                                int n;
                                if (s.equalsIgnoreCase("max")) {
                                    n = (int) Math.floor(Math.log(1 + (cookycount * (incD - 1) / priceD)) / Math.log(incD));
                                } else {
                                    try { n = Integer.parseInt(s); } catch (Exception e) { n = 0; }
                                }
                                double cost = priceD * ((Math.pow(incD, n) - 1) / (incD - 1));
                                if (n > 0 && cookycount >= cost) {
                                    cookycount -= cost;
                                    cookymultiplyer += 10 * n;
                                    priceD *= Math.pow(incD, n);
                                    System.out.printf("Gekauft: %d Mega-Cookys (-%.0f)\n", n, cost);
                                } else System.out.println("Nicht genügend Cookys oder ungültig");
                            }
                            case 5 -> {
                                System.out.print("Wie viele Legendäre Cookys? (oder max): ");
                                String s = scanner.nextLine();
                                int n;
                                if (s.equalsIgnoreCase("max")) {
                                    n = (int) Math.floor(Math.log(1 + (cookycount * (incE - 1) / priceE)) / Math.log(incE));
                                } else {
                                    try { n = Integer.parseInt(s); } catch (Exception e) { n = 0; }
                                }
                                double cost = priceE * ((Math.pow(incE, n) - 1) / (incE - 1));
                                if (n > 0 && cookycount >= cost) {
                                    cookycount -= cost;
                                    cookymultiplyer += 25 * n;
                                    priceE *= Math.pow(incE, n);
                                    System.out.printf("Gekauft: %d Legendäre Cookys (-%.0f)\n", n, cost);
                                } else System.out.println("Nicht genügend Cookys oder ungültig");
                            }
                            case 6 -> shopOffen = false;
                            default -> System.out.println("Ungültig");
                        }
                    }
                }
                case "2" -> {
                    System.out.printf("Gesammelte Cookys: %.3f | Einsammeln? (y/n): ", storedPassive);
                    String s = scanner.nextLine();
                    if (s.equalsIgnoreCase("y")) {
                        cookycount += storedPassive;
                        System.out.printf("Eingesammelt: %.3f Cookys\n", storedPassive);
                        storedPassive = 0;
                    } else {
                        System.out.println("Nicht eingesammelt.");
                    }
                }
                case "3" -> {
                    boolean inBuildings = true;
                    while (inBuildings) {
                        System.out.println("\n--- Buildings & Upgrades ---");
                        System.out.printf("Buildings:\n1 Cursor (Preis: %.1f) owned: %d | 2 Grandma (%.1f) owned: %d\n", cursor.price, cursor.count, grandma.price, grandma.count);
                        System.out.println("Commands:");
                        System.out.println("buy <cursor/grandma> <number/max>   z.B. buy cursor 10  oder buy cursor max");
                        System.out.println("list    -> listet alle Upgrades");
                        System.out.println("buy <id> -> kauft Upgrade mit id");
                        System.out.println("back     -> zurück");
                        System.out.printf("Cookys: %.1f\n", cookycount);
                        System.out.print("Eingabe: ");
                        String line = scanner.nextLine().trim();
                        if (line.equalsIgnoreCase("back")) { inBuildings = false; continue; }
                        if (line.equalsIgnoreCase("list")) {
                            for (Upgrade u : upgrades) {
                                System.out.printf("%d) %s | Preis: %s | Req Cursors: %d | %s\n", u.id, u.name, formatBig(u.price), u.reqCursors, (u.purchased ? "gekauft" : "verfügbar"));
                            }
                            continue;
                        }
                        String[] parts = line.split("\\s+");
                        if (parts.length >= 3 && parts[0].equalsIgnoreCase("buy")) {
                            String which = parts[1];
                            int n = 1;
                            if (parts[2].equalsIgnoreCase("max")) n = -1; else {
                                try { n = Integer.parseInt(parts[2]); } catch (Exception e) { n = 0; }
                            }
                            Building target = which.equalsIgnoreCase("cursor") ? cursor : (which.equalsIgnoreCase("grandma") ? grandma : null);
                            if (target == null) { System.out.println("Unknown building"); continue; }
                            if (n == -1) {
                                int max = target.maxAffordable(cookycount);
                                if (max <= 0) { System.out.println("Nicht genug Cookys."); continue; }
                                double cost = target.buyCost(max);
                                cookycount -= cost;
                                target.applyBuys(max);
                                System.out.printf("Gekauft: %d %s (-%.0f)\n", max, target.name, cost);
                            } else {
                                if (n <= 0) { System.out.println("Ungültige Anzahl"); continue; }
                                double cost = target.buyCost(n);
                                if (cookycount >= cost) {
                                    cookycount -= cost;
                                    target.applyBuys(n);
                                    System.out.printf("Gekauft: %d %s (-%.0f)\n", n, target.name, cost);
                                } else System.out.println("Nicht genug Cookys.");
                            }
                        } else if (parts.length >= 2 && parts[0].equalsIgnoreCase("buy")) {
                            try {
                                int id = Integer.parseInt(parts[1]);
                                Upgrade found = null;
                                for (Upgrade u : upgrades) if (u.id == id) { found = u; break; }
                                if (found == null) { System.out.println("Upgrade nicht gefunden"); continue; }
                                if (found.purchased) { System.out.println("Schon gekauft"); continue; }
                                if (cursor.count < found.reqCursors) { System.out.println("Voraussetzung nicht erfüllt (zu wenig Cursors)"); continue; }
                                if (cookycount < found.price) { System.out.println("Nicht genug Cookys"); continue; }
                                cookycount -= found.price;
                                found.purchased = true;
                                if (found.id == 0 || found.id == 1 || found.id == 2 || found.id == 3) upgradeCursorMultiplier *= 2;
                                if (found.id == 4) thousandFingersBought = true;
                                if (found.id >= 5 && found.id <= 15) upgradeCursorMultiplier *= 20;
                                System.out.println("Upgrade gekauft: " + found.name);
                            } catch (Exception e) {
                                System.out.println("Ungültiger Befehl");
                            }
                        } else {
                            System.out.println("Ungültig");
                        }
                    }
                }
                case "Exit" -> schalter = false;
                default -> System.out.println("Ungültige Eingabe!");
            }
        }
    }

    static String formatBig(double v) {
        if (v >= 1e30) return String.format("%.2e", v);
        if (v >= 1e9) return String.format("%.0f", v);
        return String.format("%.0f", v);
    }
}
