package com.example;

/*
    menu can't go over 1000 calories per person
 */

public class DietMenu extends FastFoodMenu {

    public DietMenu(String firstChoice, String secondChoice, double totalPrice) {
        super(firstChoice, secondChoice, "Dust lol", "Water", totalPrice);

    }

    @Override
    public void addOneMore(String additional1, double price) {
        System.out.println("Can't be added to the menu");
    }

    @Override
    public void addTwoMore(String additional1, String additional2, double price) {
        System.out.println("Can't be added to the menu");
    }
}
