package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Speicherung {
    private static final String SAVE_FILE = "Speicherung.txt";
    private static final String HIGHSCORE_FILE = "Highscores.txt";

    public static void speichern(CookyClicker game) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            writer.println("cookycount=" + game.getCookycount());
            writer.println("cookymultiplyer=" + game.getCookymultiplyer());
            writer.println("rebirthMultiplier=" + game.getRebirthMultiplier());
            writer.println("rebirthCount=" + game.getRebirthCount());
            writer.println("rebirthCost=" + game.getRebirthCost());

            Cookie[] cookies = game.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                writer.println("cookie" + i + "Price=" + cookies[i].getPrice());
            }

            System.out.println("Spielstand gespeichert!");
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    public static Map<String, String> laden() {
        Map<String, String> data = new HashMap<>();
        File file = new File(SAVE_FILE);

        if (!file.exists()) {
            System.out.println("Keine Speicherdatei gefunden. Neues Spiel wird gestartet.");
            return data;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    data.put(parts[0], parts[1]);
                }
            }
            System.out.println("Spielstand geladen!");
        } catch (IOException e) {
            System.out.println("Fehler beim Laden: " + e.getMessage());
        }

        return data;
    }

    public static void loeschen() {
        File file = new File(SAVE_FILE);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Spielstand gelöscht!");
            } else {
                System.out.println("Fehler beim Löschen des Spielstands!");
            }
        }
    }

    public static void speichereHighscore(String gameName, int score) {
        Map<String, String> highscores = ladeHighscores();
        String key = gameName;

        if (!highscores.containsKey(key) || Integer.parseInt(highscores.get(key)) < score) {
            highscores.put(key, String.valueOf(score));

            try (PrintWriter writer = new PrintWriter(new FileWriter(HIGHSCORE_FILE))) {
                for (Map.Entry<String, String> entry : highscores.entrySet()) {
                    writer.println(entry.getKey() + "=" + entry.getValue());
                }
            } catch (IOException e) {
                System.out.println("Fehler beim Speichern des Highscores: " + e.getMessage());
            }
        }
    }

    public static void speichereReaktionszeitHighscore(long milliseconds) {
        Map<String, String> highscores = ladeHighscores();
        String key = "reaktionszeit";

        if (!highscores.containsKey(key) || Long.parseLong(highscores.get(key)) > milliseconds) {
            highscores.put(key, String.valueOf(milliseconds));

            try (PrintWriter writer = new PrintWriter(new FileWriter(HIGHSCORE_FILE))) {
                for (Map.Entry<String, String> entry : highscores.entrySet()) {
                    writer.println(entry.getKey() + "=" + entry.getValue());
                }
            } catch (IOException e) {
                System.out.println("Fehler beim Speichern des Highscores: " + e.getMessage());
            }
        }
    }

    public static Map<String, String> ladeHighscores() {
        Map<String, String> highscores = new HashMap<>();
        File file = new File(HIGHSCORE_FILE);

        if (!file.exists()) {
            return highscores;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(HIGHSCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    highscores.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Laden der Highscores: " + e.getMessage());
        }

        return highscores;
    }

    public static int getHighscore(String gameName) {
        Map<String, String> highscores = ladeHighscores();
        if (highscores.containsKey(gameName)) {
            try {
                return Integer.parseInt(highscores.get(gameName));
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    public static long getReaktionszeitHighscore() {
        Map<String, String> highscores = ladeHighscores();
        if (highscores.containsKey("reaktionszeit")) {
            try {
                return Long.parseLong(highscores.get("reaktionszeit"));
            } catch (NumberFormatException e) {
                return Long.MAX_VALUE;
            }
        }
        return Long.MAX_VALUE;
    }
}