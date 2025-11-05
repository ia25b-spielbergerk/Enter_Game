package org.example;

import java.util.Map;
import java.util.Scanner;

public class CookyClicker extends MiniGame {
    private double cookycount;
    private double cookymultiplyer;
    private double rebirthMultiplier;
    private int rebirthCount;
    private double rebirthCost;

    private Cookie[] cookies;
    private double[] basePrices;

    public CookyClicker() {
        super("Cooky Clicker");
        cookycount = 0;
        cookymultiplyer = 1;
        rebirthMultiplier = 1.0;
        rebirthCount = 0;
        rebirthCost = 10000000000L;

        initializeCookies();
        loadGame();
    }

    private void initializeCookies() {
        basePrices = new double[]{10, 50, 150, 500, 2000, 10000, 100000, 1200000,
                5000000, 25000000, 100000000, 500000000, 2500000000L,
                10000000000L, 50000000000L, 67};

        cookies = new Cookie[]{
                new Cookie("Normaler Cooky", basePrices[0], 1.01, 1),
                new Cookie("Goldener Cooky", basePrices[1], 1.015, 2),
                new Cookie("Regenbogen Cooky", basePrices[2], 1.02, 5),
                new Cookie("Mega Cooky", basePrices[3], 1.025, 10),
                new Cookie("Legendärer Cooky", basePrices[4], 1.03, 25),
                new Cookie("Mythischer Cooky", basePrices[5], 1.035, 100),
                new Cookie("Exothischer Cooky", basePrices[6], 1.04, 500),
                new Cookie("Godly Cooky", basePrices[7], 1.045, 1200),
                new Cookie("Cosmic Cooky", basePrices[8], 1.05, 3000),
                new Cookie("Divine Cooky", basePrices[9], 1.055, 10000),
                new Cookie("Celestial Cooky", basePrices[10], 1.06, 30000),
                new Cookie("Transcendent Cooky", basePrices[11], 1.065, 100000),
                new Cookie("Omnipotent Cooky", basePrices[12], 1.07, 500000),
                new Cookie("Eternal Cooky", basePrices[13], 1.075, 2000000),
                new Cookie("Infinite Cooky", basePrices[14], 1.08, 10000000),
                new Cookie("67 Cooky", basePrices[15], 67, 999999999)
        };
    }

