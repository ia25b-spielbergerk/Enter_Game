package org.example;

public class NumberFormatter {
    public static String format(double num) {
        if (num >= 1e21) {
            return String.format("%.2f Trilliarden", num / 1e21);
        } else if (num >= 1e18) {
            return String.format("%.2f Trillionen", num / 1e18);
        } else if (num >= 1e15) {
            return String.format("%.2f Billiarden", num / 1e15);
        } else if (num >= 1e12) {
            return String.format("%.2f Billionen", num / 1e12);
        } else if (num >= 1_000_000_000) {
            return String.format("%.2f Milliarden", num / 1_000_000_000);
        } else if (num >= 1_000_000) {
            return String.format("%.2f Millionen", num / 1_000_000);
        } else if (num >= 1_000) {
            return String.format("%.2f Tausend", num / 1_000);
        } else {
            return String.format("%.0f", num);
        }
    }
}