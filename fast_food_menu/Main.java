package com.example;

public class Main {

    public static void main(String[] args) {

        FastFoodMenu fastFoodMenu = new FastFoodMenu("Burger", "French Fries", "Ketchup", "Coke", 6.35);
        fastFoodMenu.printStuff();

        fastFoodMenu.addOneMore("chips", 1.50);
        fastFoodMenu.printStuff();

        fastFoodMenu.addTwoMore("pizza", "peanuts", 6.43);
        fastFoodMenu.printStuff();

        fastFoodMenu.removeStuff("car", 666.00);
        fastFoodMenu.printStuff();

        fastFoodMenu.removeStuff("pizza", 4.00);
        fastFoodMenu.printStuff();

        // ======================================================================

        HealthyChoice healthyChoice = new HealthyChoice("pizza", "pasta", 44.12343);
        healthyChoice.printStuff();

        // will replace it with an apple for $1.00
        healthyChoice.addOneMore("unhealthy stuff", 99.23);
        healthyChoice.printStuff();

        healthyChoice.removeStuff("pasta", 2.59);
        healthyChoice.printStuff();


        // ======================================================================

        DietMenu dietMenu = new DietMenu("Hot dog", "French Fries", 15.00);
        dietMenu.printStuff();

        dietMenu.addTwoMore("burger", "double burger", 500);
        dietMenu.printStuff();

    }

}
