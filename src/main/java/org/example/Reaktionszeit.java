package org.example;

import java.util.Scanner;

public class Reaktionszeit extends MiniGame {
    public Reaktionszeit() {
        super("Reaktionszeit-Test");
    }

    @Override
    public void start(Scanner scanner) {
        printTitle();
        System.out.println("Drücke Enter sobald die Ampel GRÜN ist!");
        System.out.println("WICHTIG: Nicht zu früh drücken!");
        System.out.println("Enter drücken um zu starten...");
        scanner.nextLine();

        if (!rotPhase()) {
            scanner.nextLine();
            System.out.println("\n❌ ZU FRÜH! Du hast gedrückt während es noch ROT war!");
            return;
        }

        if (!gelbPhase()) {
            scanner.nextLine();
            System.out.println("\n❌ ZU FRÜH! Du hast gedrückt während es noch GELB war!");
            return;
        }

        gruenPhase(scanner);
    }

    private boolean rotPhase() {
        System.out.println("\n\n\n\n\n\033[31m[O]\033[0m  <- Rot\n[ ]  <- Gelb\n[ ]  <- Grün");
        long start = System.currentTimeMillis();
        long warteZeit = (long) (Math.random() * 2000) + 1000;

        while (System.currentTimeMillis() - start < warteZeit) {
            try {
                if (System.in.available() > 0) {
                    return false;
                }
            } catch (Exception e) {}
            try { Thread.sleep(10); } catch (Exception e) {}
        }
        return true;
    }

    private boolean gelbPhase() {
        System.out.println("\n\n\n\n\n[ ]  <- Rot\n\033[33m[O]\033[0m  <- Gelb\n[ ]  <- Grün");
        long gelbStart = System.currentTimeMillis();
        long gelbZeit = 1000;

        while (System.currentTimeMillis() - gelbStart < gelbZeit) {
            try {
                if (System.in.available() > 0) {
                    return false;
                }
            } catch (Exception e) {}
            try { Thread.sleep(10); } catch (Exception e) {}
        }
        return true;
    }

    private void gruenPhase(Scanner scanner) {
        try {
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {}

        System.out.println("\n\n\n\n\n[ ]  <- Rot\n[ ]  <- Gelb\n\033[32m[O]\033[0m  <- Grün");
        System.out.println("Grün! Jetzt drücken!");
        long gruenZeit = System.currentTimeMillis();
        scanner.nextLine();
        long reaktionsZeit = System.currentTimeMillis() - gruenZeit;

        System.out.println("Deine Reaktionszeit: " + reaktionsZeit + " ms");
    }
}