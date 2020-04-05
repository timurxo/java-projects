package com.example;


// fast food restaurant menu

import java.util.ArrayList;

public class FastFoodMenu {

    private String firstChoice;
    private String secondChoice;
    private String thirdChoice;

    private String drinks;

    private String additional1;
    private String additional2;

    private double totalPrice;

    private ArrayList<String> menu = new ArrayList<>();

    public FastFoodMenu(String firstChoice, String secondChoice, String thirdChoice, String drinks, double totalPrice) {
        this.menu.add(firstChoice);
        this.menu.add(secondChoice);
        this.menu.add(thirdChoice);
        this.menu.add(drinks);
        this.totalPrice = totalPrice;
    }

    public void addOneMore(String additional1, double price) {
        this.menu.add(additional1);
        this.totalPrice += price;
    }

    public void addTwoMore(String additional1, String additional2, double price) {
        this.menu.add(additional1);
        this.menu.add(additional2);
        this.totalPrice += price;
    }

    public boolean removeStuff(String choice, double price) {
        // if it's not there
        if(!menu.contains(choice)) {
            System.out.println("You don't have it in your list of ordered items!");
            return false;
        }
        // remove it
        menu.remove(choice);
        totalPrice -= price;

        return true;
    }

    public String getFirstChoice() {
        return firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public String getThirdChoice() {
        return thirdChoice;
    }

    public String getDrinks() {
        return drinks;
    }

    public String getAdditional1() {
        return additional1;
    }

    public String getAdditional2() {
        return additional2;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void printStuff() {
        System.out.println("You have ordered: ");

        for(String choice : menu) {
            System.out.print(choice + ", ");
        }

        System.out.println("Total price: $" + String.format("%.2f", getTotalPrice()) );
        System.out.println();
    }
}