    private void loadGame() {
        Map<String, String> data = Speicherung.laden();
        if (data.isEmpty()) return;

        try {
            if (data.containsKey("cookycount")) {
                cookycount = Double.parseDouble(data.get("cookycount"));
            }
            if (data.containsKey("cookymultiplyer")) {
                cookymultiplyer = Double.parseDouble(data.get("cookymultiplyer"));
            }
            if (data.containsKey("rebirthMultiplier")) {
                rebirthMultiplier = Double.parseDouble(data.get("rebirthMultiplier"));
            }
            if (data.containsKey("rebirthCount")) {
                rebirthCount = Integer.parseInt(data.get("rebirthCount"));
            }
            if (data.containsKey("rebirthCost")) {
                rebirthCost = Double.parseDouble(data.get("rebirthCost"));
            }

            for (int i = 0; i < cookies.length; i++) {
                String key = "cookie" + i + "Price";
                if (data.containsKey(key)) {
                    double price = Double.parseDouble(data.get(key));
                    cookies[i].setPrice(price);
                }
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Laden der Daten: " + e.getMessage());
        }
    }

    @Override
    public void start(Scanner scanner) {
        boolean running = true;

        while (running) {
            printTitle();
            displayStats();
            System.out.println("Drücke [Enter] um Cookys aktiv zu bekommen");
            System.out.println("1 = Shop, 2 = Rebirth, 3 = Spielstand, 4 = Speichern, 5 = Reset, Exit = Zurück");
            String input = scanner.nextLine();

            switch (input) {
                case "" -> cookycount += cookymultiplyer * rebirthMultiplier;
                case "1" -> openShop(scanner);
                case "2" -> handleRebirth(scanner);
                case "3" -> showSpielstand(scanner);
                case "4" -> Speicherung.speichern(this);
                case "5" -> handleReset(scanner);
                case "Exit" -> {
                    Speicherung.speichern(this);
                    running = false;
                }
                default -> System.out.println("Ungültige Eingabe!");
            }
        }
    }

    private void displayStats() {
        System.out.printf("Cookys: %s\n", NumberFormatter.format(cookycount));
        System.out.printf("Aktueller Multiplier: %s | Rebirth Multiplier: %.2fx | Rebirths: %d\n",
                NumberFormatter.format(cookymultiplyer), rebirthMultiplier, rebirthCount);
    }

    private void showSpielstand(Scanner scanner) {
        boolean inStats = true;

        while (inStats) {
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.println("║                    SPIELSTAND ÜBERSICHT                    ║");
            System.out.println("╚════════════════════════════════════════════════════════════╝");

            System.out.println("\n--- Allgemeine Statistiken ---");
            System.out.printf("Cookys gesamt: %s\n", NumberFormatter.format(cookycount));
            System.out.printf("Klick-Multiplier: %s\n", NumberFormatter.format(cookymultiplyer));
            System.out.printf("Cookys pro Klick (mit Rebirth): %s\n", NumberFormatter.format(cookymultiplyer * rebirthMultiplier));

            System.out.println("\n--- Rebirth Statistiken ---");
            System.out.printf("Rebirths durchgeführt: %d\n", rebirthCount);
            System.out.printf("Rebirth Multiplier: %.2fx\n", rebirthMultiplier);
            System.out.printf("Kosten für nächsten Rebirth: %s\n", NumberFormatter.format(rebirthCost));
            if (cookycount >= rebirthCost) {
                System.out.println("Status: ✅ Rebirth verfügbar!");
            } else {
                System.out.printf("Noch benötigt: %s\n", NumberFormatter.format(rebirthCost - cookycount));
            }

            System.out.println("\n--- Cookie Preise ---");
            for (int i = 0; i < cookies.length - 1; i++) {
                Cookie c = cookies[i];
                System.out.printf("%s: %s (+%d Mult.)\n",
                        c.getName(),
                        NumberFormatter.format(c.getPrice()),
                        c.getMultiplierBonus());
            }

            System.out.println("\n--- Fortschritt ---");
            double totalMultiplierBought = (cookymultiplyer - 1);
            System.out.printf("Gekaufte Multiplier: %s\n", NumberFormatter.format(totalMultiplierBought));
            System.out.printf("Durchschnittlicher Cookie-Preis: %s\n", NumberFormatter.format(calculateAveragePrice()));

            double rebirthProgress = (cookycount / rebirthCost) * 100;
            if (rebirthProgress > 100) rebirthProgress = 100;
            System.out.printf("Fortschritt zum nächsten Rebirth: %.2f%%\n", rebirthProgress);
            displayProgressBar(rebirthProgress);

            System.out.println("\n--- Geheimnisse ---");
            if (cookies[15].getPrice() > 67) {
                System.out.println("🎉 Secret Cookie gefunden: 67 Cooky!");
            } else {
                System.out.println("🔒 Secret Cookie: ??? (Tipp: Probiere 67 im Shop)");
            }

            System.out.println("\n[Enter] = Zurück zum Spiel");
            scanner.nextLine();
            inStats = false;
        }
    }

    private void displayProgressBar(double percentage) {
        int barLength = 40;
        int filled = (int) ((percentage / 100.0) * barLength);

        System.out.print("[");
        for (int i = 0; i < barLength; i++) {
            if (i < filled) {
                System.out.print("█");
            } else {
                System.out.print("░");
            }
        }
        System.out.println("]");
    }

    private double calculateAveragePrice() {
        double total = 0;
        int count = 0;
        for (int i = 0; i < cookies.length - 1; i++) {
            total += cookies[i].getPrice();
            count++;
        }
        return count > 0 ? total / count : 0;
    }

    private void openShop(Scanner scanner) {
        boolean shopOpen = true;

        while (shopOpen) {
            System.out.println("\n--- Shop ---");
            for (int i = 0; i < cookies.length - 1; i++) {
                Cookie c = cookies[i];
                System.out.printf("%d %s: +%d Multiplier (%s)\n",
                        i + 1, c.getName(), c.getMultiplierBonus(),
                        NumberFormatter.format(c.getPrice()));
            }
            System.out.println("0 Zurück");
            System.out.printf("Aktueller Multiplier: %s | Cookys: %s\n",
                    NumberFormatter.format(cookymultiplyer), NumberFormatter.format(cookycount));
            System.out.print("Wähle: ");

            int choice;
            while (!scanner.hasNextInt()) {
                System.out.println("Bitte Zahl eingeben!");
                scanner.nextLine();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                shopOpen = false;
            } else if (choice == 67) {
                buyCookie(scanner, cookies.length - 1);
            } else if (choice >= 1 && choice <= cookies.length - 1) {
                buyCookie(scanner, choice - 1);
            } else {
                System.out.println("Ungültig");
            }
        }
    }

    private void buyCookie(Scanner scanner, int index) {
        Cookie cookie = cookies[index];

        if (index == cookies.length - 1) {
            System.out.printf("67 Cooky: + %d Multiplier (%s)\n",
                    cookie.getMultiplierBonus(), NumberFormatter.format(cookie.getPrice()));
        }

        System.out.printf("Wie viele %s? (oder max): ", cookie.getName());
        String input = scanner.nextLine();

        int amount;
        if (input.equalsIgnoreCase("max")) {
            amount = cookie.calculateMaxAffordable(cookycount);
        } else {
            try {
                amount = Integer.parseInt(input);
            } catch (Exception e) {
                amount = 0;
            }
        }

        double cost = cookie.calculateCost(amount);
        if (amount > 0 && cookycount >= cost) {
            cookycount -= cost;
            cookymultiplyer += cookie.getMultiplierBonus() * amount;
            cookie.buy(amount);
            System.out.printf("Gekauft: %d %s (-%s)\n", amount, cookie.getName(), NumberFormatter.format(cost));
        } else {
            System.out.println("Nicht genügend Cookys oder ungültig");
        }
    }

    private void handleRebirth(Scanner scanner) {
        System.out.println("\n=== REBIRTH ===");
        System.out.printf("Kosten: %s Cookys\n", NumberFormatter.format(rebirthCost));
        System.out.printf("Du hast: %s Cookys\n", NumberFormatter.format(cookycount));
        System.out.printf("Aktueller Rebirth Multiplier: %.2fx\n", rebirthMultiplier);
        System.out.printf("Neuer Rebirth Multiplier: %.2fx\n", rebirthMultiplier + 0.5);
        System.out.println("\nWARNUNG: Rebirth setzt alle Cookys und Multiplier zurück!");
        System.out.println("Du behältst nur den erhöhten Rebirth Multiplier.");

        if (cookycount >= rebirthCost) {
            System.out.print("\nRebirth durchführen? (y/n): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                performRebirth();
            } else {
                System.out.println("Rebirth abgebrochen.");
            }
        } else {
            System.out.printf("\nNicht genügend Cookys! Benötigt: %s\n",
                    NumberFormatter.format(rebirthCost - cookycount));
        }
    }

    private void performRebirth() {
        cookycount = 0;
        cookymultiplyer = 1;
        rebirthMultiplier += 0.5;
        rebirthCount++;
        rebirthCost *= 10;

        for (int i = 0; i < cookies.length; i++) {
            cookies[i].reset(basePrices[i]);
        }

        System.out.println("\n✨ REBIRTH ERFOLGREICH! ✨");
        System.out.printf("Neuer Rebirth Multiplier: %.2fx\n", rebirthMultiplier);
        System.out.printf("Rebirths gesamt: %d\n", rebirthCount);
    }

    private void handleReset(Scanner scanner) {
        System.out.println("\n=== RESET WARNUNG ===");
        System.out.println("Dies löscht deinen GESAMTEN Spielstand!");
        System.out.println("Alle Cookys, Multiplier und Rebirths gehen verloren!");
        System.out.print("Wirklich zurücksetzen? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            cookycount = 0;
            cookymultiplyer = 1;
            rebirthMultiplier = 1.0;
            rebirthCount = 0;
            rebirthCost = 10000000000L;

            for (int i = 0; i < cookies.length; i++) {
                cookies[i].reset(basePrices[i]);
            }

            Speicherung.loeschen();
            System.out.println("\n❌ SPIEL ZURÜCKGESETZT! ❌");
        } else {
            System.out.println("Reset abgebrochen.");
        }
    }

    public double getCookycount() {
        return cookycount;
    }

    public double getCookymultiplyer() {
        return cookymultiplyer;
    }

    public double getRebirthMultiplier() {
        return rebirthMultiplier;
    }

    public int getRebirthCount() {
        return rebirthCount;
    }

    public double getRebirthCost() {
        return rebirthCost;
    }

    public Cookie[] getCookies() {
        return cookies;
    }
}