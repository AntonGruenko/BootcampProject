package com.example.bootcampproject;

public class Product {
    private String name;
    private double kcal;
    private double proteins;
    private double fats;
    private double carbs;

    public Product(String name, double kcal, double proteins, double fats, double carbs) {
        this.name = name;
        this.kcal = kcal;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }
}
