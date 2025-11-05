package org.example;

import java.util.Scanner;

public class Klicktest extends MiniGame {
    public Klicktest() {
        super("Klicktest");
    }

    @Override
    public void start(Scanner scanner) {
        printTitle();

        int highscore = Speicherung.getHighscore("klicktest");
        if (highscore > 0) {
            System.out.println("🏆 Aktueller Highscore: " + highscore + " Klicks");
        }

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

        if (klicks > highscore) {
            System.out.println("🎉 NEUER HIGHSCORE! 🎉");
            Speicherung.speichereHighscore("klicktest", klicks);
        } else if (highscore > 0) {
            int differenz = highscore - klicks;
            System.out.println("Noch " + differenz + " Klicks bis zum Highscore!");
        }
    }
}