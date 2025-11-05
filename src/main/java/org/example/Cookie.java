package org.example;

public class Cookie {
    private String name;
    private double price;
    private double priceIncrease;
    private int multiplierBonus;

    public Cookie(String name, double price, double priceIncrease, int multiplierBonus) {
        this.name = name;
        this.price = price;
        this.priceIncrease = priceIncrease;
        this.multiplierBonus = multiplierBonus;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceIncrease() {
        return priceIncrease;
    }

    public int getMultiplierBonus() {
        return multiplierBonus;
    }

    public int calculateMaxAffordable(double money) {
        if (money < price) return 0;
        return (int) Math.floor(Math.log(1 + (money * (priceIncrease - 1) / price)) / Math.log(priceIncrease));
    }

    public double calculateCost(int amount) {
        if (amount <= 0) return 0;
        return price * ((Math.pow(priceIncrease, amount) - 1) / (priceIncrease - 1));
    }

    public void buy(int amount) {
        price *= Math.pow(priceIncrease, amount);
    }

    public void reset(double basePrice) {
        this.price = basePrice;
    }
}